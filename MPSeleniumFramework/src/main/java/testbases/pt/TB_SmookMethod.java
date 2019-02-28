package testbases.pt;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import helpers.BrowserFactory;
import pages.pt.Pages;

public class TB_SmookMethod {
	protected WebDriver driver;
	protected Logger logger = LogManager.getLogger(TB_SmookMethod.class);
	protected String gURL = "";
	protected String gBrowser = "";
	
	@BeforeTest
	@Parameters({"url","browser"})
	public void prerequisitos(String url, String browser, ITestContext itc) {
		logger.info("***************************** Starting @BeforeTest **********************************");
		Reporter.log("Starting BeforeTest");
		gURL = url;
		gBrowser = browser;
		Assert.assertFalse(gURL.equals(""),"No se ha seteado una URL valida!");
		Assert.assertFalse(gBrowser.equals(""),"No se ha seteado un browser valido!");
		logger.trace("URL Seteada:"+gURL);
		logger.trace("Browser Seteado:"+gBrowser);
	}
	
	@BeforeMethod
	public void beforeMethod(ITestContext itc) {
		logger.info("***************************** Starting @BeforeMethod **********************************");
		Reporter.log("Starting Browser");
		driver = BrowserFactory.startBrowser(gBrowser, gURL);
		itc.setAttribute("WebDriver", driver);
		Pages.setDriver(driver);
		Reporter.log("Browser Started");
		logger.info("Browser Started");
	}
	
	@AfterMethod
	public void Close()
	{
		Reporter.log("Closing Browser...");
		logger.info("Closing Browser...");
		driver.quit();
	}
	
	@AfterTest
	public void End()
	{
		Reporter.log("Test Finished");
		logger.info("Test Finished");
	}
	
}