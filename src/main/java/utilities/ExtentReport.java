package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReport {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    private static String reportPath = "target/Spark.html";

    // Initialize the ExtentReports instance
    public static void initialize() {
        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
        spark.config().setReportName("Custom Test Report"); // Set the report name
        spark.config().setDocumentTitle("Test Report"); // Set the document title
        spark.config().setTheme(Theme.DARK); // Set the theme (DARK or STANDARD)
        spark.config().setTimelineEnabled(true); // Enable the timeline view
        spark.config().setEncoding("utf-8"); // Set encoding
        spark.config().setTimeStampFormat("yyyy-MM-dd HH:mm:ss"); // Set timestamp format
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    // Start a new test case
    public static void startTest(String testName) {
        ExtentTest extentTest = extent.createTest(testName);
        test.set(extentTest);
    }

    // Log actions on elements
    public static void logElementAction(String elementName, String action) {
        test.get().info("Action on element '" + elementName + "': " + action);
    }

    // Log actions
    public static void logAction(String action) {
        test.get().info("Action: " + action);
    }

    // Log a passing message
    public static void logPass(String message) {
        test.get().pass(message);
    }

    // Log a failing message with a red label
    public static void logFail(String message) {
        test.get().fail(MarkupHelper.createLabel(message, ExtentColor.RED));
    }

    // Log a warning message with a yellow label
    public static void logWarning(String message) {
        test.get().warning(MarkupHelper.createLabel(message, ExtentColor.YELLOW));
    }

    // Log an informational message with a blue label
    public static void logInfo(String message) {
        test.get().info(MarkupHelper.createLabel(message, ExtentColor.BLUE));
    }

    // Log a screenshot using Base64 string
    public static void logScreenCaptureFromBase64String(String base64String, String message) {
        try {
            test.get().addScreenCaptureFromBase64String(base64String, message);
        } catch (Exception e) {
            test.get().fail("Failed to add screenshot: " + e.getMessage());
        }
    }

    // Log a code block
    public static void logCodeBlock(String code) {
        test.get().pass(MarkupHelper.createCodeBlock(code));
    }

    // Create a child node (step)
    public static void createNode(String nodeName) {
        ExtentTest childTest = test.get().createNode(nodeName);
        test.set(childTest);
    }

    // Assign category
    public static void assignCategory(String category) {
        test.get().assignCategory(category);
    }

    // Assign author
    public static void assignAuthor(String author) {
        test.get().assignAuthor(author);
    }

    // Assign device
    public static void assignDevice(String device) {
        test.get().assignDevice(device);
    }

    // Log an exception
    public static void logException(Throwable throwable) {
        test.get().fail(throwable);
    }

    // Finalize the report
    public static void finalizeReport() {
        if (extent != null) {
            extent.flush();
        }
    }
}
