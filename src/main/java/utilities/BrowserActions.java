package utilities;

import org.openqa.selenium.WebDriver;

public class BrowserActions {
    private WebDriver driver;

    public BrowserActions(WebDriver driver) {
        this.driver = driver;
    }

    // Navigate to a specified URL
    public void navigateTo(String url) {
        driver.get(url);
    }

    // Maximize the browser window
    public void maximizeWindow() {
        driver.manage().window().maximize();
    }

    // Refresh the browser
    public void refresh() {
        driver.navigate().refresh();
    }

    // Navigate back in the browser
    public void navigateBack() {
        driver.navigate().back();
    }

    // Switch to a new browser tab
    public void switchToTab(int index) {
        driver.switchTo().window(driver.getWindowHandles().toArray()[index].toString());
    }
}
