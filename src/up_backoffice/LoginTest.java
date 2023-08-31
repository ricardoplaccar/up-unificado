package up_backoffice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class LoginTest {

//	public static String url_site = "https://localhost:1476/conta/entrar?ReturnUrl=%2F";
	public static String url_site = "https://up-leilaovip-backoffice-hom.azurewebsites.net/conta/entrar?ReturnUrl=%2F";
//	
	public static final String salvocomsucesso = "Salvo com sucesso.";
	
	public static WebDriver IniciaLogin() {
		

		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		WebDriver driver = new ChromeDriver(options);

		// driver.manage().timeouts().implicitlyWait(20, null);
		driver.manage().deleteAllCookies();
		driver.get(url_site);
		driver.findElement(By.id("Login_Login")).clear();
		driver.findElement(By.id("Login_Login")).sendKeys("ricsistemas@gmail.com");
		driver.findElement(By.id("Login_Senha")).clear();
	//	driver.findElement(By.id("Login_Senha")).sendKeys("Up123456");
		driver.findElement(By.id("Login_Senha")).sendKeys("Ra870312");
		
		driver.findElement(By.cssSelector("i.fa-solid.fa-right-to-bracket")).click();
		return driver;

	}
	/*
	 * public static ChromeDriver IniciaSite() { /* var driver = new
	 * ChromeDriver(Endpoint.LocalDriverTests);
	 * driver.manage().timeouts().ImplicitWait = TimeSpan.FromSeconds(20);
	 * driver.manage().Cookies.DeleteAllCookies();
	 * driver.navigate().GoToUrl(Endpoint.UrlSiteHomologacao);
	 * driver.manage().Window.Maximize();
	 * driver.findElement(By.cssSelector("button.cookies-save")).click();
	 *
	 * return null; }
	 */
}
