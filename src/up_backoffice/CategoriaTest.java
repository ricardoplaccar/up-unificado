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
import model.ProdutoTipo;
import model.vTest;
import util.Gerar;

class CategoriaTest {
	int controleTempo = 1000;
	boolean finaliza = false;
	public vTest ntest = new vTest();

	
	WebDriver driver = LoginTest.IniciaLogin();

	@AfterEach
	void tearDown() throws Exception {
		if (!finaliza)
			return;
		driver.close();
		driver.quit();

	}

	@Test
	void CadastroCategoria_Imovel_Deve_Retornar_Sucesso() {

		var cat = new Categoria(ntest, ProdutoTipo.Imovel);
		CadastroCategoriaGeral(cat);
	}

	@Test
	void CadastroCategoria_Veiculo_Deve_Retornar_Sucesso() {

		var cat = new Categoria(ntest, ProdutoTipo.Veiculo);
		CadastroCategoriaGeral(cat);

	}



	@Test
	void CadastroSubCategoria_Imovel_Deve_Retornar_Sucesso() {
		var cat = new Categoria(ntest, ProdutoTipo.Imovel,0);
		CadastroSubCategoriaGeral(cat);
	
	}

	@Test
	void CadastroSubCategoria_Veiculo_Deve_Retornar_Sucesso() {
		var cat = new Categoria(ntest, ProdutoTipo.Veiculo,0);
		CadastroSubCategoriaGeral(cat);
	
	}

	private void CadastroCategoriaGeral(Categoria cat) {
		Gerar.Aguarde(controleTempo);
		driver.findElement(By.partialLinkText("Categoria")).click();
		Gerar.Aguarde(controleTempo);
		driver.findElement(By.partialLinkText("Novo")).click();
		Gerar.Aguarde(controleTempo);
		driver.findElement(By.id("Categoria_Nome")).click();
		driver.findElement(By.id("Categoria_Nome")).sendKeys(cat.Categoria);
		driver.findElement(By.xpath("//*[@id=\"placeholder\"]/div[1]/div[1]/button[1]")).click();
		Gerar.Aguarde(controleTempo * 2);
		String texto = driver.findElement(By.cssSelector("div.alert.alert-dismissible.fade.show.alert-success"))
				.getText();
		assertEquals(LoginTest.salvocomsucesso, texto);

	}	
	
	private void CadastroSubCategoriaGeral(Categoria cat) {
		Gerar.Aguarde(controleTempo);
		driver.findElement(By.partialLinkText("Subcategorias")).click();
		Gerar.Aguarde(controleTempo);
		driver.findElement(By.partialLinkText("Novo")).click();
		Gerar.Aguarde(controleTempo);
		driver.findElement(By.id("SubCategoria_Nome")).click();
		Gerar.Aguarde(controleTempo);
		driver.findElement(By.id("SubCategoria_Nome")).sendKeys(cat.SubCategoria);
		Gerar.Aguarde(controleTempo);
		new Select(driver.findElement(By.id("SubCategoria_CategoriaId"))).selectByVisibleText(cat.Categoria);
		driver.findElement(By.xpath("//*[@id=\"placeholder\"]/div[1]/div[1]/button[1]")).click();
		Gerar.Aguarde(controleTempo * 2);
		String texto = driver.findElement(By.cssSelector("div.alert.alert-dismissible.fade.show.alert-success"))
				.getText();
		assertEquals(LoginTest.salvocomsucesso, texto);
	}

}