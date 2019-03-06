package testcases.pt.smook;

import org.testng.Reporter;
import org.testng.annotations.Test;

import dataproviders.pt.Provider;
import pages.pt.Pages;
import testbases.pt.TB_SmookMethod;
import valueobjects.VOClient;
import valueobjects.VOCreditCard;
import valueobjects.VOResData;

public class TC_HPHotelConCambios extends TB_SmookMethod{
	
	@Test (enabled=true, priority = 1, groups = { "HPHotelConCambios" },
	dataProvider = "hpHotelProvider", dataProviderClass = Provider.class)
	public void HPHotelConCambioDeDestino(VOResData voResData, VOClient voClient, VOCreditCard voCreditCard){
		Reporter.log("Starting @HPHotelConCambioDeDestino");
		logger.info("Starting @HPHotelConCambioDeDestino");
		Pages.home().widgetHotel.searchHotel(voResData);
		Pages.home().widgetHotel.clickSearchButton();
		//Lo tengo hardcodeado a Las vegas, necesito hacerlo dinamico con un archivo o una funcion
		Pages.hotelList().widget.setDestin("Las Vegas (y alrededores)");
		Pages.hotelList().widget.clickSubmit();
		Pages.hotelList().list.selectFirstHotelAvailable();
		Pages.roomList().selectFirstRoom();
		Pages.resDetail().clearAndFillForm(voClient);
		Pages.resDetail().clickOnContinue();
		Pages.payMethod().fillCreditForm(voCreditCard);
	}
	
	@Test (enabled=true, priority = 2, groups = { "HPHotelConCambios" },
			dataProvider = "hpHotelProvider", dataProviderClass = Provider.class)
	public void HPHotelConCambioDeFecha(VOResData voResData, VOClient voClient, VOCreditCard voCreditCard){		
		Reporter.log("Starting @HPHotelConCambioDeFecha");
		logger.info("Starting @HPHotelConCambioDeFecha");
		Pages.home().widgetHotel.searchHotel(voResData);
		Pages.home().widgetHotel.clickSearchButton();
		Pages.hotelList().widget.setStartDate(voResData.getStartDateAsLocalDate().plusDays(5).toString("dd/MM/yyyy"));
		Pages.hotelList().widget.setEndDate(voResData.getEndDateAsLocalDate().plusDays(5).toString("dd/MM/yyyy"));
		Pages.hotelList().widget.clickSubmit();
		Pages.hotelList().list.selectFirstHotelAvailable();
		Pages.roomList().selectFirstRoom();
		Pages.resDetail().clearAndFillForm(voClient);
		Pages.resDetail().clickOnContinue();
		Pages.payMethod().fillCreditForm(voCreditCard);
	}
	
	@Test (enabled=true, priority = 3, groups = { "HPHotelConCambios" },
			dataProvider = "hpHotelProvider", dataProviderClass = Provider.class)
	public void HPHotelConCambioDeOcupantes(VOResData voResData, VOClient voClient, VOCreditCard voCreditCard){		
		Reporter.log("Starting @HPHotelConCambioDeOcupantes");
		logger.info("Starting @HPHotelConCambioDeOcupantes");
		Pages.home().widgetHotel.searchHotel(voResData);
		Pages.home().widgetHotel.clickSearchButton();
		Pages.hotelList().widget.setAdults(4);
		Pages.hotelList().widget.clickSubmit();
		Pages.hotelList().list.selectFirstHotelAvailable();
		Pages.roomList().selectFirstRoom();
		Pages.resDetail().clearAndFillForm(voClient);
		Pages.resDetail().clickOnContinue();
		Pages.payMethod().fillCreditForm(voCreditCard);
	}
}
