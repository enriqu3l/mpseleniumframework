package pages.pt.vuelos;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import config.FWConfig;
import helpers.JSWaiter;

public class FlightListPage {
	private WebDriverWait wait;
	private WebDriver driver;
	private static Logger logger = LogManager.getLogger(FlightListPage.class);
	
	public FlightListPage(WebDriver _driver){
		Assert.assertFalse(null==_driver,"La variable 'driver' es null");
		this.driver = _driver;
		this.wait = new WebDriverWait(_driver,FWConfig.WAIT_PT);
		PageFactory.initElements(new AjaxElementLocatorFactory(_driver, FWConfig.WAITPF_PT),this);
		
		//Esperar a que la url sea la correcta
		wait.until(ExpectedConditions.urlContains("/paquetes/resultados"));
		waitForContentToBeReady();
	}
	
	
	private By byLoaderOverlayPage = By.xpath("//*[@class='loader__title']/parent::*/parent::*");
	private By byLoaderOverlayPage2 = By.cssSelector(".loader-overlay.ng-trigger"); //no funciona en stage-spa
	//private By byLoaderButton = By.cssSelector(".list-product-rate .loader"); //Ya lo declare en la seccion del listado
	private By byLoaderOverlayFiltros = By.cssSelector(".card-body .loader-overlay");
	
	@FindBy(how=How.CSS, using=".loader__title")
	private WebElement loaderTitle;
	
	@FindBy(how=How.CSS, using=".spinner")
	private WebElement spiner;
	
	
	//-------------- Header Section --------------------------------
	private By byPageHeaderTitle = By.cssSelector(".page-header .page-header-title");
	
	@FindBy(how=How.CSS, using=".page-header .page-header-title")
	private WebElement pageHeaderTitle;
	
	
	//-------------- List Section ----------------------------------	
	@FindBy(how=How.CSS, using=".list-product .list-product-block")
	private List<WebElement> listAllBlocksResults;
	
	@FindBy(how=How.CSS, using=".list-product-block .list-product-rate .list-product-rate-action a")
	private WebElement listButtonFirstItem; //primer boton
	
	private By byListListProduct = By.cssSelector(".list-product");
	private By byListProductRateFinal = By.cssSelector(".list-product-rate .product-rate-final");
	private By byListProductHotelName = By.cssSelector(".list-product-item-content .list-product-name");
	private By byListButtonSeeOffer = By.cssSelector(".list-product-rate .list-product-rate-action .btn");
	private By byListListProductRateLoaderButton = By.cssSelector(".list-product .list-product-rate .loader");
	
	
	
	
	//+++++++++++++++++++++++++++++++++++ WAITS ++++++++++++++++++++++++++++++++++++++++++++++++
	public void waitForContentToBeReady() {
		JSWaiter.setDriver(driver);
		JSWaiter.waitUntilJSReady();
	}
}
