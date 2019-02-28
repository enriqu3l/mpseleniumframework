package dataproviders.tb;

import org.testng.annotations.DataProvider;

import config.FWConfig;
import utility.ExcelUtils;
import valueobjects.VOClient;
import valueobjects.VOCreditCard;
import valueobjects.VOResData;

public class Provider {
	@DataProvider(name = "hpHotelProvider")
	public Object[][] hpHotelProvider() {
		// String filePath = FWConfig.PATH_DATASOURCE+FWConfig.FILE_HPHOTELRESDATA;
		String filePath = System.getProperty("user.dir") + FWConfig.PATH_INPUTDATA_PT + "HPHotelResData.xlsx";
		VOResData voResData = new VOResData();
		voResData.setDataUsingList(ExcelUtils.getRow(filePath, 1));

		// String filePath2 = FWConfig.PATH_DATASOURCE+FWConfig.FILE_CLIENTDATA;
		String filePath2 = System.getProperty("user.dir") + FWConfig.PATH_INPUTDATA_PT + "ClientData.xlsx";
		VOClient voClient = new VOClient();
		voClient.setDataUsingList(ExcelUtils.getRow(filePath2, 1));

		// String filePath3 = FWConfig.PATH_DATASOURCE+FWConfig.FILE_CREDITCARDSDATA;
		String filePath3 = System.getProperty("user.dir") + FWConfig.PATH_INPUTDATA_PT + "CreditCardsData.xlsx";
		VOCreditCard voCreditCard = new VOCreditCard();
		voCreditCard.setDataUsingList(ExcelUtils.getRow(filePath3, 1));

		return new Object[][] { { voResData, voClient, voCreditCard } };
	}

	@DataProvider(name = "hpPaqueteProvider")
	public Object[][] hpPaqueteProvider() {
		// String filePath = FWConfig.PATH_DATASOURCE+FWConfig.FILE_HPHOTELRESDATA;
		String filePath = System.getProperty("user.dir") + FWConfig.PATH_INPUTDATA_PT + "HPPackageResData.xlsx";
		VOResData voResData = new VOResData();
		voResData.setDataUsingList(ExcelUtils.getRow(filePath, 1));

		// String filePath2 = FWConfig.PATH_DATASOURCE+FWConfig.FILE_CLIENTDATA;
		String filePath2 = System.getProperty("user.dir") + FWConfig.PATH_INPUTDATA_PT + "ClientData.xlsx";
		VOClient voClient = new VOClient();
		voClient.setDataUsingList(ExcelUtils.getRow(filePath2, 1));

		// String filePath3 = FWConfig.PATH_DATASOURCE+FWConfig.FILE_CREDITCARDSDATA;
		String filePath3 = System.getProperty("user.dir") + FWConfig.PATH_INPUTDATA_PT + "CreditCardsData.xlsx";
		VOCreditCard voCreditCard = new VOCreditCard();
		voCreditCard.setDataUsingList(ExcelUtils.getRow(filePath3, 1));

		return new Object[][] { { voResData, voClient, voCreditCard } };
	}
}
