package up_backoffice;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class Ofertas {

	@Test
	public void test() {

		var Login = new LoginTest();
		WebDriver driver = LoginTest.IniciaLogin();

		driver.findElement(By.linkText("Ofertas")).click();
		driver.findElement(By.linkText("Eventos")).click();
		driver.findElement(By.linkText("Novo")).click();
		driver.findElement(By.id("Evento_Nome")).click();
		driver.findElement(By.id("Evento_Nome")).clear();
		driver.findElement(By.id("Evento_Nome")).sendKeys("homolog-00000000");
		driver.findElement(By.id("Evento_CanalId")).click();
		new Select(driver.findElement(By.id("Evento_CanalId"))).selectByVisibleText("Privado (01)");
		driver.findElement(By.id("Evento_Tipo")).click();
		new Select(driver.findElement(By.id("Evento_Tipo"))).selectByVisibleText("Autom�tico");

	}

}
