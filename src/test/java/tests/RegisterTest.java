package tests;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.RegisterPage;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class RegisterTest {

    WebDriver driver;

    @BeforeClass
    public void setUp(){
        driver= new ChromeDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void wait2(){
         String currentWindowId= driver.getWindowHandle();
        driver.findElement(By.xpath("//a[@href=\"https://www.linkedin.com/company/orangehrm/mycompany/\"]")).click();

        for (String windowId: driver.getWindowHandles()){
            String title= driver.switchTo().window(windowId).getTitle();

            if (title.contains("OrangeHRM | LinkedIn")){
                driver.getTitle();
                System.out.println(   driver.getTitle());
                driver.findElement(By.cssSelector("button.modal__dismiss")).click();
                break;
            }

        }

        driver.switchTo().window(currentWindowId);

    }



    @AfterMethod
    public void takingScreenShot(ITestResult result) throws IOException {

        if (ITestResult.FAILURE==result.getStatus()){
            File screen= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            File des= new File("src/test/resources/"+ result.getName()+".png");
            FileUtils.copyFile(screen,des);
        }
    }

}
