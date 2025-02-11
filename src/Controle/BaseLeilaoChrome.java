package Controle;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import mock.EnderecoMock;
import mock.ImovelMock;
import mock.VeiculoMock;
import model.Categoria;
import model.Comitente;
import model.DataEvento;
import model.Foto;
import model.FotoTipo;
import model.Login;
import model.Pedido;
import model.vTest;

public class BaseLeilaoChrome extends Login {

	protected void HastaPreencheLeilao(ChromeDriver driver, vTest test) throws Exception {

		var DataEv = new DataEvento(1);

		driver.findElement(By.id("Holder_txt1DataLeilao")).sendKeys(DataEv.InicioDisputaReal);
		driver.findElement(By.id("Holder_txt1HoraLeilao")).sendKeys(SeparaHora(DataEv.InicioDisputaReal));

		driver.findElement(By.id("select2-Holder_drp1LeiloeiroSelect-container")).click();
		Aguarde(500);
		var Aux = "/html/body/span/span/span[1]/input";

		driver.findElement(By.xpath(Aux)).sendKeys("Erico");
		Aguarde(500);

		driver.findElement(By.xpath(Aux)).sendKeys(Keys.RETURN);
		Aguarde(250);

		driver.findElement(By.id("Holder_txt1Titulo")).sendKeys("Barramento" + test.Desc);
		Aguarde(250);

		driver.findElement(By.id("Holder_txt1EnderecoLeilao"))
				.sendKeys("FÓRUM LOCAL | Endereço: RUA CORONEL PATROCÍNIO");
		Aguarde(1000);
		driver.findElement(By.id("Holder_txt1EnderecoLeilao")).sendKeys(Keys.RETURN);
		Aguarde(250);
		new Select(driver.findElement(By.id("Holder_drp1Tipo"))).selectByVisibleText("Eletronico");
		new Select(driver.findElement(By.id("Holder_drp1Praca"))).selectByIndex(1);
		Aguarde(250);

		driver.findElement(By.id("Holder_dv_chkDestaque")).click();
		Aguarde(250);
		driver.findElement(By.id("xpDataleilao")).click();
		Aguarde(1500);
		driver.findElement(By.id("Holder_txt1NomeGrupo")).sendKeys("Imóveis");

		Aguarde(250);

		driver.findElement(By.id("Holder_txt1DescricaoDocumento")).sendKeys("Descrição Geral: " + test.Desc);

		HastaSalvaLeilao(driver);

		EnviarEditalControleChrome(driver);

		HastaSalvaLeilao(driver);
		/*
		 * driver.findElement(By.id("Holder_txt3DocumentoDescricao")).sendKeys("Edital")
		 * ; Aguarde(250);
		 * 
		 * driver.findElement(By.id("Holder_btn3DocumentoEnvia")).click();
		 * Aguarde(1000);
		 */

		inserirCapa(driver, FotoTipo.capaControle, test.Num);

		HastaSalvaLeilao(driver);
		test.SalvaControleLeilao();

	}

	protected boolean Valida_Processo(ChromeDriver driver) {
		var Comitente = driver.findElement(By.id("Holder_txt1Comitente")).getAttribute("value");
		Aguarde(1000);
		return (Comitente.length() < 1);

	}

	protected void EnviaImagemEvento(ChromeDriver driver, int i, vTest test) throws Exception {

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

	protected void SelecioneProcesso(ChromeDriver driver, String cprocesso) {
		// TODO Auto-generated method stub
		driver.findElement(By.partialLinkText("Processo")).click();
		Aguarde(250);
		driver.findElement(By.partialLinkText("Cadastro")).click();
		Aguarde(250);

		System.out.println("Seleciona Processo");

		driver.findElement(By.id("Holder_txt1NumeroProcesso")).sendKeys(cprocesso);
		Aguarde(200);
		driver.findElement(By.id("Holder_txt1Comitente")).click();
		Aguarde(500);

	}

	protected void EnviarDocumentoEvento(ChromeDriver driver, int i, vTest test) throws Exception {

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

	void SalvarEventoChrome(ChromeDriver driver) {
		driver.findElement(By.id("Holder_btnSalvar")).click();
		Aguarde(1000 * 4);
	}

	protected void PreecheSegmento(ChromeDriver driver, String str) {

		driver.findElement(By.id("Holder_txt1NomeGrupo")).sendKeys(str);

	}

	protected void EnviarDocumentoBemChrome(ChromeDriver driver, int i) {

		new Select(driver.findElement(By.id("Holder_drp3DocumentoCategoriaCod")))
				.selectByVisibleText(DescricaoArquivoBens[i]);
		driver.findElement(By.xpath("//*[@id=\"filestyle-0\"]")).sendKeys(DocumentoArquivoBens[i]);
		Aguarde(250);
		driver.findElement(By.id("Holder_txt3DocumentoDescricao")).clear();
		Aguarde(250);
		driver.findElement(By.id("Holder_btn3DocumentoEnvia")).click();
		Aguarde(5000);

	}

	protected void EnviarEditalControleChrome(ChromeDriver driver) {

		new Select(driver.findElement(By.id("Holder_drp3DocumentoCategoriaCod")))
				.selectByVisibleText(DescricaoArquivoEvento[0]);
		driver.findElement(By.id("filestyle-0")).sendKeys(DocumentoArquivoEvento[0]);
		Aguarde(250);
		driver.findElement(By.id("Holder_txt3DocumentoDescricao")).clear();
		driver.findElement(By.id("Holder_txt3DocumentoDescricao")).sendKeys(DescricaoArquivoEvento[0]);
		Aguarde(250);

		driver.findElement(By.id("Holder_btn3DocumentoEnvia")).click();
		Aguarde(5000);

	}

	protected void HastaNovoLeilao(ChromeDriver driver, vTest test) {
		// TODO Auto-generated method stub

		var DataEv = new DataEvento(03);
		var processo = test.GeraProcesso();

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
		Aguarde(250);

		driver.findElement(By.id("Holder_txt1EnderecoLeilao"))
				.sendKeys("FÓRUM LOCAL | Endereço: RUA CORONEL PATROCÍNIO");
		Aguarde(1000);
		driver.findElement(By.id("Holder_txt1EnderecoLeilao")).sendKeys(Keys.RETURN);
		Aguarde(250);
		new Select(driver.findElement(By.id("Holder_drp1Tipo"))).selectByVisibleText("Eletronico");
		new Select(driver.findElement(By.id("Holder_drp1Praca"))).selectByIndex(1);
		Aguarde(250);

		driver.findElement(By.id("Holder_dv_chkDestaque")).click();
		Aguarde(250);
		driver.findElement(By.id("xpDataleilao")).click();
		Aguarde(1500);
		driver.findElement(By.id("Holder_txt1NomeGrupo")).clear();
		// driver.findElement(By.id("Holder_txt1URLredirecionamento")).sendKeys("#");

		// driver.findElement(By.id("Holder_txt1NomeGrupo")).sendKeys("Imóveis");
		// Segmento

		test.SalvaControleProcesso(processo);

	}

	protected void HastaSalvaLeilao(ChromeDriver driver) {

		driver.findElement(By.id("Holder_btnSalvar")).click();
		Aguarde(3000);

	}

	protected void HastaNovoProcessoChrome(ChromeDriver driver, vTest test) throws Exception {

		driver.findElement(By.partialLinkText("Processo")).click();
		Aguarde(500);
		driver.findElement(By.partialLinkText("Cadastro")).click();
		Aguarde(500);
		HastaPreencheProcesso(driver, test);

	}

	protected void HastaPreencheProcesso(ChromeDriver driver, vTest test) {
		// TODO Auto-generated method stub
		var Comit = new Comitente(test, 2);
		// var DataEv = new DataEvento(1);
		System.out.println("Preenche processo");

		Aguarde(500);

		var este = driver.findElement(By.id("Holder_txt1NumeroProcesso")).getAttribute("Value");

		if (este == null) {
			// driver.findElement(By.id("Holder_txt1NumeroProcesso")).clear();
			driver.findElement(By.id("Holder_txt1NumeroProcesso")).sendKeys(test.processo);
			Aguarde(500);

		}

//		driver.findElement(By.id("Holder_txt1NumeroProcesso")).clear();
//		driver.findElement(By.id("Holder_txt1NumeroProcesso")).sendKeys(test.processo);
//		Aguarde(500);

		driver.findElement(By.id("Holder_txt1Comitente")).click();

		System.out.println("Comitente:" + Comit.Nome);
		driver.findElement(By.id("Holder_txt1Comitente")).clear();
		driver.findElement(By.id("Holder_txt1Comitente")).sendKeys(Comit.Nome);
		Aguarde(1000);
		driver.findElement(By.id("Holder_txt1Comitente")).sendKeys(Keys.RETURN);

		Aguarde(250);
// Novo processo não preenche data
//		driver.findElement(By.id("Holder_txt1DataLeilao")).sendKeys(DataEv.InicioDisputaReal);
//		driver.findElement(By.id("Holder_txt1HoraLeilao")).sendKeys(SeparaHora(DataEv.InicioDisputaReal));

		new Select(driver.findElement(By.id("Holder_drp1Estado"))).selectByVisibleText("PR");
		Aguarde(500);
		new Select(driver.findElement(By.id("Holder_drp1Cidade"))).selectByVisibleText("CURITIBA");
		Aguarde(250);

		new Select(driver.findElement(By.id("Holder_drp1TipoAcao"))).selectByValue("1379");
		Aguarde(250);

		new Select(driver.findElement(By.id("Holder_drp1Juizo"))).selectByValue("1383");
		Aguarde(250);

		new Select(driver.findElement(By.id("Holder_drp1Vara"))).selectByValue("1394");
		Aguarde(250);

		driver.findElement(By.id("Holder_txt1QTDlotes")).sendKeys("1");

		new Select(driver.findElement(By.id("Holder_drp1FilialCod"))).selectByValue("189993");
		Aguarde(250);

		driver.findElement(By.id("Holder_txt1URLprocesso"))
				.sendKeys("https://www3.tjrj.jus.br/consultaprocessual/#/consultapublica#porNumero");

		Aguarde(TestControleTempo / 4);
		driver.findElement(By.id("Holder_btnSalvar")).click();

		Aguarde(1000);

		test.SalvaControleProcesso(test.processo);

	}

	protected void PreecheBemImovelChrome(ChromeDriver driver, vTest test) throws Exception {
		// TODO Auto-generated method stub

		var esteprocesso = test.LeiaControleProcesso();
		driver.findElement(By.partialLinkText("Bens")).click();
		Aguarde(TestControleTempo / 4);

		for (int x = 1; x <= loteImoveisQtd; x++) {
			System.out
					.println(" => ----------------------------------------------------------------------------------");
			System.out.println(String.format("  =>Add Produto %d/%d", x, loteImoveisQtd));

			var im = new ImovelMock(test);
			var endereco = new EnderecoMock(test, 11);

			driver.findElement(By.partialLinkText("Cadastro")).click();
			Aguarde(250);
			System.out.println("Preenchendo Processo");

			driver.findElement(By.id("Holder_txt1NumeroProcesso")).sendKeys(esteprocesso);
			new Select(driver.findElement(By.id("Holder_drp1FilialCod"))).selectByVisibleText("HASTAVIP");
			Aguarde(250);

			var str = MaskFloat(im.Avaliado);

			driver.findElement(By.id("Holder_txt1Avaliacao")).sendKeys(str);
			System.out.println("Categoria:" + im.Categoria.Categoria.toUpperCase());

			new Select(driver.findElement(By.id("Holder_drp1CategoriaCod")))
					.selectByVisibleText(im.Categoria.Categoria.toUpperCase());
			Aguarde(250);

			System.out.println("Categoria:" + im.Categoria.SubCategoria.toUpperCase());

			ValidaSubCategoria(driver, im.Categoria);

			new Select(driver.findElement(By.id("Holder_drp1SubCategoriaCod")))
					.selectByVisibleText(im.Categoria.SubCategoria.toUpperCase());
			Aguarde(250);

			driver.findElement(By.id("Holder_txt1Titulo")).sendKeys(im.Nome);
			driver.findElement(By.id("Holder_txt1Descricao")).sendKeys(im.Nome);

			driver.findElement(By.id("Holder_txt1CEP")).sendKeys(endereco.Cep);
			Aguarde(TestControleTempo * 2);

			driver.findElement(By.id("Holder_txt1Endereco")).click();

			Aguarde(TestControleTempo * 4);

			var CepExist = false;

			try {
				CepExist = driver.findElement(By.id("Holder_ctrEndereco_lblInformativo")).isDisplayed();

			} catch (Exception ex) {

			}

			if (!CepExist) {

				System.out.println("Endereco Vazio");

				driver.findElement(By.id("Holder_ctrEndereco_txt6Numero")).sendKeys("100");
				driver.findElement(By.id("Holder_ctrEndereco_txt6Endereco")).click();
				Aguarde(TestControleTempo * 4);

				CampoValido(driver, "Holder_ctrEndereco_txt6Endereco");
				CampoValido(driver, "Holder_ctrEndereco_txt6Bairro");
				// combo box
				ComboBoxValido(driver, "Holder_ctrEndereco_drp6EstadoCod");
				ComboBoxValido(driver, "Holder_ctrEndereco_drp6CidadeCod");
				// campo
				CampoValido(driver, "Holder_ctrEndereco_txt6Cidade");
				driver.findElement(By.id("Holder_ctrEndereco_btn6IncluirEndereco")).click();// salva endereço
				Aguarde(TestControleTempo * 4);

			} else {
				System.out.println("Passou endereço Completo");
			}
//		var span = driver.findElement(By.id("Holder_ctrEndereco_lblInformativo")).getAttribute("span");
//		System.out.println("Span Texto=>" + span);

			var ped = new Pedido("Imovel");

			ped.SetValor(im.LanceInicial);
			ped.SetAvaliado(im.Avaliado);

			Aguarde(TestControleTempo * 4);
			driver.findElement(By.id("xpDataleilao")).click();
			Aguarde(TestControleTempo * 5);

			driver.findElement(By.id("Holder_txt1ValorIncremento")).sendKeys(MaskFloat("1.000,00"));

			driver.findElement(By.id("Holder_txt1Praca1Minimo")).sendKeys(MaskFloat(ped.GetValor(0)));
			driver.findElement(By.id("Holder_txt1Praca2Percentual")).sendKeys(MaskFloat("0"));

			driver.findElement(By.id("Holder_txt1Praca2Minimo")).sendKeys(MaskFloat(ped.GetValor(1)));
			driver.findElement(By.id("Holder_txt1Praca2Percentual")).sendKeys(MaskFloat(ped.GetDesconto(1)));

			driver.findElement(By.id("Holder_txt1Praca3Minimo")).sendKeys(MaskFloat(ped.GetValor(2)));
			driver.findElement(By.id("Holder_txt1Praca3Percentual")).sendKeys(MaskFloat(ped.GetDesconto(2)));

			SalvarProduto(driver);
			Aguarde(TestControleTempo);

			var bem = driver.findElement(By.id("Holder_lblCodigo")).getText();
			bem = bem.substring(11);
			System.out.println("Item:" + bem);

			EnviarDocumentoBem(driver, 0, test); // laudo
			// EnviarDocumentoBem(driver, 1, test);//matricula
			// EnviarDocumentoBem(driver, 2, test);//Documento

			var ListaFoto = im.ListaFotos;
			inserirFotosBensTodos(driver, FotoTipo.capaControle, ListaFoto, test.Num);
			Aguarde(TestControleTempo);

			SalvarProduto(driver);
			Aguarde(1000);
			SalvaBens(test.Leilao, bem);

			SalvarProduto(driver);

		}

	}

	private void ComboBoxValido(final ChromeDriver driver, final String id) {

		var este = new Select(driver.findElement(By.id(id)));
		Aguarde(1000);
		if (!este.getOptions().isEmpty()) {
			este.selectByIndex(6);
			System.out.println("<<Combo Falou >> ==>" + id);

		}

	}

	private void CampoValido(ChromeDriver driver, String campoid) {
		var endTam = driver.findElement(By.id(campoid)).getText();

		if (endTam.length() < 3) {
			driver.findElement(By.id(campoid)).clear();
			driver.findElement(By.id(campoid)).sendKeys("Qualquer");
			System.out.println("Falou endereco=>" + campoid);
		}

	}

	private void ValidaSubCategoria(ChromeDriver driver, Categoria cat) {
		// TODO Auto-generated method stub
		var achei = false;
		List<WebElement> allOptions = driver.findElements(By.id("Holder_drp1SubCategoriaCod"));
		for (int i = 0; i < allOptions.size(); i++) {

			if (allOptions.get(i).getText().contains(cat.SubCategoria.toUpperCase())) {
				achei = true;
				System.out.println("Achou a categoria");
				break;
			}
		}
		if (!achei) {
			cat.SubCategoria = "Imóveis";
			System.out.println("   =>Não Achou:" + cat.SubCategoria.toUpperCase());
		}

	}

	protected void inserirFotosBensTodos(WebDriver driver, FotoTipo fototipo, ArrayList<Foto> ListaFoto, String num)
			throws Exception {
		var nfoto = 0;
//		StringBuffer lista= new StringBuffer();
		for (Foto item : ListaFoto) {
			nfoto++;
			var strDestino = redimensionaImg(item.local);

			if (nfoto == 1) {
				var foto = getCapa(fototipo, strDestino, num);
				driver.findElement(By.id("filestyle-2")).sendKeys(foto);
				Aguarde(TestControleTempo * 2);
				driver.findElement(By.id("Holder_btn3DocumentoEnvia2")).click();
				Aguarde(TestControleTempo * 2);

			} else {

				driver.findElement(By.id("filestyle-2")).sendKeys(strDestino);
				Aguarde(500);

			}

		}

		driver.findElement(By.id("Holder_btn3DocumentoEnvia2")).click();
		Aguarde(TestControleTempo * 2);

	}

	protected void AddBemVeiculoChrome(ChromeDriver driver, vTest test) throws Exception {

		driver.findElement(By.partialLinkText("Bens")).click();
		Aguarde(TestControleTempo / 4);

		for (int n = 1; n <= loteVeiculoQtd; n++) {

			System.out.println(String.format(" ======> Veiculo %d/%d ", n, loteVeiculoQtd));

			var vcMock = new VeiculoMock(test);
			var vc = vcMock.getVeiculo();
			var endereco = new EnderecoMock(test);

			driver.findElement(By.partialLinkText("Cadastro")).click();
			Aguarde(250);
			System.out.println("Preenchendo Processo");

			driver.findElement(By.id("Holder_txt1NumeroProcesso")).sendKeys(test.processo);
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

			if (endTam.length() < 50 && endTam.length() != 0) {

				System.out.println("tam=" + endTam.length());

				driver.findElement(By.id("Holder_ctrEndereco_txt6Numero")).sendKeys("100");

				Aguarde(TestControleTempo);

				String s = driver.findElement(By.id("Holder_ctrEndereco_txt6Endereco")).getAttribute("value");
				var x = s.length();
				if (x == 0) {
					driver.findElement(By.id("Holder_ctrEndereco_txt6Endereco")).sendKeys("Qualquer");
					driver.findElement(By.id("Holder_ctrEndereco_txt6Bairro")).sendKeys("Qualquer");

					new Select(driver.findElement(By.id("Holder_ctrEndereco_drp6EstadoCod"))).selectByIndex(2);
					Aguarde(TestControleTempo / 4);
					new Select(driver.findElement(By.id("Holder_ctrEndereco_drp6CidadeCod"))).selectByIndex(1);

					driver.findElement(By.id("Holder_ctrEndereco_txt6Cidade")).sendKeys("Qualquer");

					System.out.println("Falou endereco=>" + test.Desc);

				}

				driver.findElement(By.id("Holder_ctrEndereco_btn6IncluirEndereco")).click();// salva endereço

			}

			System.out.println("Passou tam =" + endTam.length());

//		var span = driver.findElement(By.id("Holder_ctrEndereco_lblInformativo")).getAttribute("span");
//		System.out.println("Span Texto=>" + span);

			var ped = new Pedido("Veículo");

			ped.SetValor(vc.LanceInicial);
			ped.SetAvaliado(vc.Avaliado);

			Aguarde(TestControleTempo * 4);
			driver.findElement(By.id("xpDataleilao")).click();
			Aguarde(TestControleTempo * 5);

			driver.findElement(By.id("Holder_txt1ValorIncremento")).sendKeys(MaskFloat(vc.Incremento));

			driver.findElement(By.id("Holder_txt1Praca1Minimo")).sendKeys(MaskFloat(ped.GetValor(0)));
			driver.findElement(By.id("Holder_txt1Praca2Percentual")).sendKeys(MaskFloat("0"));

			driver.findElement(By.id("Holder_txt1Praca2Minimo")).sendKeys(MaskFloat(ped.GetValor(1)));
			driver.findElement(By.id("Holder_txt1Praca2Percentual")).sendKeys(MaskFloat(ped.GetDesconto(1)));

			driver.findElement(By.id("Holder_txt1Praca3Minimo")).sendKeys(MaskFloat(ped.GetValor(2)));
			driver.findElement(By.id("Holder_txt1Praca3Percentual")).sendKeys(MaskFloat(ped.GetDesconto(2)));

			SalvarProduto(driver);
			Aguarde(TestControleTempo);

			var bem = driver.findElement(By.id("Holder_lblCodigo")).getText();
			SalvaBens(test.Leilao, bem);

			EnviarDocumentoBem(driver, 0, test);
			EnviarDocumentoBem(driver, 1, test);
			EnviarDocumentoBem(driver, 2, test);

			var ListaFoto = vc.ListFotos;
			inserirFotosBensTodos(driver, FotoTipo.capaControle, ListaFoto, test.Num);
			Aguarde(TestControleTempo);

			SalvarProduto(driver);

		}
	}

	protected void EnviarDocumentoBem(ChromeDriver driver, int i, vTest test) {

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

	protected void SalvarProduto(ChromeDriver driver) {
		// TODO Auto-generated method stub
		driver.findElement(By.id("Holder_btnSalvar")).click();

	}

	protected void inserirCapa(WebDriver driver, FotoTipo fototipo, String num) throws Exception {

		var foto = getCapa(fototipo, null, num);

		System.out.print("=>>foto Capa:" + foto);

		driver.findElement(By.id("filestyle-2")).sendKeys(foto);

		driver.findElement(By.id("Holder_btn3DocumentoEnvia2")).click();

	}

	protected void inserirFotosBens(WebDriver driver, FotoTipo fototipo, ArrayList<Foto> ListaFoto, String num)
			throws Exception {
		var nfoto = 0;
		String lista = "";

		for (Foto item : ListaFoto) {
			nfoto++;
			var strDestino = redimensionaImg(item.local);
			lista = lista + strDestino + ",";

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
		// System.out.print(lista);
	}

}
