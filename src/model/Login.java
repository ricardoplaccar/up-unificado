package model;

import java.util.Collections;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class Login extends Constants {

	protected ChromeDriver LoginChrome(String url_login) {

		var options = new MyEdgeOptions();

		var driver = new ChromeDriver(options.getOptions());

		// driver.manage().deleteAllCookies();
		driver.get(url_login);
		if (ValidaLogin(driver, url_login))

		{

			return driver;

		}

		AguardeEdge(driver, urlProxy);
		driver.findElement(By.id("inputEmail")).click();
		driver.findElement(By.id("inputEmail")).clear();
		driver.findElement(By.id("inputEmail")).sendKeys(User);
		driver.findElement(By.id("login-submit")).click();
		AguardeEdge(driver, url_login);
		return driver;
	}

	

	protected EdgeDriver LoginEdge(String url_login) {

		EdgeOptions options = new EdgeOptions();
//		System.setProperty("webdriver.edge.driver", "C:\\Webdriver\\EdgeDriver.exe");

//WebDriverManager manager = WebDriverManager.edgedriver();
//manager.config().setEdgeDriverVersion("84.0.522.49");
// manager.setup();
//options.setBinary("C:\\Program Files (x86)\\Microsoft\\Edge\\Application\\msedge.exe");

		options.addArguments("--start-maximized");
		options.addArguments("--log-level=3");
		options.setExperimentalOption("useAutomationExtension", false);
		options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));

		EdgeDriver driver = new EdgeDriver(options);
		driver.get(url_login);
				
		if (ValidaLogin(driver, urlHasta))

		{

			return driver;

		}
		
		
		
		AguardeEdge(driver, urlProxy);
		driver.findElement(By.id("inputEmail")).click();
		driver.findElement(By.id("inputEmail")).clear();
		driver.findElement(By.id("inputEmail")).sendKeys(User);
		driver.findElement(By.id("login-submit")).click();
		AguardeEdge(driver, url_login);
		return driver;

	}

}
