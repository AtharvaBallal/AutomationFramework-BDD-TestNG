package StepDefinitions;

import Drivers.DriverFactory;
import Pages.RegisterUser;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;

public class RegisterUserStep {

    private final RegisterUser RegisterUser = new RegisterUser(DriverFactory.getDriver());

    @Given("User is on home page")
    public void User_is_on_home_page() {

        System.out.print("User is on home page");
    }


    @Then("Verify that home page is visible successfully")
    public void verify_that_home_page_is_visible_successfully() {
        RegisterUser.verifyPage();

    }

    @Then("Click on {string} button")
    public void click_on_signup_button(String string) {
        RegisterUser.click_signup_button(string);

    }

    @Then("Verify {string} is visible")
    public void verify_is_visible(String newUser) {
        Assert.assertTrue(RegisterUser.isDisplayed(newUser));
    }

    @Then("Enter {string} and {string} in name and emailaddress field")
    public void enter_and_in_and_field(String name, String email) {
        RegisterUser.enter_name_and_email_address(name, email);
    }

    @Then("Click Signup button")
    public void click_button() {
        RegisterUser.click_button();
    }

    @Then("Verify that {string} is visible")
    public void verify_that_is_visible(String string) {
        Assert.assertTrue(RegisterUser.isDisplayed(string));
    }

    @Then("Click Continue button")
    public void click_continue_button() {

    }

    @Then("Click Delete Account button")
    public void click_delete_account_button() {

    }

    @Then("Fill details: Title, Name, Email, Password, Date of birth")
    public void fill_details_title_name_email_password_date_of_birth() {

    }

    @Then("Select checkbox {string}")
    public void select_checkbox(String string) {

    }

    @Then("Fill details: First name, Last name, Company, Address, Address2, Country, State, City, Zipcode, Mobile Number")
    public void fill_details_first_name_last_name_company_address_address2_country_state_city_zipcode_mobile_number() {

    }

    @Then("Click {string}")
    public void click(String string) {

    }

    @Then("Verify that {string} is visible and click {string} button")
    public void verify_that_is_visible_and_click_button(String string, String string2) {

    }

}
