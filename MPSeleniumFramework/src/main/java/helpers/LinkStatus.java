package helpers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import utility.FWUtils;

public class LinkStatus {
	private static Logger logger = LogManager.getLogger(FWUtils.class);
	public static List<String> brokenLinks;

	public static void Initialize() {
		brokenLinks = new ArrayList<String>();
	}
	
	public static String verifyLink(String link) {
		String message = "";
		try {
			URL url = new URL(link);
			HttpURLConnection urlConnect = (HttpURLConnection) url.openConnection();
			urlConnect.setConnectTimeout(5000);
			urlConnect.connect();
			if(urlConnect.getResponseCode()==200) {
				//Do nothing
			}else {
				message = link+" : "+urlConnect.getResponseMessage()+" : "+urlConnect.getResponseCode(); 
				brokenLinks.add(message);
				logger.error(brokenLinks.size()+" - "+message);
			}
		} catch (SocketTimeoutException e) {
			logger.warn("Found SocketTimeoutException at: "+ link);
		} catch (MalformedURLException e) {
			logger.warn("Found MalformedURLException at: "+ link);
		} catch (IOException e) {
			logger.warn("Found IOException at: "+ link);
		}
		return message;
	}
	
	public static List<String> getBrokenLinksStatus(){
		return brokenLinks;
	}
	
	public static int getBrokenLinksCount(){
		return brokenLinks.size();
	}
}
