package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

// Singleton Class for WebDriver
public class WebDriverSingleton {
    private static WebDriver driver;

    private WebDriverSingleton() {
        // Private constructor to prevent instantiation
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            driver = new ChromeDriver();
        }
        return driver;
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

// Usage in Test Case

    @Test
    public void testLogin() {
        WebDriver driver = WebDriverSingleton.getDriver();
        driver.get("https://example.com/login");
        // Perform login actions
    }

    @AfterMethod
    public void tearDown() {
        WebDriverSingleton.closeDriver();
    }
}
