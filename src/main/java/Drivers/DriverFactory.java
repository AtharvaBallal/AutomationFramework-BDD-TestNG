package Drivers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class DriverFactory {

        public WebDriver driver;

        public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

        /**
         * This method is used to initialize threadlocal driver on the basis of given browser
         * @param browser
         * @return
         */
        public WebDriver init_driver(String browser){

            System.out.println("Browser value is:"+browser);

            if (browser.equals("chrome")) {
                System.setProperty("webdriver.chrome.driver", "src/test/resources/Drivers/chromedriver.exe");
                driver = new ChromeDriver();

            } else if (browser.equals("firefox")) {

                System.setProperty("webdriver.gecko.driver", "src/test/resources/Drivers/geckodriver");
                driver = new FirefoxDriver();

            } else if (browser.equals("edge")) {

                System.setProperty("webdriver.edge.driver", "src/test/resources/Drivers/geckodriver/edgedriver");
                driver = new EdgeDriver();

            } else {

                System.out.println("Please pass the correct browser value: " + browser);
            }

            tlDriver.set(driver);
            getDriver().manage().deleteAllCookies();
            getDriver().manage().window().maximize();
            return getDriver();

        }

    /**
     * This method is used to get the driver with treadlocal
     * @param
     * @return
     */
    public static synchronized WebDriver getDriver(){

        return tlDriver.get();
    }

}
