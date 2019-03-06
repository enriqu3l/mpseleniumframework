package pages.pt.cruceros;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import config.FWConfig;
import helpers.JSWaiter;
import pages.pt.Pages;
import utility.FWUtils;

public class HomeCrucerosPage {
	private WebDriverWait wait;
	private WebDriver driver;
	private static Logger logger = LogManager.getLogger(HomeCrucerosPage.class);
	
	//Constructor
	public HomeCrucerosPage(WebDriver _driver){
		Assert.assertFalse(null==_driver,"La variable 'driver' es null");
		this.driver = _driver;
		this.wait = new WebDriverWait(_driver,FWConfig.WAIT_PT);
		PageFactory.initElements(new AjaxElementLocatorFactory(_driver, FWConfig.WAITPF_PT),this);
		//Esperar a que la url sea la correcta
		wait.until(ExpectedConditions.urlContains("/cruceros"));
		logger.trace("--->CurrentURL:"+driver.getCurrentUrl());
	}
	
	public void goTo() {
		Pages.topnav(driver).clickCruceros();
	}
	
	public boolean isAt() {
		return driver.getTitle().contains("Reserva un crucero");
	}
	
	public int checkBrokenLinks() {
		//Se lanzan errores de tipo: "stale element reference: element is not attached to the page document"
		//Cuando se ejecuta esta funcion en paginas que tienen un baner de cuenta regresiva, dado que el banner
		//de cuenta regresiva esta creamdo y eliminando etiquetas de tipo "a"
		return FWUtils.checkBrokenLinks(driver.findElements(new By.ByTagName("a")));
	}
}
