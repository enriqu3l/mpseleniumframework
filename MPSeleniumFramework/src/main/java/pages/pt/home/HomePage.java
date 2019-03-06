package pages.pt.home;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import config.FWConfig;
import pages.pt.Pages;
import pages.pt.home.components.WidgetFlight;
import pages.pt.home.components.WidgetHotel;
import pages.pt.home.components.WidgetPackage;
import utility.FWUtils;

public class HomePage {
	private WebDriverWait wait;
	private WebDriver driver;
	private static Logger logger = LogManager.getLogger(HomePage.class);
	
	public WidgetHotel widgetHotel;
	public WidgetPackage widgetPackage;
	public WidgetFlight widgetFlight;
	
	//Constructor
	public HomePage(WebDriver _driver){
		Assert.assertFalse(null==_driver,"La variable 'driver' es null");
		this.driver = _driver;
		this.wait = new WebDriverWait(_driver,FWConfig.WAIT_PT);
		widgetHotel = new WidgetHotel(_driver);
		widgetPackage = new WidgetPackage(_driver);
		widgetFlight = new WidgetFlight(_driver);
	}
	
	public void goToHard(String URL) {
		driver.get(URL);
		//Esperar a que la url sea la correcta
		wait.until(ExpectedConditions.urlContains("pricetravel."));
	}
	
	public void goTo() {
		Pages.topnav(driver).clickLogo();
	}

	public boolean isAt() {
		return driver.getTitle().contains("PriceTravel - Viaja fácil, sin pretextos");
	}
	
	public int checkBrokenLinks() {
		//Se lanzan errores de tipo: "stale element reference: element is not attached to the page document"
		//Cuando se ejecuta esta funcion en paginas que tienen un baner de cuenta regresiva, dado que el banner
		//de cuenta regresiva esta creamdo y eliminando etiquetas de tipo "a"
		return FWUtils.checkBrokenLinks(driver.findElements(new By.ByTagName("a")));
	}
}