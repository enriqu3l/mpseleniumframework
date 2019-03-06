package testcases.pt.smook;

import org.testng.Reporter;
import org.testng.annotations.Test;

import dataproviders.pt.Provider;
import pages.pt.Pages;
import testbases.pt.TB_SmookMethod;
import valueobjects.VOClient;
import valueobjects.VOCreditCard;
import valueobjects.VOResData;

public class TC_HPHotelConPaginado extends TB_SmookMethod{
	
	@Test (enabled=true, priority = 1, groups = { "HPHotelConPaginado" },
			dataProvider = "hpHotelProvider", dataProviderClass = Provider.class)
	public void HPHotelConCambioDePagina(VOResData voResData, VOClient voClient, VOCreditCard voCreditCard){
		Reporter.log("Starting test HPHotelConCambioDePagina");
		logger.info("Starting test HPHotelConCambioDePagina");
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
