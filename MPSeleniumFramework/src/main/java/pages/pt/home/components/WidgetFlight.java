package pages.pt.home.components;

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

import config.FWConfig;
import valueobjects.VOResData;

public class WidgetFlight{
	private WebDriver driver;
	private WebDriverWait wait;
	private static Logger logger = LogManager.getLogger(WidgetFlight.class);
	
	private WidgetMenu menu;
	
	public WidgetFlight(WebDriver _driver) {
		this.driver = _driver;
		this.wait = new WebDriverWait(_driver,FWConfig.WAIT_PT);
		PageFactory.initElements(new AjaxElementLocatorFactory(_driver, FWConfig.WAITPF_PT),this);
	}
	
	By byAutocompleteDropdown = By.cssSelector(".pt-customJqueryUi .ui-autocomplete[style*='display: block;']");
	private By byWidgetAgeKid = By.cssSelector(".ap_age");
	
	
	//--------------- Widget Vuelos ---------------
	@FindBy(how=How.ID, using="ap_booker_Flight")
	WebElement flightContainer;
	
	@FindBy(how=How.ID, using="var2_2")
	private WebElement radioButtonFlight;
	
	@FindBy(how=How.ID, using="var2_3")
	private WebElement radioButtonFlightPackage;
	
	@FindBy(how=How.ID, using="ap_origin_flight")
	private WebElement inputOrigin;
	
	@FindBy(how=How.ID, using="ap_dest_flight")
	private WebElement inputDestin;
	
	@FindBy(how=How.ID, using="ap_flight_start")
	private WebElement inputFlightStart;
	
	@FindBy(how=How.ID, using="ap_flight_end")
	private WebElement inputFlightEnd;
	
	@FindBy(how=How.CSS, using="#ap_booker_singleDestination .ptw-field-date:nth-child(1) .ui-datepicker-trigger")
	private WebElement imageDateStartTrigger;
	
	@FindBy(how=How.CSS, using="#ap_booker_singleDestination .ptw-field-date:nth-child(2) .ui-datepicker-trigger")
	private WebElement imageDateEndTrigger;
	
	@FindBy(how=How.CSS, using="#ap_booker_Flight_form .ptw-submit-btn")
	private WebElement buttonSearchFlight;
	
	@FindBy(how=How.ID, using="ap_booker_flight_adults1")
	WebElement selectAdults;
	
	@FindBy(how=How.ID, using="ap_booker_flight_minors1")
	WebElement selectKids;
	
	@FindBy(how=How.ID, using="ap_minorsAges_flight_container1")
	WebElement minorAgeFlightContainer;
	

	//++++++++++++++++++ Flight Methods ++++++++++++++++++++++++
	public void setOrigin(String origin) {
		logger.info("Starting widgetSetPackageOrigin()");
		inputOrigin.clear();
		inputOrigin.sendKeys(origin);
		logger.trace("Origin: "+ origin);
		wait.until(ExpectedConditions.visibilityOfElementLocated(byAutocompleteDropdown));
		inputOrigin.sendKeys(Keys.ENTER);
	}
	
	public void setDestin(String destin) {
		logger.info("Starting widgetSetPackageDestin()");
		inputDestin.clear();
		inputDestin.sendKeys(destin);
		logger.trace("Destin: "+ destin);
		wait.until(ExpectedConditions.visibilityOfElementLocated(byAutocompleteDropdown));
		inputDestin.sendKeys(Keys.ENTER);
	}
	
	public void setStartDate(String startDate) {
		inputFlightStart.clear();
		inputFlightStart.sendKeys(startDate);
		logger.trace("Start Date: "+startDate);
	}
	
	public void setEndDate(String endDate) {
		inputFlightEnd.clear();
		inputFlightEnd.sendKeys(endDate);
		//Aqui voy a cerrar el datepicker dropdown haciendo click en el icono
		inputFlightEnd.click();
		logger.trace("End Date: "+endDate);
	}
	
	public void setAdults(int adultsNumber) {
		Select adults = new Select(selectAdults);
		adults.selectByVisibleText(Integer.toString(adultsNumber));
	}
	
	public void setKids(int kidsNumber) {
		Select kids = new Select(selectKids);
		kids.selectByVisibleText(Integer.toString(kidsNumber));
	}
	
	public void setPassengers(VOResData voResData) {
		setAdults(voResData.getAdultsFromRoom(0));
		setKids(voResData.getKidsFromRoom(0));
		for(int i=0;i<voResData.getKidsFromRoom(0);i++) {
			Select age = new Select(minorAgeFlightContainer.findElements(byWidgetAgeKid).get(i));
			age.selectByVisibleText(Integer.toString(voResData.getAgeFromAgekids(0, i)));
		}
	}
	
	public void clickSearchButton() {
		buttonSearchFlight.click();
	}
	
	public void searchFlight(VOResData voResData){
		logger.info("Starting searchPackage");
		setProduct("Vuelos");
		setOrigin(voResData.getOrigin());
		setDestin(voResData.getDestination());
		setStartDate(voResData.getStartDate());
		setEndDate(voResData.getEndDate());
		setPassengers(voResData);
	}
	
	public void setProduct(String product) {
		menu.setProduct(product);
	}
}
