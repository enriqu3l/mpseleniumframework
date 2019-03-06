package testlisteners;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import config.FWConfig;
import helpers.ExtentManager;
import utility.FWUtils;

public class ExtentReportListener implements ITestListener{
	private WebDriver driver = null;
	private static Logger logger = LogManager.getLogger(ExtentReportListener.class);
	private static ExtentReports extent = ExtentManager.createInstance("test-output/extent.html");
	private static ThreadLocal<ExtentTest> parentTest = new ThreadLocal<ExtentTest>();
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
	
	@Override
	public synchronized void onTestStart(ITestResult result) {
		logger.info("Starting onTestStart");
		ExtentTest child = parentTest.get().createNode(result.getMethod().getMethodName());
        test.set(child);
	}

	@Override
	public synchronized  void onTestSuccess(ITestResult result) {
		logger.info("Starting onTestSuccess");
		test.get().pass("Test passed");
	}

	@Override
	public synchronized  void onTestFailure(ITestResult result) {
		logger.info("Starting onTestFailure");
		ITestContext context = result.getTestContext();
	    driver = (WebDriver) context.getAttribute("WebDriver");
	    
		//Imprimir pantalla de la falla!
	    String pathFolder = System.getProperty("user.dir") + FWConfig.PATH_OUTPUTDATA_SCREENSHOOTS_FAILURES;
		String pathScreen = FWUtils.ScreenShot(driver, "failure_"+result.getName(), pathFolder);
		try {
			test.get().fail(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(pathScreen).build());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public synchronized  void onTestSkipped(ITestResult result) {
		logger.info("Starting onTestSkipped");
		test.get().skip(result.getThrowable());
	}

	@Override
	public synchronized  void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		logger.info("Starting onTestFailedButWithinSuccessPercentage");
	}

	@Override
	public synchronized  void onStart(ITestContext context) {
		logger.info("Starting onStart");
		ExtentTest parent = extent.createTest(context.getName());
        parentTest.set(parent);
	}

	@Override
	public synchronized  void onFinish(ITestContext context) {
		logger.info("Starting onFinish");
		extent.flush();
	}
}
