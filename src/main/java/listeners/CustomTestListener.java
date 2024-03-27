package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class CustomTestListener implements ITestListener {
	
	 private ExtentReports extent;
	  private ExtentTest test;

	@Override
	public void onStart(ITestContext context) {
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter("./test-output/ExtentReports/ExtentReport.html");
	    extent = new ExtentReports();
	    extent.attachReporter(sparkReporter);
	    System.out.println("Test started: " + context.getName());
	}
	
    @Override
    public void onTestSuccess(ITestResult result) {
    	   test.log(Status.PASS, "Test passed");
        System.out.println("Test passed: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.log(Status.FAIL, "Test failed");
        System.out.println("Test failed: " + result.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
    	extent.flush();
        System.out.println("All tests finished in " + context.getName());
    }

	@Override
	public void onTestSkipped(ITestResult result) {
		test.log(Status.SKIP, "Test skipped");
		System.out.println("All tests skipped in " + result.getName());		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
	    System.out.println("Test started: " + result.getName());
	}
}
