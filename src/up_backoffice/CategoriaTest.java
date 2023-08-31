package up_backoffice;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import model.Categoria;
import model.vTest;
import util.Gerar;

class CategoriaTest {
	int controleTempo = 1000;
	boolean finaliza = true;
	public vTest nnum = new vTest();
	

	Categoria categoria = new Categoria(nnum); 
	WebDriver driver = LoginTest.IniciaLogin();

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
		if (!finaliza)
			return;
		driver.close();
		driver.quit();

	}

//	@Test
	void CadastroCategoria_Deve_Retornar_Sucesso() throws IOException {

		Gerar.Aguarde(controleTempo);
		driver.findElement(By.partialLinkText("Categoria")).click();
		Gerar.Aguarde(controleTempo);
		driver.findElement(By.partialLinkText("Novo")).click();

		driver.findElement(By.id("Categoria_Nome")).click();
		driver.findElement(By.id("Categoria_Nome")).sendKeys(categoria.Categoria);

		driver.findElement(By.xpath("//*[@id=\"placeholder\"]/div[1]/div[1]/button[1]")).click();

		Gerar.Aguarde(controleTempo * 2);

		String texto = driver.findElement(By.cssSelector("div.alert.alert-dismissible.fade.show.alert-success"))
				.getText();

		assertEquals(LoginTest.salvocomsucesso, texto);

	}

	@Test
	void CadastroSubCategoria_Deve_Retornar_Sucesso() throws IOException {

		CadastroCategoria_Deve_Retornar_Sucesso();
		Gerar.Aguarde(controleTempo);
		driver.findElement(By.partialLinkText("Subcategorias")).click();
		Gerar.Aguarde(controleTempo);
		driver.findElement(By.partialLinkText("Novo")).click();

		driver.findElement(By.id("SubCategoria_Nome")).click();
		driver.findElement(By.id("SubCategoria_Nome")).sendKeys(categoria.SubCategoria);

		new Select(driver.findElement(By.id("SubCategoria_TipoProduto"))).selectByVisibleText("Veículo");

		new Select(driver.findElement(By.id("SubCategoria_CategoriaId"))).selectByVisibleText(categoria.Categoria);

		driver.findElement(By.xpath("//*[@id=\"placeholder\"]/div[1]/div[1]/button[1]")).click();

		Gerar.Aguarde(controleTempo * 2);

		String texto = driver.findElement(By.cssSelector("div.alert.alert-dismissible.fade.show.alert-success"))
				.getText();

		assertEquals(LoginTest.salvocomsucesso, texto);

	}
}