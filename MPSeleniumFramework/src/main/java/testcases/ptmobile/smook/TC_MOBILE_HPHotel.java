package testcases.ptmobile.smook;

import org.testng.Reporter;
import org.testng.annotations.Test;

import dataproviders.ptmobile.MobileProvider;
import pages.pt.Pages;
import testbases.ptmobile.TB_MobileSmook;
import valueobjects.VOClient;
import valueobjects.VOCreditCard;
import valueobjects.VOResData;

public class TC_MOBILE_HPHotel extends TB_MobileSmook{
	
	@Test (enabled=true, priority = 3, dataProvider = "hpHotelProvider", dataProviderClass = MobileProvider.class)
	public void MobileHPHotel(VOResData voResData, VOClient voClient, VOCreditCard voCreditCard){
		Reporter.log("Starting test HPHotelHPHotelUsingDataRow");
		logger.info("Starting test HPHotelHPHotelUsingDataRow");
		Pages.home().widgetHotel.searchHotel(voResData);
		Pages.home().widgetHotel.clickSearchButton();
		Pages.hotelList().list.selectFirstHotelAvailable();
		Pages.roomList().selectFirstRoom();
		Pages.resDetail().clearAndFillForm(voClient);
		//PagesNew.resDetail().clickOnContinue();
		//PagesNew.payMethod().fillCreditForm(voCreditCard);
		//PagesNew.payMethod().clickOnCompleteReservation();
		//PagesNew.thankYou().verifyCheckOutCompleted();
	}
}
