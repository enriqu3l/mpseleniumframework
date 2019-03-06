package testcases.pt.smook;

import org.testng.Reporter;
import org.testng.annotations.Test;

import dataproviders.pt.Provider;
import pages.pt.Pages;
import testbases.pt.TB_Smook;
import valueobjects.VOClient;
import valueobjects.VOCreditCard;
import valueobjects.VOResData;

public class TC_HPVuelo extends TB_Smook{
	
	@Test (enabled=true, priority = 1, dataProvider = "hpFlightProvider", dataProviderClass = Provider.class)
	public void HPFlight(VOResData voResData, VOClient voClient, VOCreditCard voCreditCard){
		Reporter.log("Starting test HPFlight");
		logger.info("Starting test HPFlight");
		
		//------- ATENCION -----------
		//Este happypath de vuelo solo es capaz de hacer reservaciones de 1 solo pasajero
		//es decir, no esta preparado para hacer reservaciones de 2 o mas personas
		
		Pages.home().widgetFlight.searchFlight(voResData);
		Pages.home().widgetFlight.clickSearchButton();
		
		//Aun no se desarrolla!
		
		//Pages.payMethod().clickOnCompleteReservation();
		//Pages.thankYou().verifyCheckOutCompleted();
	}
}
