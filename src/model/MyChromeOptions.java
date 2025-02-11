package model;

import org.openqa.selenium.chrome.ChromeOptions;

public class MyChromeOptions {
	public ChromeOptions getOptions() {
		ChromeOptions options = new ChromeOptions();

		options = new ChromeOptions();
//options.addArguments("--headless");

		// Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe /T");
		// System.setProperty("webdriver.chrome.driver",
		// "c:\\Webdriver\\chromedriver.exe");
 var userDataDir ="C:\\Webdriver\\UserDados";
 var profileName = "C:\\Webdriver\\UserPerfil"; 
		options.addArguments("--start-maximized");
//options.addArguments("--disable-gpu");
//options.addArguments("--window-size=1366x768");
		options.addArguments("--disable-application-cache");
		options.addArguments("--disable-infobars");
		options.addArguments("--no-sandbox");
		// options.addArguments("--hide-scrollbars");
		options.addArguments("--enable-logging");
		options.addArguments("--log-level=3");
		options.addArguments("--single-process");
		options.addArguments("--ignore-certificate-errors");
		options.addArguments("--homedir=/tmp");
		options.addArguments("user-data-dir=" + userDataDir);
	//	options.addArguments("profile-directory=" + profileName);
		options.setBinary("C:\\Webdriver\\chrome-win64\\chrome.exe");
		System.setProperty("webdriver.chrome.driver", "C:\\Webdriver\\chromedriver-win64\\chromedriver.exe");
		
		
		
		
		
		return options;
	}
}
