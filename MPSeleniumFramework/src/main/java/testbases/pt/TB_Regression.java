package testbases.pt;

import java.util.List;

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

import config.FWConfig;
import helpers.BrowserFactory;
import pages.pt.Pages;
import utility.BasicUtils;
import utility.ExcelUtils;

public class TB_Regression {
	protected WebDriver driver;
	protected Logger logger = LogManager.getLogger(TB_Regression.class);
	protected String gBaseURL = "";
	protected String gBrowser = "";

	@BeforeTest
	@Parameters({"browser"})
	public void setup(String browser, ITestContext itc) {
		gBrowser = browser;
		Assert.assertFalse(gBrowser.equals(""), "No se ha seteado un browser valido!");

		// String url ="https://www.pricetravel.com/hoteles/cancun-area?checkin=2019-02-01&checkout=2019-02-03&placetype=3&placeid=69364&source=SPA-Hotel-List&rooms=1&room1.adults=2&agekids=";
		// String url ="https://stage-spa.pricetravel.com/hoteles/cancun-area?room1.adults=2&room1.kids=0&room1.agekids=&checkin=2019%2F02%2F02&checkout=2019%2F02%2F04&rooms=1&adults=2&kids=0&agekids=&pdisplay=Canc%C3%BAn%20(y%20alrededores),%20M%C3%A9xico&placeid=69364&placetype=3&puri=cancun-area&quotelist=true&returningfromairport=&startingfromairport=&actiontype=1";
		String filePath = System.getProperty("user.dir") + FWConfig.PATH_INPUTDATA_PT+FWConfig.FILE_HLLANDINGPAGEDATA;
		List<String> data = ExcelUtils.getRow(filePath, 1);
		gBaseURL = BasicUtils.createUrlLandingPageHL(data);
		logger.info("gBrowser: "+gBrowser);
		Assert.assertFalse(gBaseURL.equals(""), "No se ha seteado una URL valida!");
	}

	@BeforeMethod
	public void prerequisites(ITestContext itc) {
		Reporter.log("Starting prerequisites");
		logger.info("Starting prerequisites");
		driver = BrowserFactory.startBrowser(gBrowser, gBaseURL);
		itc.setAttribute("WebDriver", driver);
		Pages.setDriver(driver);
		Reporter.log("Browser Started");
		logger.info("Browser Started");
	}

	@AfterMethod
	public void Close() {
		Reporter.log("Closing Browser...");
		logger.info("Closing Browser...");
		driver.quit();
	}

	@AfterTest
	public void End() {
		Reporter.log("Test Finished");
		logger.info("Test Finished");
	}
}
