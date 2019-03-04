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
	
	@Test (enabled=true, priority = 1, dataProvider = "hpPaqueteProvider", dataProviderClass = Provider.class)
	public void HPPackage(VOResData voResData, VOClient voClient, VOCreditCard voCreditCard){
		Reporter.log("Starting test HPPackage");
		logger.info("Starting test HPPackage");
		
		//------- ATENCION -----------
		//Este happypath de paquete solo es capaz de hacer reservaciones de 1 solo pasajero
		//es decir, no esta preparado para hacer reservaciones de 2 o mas personas
		
		Pages.home().widgetFlight.searchFlight(voResData);
		Pages.home().widgetFlight.clickSearchButton();
		Pages.packageList().listSelectFirstHotelAvailable();
		Pages.packageRoomList().selectFirstRoom();
		Pages.packageResDetail().clearAndFillForm(voClient);
		Pages.packageResDetail().clickContinue();
		Pages.payMethod().fillCreditForm(voCreditCard);
		//Pages.payMethod().clickOnCompleteReservation();
		//Pages.thankYou().verifyCheckOutCompleted();
	}
}
