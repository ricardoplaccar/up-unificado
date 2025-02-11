package Controle;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;

import model.DataEvento;
import model.vTest;

public class LeilaoChromeTest extends BaseLeilaoChrome {

	@Test
	public void HastaCriaLeilao_Eletronico_Chrome() throws Exception {

		var test = new vTest(1);

		var processo = test.GeraProcesso();

		var driver = LoginChrome(urlHasta);

		SelecioneProcesso(driver, processo);

		var ProcessoNovo = Valida_Processo(driver);

		if (ProcessoNovo) {
			HastaPreencheProcesso(driver, test);
		}

		driver.findElement(By.id("Holder_btnCriarLeilao")).click();

		Aguarde(2000);
		// Troca de Aha
		ArrayList<String> newTb = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(newTb.get(1));
		Aguarde(500);
		HastaPreencheLeilao(driver, test);
		PreecheBemImovelChrome(driver, test);

		HastaLotearBemEdge(driver, test);

	}

	protected void HastaLotearBemEdge(ChromeDriver driver, vTest test) throws FileNotFoundException, IOException {

		driver.get("https://controleh.vipleiloes.com.br");

		Aguarde(8000);

		var nulote = 1;

		var listaBens = LeiaListaCodigoProduto(test.Leilao);
		var leilao = test.LeiaControleLeilao();

		System.out.println();
		if (listaBens.size() < 1) {
			System.out.println("Sem Lotes");
			Assert.fail("Sem Lotes");
		}

		for (String bem : listaBens) {
			System.out.println("Codigo controle: " + bem);

		}

		driver.findElement(By.xpath("//*[@id=\"UserMenu_MyAccordion\"]/div[11]/li")).click();
		Aguarde(1000);
		driver.findElement(By.partialLinkText("LOTEAR")).click();
		Aguarde(500);

		driver.findElement(By.id("ContentPlaceHolder1_txtEdital")).sendKeys("TST" + leilao);
		Aguarde(250);
		driver.findElement(By.id("ContentPlaceHolder1_btnEdital")).click();
		Aguarde(1500);

		for (String bem : listaBens) {

			driver.findElement(By.id("ContentPlaceHolder1_txtLotear")).clear();
			driver.findElement(By.id("ContentPlaceHolder1_txtLotear")).sendKeys(bem);

			driver.findElement(By.id("ContentPlaceHolder1_btnLotear")).click();
			Aguarde(1500);

			driver.findElement(By.id("ContentPlaceHolder1_grdEstoque_txtLote_0")).sendKeys(String.format("%d", nulote));

			Aguarde(250);
			driver.findElement(By.id("ContentPlaceHolder1_txtIncrementoMinimo")).clear();
			driver.findElement(By.id("ContentPlaceHolder1_txtIncrementoMinimo")).sendKeys("10000");
			Aguarde(250);

			driver.findElement(By.id("ContentPlaceHolder1_chkIgual")).click();
			Aguarde(250);

			driver.findElement(By.id("ContentPlaceHolder1_btnLoteando")).click();
			Aguarde(1500);

			nulote++;
		}

	}

	@Test
	void CadastrarNovoLeilaoVeiculo() throws Exception {
		var test = new vTest();
		ChromeDriver driver = LoginChrome(urlHasta);
		System.out.println("Iniciando Processo");

//		CadastrarNovoProcesso(driver, test);

		HastaNovoLeilao(driver, test);
		PreecheSegmento(driver, "Veículos");

		SalvarEventoChrome(driver); // Salva e entra em modo Update

		EnviarDocumentoEvento(driver, 0, test);
		EnviarDocumentoEvento(driver, 1, test);
		EnviarDocumentoEvento(driver, 2, test);
		EnviarDocumentoEvento(driver, 3, test);

		EnviaImagemEvento(driver, 0, test);
//		EnviaImagemEvento(driver, 1, test);

		SalvarEventoChrome(driver);
		test.SalvaControleLeilao();
		// lotes /Bem/Bens

	}

	@Test
	public void Eletronico_CadastrarNovoProcesso() throws Exception {
		var test = new vTest(1);
		ChromeDriver driver = LoginChrome(urlHasta);
		System.out.println("Iniciando Processo");

		test.GeraProcesso();
		HastaNovoProcessoChrome(driver, test);

		// HastaNovoLeilao(driver, test);

		// PreecheSegmento(driver, "Imóveis");

		SalvarEventoChrome(driver); // Salva e entra em modo Update
		/*
		 * EnviarDocumentoEvento(driver, 0, test); EnviarDocumentoEvento(driver, 1,
		 * test); EnviarDocumentoEvento(driver, 2, test); EnviarDocumentoEvento(driver,
		 * 3, test);
		 * 
		 * EnviaImagemEvento(driver, 0, test); // EnviaImagemEvento(driver, 1, test);
		 * 
		 * SalvarEventoChrome(driver); test.SalvaControleLeilao(); // lotes /Bem/Bens
		 */
	}

//	@Test
	public void HastaCadastrarNovoProcesso_Imovel() throws Exception {
		var test = new vTest(1);
		ChromeDriver driver = LoginChrome(urlHasta);
		System.out.println("Iniciando Processo");

		SalvarEventoChrome(driver); // Salva e entra em modo Update
		EnviarDocumentoEvento(driver, 0, test);
		EnviarDocumentoEvento(driver, 1, test);
		EnviarDocumentoEvento(driver, 2, test);
		EnviarDocumentoEvento(driver, 3, test);
		EnviaImagemEvento(driver, 0, test);
		SalvarEventoChrome(driver);
		test.SalvaControleLeilao();
		// lotes /Bem/Bens

	}

	@Test
	public void HastaCadastrarNovoBemVeiculo() throws Exception {
		var test = new vTest(1);
		ChromeDriver driver = LoginChrome(urlHasta);
		AddBemVeiculoChrome(driver, test);
		// LotearBem(driver);

	}

	@Test
	public void HastaCadastrarNovo_Processo_NovoBemImovel() throws Exception {
		var test = new vTest(1);
		ChromeDriver driver = LoginChrome(urlHasta);
		System.out.println("Cadastrando Novo Processo");
		test.GeraProcesso();
		HastaNovoProcessoChrome(driver, test);
		PreecheBemImovelChrome(driver, test);
		// LotearBem(driver);

	}

	@Test
	public void HastaCadastrar_NovoBemImovel() throws Exception {
		var test = new vTest(1);
		ChromeDriver driver = LoginChrome(urlHasta);
		PreecheBemImovelChrome(driver, test);

		// LotearBem(driver);

	}

	@Test
	public void HastaExporta_Codigo_Produto() throws Exception {
		var test = new vTest(1);
		ChromeDriver driver = LoginChrome(url_controle_barramento);
		ExportaEstoqueChrome(driver, test);

		// LotearBem(driver);

	}

	public void ExportaEstoqueChrome(ChromeDriver driver, vTest test) {
		// TODO Auto-generated method stu
		Aguarde(3000);
		var listaCodigoProdutos = test.LeiaListaCodigoProduto(test.Leilao);

		Aguarde(1000);

		int tam = listaCodigoProdutos.size();
		int x = 1;

		for (String codigo : listaCodigoProdutos)

		{

			System.out.printf("Codigo %d/%d \n\r", x, tam);
			Select dropdown = new Select(
					driver.findElement(By.xpath("/html/body/div[1]/main/article/div[2]/div/form/div/div[1]/select")));

			dropdown.selectByIndex(3);

			driver.findElement(By.id("Campo")).clear();
			driver.findElement(By.id("Campo")).sendKeys(codigo);
			Aguarde(500);
			driver.findElement(By.xpath("/html/body/div[1]/main/article/div[2]/div/form/div/div[5]/div/button"))
					.click();

			Aguarde(500);
			driver.findElement(By.partialLinkText(codigo)).click();
			Aguarde(1000);

			driver.findElement(By.xpath("/html/body/div[1]/main/article/div[2]/div[1]/div/div/div/button")).click();
			Aguarde(1000);

			driver.findElement(By.xpath("/html/body/div[1]/main/article/div[2]/div[1]/div/div/div/ul/li[2]/a")).click();
			Aguarde(1000);

			driver.findElement(By.xpath("/html/body/div[1]/main/article/div[1]/div/div[1]/h3/a")).click();
			Aguarde(10000);
			x++;
		} // Código a ser executado

	}

}
