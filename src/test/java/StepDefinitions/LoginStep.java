package StepDefinitions;
import Drivers.DriverFactory;
import Pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;


public class LoginStep {

    private final LoginPage loginPage = new LoginPage(DriverFactory.getDriver());


    @Given("user is on the login page")
    public void user_is_on_the_login_page() {
        DriverFactory.getDriver().get("htts://www.google.co.in/");

    }

    @Then("user enter ID and Password")
    public void user_enter_id_and_password() {

        loginPage.page();
    }

    @Then("user click on the login button")
    public void user_click_on_the_login_button() {

        loginPage.page();
    }
}
