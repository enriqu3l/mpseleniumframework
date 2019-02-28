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

public class TC_HPHotelConCambios extends TB_SmookMethod{
	
	@Test (enabled=true, priority = 1, groups = { "HPHotelConCambios" })
	public void HPHotelConCambioDeDestino(){
		Reporter.log("Starting @HPHotelConCambioDeDestino");
		logger.info("Starting @HPHotelConCambioDeDestino");
		VOResData voResData = DDManager.getResDataDefault(FWConfig.FILE_HPHOTELRESDATA);
		VOCreditCard voCreditCard = DDManager.getCreditCardDefault();
		VOClient voClient = DDManager.getClientDataDefault(FWConfig.FILE_CLIENTDATA);
		Pages.home().widget.searchHotel(voResData);
		Pages.home().widget.clickSearchHotelButton();
		//Lo tengo hardcodeado a Las vegas, necesito hacerlo dinamico con un archivo o una funcion
		Pages.hotelList().widget.setDestin("Las Vegas (y alrededores)");
		Pages.hotelList().widget.clickSubmit();
		Pages.hotelList().list.selectFirstHotelAvailable();
		Pages.roomList().selectFirstRoom();
		Pages.resDetail().clearAndFillForm(voClient);
		Pages.resDetail().clickOnContinue();
		Pages.payMethod().fillCreditForm(voCreditCard);
	}
	
	@Test (enabled=true, priority = 1, groups = { "HPHotelConCambios" })
	public void HPHotelConCambioDeFecha(){		
		Reporter.log("Starting @HPHotelConCambioDeFecha");
		logger.info("Starting @HPHotelConCambioDeFecha");
		VOResData voResData = DDManager.getResDataDefault(FWConfig.FILE_HPHOTELRESDATA);
		VOCreditCard DO_CreditCard = DDManager.getCreditCardDefault();
		VOClient voClient = DDManager.getClientDataDefault(FWConfig.FILE_CLIENTDATA);
		Pages.home().widget.searchHotel(voResData);
		Pages.home().widget.clickSearchHotelButton();
		Pages.hotelList().widget.setStartDate(voResData.getStartDateAsLocalDate().plusDays(5).toString());
		Pages.hotelList().widget.setEndDate(voResData.getEndDateAsLocalDate().plusDays(5).toString());
		Pages.hotelList().widget.clickSubmit();
		Pages.hotelList().list.selectFirstHotelAvailable();
		Pages.roomList().selectFirstRoom();
		Pages.resDetail().clearAndFillForm(voClient);
		Pages.resDetail().clickOnContinue();
		Pages.payMethod().fillCreditForm(DO_CreditCard);
	}
	
	@Test (enabled=true, priority = 1, groups = { "HPHotelConCambios" })
	public void HPHotelConCambioDeOcupantes(){		
		Reporter.log("Starting @HPHotelConCambioDeOcupantes");
		logger.info("Starting @HPHotelConCambioDeOcupantes");
		VOResData voResData = DDManager.getResDataDefault(FWConfig.FILE_HPHOTELRESDATA);
		VOCreditCard DO_CreditCard = DDManager.getCreditCardDefault();
		VOClient voClient = DDManager.getClientDataDefault(FWConfig.FILE_CLIENTDATA);
		Pages.home().widget.searchHotel(voResData);
		Pages.home().widget.clickSearchHotelButton();
		Pages.hotelList().widget.setAdults(4);
		Pages.hotelList().widget.clickSubmit();
		Pages.hotelList().list.selectFirstHotelAvailable();
		Pages.roomList().selectFirstRoom();
		Pages.resDetail().clearAndFillForm(voClient);
		Pages.resDetail().clickOnContinue();
		Pages.payMethod().fillCreditForm(DO_CreditCard);
	}
}
