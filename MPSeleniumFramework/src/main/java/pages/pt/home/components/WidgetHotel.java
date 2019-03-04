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

public class WidgetHotel{
	private WebDriver driver;
	private WebDriverWait wait;
	private static Logger logger = LogManager.getLogger(WidgetHotel.class);
	
	private WidgetMenu menu;
	
	public WidgetHotel(WebDriver _driver) {
		this.driver = _driver;
		this.wait = new WebDriverWait(_driver,FWConfig.WAIT_PT);
		PageFactory.initElements(new AjaxElementLocatorFactory(_driver, FWConfig.WAITPF_PT),this);
		menu = new WidgetMenu(_driver);
	}
	
	By byAutocompleteDropdown = By.cssSelector(".pt-customJqueryUi .ui-autocomplete[style*='display: block;']");
	//By byWidgetAutocompleteDropdownHotelDest = By.cssSelector(".pt-customJqueryUi #ui-id-1");
	private By byWidgetAgeKid = By.cssSelector(".ap_age");
	
	
	//--------------- Widget Hoteles ---------------
	@FindBy(how=How.ID, using="var1_1")
	private WebElement radioButtonHotel;
	
	@FindBy(how=How.ID, using="var1_3")
	private WebElement radioButtonHotelFlight;
	
	@FindBy(how=How.ID, using="ap_dest_hotel")
	private WebElement inputDestHotel;
	
	@FindBy(how=How.ID, using="ap_dest_start")
	private WebElement inputDestStartHotel;
	
	@FindBy(how=How.CSS, using="#ap_booker_Hotel_form .ptw-field-date:nth-child(1) .ui-datepicker-trigger")
	private WebElement imageDateStartHotelTrigger;
	
	@FindBy(how=How.ID, using="ap_dest_end")
	private WebElement inputDestEndHotel;
	
	@FindBy(how=How.CSS, using="#ap_booker_Hotel_form .ptw-field-date:nth-child(2) .ui-datepicker-trigger")
	private WebElement imageDateEndHotelTrigger;
	
	@FindBy(how=How.ID, using="ap_booker_Hotel_rooms")
	private WebElement selectBookerHotelRooms;
	
	@FindBy(how=How.ID, using="ap_booker_Hotel_adults0")
	private WebElement selectBookerHotelAdults0;
	
	@FindBy(how=How.ID, using="ap_booker_Hotel_minors0")
	private WebElement selectBookerHotelMinors0;
	
	@FindBy(how=How.CSS, using="#ap_booker_Hotel_form .ptw-submit-btn")
	private WebElement buttonSearchHoteles;
	
	@FindBy(how=How.CSS, using="#ap_booker_Hotel_form .ap_booker_Hotelroom")
	private  List<WebElement> allBlockRooms;
	
	@FindBy(how=How.CSS, using="#ap_booker_Hotel_form .ap_minorsAges_Hotel_container")
	private  List<WebElement> minorsAgesHotelContainer;
	
	private By byHotelAdults = By.cssSelector(".ap_booker_Hotel_adults");
	private By byHotelMinors = By.cssSelector(".ap_booker_Hotel_minors");
	
	
	//+++++++++++++++++ Hotels  Methods ++++++++++++++++++++++++++++++
	public void setDestin(String destin) {
		logger.info("Starting widgetSelectHotelDestin()");
		inputDestHotel.clear();
		inputDestHotel.sendKeys(destin);
		logger.trace("Destin: "+ destin);
		wait.until(ExpectedConditions.visibilityOfElementLocated(byAutocompleteDropdown));
		inputDestHotel.sendKeys(Keys.ENTER);
	}
	
	public void setStartDate(String startDate) {
		inputDestStartHotel.clear();
		inputDestStartHotel.sendKeys(startDate);
		logger.trace("Start Date: "+startDate);
	}
	
	public void setEndDate(String endDate) {
		inputDestEndHotel.clear();
		inputDestEndHotel.sendKeys(endDate);
		//Aqui voy a cerrar el datepicker dropdown haciendo click en el icono
		imageDateEndHotelTrigger.click();
		logger.trace("End Date: "+endDate);
	}
	
	public void setOccupants(VOResData voHotelRes) {
		List<WebElement> lweBlockRooms = allBlockRooms;
		List<WebElement> lweMinorsAgeContainer = minorsAgesHotelContainer;
		WebElement weRooms = selectBookerHotelRooms;
		By byAdults = byHotelAdults;
		By byKids = byHotelMinors;
		By byAgeKid = byWidgetAgeKid;
		//Seleccion de rooms, adults, kids y agekids
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
	
	public void clickSearchButton() {
		buttonSearchHoteles.click();
	}
	
	public void searchHotel(VOResData voResData){
		logger.info("Starting SearchHotel");
		setProduct("Hoteles");
		setDestin(voResData.getDestination());
		setStartDate(voResData.getStartDate());
		setEndDate(voResData.getEndDate());
		setOccupants(voResData);
	}
	
	public void setProduct(String product) {
		menu.setProduct(product);
	}
}
