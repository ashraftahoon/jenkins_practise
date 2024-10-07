package tests;

import configReader.ConfigPropReader;
import factory.DriveFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;
import utilities.BrowserActions;
import utilities.ExtentReport;
import utilities.JsonDataReader;

import java.lang.reflect.Method;
import java.util.Base64;

import static org.openqa.selenium.devtools.v128.page.Page.captureScreenshot;

public class TestBase {

    public WebDriver driver;
    public static JsonDataReader getdata;
    public SoftAssert softAssert;
    public BrowserActions browserActions;

    @BeforeClass
    public void setUP() {

        // Initialize ExtentReport
        ExtentReport.initialize();
        // Initialize configuration reader
        ConfigPropReader configReaderobj = new ConfigPropReader("./src/test/resources/config.properties");
        String browser = configReaderobj.getBrowser();
        String url = configReaderobj.getUrl();
        //Initialize WebDriver using driver factory
        driver = DriveFactory.createDriver(browser);
        //object from browser action to perform actions on browser
        browserActions = new BrowserActions(driver);
        browserActions.navigateTo(url);
        browserActions.maximizeWindow();

    }


    @BeforeMethod
    public void setUpMethod(Method method) {
        // Create a test in Extent Report
        ExtentReport.startTest(method.getName());
    }

    @AfterMethod
    public void tearDownMethod(ITestResult result) {
        boolean isFailure = result.getStatus() == ITestResult.FAILURE;
        if (isFailure) {
            // Capture screenshot
            TakesScreenshot ts = (TakesScreenshot) driver;
            String base64Screenshot = ts.getScreenshotAs(OutputType.BASE64);
            // Add screenshot to the report
            ExtentReport.logScreenCaptureFromBase64String(base64Screenshot, "Screenshot on Failure");
        }
        if (result.getStatus() == ITestResult.FAILURE) {
            ExtentReport.logFail("Test failed: " + result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            ExtentReport.logPass("Test passed");
        } else if (result.getStatus() == ITestResult.SKIP) {
            ExtentReport.logWarning("Test skipped: " + result.getThrowable());
        }
        ExtentReport.finalizeReport();
    }


    @AfterClass
    public void tearDown() {
        // browserActions.closeBrowser();
    }


//    private void captureScreenshot(String methodName) {
//        try {
//            TakesScreenshot screenshot = (TakesScreenshot) driver;
//            byte[] screenshotData = screenshot.getScreenshotAs(OutputType.BYTES);
//            // Add screenshot to Extent Report
//            ExtentReport.logFail("Screenshot for failed test: " + methodName);
//            ExtentReport.getTest().addScreenCaptureFromBase64String(Base64.getEncoder().encodeToString(screenshotData));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


}
