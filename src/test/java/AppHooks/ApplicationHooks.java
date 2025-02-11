package AppHooks;

import Drivers.DriverFactory;
import Utils.ConfigReader;
import Utils.Reporting;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class ApplicationHooks {

    private DriverFactory driverFactory;
    private WebDriver driver;
    private ConfigReader configReader;
    private static ThreadLocal<Reporting> report = new ThreadLocal<>();
    Properties prop;

    @Before(order = 0)
    public void getProperty() {
        configReader = new ConfigReader();
        prop = configReader.init_prop();
    }

    @Before(order = 1)
    public void startReport(Scenario scenario) throws IOException {

        String featureName = "";
        String scenarioName = scenario.getName();
        String env = prop.getProperty("environment");
        String url = prop.getProperty("url");

        // Extract feature file name safely
        Path featurePath = Paths.get(scenario.getUri());
        String featureFilePath = featurePath.toString();
        String featurefile = new File(featureFilePath).getName();

        // Read the first line containing the Feature name
        try (BufferedReader reader = new BufferedReader(new FileReader(featureFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Feature:")) {
                    featureName = line.replace("Feature:", "").trim();
                    break;
                }
            }
        }


        // **Each scenario gets its own Reporting instance**
        Reporting scenarioReport = new Reporting();
        scenarioReport.setValues(featurefile, featureName, scenarioName, env, url);

        // **Store in ThreadLocal**
        report.set(scenarioReport);
    }

    @Before(order = 2)
    public void launchBrowser() {
        String browserName = prop.getProperty("browser");
        driverFactory = new DriverFactory();
        driver = driverFactory.init_driver(browserName);
    }

    @Before(order = 3)
    public void getUrl() {
        String url = prop.getProperty("url");
        driver.get(url);
    }

    @After(order = 0)
    public void quitBrowser() {
        DriverFactory.quitDriver();
    }

    @After(order = 1)
    public void endReport() {
        // Ensure each scenario generates its report
        Reporting scenarioReport = report.get();
        if (scenarioReport != null) {
            scenarioReport.saveReport();
            report.remove(); // Clean up ThreadLocal after scenario
        }
    }

    @After(order = 2)
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            String screenShotName = scenario.getName().replaceAll(" ", "_");
            byte[] sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(sourcePath, "image/png", screenShotName);
        }
    }
}
