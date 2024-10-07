package factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriveFactory {

    public static WebDriver createDriver(String browser) {
        WebDriver driver=null;
        switch (browser.toLowerCase()) {
            case "chrome":
                driver=new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            // Add support for other browsers as needed
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
        return driver;
    }
}
