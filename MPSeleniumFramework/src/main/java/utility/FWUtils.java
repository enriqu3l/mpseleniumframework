package utility;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import config.FWConfig;
import helpers.LinkStatus;

public class FWUtils {
	private static Logger logger = LogManager.getLogger(FWUtils.class);

	// Check if a element is present
	public static boolean existsElement(WebDriver driver, By by) {
		return !driver.findElements(by).isEmpty();
	}

	// Check if a element is NOT present
	public static boolean noExistsElement(WebDriver driver, By by) {
		return driver.findElements(by).isEmpty();
	}

	//Convert "WebElement" to "By"
	public static By toByVal(WebElement we) {
		// By format = "[foundFrom] -> locator: term"
		// see RemoteWebElement toString() implementation
		String[] data = we.toString().split(" -> ")[1].replace("]", "").split(": ");
		String locator = data[0];
		String term = data[1];
		switch (locator) {
		case "xpath":
			return By.xpath(term);
		case "css selector":
			return By.cssSelector(term);
		case "id":
			return By.id(term);
		case "tag name":
			return By.tagName(term);
		case "name":
			return By.name(term);
		case "link text":
			return By.linkText(term);
		case "class name":
			return By.className(term);
		}
		return (By) we;
	}

	//++++++++++++++++ ScreenShots +++++++++++++++++++
	
	// Guardar un ScreenShot
	public static void ScreenShot(WebDriver d) {
		File src = ((TakesScreenshot) d).getScreenshotAs(OutputType.FILE);
		try {
			String filePath = System.getProperty("user.dir") + FWConfig.PATH_OUTPUTDATA_SCREENSHOOTS_FAILURES;
			FileUtils.copyFile(src, new File(filePath + "Screen_"+ System.currentTimeMillis() + ".png"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}// End of function

	// Guardar un ScreenShot y permite ponerle un nombre a la captura
	public static void ScreenShot(WebDriver d, String _name) {
		File src = ((TakesScreenshot) d).getScreenshotAs(OutputType.FILE);
		try {
			String filePath = System.getProperty("user.dir") + FWConfig.PATH_OUTPUTDATA_SCREENSHOOTS_FAILURES;
			FileUtils.copyFile(src,
					new File(filePath + _name + "_" + System.currentTimeMillis() + ".png"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}// End of function

	/**
	 * Guardar un ScreenShot y permite ponerle un nombre a la captura y decidir
	 * donde guardar la captura. La imagen se guarda en formato png
	 * 
	 * @param webDriver
	 * @param name
	 * @param path
	 */
	public static void ScreenShot(WebDriver d, String _name, String _path) {
		File src = ((TakesScreenshot) d).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(src, new File(_path + "Screen_" + _name + "_" + System.currentTimeMillis() + ".png"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}// End of function
	
	
	//++++++++++++++++ Browser functions +++++++++++++++++++
	
	//Swichea a una nueva tab si la hay
	public static void switchToNewTabIfOpened(WebDriver driver, int tabsCount) {
		System.out.println("Before, tabsCount: " + tabsCount);
		List<String> browserTabs = new ArrayList<String>(driver.getWindowHandles());
		System.out.println("After, tabsCount: " + browserTabs.size());
		if (browserTabs.size() > tabsCount) {
			driver.switchTo().window(browserTabs.get(browserTabs.size()-1)); // La primer tab comienza con 0
			System.out.println("Switching to tab: "+browserTabs.size());
		}
	}
	
	//++++++++++++++++ BrokenLinks +++++++++++++++++++
	public static int checkBrokenLinks(List<WebElement> links) {
		logger.trace("Number of links: "+links.size());
		LinkStatus.Initialize();
		for(WebElement link : links) {
			String URL = link.getAttribute("href");
			LinkStatus.verifyLink(URL);
		}
		int brokenLinks = LinkStatus.getBrokenLinksStatus().size();
		logger.trace("Number of broken links: "+brokenLinks);
		return brokenLinks;
	}
}
