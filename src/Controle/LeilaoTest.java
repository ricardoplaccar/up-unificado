package Controle;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import Mock.VeiculoMock;
import model.Constants;
import model.DataEvento;
import model.Endereco;
import model.Foto;
import model.FotoTipo;
import model.vTest;

class LeilaoTest extends Constants {

	@Test
	void CadastrarNovoLeilao() throws Exception {
		var test = new vTest();
		var DataEv = new DataEvento(03);

		ChromeDriver driver = IniciaLogin();

		driver.findElement(By.partialLinkText("Leilão")).click();
		Aguarde(TestControleTempo / 4);
		driver.findElement(By.partialLinkText("Cadastro")).click();

		Aguarde(TestControleTempo / 4);
		driver.findElement(By.id("Holder_txt1NumeroProcesso")).sendKeys(processo);
		new Select(driver.findElement(By.id("Holder_drp1Filial"))).selectByVisibleText("HASTAVIP");

		driver.findElement(By.id("Holder_txt1DataLeilao")).sendKeys(DataEv.InicioDisputaReal);
		driver.findElement(By.id("Holder_txt1HoraLeilao")).sendKeys(DataEv.HoraExibir);

		driver.findElement(By.xpath("//*[@id=\"cardDemo1\"]/div[2]/div[1]/div[1]/div[5]/span/span[1]")).click();
		Aguarde(250);

		driver.findElement(By.xpath("/html/body/span/span/span[1]/input")).sendKeys("Erico");
		Aguarde(250);
		driver.findElement(By.xpath("/html/body/span/span/span[1]/input")).sendKeys(Keys.RETURN);
		Aguarde(250);

		driver.findElement(By.id("Holder_txt1Titulo")).sendKeys("Barramento" + test.Desc);

		new Select(driver.findElement(By.id("Holder_drp1Tipo"))).selectByVisibleText("Automático");
		Aguarde(250);

		new Select(driver.findElement(By.id("Holder_drp1Tipo"))).selectByIndex(2);
		Aguarde(250);

		new Select(driver.findElement(By.id("Holder_drp1Praca"))).selectByIndex(1);
		Aguarde(250);

		driver.findElement(By.id("Holder_dv_chkDestaque")).click();
		Aguarde(250);

		new Select(driver.findElement(By.id("Holder_drpProposta"))).selectByVisibleText("LanceLivre");
		Aguarde(250);

		SalvarEvento(driver); // Salva e entra em modo Update

		EnviarDocumentoEvento(driver, 0, test);
		EnviarDocumentoEvento(driver, 1, test);
		EnviarDocumentoEvento(driver, 2, test);
		EnviarDocumentoEvento(driver, 3, test);

		EnviaImagemEvento(driver, 0, test);
		EnviaImagemEvento(driver, 1, test);

		SalvarEvento(driver);
		// lotes /Bem/Bens

	}

	@Test
	void CadastrarNovoBemVeiculo() throws Exception {

		if (num_reuso == 0) {
			Assert.fail("num_reuso = 0");
		}

		var test = new vTest(num_reuso);

		ChromeDriver driver = IniciaLogin();
		AddBemVeiculo(driver, test);

	}

	private void AddBemVeiculo(ChromeDriver driver, vTest test) throws Exception {

		var vc = new VeiculoMock(test);
		var endereco = new Endereco(test);

		driver.findElement(By.partialLinkText("Bens")).click();
		Aguarde(TestControleTempo / 4);
		driver.findElement(By.partialLinkText("Cadastro")).click();
		Aguarde(250);
		driver.findElement(By.id("Holder_txt1NumeroProcesso")).sendKeys(processo);
		new Select(driver.findElement(By.id("Holder_drp1FilialCod"))).selectByVisibleText("HASTAVIP");
		Aguarde(250);

		var str = MaskFloat(vc.Avaliado);

		driver.findElement(By.id("Holder_txt1Avaliacao")).sendKeys(str);

		new Select(driver.findElement(By.id("Holder_drp1CategoriaCod"))).selectByVisibleText("VEÍCULOS");
		Aguarde(500);
		new Select(driver.findElement(By.id("Holder_drp1SubCategoriaCod"))).selectByVisibleText("VEÍCULOS");
		Aguarde(500);

		driver.findElement(By.id("Holder_txt1Titulo")).sendKeys(vc.Nome);
		driver.findElement(By.id("Holder_txt1Descricao")).sendKeys(vc.Marca + " - " + vc.Modelo + " - " + vc.Nome);

		driver.findElement(By.id("Holder_txt1CEP")).sendKeys(endereco.Cep);
		Aguarde(TestControleTempo * 2);

		driver.findElement(By.id("Holder_txt1Endereco")).click();

		Aguarde(TestControleTempo * 4);

		var endTam = driver.findElement(By.id("Holder_txt1Endereco")).getText();


		  if (endTam.length() < 50 && endTam.length()!=0) {

			System.out.println("tam="+endTam.length() );

		  driver.findElement(By.id("Holder_ctrEndereco_txt6Numero")).sendKeys("100");

		  Aguarde(TestControleTempo);

		  String s =
		  driver.findElement(By.id("Holder_ctrEndereco_txt6Endereco")).getAttribute(
		  "value"); var x = s.length(); if (x == 0) {
		  driver.findElement(By.id("Holder_ctrEndereco_txt6Endereco")).sendKeys(
		  "Qualquer");
		  driver.findElement(By.id("Holder_ctrEndereco_txt6Bairro")).sendKeys(
		  "Qualquer");

		  new Select(driver.findElement(By.id("Holder_ctrEndereco_drp6EstadoCod"))).
		  selectByIndex(2); Aguarde(TestControleTempo / 4); new
		  Select(driver.findElement(By.id("Holder_ctrEndereco_drp6CidadeCod"))).
		  selectByIndex(1);

		  driver.findElement(By.id("Holder_ctrEndereco_txt6Cidade")).sendKeys(
		  "Qualquer");

		  System.out.println("Falou endereco=>" + test.Desc);

		  }

		  driver.findElement(By.id("Holder_ctrEndereco_btn6IncluirEndereco")).click();// salva endereço

		  }

			System.out.println("Passou tam="+endTam.length() );


//		var span = driver.findElement(By.id("Holder_ctrEndereco_lblInformativo")).getAttribute("span");
//		System.out.println("Span Texto=>" + span);

		Aguarde(TestControleTempo * 4);
		driver.findElement(By.id("xpDataleilao")).click();
		Aguarde(TestControleTempo * 5);

		var Incremento = MaskFloat(vc.Incremento);

		driver.findElement(By.id("Holder_txt1ValorIncremento")).sendKeys(Incremento);

		var LanceInicial = MaskFloat(vc.LanceInicial);

		driver.findElement(By.id("Holder_txt1Praca1Minimo")).sendKeys(LanceInicial);

		SalvarProduto(driver);

		Aguarde(TestControleTempo);

		EnviarDocumentoBem(driver, 0, test);
		EnviarDocumentoBem(driver, 1, test);
		EnviarDocumentoBem(driver, 2, test);

		var ListaFoto = vc.ListFotos;
		inserirFotosBens(driver, FotoTipo.capaControle, ListaFoto, test.Num);
		Aguarde(TestControleTempo);
		SalvarProduto(driver);

	}

	private String MaskFloat(String Value) {

		try {
			var avaliado = NumberFormat.getNumberInstance().parse(Value).doubleValue();

			DecimalFormat df = new DecimalFormat("####");
			return df.format(avaliado);

		} catch (

		ParseException e) {
			e.printStackTrace();
			return "0";
		}

	}

	private void EnviarDocumentoBem(ChromeDriver driver, int i, vTest test) {

		new Select(driver.findElement(By.id("Holder_drp3DocumentoCategoriaCod")))
				.selectByVisibleText(DescricaoArquivoBens[i]);
		driver.findElement(By.xpath("//*[@id=\"filestyle-0\"]")).sendKeys(DocumentoArquivoBens[i]);
		Aguarde(250);
		driver.findElement(By.id("Holder_txt3DocumentoDescricao")).clear();
		Aguarde(250);
		driver.findElement(By.id("Holder_txt3DocumentoDescricao")).sendKeys(DescricaoArquivoBens[i] + test.Desc);
		driver.findElement(By.id("Holder_btn3DocumentoEnvia")).click();
		Aguarde(5000);

	}

	private void SalvarProduto(ChromeDriver driver) {
		// TODO Auto-generated method stub
		driver.findElement(By.id("Holder_btnSalvar")).click();

	}

	private void inserirFotosBens(WebDriver driver, FotoTipo fototipo, ArrayList<Foto> ListaFoto, String num)
			throws Exception {
		var nfoto = 0;

		for (Foto item : ListaFoto) {
			nfoto++;
			var strDestino = redimensionaImg(item.local);

			if (nfoto == 1) {
				var foto = getCapa(fototipo, strDestino, num);
				driver.findElement(By.id("filestyle-2")).sendKeys(foto);

			} else {

				driver.findElement(By.id("filestyle-2")).sendKeys(strDestino);

			}
			Aguarde(TestControleTempo * 2);
			driver.findElement(By.id("Holder_btn3DocumentoEnvia2")).click();
			Aguarde(TestControleTempo * 2);

		}

	}

	private void EnviaImagemEvento(ChromeDriver driver, int i, vTest test) throws Exception {

		var foto = getCapa(FotoTipo.capaControle, ImagemArquivoEvento[i], test.Num);

		if (i != 0) {
			foto = ImagemArquivoEvento[i];
		}

		new Select(driver.findElement(By.id("Holder_drp3DocumentoCategoriaCod2")))
				.selectByVisibleText(ImagemDescricao[i]);
		Aguarde(TestControleTempo);

		driver.findElement(By.id("Holder_txt3DocumentoDescricao2")).clear();
		Aguarde(TestControleTempo / 4);
		driver.findElement(By.id("Holder_txt3DocumentoDescricao2")).sendKeys(ImagemDescricao[i] + test.Desc);
		Aguarde(TestControleTempo / 2);

		driver.findElement(By.id("filestyle-2")).sendKeys(foto);
		Aguarde(TestControleTempo);
		driver.findElement(By.id("Holder_btn3DocumentoEnvia2")).click();
		Aguarde(TestControleTempo * 2);

	}

	private void EnviarDocumentoEvento(ChromeDriver driver, int i, vTest test) throws Exception {

		new Select(driver.findElement(By.id("Holder_drp3DocumentoCategoriaCod")))
				.selectByVisibleText(DescricaoArquivoEvento[i]);
		driver.findElement(By.xpath("//*[@id=\"filestyle-0\"]")).sendKeys(DocumentoArquivoEvento[i]);
		Aguarde(250);
		driver.findElement(By.id("Holder_txt3DocumentoDescricao")).clear();
		Aguarde(250);
		driver.findElement(By.id("Holder_txt3DocumentoDescricao")).sendKeys(DescricaoArquivoEvento[i] + test.Desc);
		driver.findElement(By.id("Holder_btn3DocumentoEnvia")).click();
		Aguarde(5000);

	}

	void SalvarEvento(ChromeDriver driver) {
		driver.findElement(By.id("Holder_btnSalvar")).click();
		Aguarde(1000 * 4);
	}

	private ChromeDriver IniciaLogin() {

		// System.setProperty("<Path of the ChromeDriver>");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		var driver = new ChromeDriver(options);
		driver.manage().deleteAllCookies();
		driver.get(url_site_controle);
		Aguarde(1000);
		driver.findElement(By.id("Usuario")).click();
		driver.findElement(By.id("Usuario")).clear();
		driver.findElement(By.id("Usuario")).sendKeys("nickison.prestes");
		driver.findElement(By.id("Senha")).click();
		driver.findElement(By.id("Senha")).clear();
		driver.findElement(By.id("Senha")).sendKeys("Vip@123123");
		driver.findElement(By.id("btnLogar")).click();
		Aguarde(250);
		return driver;
	}
}
