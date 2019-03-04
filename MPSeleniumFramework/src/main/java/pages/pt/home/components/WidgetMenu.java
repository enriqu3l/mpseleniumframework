package pages.pt.home.components;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import config.FWConfig;

public class WidgetMenu{
	private static Logger logger = LogManager.getLogger(WidgetMenu.class);
	
	public WidgetMenu(WebDriver _driver) {
		PageFactory.initElements(new AjaxElementLocatorFactory(_driver, FWConfig.WAITPF_PT),this);
	}
	
	//--------------- Widget------------------------
	@FindBy(how=How.CSS, using="#ptw-menu > ul")
	private WebElement widgetMenu;
	
	By byWidgetProductActive = By.cssSelector("li.ptw-active > a");
	
	public void setProduct(String product) {
		logger.info("Starting setProduct");
		if(!widgetMenu.findElement(byWidgetProductActive).getText().trim().equalsIgnoreCase(product)) {
			widgetMenu.findElement(By.linkText(product) ).click();
			logger.trace("Widget menu selected: "+ product);
		}else {
			logger.trace("Widget menu product: "+product+" was already selected: ");
		}
		logger.info("Ending setProduct");
	}
	
	public String getSelectedProduct() {
		return widgetMenu.findElement(byWidgetProductActive).getText();
	}
	
	public int getProductCount() {
		return widgetMenu.findElements(By.cssSelector("li")).size();
	}
}
