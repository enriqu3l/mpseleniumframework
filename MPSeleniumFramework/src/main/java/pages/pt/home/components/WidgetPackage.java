package pages.pt.home.components;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import config.FWConfig;
import valueobjects.VOResData;

public class WidgetPackage{
	private WebDriver driver;
	private WebDriverWait wait;
	private static Logger logger = LogManager.getLogger(WidgetPackage.class);
	
	private WidgetMenu menu;
	
	public WidgetPackage(WebDriver _driver) {
		this.driver = _driver;
		this.wait = new WebDriverWait(_driver,FWConfig.WAIT_PT);
		PageFactory.initElements(new AjaxElementLocatorFactory(_driver, FWConfig.WAITPF_PT),this);
		menu = new WidgetMenu(_driver);
	}
	
	By byAutocompleteDropdown = By.cssSelector(".pt-customJqueryUi .ui-autocomplete[style*='display: block;']");
	//By byAutocompleteDropdownPackageOrigin = By.cssSelector(".pt-customJqueryUi #ui-id-2");
	//By byAutocompleteDropdownPackageDest = By.cssSelector(".pt-customJqueryUi #ui-id-3");
	private By byWidgetAgeKid = By.cssSelector(".ap_age");
	
	
	//--------------- Widget Paquetes ---------------
	@FindBy(how=How.ID, using="var3_3")
	private WebElement radioButtonHotelFlightPackage;
	
	@FindBy(how=How.ID, using="ap_origin_flightPackage")
	private WebElement inputOriginFlightPackage;
	
	@FindBy(how=How.ID, using="ap_dest_flightPackage")
	private WebElement inputDestFlightPackage;
	
	@FindBy(how=How.ID, using="ap_flightPackage_start")
	private WebElement inputFlightPackageStart;
	
	@FindBy(how=How.ID, using="ap_flightPackage_end")
	private WebElement inputFlightPackageEnd;
	
	@FindBy(how=How.CSS, using="#ap_booker_FlightPackage_form .ptw-field-date:nth-child(1) .ui-datepicker-trigger")
	private WebElement imageDateStartPackageTrigger;
	
	@FindBy(how=How.CSS, using="#ap_booker_FlightPackage_form .ptw-field-date:nth-child(2) .ui-datepicker-trigger")
	private WebElement imageDateEndPackageTrigger;
	
	@FindBy(how=How.CSS, using="#ap_booker_FlightPackage_form .ptw-submit-btn")
	private WebElement buttonSearchPackage;
	
	@FindBy(how=How.ID, using="ap_booker_FlightPackage_rooms")
	private WebElement selectBookerPackageRooms;
	
	@FindBy(how=How.CSS, using="#ap_booker_FlightPackage_form .ap_booker_FlightPackageroom")
	private  List<WebElement> allBlockPackageRooms;
	
	@FindBy(how=How.CSS, using="#ap_booker_FlightPackage_form .ap_minorsAges_FlightPackage_container")
	private  List<WebElement> minorsAgesPackageContainer;
	
	private By byPackageAdults = By.cssSelector(".ap_booker_FlightPackage_adults");
	private By byPackageMinors = By.cssSelector(".ap_booker_FlightPackage_minors");

	
	public void setOccupants(VOResData voHotelRes) {
		List<WebElement> lweBlockRooms = allBlockPackageRooms;
		List<WebElement> lweMinorsAgeContainer = minorsAgesPackageContainer;
		WebElement weRooms = selectBookerPackageRooms;
		By byAdults = byPackageAdults;
		By byKids = byPackageMinors;
		By byAgeKid = byWidgetAgeKid;

		//Aqui el codigo para realizar la seleccion de rooms, adults, kids y agekids
		Select rooms = new Select(weRooms);
		rooms.selectByVisibleText(Integer.toString(voHotelRes.getRoomCount()));
		if(lweBlockRooms.size() != voHotelRes.getRoomCount()) {
			 logger.error("No se crearon los suficientes rooms containers, allBlocksRooms:"+lweBlockRooms.size());
			 Assert.fail("LAF>>>No se crearon los suficientes rooms containers, allBlocksRooms:"+lweBlockRooms.size());
		}
		//Ya se crearon los rooms ahora hay que llenar los adultos y niños
		for(int i=0;i<lweBlockRooms.size();i++) {
			WebElement weAdults = lweBlockRooms.get(i).findElement(byAdults);
			WebElement weKids = lweBlockRooms.get(i).findElement(byKids);
			
			Select adults = new Select(weAdults);
			adults.selectByVisibleText(Integer.toString(voHotelRes.getAdultsFromRoom(i)));
			
			Select kids = new Select(weKids);
			kids.selectByVisibleText(Integer.toString(voHotelRes.getKidsFromRoom(i)));
		}
		//Validar que se crearon campos de rooms para los agekids
		if(lweMinorsAgeContainer.size() != voHotelRes.getRoomCount()) {
			 logger.error("La cantidad de rooms no coincide con la cantidad de agecontainers, widgetMinorsAgesHotelContainer:"+lweMinorsAgeContainer.size());
			 Assert.fail("LAF>>>La cantidad de rooms no coincide con la cantidad de agecontainers, widgetMinorsAgesHotelContainer:"+lweMinorsAgeContainer.size());
		}
		//Ahora hay que llenar las edades de los niños en cada cuarto
		for(int i=0; i<lweMinorsAgeContainer.size();i++) {
			//Recorrer cada room
			if(voHotelRes.getKidsFromRoom(i)>0) {
				//Entra solo si el cuarto contiene niños
				List<WebElement> minors = lweMinorsAgeContainer.get(i).findElements(byAgeKid);
				for(int j=0;j<minors.size();j++) {
					//Recorrer cada niño y setear la edad
					Select minor = new Select(minors.get(j));
					minor.selectByVisibleText(Integer.toString(voHotelRes.getAgeFromAgekids(i, j)));
				}
			}
		}
	}
	
	public void setOrigin(String origin) {
		logger.info("Starting widgetSetPackageOrigin()");
		inputOriginFlightPackage.clear();
		inputOriginFlightPackage.sendKeys(origin);
		logger.trace("Origin: "+ origin);
		wait.until(ExpectedConditions.visibilityOfElementLocated(byAutocompleteDropdown));
		inputOriginFlightPackage.sendKeys(Keys.ENTER);
	}
	
	public void setDestin(String destin) {
		logger.info("Starting widgetSetPackageDestin()");
		inputDestFlightPackage.clear();
		inputDestFlightPackage.sendKeys(destin);
		logger.trace("Destin: "+ destin);
		wait.until(ExpectedConditions.visibilityOfElementLocated(byAutocompleteDropdown));
		inputDestFlightPackage.sendKeys(Keys.ENTER);
	}
	
	public void setStartDate(String startDate) {
		inputFlightPackageStart.clear();
		inputFlightPackageStart.sendKeys(startDate);
		logger.trace("Start Date: "+startDate);
	}
	
	public void setEndDate(String endDate) {
		inputFlightPackageEnd.clear();
		inputFlightPackageEnd.sendKeys(endDate);
		//Aqui voy a cerrar el datepicker dropdown haciendo click en el icono
		imageDateEndPackageTrigger.click();
		logger.trace("End Date: "+endDate);
	}
	
	public void clickSearchButton() {
		buttonSearchPackage.click();
	}
	
	public void searchPackage(VOResData voResData){
		logger.info("Starting searchPackage");
		setProduct("Paquetes");
		setOrigin(voResData.getOrigin());
		setDestin(voResData.getDestination());
		setStartDate(voResData.getStartDate());
		setEndDate(voResData.getEndDate());
		setOccupants(voResData);
	}
	
	public void setProduct(String product) {
		menu.setProduct(product);
	}
}
