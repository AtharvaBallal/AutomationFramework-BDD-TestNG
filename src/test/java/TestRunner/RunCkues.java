package TestRunner;
import io.cucumber.junit.Cucumber;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.junit.runner.RunWith;
import org.testng.annotations.DataProvider;


@RunWith(Cucumber.class)
@CucumberOptions(

        features = "src/test/resources/Features",
        glue = {"StepDefinitions", "AppHooks"},
        plugin = {"pretty",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "timeline:src/test/resources/Reports/test-output-thread/",
                "rerun:src/test/resources/RerunLog/Rerun.txt"
        },
        tags = "@Login"
)
public class RunCkues extends AbstractTestNGCucumberTests {

        @Override
        @DataProvider(parallel = true)
        public Object [] [] scenarios(){
                return super.scenarios();
        }
}