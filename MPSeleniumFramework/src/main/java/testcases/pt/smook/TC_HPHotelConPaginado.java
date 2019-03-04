package testcases.pt.smook;

import org.testng.Reporter;
import org.testng.annotations.Test;

import config.FWConfig;
import helpers.DDManager;
import pages.pt.Pages;
import testbases.pt.TB_SmookMethod;
import valueobjects.VOClient;
import valueobjects.VOCreditCard;
import valueobjects.VOResData;

public class TC_HPHotelConPaginado extends TB_SmookMethod{
	
	@Test
	public void HPHotelConCambioDePagina(){
		Reporter.log("Starting test HPHotelConCambioDePagina");
		logger.info("Starting test HPHotelConCambioDePagina");
		VOResData voResData = DDManager.getResDataDefault(FWConfig.FILE_HPHOTELRESDATA);
		VOCreditCard voCreditCard = DDManager.getCreditCardDefault();
		VOClient voClient = DDManager.getClientDataDefault(FWConfig.FILE_CLIENTDATA);
		Pages.home().widgetHotel.searchHotel(voResData);
		Pages.home().widgetHotel.clickSearchButton();
		Pages.hotelList().paging.clickOnNextPage();
		Pages.hotelList().list.selectFirstHotelAvailable();
		Pages.roomList().selectFirstRoom();
		Pages.resDetail().clearAndFillForm(voClient);
		Pages.resDetail().clickOnContinue();
		Pages.payMethod().fillCreditForm(voCreditCard);
	}
}
