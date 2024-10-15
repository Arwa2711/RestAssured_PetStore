import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
public class ExtendReport  implements ITestListener {
        private static ExtentReports extent;
        private static ThreadLocal<ExtentTest>
        extentTest = new ThreadLocal<>();

        @Override
        public void onStart(ITestContext context) {
            String extentReportPath = "reports/extent-report.html"; // Replace with your desired path
            ExtentSparkReporter reporter = new ExtentSparkReporter(new File(extentReportPath));


            extent = new ExtentReports();
            extent.attachReporter(reporter);
        }

        @Override
        public void onTestStart(ITestResult result) {
            ExtentTest test = extent.createTest(result.getMethod().getMethodName());
              extentTest.set(test);


            // Add additional information to the test if needed
            test.assignAuthor("Your Name");
            test.assignCategory("API Testing");
        }

        @Override
        public void onTestSuccess(ITestResult result) {
            ExtentTest test = extentTest.get();
            test.log(com.aventstack.extentreports.Status.PASS, "Test Case Passed");
        }

        @Override
        public void onTestFailure(ITestResult result) {
            ExtentTest test = extentTest.get();
            test.log(com.aventstack.extentreports.Status.FAIL, "Test Case Failed");

            // Capture screenshot or other relevant information if needed
            // For example:
            // test.addScreenCaptureFromPath("path/to/screenshot.png");
            // test.log(com.aventstack.extentreports.Status.FAIL, "Error: " + result.getThrowable().getMessage());
        }

        @Override
        public void onTestSkipped(ITestResult result) {
            ExtentTest test = extentTest.get();
            test.log(com.aventstack.extentreports.Status.SKIP, "Test Case Skipped");
        }

        @Override
        public void onFinish(ITestContext context) {
            extent.flush();
        }

        // Custom filter to add test information to the ExtentTest



}
