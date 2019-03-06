package testlisteners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import config.FWConfig;
import utility.FWUtils;

public class ListenerRegression implements ITestListener{
	private WebDriver driver = null;
	private static Logger logger = LogManager.getLogger(ListenerRegression.class);

	@Override
	public void onTestStart(ITestResult result) {
		logger.info("Starting onTestStart");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		logger.info("Starting onTestSuccess");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		logger.info("Starting onTestFailure");
		logger.info("Fallo la prueba: "+result.getName());
		
		ITestContext context = result.getTestContext();
	    driver = (WebDriver) context.getAttribute("WebDriver");
		
		//Imprimir pantalla de la falla!
	    String path = System.getProperty("user.dir") + FWConfig.PATH_OUTPUTDATA_SCREENSHOOTS_FAILURES;
		FWUtils.ScreenShot(driver, "FAILURE-"+result.getName(), path);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		logger.info("Starting onTestSkipped");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		logger.info("Starting onTestFailedButWithinSuccessPercentage");
	}

	@Override
	public void onStart(ITestContext context) {
		logger.info("Starting onStart");
	}

	@Override
	public void onFinish(ITestContext context) {
		logger.info("Starting onFinish");
	}
}
