package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterUser extends PageActions {

    private final WebDriver driver;

    public RegisterUser(WebDriver driver){
        this.driver = driver;
    }

    public void getUrl(String url){
        driver.get(url);

    }

    public void verifyPage(){
        String pageName = driver.findElement(By.tagName("title")).getText();
        System.out.println(pageName);
    }

    public void click_signup_button(String locator){

        click(locator);
    }

    public void click_button(){

        click("Signup");
    }


    public void enter_name_and_email_address(String name, String email){
        enterValue(name, "signup-name");
        enterValue(email, "signup-email");

    }



}
