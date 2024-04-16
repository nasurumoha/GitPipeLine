package listeners;

import java.util.Date;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.ITestNGMethod;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class CustomeTestListenerUpdated implements ITestListener {
	
    private ExtentReports extent;
    private ExtentTest suiteTest;
    private ExtentTest test;

    @Override
    public void onStart(ITestContext context) {
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("./test-output/ExtentReportsDupl/ExtentReport.html");
        htmlReporter.config().setDocumentTitle("Automation Report");
        htmlReporter.config().setReportName("Functional Testing");
//        htmlReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        suiteTest = extent.createTest(context.getSuite().getName());

        suiteTest.getModel().setName(context.getSuite().getName());
        suiteTest.getModel().setStartTime(getTime(context.getStartDate()));
        suiteTest.info("Suite started: " + context.getName());
    }
	
    @Override
    public void onTestStart(ITestResult result) {
        test = suiteTest.createNode(result.getMethod().getMethodName());
        test.getModel().setStartTime(getTime(new Date(result.getStartMillis())));
        suiteTest.info("Test started: " + result.getName());
    }
	
    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS, "Test passed");
        test.getModel().setEndTime(getTime(new Date(result.getEndMillis())));
        suiteTest.info("Test passed: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.log(Status.FAIL, "Test failed");
        test.getModel().setEndTime(getTime(new Date(result.getEndMillis())));
        suiteTest.info("Test failed: " + result.getName());
        suiteTest.info("Failure reason: " + result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.log(Status.SKIP, "Test skipped");
        test.getModel().setEndTime(getTime(new Date(result.getEndMillis())));
        suiteTest.info("All tests skipped in " + result.getName());		
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) { }
	
    @Override
    public void onFinish(ITestContext context) {
        suiteTest.getModel().setEndTime(getTime(context.getEndDate()));
        suiteTest.info("All tests finished in " + context.getName());
        suiteTest.info("Passed: " + context.getPassedTests().size());
        suiteTest.info("Failed: " + context.getFailedTests().size());
        suiteTest.info("Skipped: " + context.getSkippedTests().size());
//        suiteTest.info("Included Groups: " + context.getIncludedGroups());
//        suiteTest.info("Excluded Groups: " + context.getExcludedGroups());
        
        String includedGroups = String.join(", ", context.getIncludedGroups());
        suiteTest.info("Included Groups: " + includedGroups);
        
        String excludedGroups = String.join(", ", context.getExcludedGroups());
        suiteTest.info("Excluded Groups: " + excludedGroups);

        for (ITestNGMethod method : context.getExcludedMethods()) {
            suiteTest.info("Excluded Method: " + method.getMethodName());
        }

        extent.flush();
    }
	
    private Date getTime(Date date) {
        return date;
    }
}
