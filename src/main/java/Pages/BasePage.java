package Pages;

import Drivers.DriverFactory;
import Utils.LocatorReader;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage{
    WebDriver driver = DriverFactory.getDriver();



    // Validate Locator
    private void validateLocator(String locator) {
        if (locator == null || locator.trim().isEmpty()) {
            String errorMessage = "Locator cannot be null or empty";
            System.out.println(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
    }


    // Click Action
    public void click (String locator){
        validateLocator(locator);
        try {
            By element = LocatorReader.getLocator(locator);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement clickableElement = wait.until(ExpectedConditions.elementToBeClickable(element));
            clickableElement.click();
            System.out.println("Clicked element with locator: " + locator);
        } catch (Exception e) {
            System.err.println("Error clicking element with locator: " + locator + ". Error: " + e.getMessage());
            throw e;
        }
    }

    // Send Keys Action
    public void enterValue (String text, String locator){
        validateLocator(locator);
        try {
            By element = LocatorReader.getLocator(locator);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement inputElement = wait.until(ExpectedConditions.visibilityOfElementLocated(element));
            inputElement.clear();
            inputElement.sendKeys(text);
            System.out.println("Entered text in element with locator: " + locator);
        } catch (Exception e) {
            System.err.println("Error entering text in element with locator: " + locator + ". Error: " + e.getMessage());
            throw e;
        }
    }

    // Get Text Action
    public String getText (String locator){
        validateLocator(locator);
        try {
            By element = LocatorReader.getLocator(locator);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement textElement = wait.until(ExpectedConditions.visibilityOfElementLocated(element));
            String text = textElement.getText();
            System.out.println("Retrieved text from element with locator: " + locator);
            return text;
        } catch (Exception e) {
            System.err.println("Error retrieving text from element with locator: " + locator + ". Error: " + e.getMessage());
            throw e;
        }
    }

    // Select Dropdown by Visible Text
    public void selectDropdownByText (String visibleText, String locator){
        validateLocator(locator);
        try {
            By element = LocatorReader.getLocator(locator);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(element));
            new Select(dropdown).selectByVisibleText(visibleText);
            System.out.println("Selected dropdown option: " + visibleText + " for locator: " + locator);
        } catch (Exception e) {
            System.err.println("Error selecting dropdown option for locator: " + locator + ". Error: " + e.getMessage());
            throw e;
        }
    }

    // Get Attribute Value
    public String getAttribute (String attributeName, String locator){
        validateLocator(locator);
        try {
            By element = LocatorReader.getLocator(locator);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement attributeElement = wait.until(ExpectedConditions.visibilityOfElementLocated(element));
            String attributeValue = attributeElement.getAttribute(attributeName);
            System.out.println("Retrieved attribute '" + attributeName + "' from element with locator: " + locator);
            return attributeValue;
        } catch (Exception e) {
            System.err.println("Error retrieving attribute '" + attributeName + "' from locator: " + locator + ". Error: " + e.getMessage());
            throw e;
        }
    }

    // Hover Over an Element
    public void hoverOver (String locator){
        validateLocator(locator);
        try {
            By element = LocatorReader.getLocator(locator);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement hoverElement = wait.until(ExpectedConditions.visibilityOfElementLocated(element));
            Actions actions = new Actions(driver);
            actions.moveToElement(hoverElement).perform();
            System.out.println("Hovered over element with locator: " + locator);
        } catch (Exception e) {
            System.err.println("Error hovering over element with locator: " + locator + ". Error: " + e.getMessage());
            throw e;
        }
    }

    // Check if Element is Displayed
    public boolean isDisplayed (String locator){
        validateLocator(locator);
        try {
            By element = LocatorReader.getLocator(locator);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement visibleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(element));
            boolean isDisplayed = visibleElement.isDisplayed();
            System.out.println("Element with locator: " + locator + " is displayed: " + isDisplayed);
            return isDisplayed;
        } catch (Exception e) {
            System.err.println("Error checking visibility of element with locator: " + locator + ". Error: " + e.getMessage());
            return false;
        }
    }

    // Scroll to Element
    public void scrollTo (String locator){
        validateLocator(locator);
        try {
            By element = LocatorReader.getLocator(locator);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement scrollElement = wait.until(ExpectedConditions.visibilityOfElementLocated(element));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", scrollElement);
            System.out.println("Scrolled to element with locator: " + locator);
        } catch (Exception e) {
            System.err.println("Error scrolling to element with locator: " + locator + ". Error: " + e.getMessage());
            throw e;
        }
    }

    // Wait for Element to be Visible
    public void waitForVisibility (int timeoutSeconds, String locator){
        validateLocator(locator);
        try {
            By element = LocatorReader.getLocator(locator);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(element));
            System.out.println("Waited for element visibility with locator: " + locator);
        } catch (Exception e) {
            System.err.println("Error waiting for visibility of element with locator: " + locator + ". Error: " + e.getMessage());
            throw e;
        }
    }

    // Drag and Drop
    public void dragAndDrop (String sourceLocator, String targetLocator){
        validateLocator(sourceLocator);
        validateLocator(targetLocator);
        try {
            By sourceElement = LocatorReader.getLocator(sourceLocator);
            By targetElement = LocatorReader.getLocator(targetLocator);
            WebElement source = driver.findElement(sourceElement);
            WebElement target = driver.findElement(targetElement);
            Actions actions = new Actions(driver);
            actions.dragAndDrop(source, target).perform();
            System.out.println("Dragged element from locator: " + sourceLocator + " to locator: " + targetLocator);
        } catch (Exception e) {
            System.err.println("Error dragging element from locator: " + sourceLocator + " to locator: " + targetLocator + ". Error: " + e.getMessage());
            throw e;
        }
    }

}
