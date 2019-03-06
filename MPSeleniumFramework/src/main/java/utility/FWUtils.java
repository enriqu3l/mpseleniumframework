package utility;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.LocalDateTime;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import config.FWConfig;
import helpers.LinkStatus;

public class FWUtils {
	private static Logger logger = LogManager.getLogger(FWUtils.class);
	
	// Check if a element is present
	public static boolean existsElement(WebElement element) {
		try {
			element.isDisplayed();
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
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

	//++++++++++++++++++++++++++++++++++++++++++++++++
	//++++++++++++++++ ScreenShots +++++++++++++++++++
	
	// Guardar un ScreenShot
	public static String ScreenShot(WebDriver d) {
		String date = LocalDateTime.now().toString("yyyy-MM-dd_HH-mm-ss");
		File src = ((TakesScreenshot) d).getScreenshotAs(OutputType.FILE);
		String filePath = System.getProperty("user.dir") + FWConfig.PATH_OUTPUTDATA_SCREENSHOOTS_FAILURES + "Screen_"+ date + ".png";
		try {
			FileUtils.copyFile(src, new File(filePath));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return filePath;
	}// End of function

	// Guardar un ScreenShot y permite ponerle un nombre a la captura
	public static String ScreenShot(WebDriver d, String _name) {
		String date = LocalDateTime.now().toString("yyyy-MM-dd_HH-mm-ss");
		File src = ((TakesScreenshot) d).getScreenshotAs(OutputType.FILE);
		String filePath = System.getProperty("user.dir") + FWConfig.PATH_OUTPUTDATA_SCREENSHOOTS_FAILURES+ _name + "_" + date + ".png";
		try {
			FileUtils.copyFile(src, new File(filePath));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return filePath;
	}// End of function

	/**
	 * Guardar un ScreenShot y permite ponerle un nombre a la captura y decidir
	 * donde guardar la captura. La imagen se guarda en formato png
	 * 
	 * @param webDriver
	 * @param name
	 * @param path
	 */
	public static String ScreenShot(WebDriver d, String _name, String _path) {
		String date = LocalDateTime.now().toString("yyyy-MM-dd_HH-mm-ss");
		File src = ((TakesScreenshot) d).getScreenshotAs(OutputType.FILE);
		String filePath = _path + "Screen_" + _name + "_" + date + ".png";
		try {
			FileUtils.copyFile(src, new File(filePath));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return filePath;
	}// End of function
	
	//++++++++++++++++++++++++++++++++++++++++++++++++++++++
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
		
		//Se lanzan errores de tipo: "stale element reference: element is not attached to the page document"
		//Cuando se ejecuta esta funcion en paginas que tienen un baner de cuenta regresiva, dado que el banner
		//de cuenta regresiva esta creamdo y eliminando etiquetas de tipo "a"
		
		logger.trace("Number of links: "+links.size());
		LinkStatus.Initialize();
		for(WebElement link : links) {
			if(null==link.getAttribute("href")) {
				logger.trace("Got null when try to get Attribute: href");
			}else if(link.getAttribute("href").isEmpty() || link.getAttribute("href").equals(""))
			{
				logger.trace("URL is empty");
			}else {
				//logger.trace("URL: "+link.getAttribute("href"));
				LinkStatus.verifyLink(link.getAttribute("href"));
			}
		}
		int brokenLinks = LinkStatus.getBrokenLinksCount();
		if(brokenLinks>0){logger.error("Number of broken links: "+brokenLinks);}
		return brokenLinks;
	}
}
