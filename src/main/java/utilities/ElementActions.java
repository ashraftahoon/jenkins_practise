package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

public class ElementActions {
    private WebDriver driver;

    public ElementActions(WebDriver driver) {
        this.driver = driver;
    }

    // Click on an element
    public static void click(WebDriver driver, By locator, String elementName) {
        try {
            WebElement element = driver.findElement(locator);
            ExtentReport.logAction("Attempting to click on element: " + elementName);
            element.click();
            ExtentReport.logPass("Successfully clicked on element: " + elementName);
        } catch (Exception e) {
            ExtentReport.logFail("Failed to click on element: " + elementName + ". Error: " + e.getMessage());
        }
    }

    // Click on an element using Actions class
    public boolean clickElementByAction(By locator) {
        try {
            WebElement element = Helper.waitForElementClickable(driver, locator, Duration.ofSeconds(30));
            Actions actions = new Actions(driver);
            actions.moveToElement(element).click().build().perform(); // Single click instead of double click
            ExtentReport.logAction("Clicked on element using Actions: " + locator);
            return true;
        } catch (Exception e) {
            ExtentReport.logFail("Failed to click element using Actions: " + locator + ". Error: " + e.getMessage());
            return false;
        }
    }

    // Enter text into an element
    public static  void enterText(WebDriver driver , By locator, String text, String elementName) {
        try {
            WebElement element = driver.findElement(locator);
            ExtentReport.logAction("Typing text into element: " + elementName);
            element.clear();
            element.sendKeys(text);
            ExtentReport.logPass("Successfully typed text into element: " + elementName);
        } catch (Exception e) {
            ExtentReport.logFail("Failed to type text into element: " + elementName + ". Error: " + e.getMessage());
        }
    }

    // Get the text of an element
    public String getText(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            return element.getText();
        } catch (Exception e) {
            ExtentReport.logFail("Failed to get text from element: " + locator + ". Error: " + e.getMessage());
            return "";
        }
    }

    // Select a value from a dropdown
    public static void selectDropdownByValue(WebDriver driver, By locator, String value) {
        try {
            WebElement element = driver.findElement(locator);
            Select dropdown = new Select(element);
            dropdown.selectByValue(value);
            ExtentReport.logAction("Selected value '" + value + "' from dropdown: " + locator);
        } catch (Exception e) {
            ExtentReport.logFail("Failed to select value from dropdown: " + locator + ". Error: " + e.getMessage());
        }
    }
}
