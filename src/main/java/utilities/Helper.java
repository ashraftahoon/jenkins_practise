package utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class Helper {


    public static WebElement waitForElementClickable(WebDriver driver, By locator, Duration timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }


    public static String getAlertText(WebDriver driver) {
        String alertText = "";
        try {
            Alert alert = driver.switchTo().alert();
            alertText = alert.getText();
        } catch (NoAlertPresentException e) {
            System.out.println("No alert present.");
            System.out.println(e.getMessage());
        }
        return alertText;
    }

    public static void acceptAlert(WebDriver driver) {
        try {
            Alert alert = driver.switchTo().alert();
            alert.accept();
        } catch (NoAlertPresentException e) {
            System.out.println("No alert present.");
        }
    }

    public static void dismissAlert(WebDriver driver) {
        try {
            Alert alert = driver.switchTo().alert();
            alert.dismiss();
        } catch (NoAlertPresentException e) {
            System.out.println("No alert present.");
        }
    }

    // Generate a unique email using a base email and domain, appending a timestamp
    public static String generateUniqueEmail() {
        // Generate a timestamp

        String domain = "@qeema.net";
        String baseEmail = "LionelMessi";
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        // Concatenate base email, timestamp, and domain to form the unique email
        return baseEmail + timestamp + domain;
    }

}
