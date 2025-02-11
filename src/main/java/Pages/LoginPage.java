package Pages;

import org.openqa.selenium.WebDriver;

public class LoginPage extends PageActions {

    private final WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    public void page(){

        System.out.println( "Login Page");

    }



}
