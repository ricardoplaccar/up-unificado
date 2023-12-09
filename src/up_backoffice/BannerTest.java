package up_backoffice;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import model.Banner;
import model.DataEvento;
import model.vTest;
import util.Gerar;

class BannerTest {

	int controleTempo = 1000;
	vTest test = new vTest(0); 

	@Test
	void CadastrarBanner_Deve_Retornar_sucesso() {
	
		
		var bn = new Banner(test);
		     WebDriver driver = LoginTest.IniciaLogin();
			driver.findElement(By.linkText("Banners")).click();
			Gerar.Aguarde(controleTempo);
			driver.findElement(By.partialLinkText("Novo")).click();
			driver.findElement(By.id("Banner_Titulo")).sendKeys(bn.Nome);
			driver.findElement(By.id("Banner_NovaImagem")).sendKeys(bn.Imagem);
			Gerar.Aguarde(controleTempo);
			driver.findElement(By.xpath("/html/body/div/div[3]/form/div/div[3]/div/div/div[4]/div/div/div[2]/label/input")).sendKeys(bn.ImagemCelular);
			Gerar.Aguarde(controleTempo);
			
			DataEvento dataEvento = new DataEvento();
			PreencheData(driver,"Banner_InicioExibicaoEm",dataEvento.HoraExibir);
			driver.findElement(By.xpath("//*[@id=\"placeholder\"]/div[1]/div[1]/button[1]")).click();
		
		
	}

	private void PreencheData(WebDriver driver, String campoid, String valor) {

		var element = driver.findElement(By.id(campoid));
		element.click();
		var tam = valor.length() + 1;
		var index = 1;
		for (int x = 0; x < tam - 1; x++) {
			var este = new Actions(driver);
			var parte = valor.substring(x, index);
			index++;
			if (x == 8) {
				new Actions(driver).keyDown(Keys.ARROW_RIGHT).perform();

			}
			este.sendKeys(parte).build().perform();
		}

	}
	
}
