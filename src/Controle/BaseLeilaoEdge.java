package Controle;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;

import mock.EnderecoMock;
import mock.ImovelMock;
import mock.VeiculoMock;
import model.Comitente;
import model.DataEvento;
import model.Foto;
import model.FotoTipo;
import model.Login;
import model.Pedido;
import model.vTest;

public class BaseLeilaoEdge extends Login {


	protected void Vip_PreencheBemImovelEdge(EdgeDriver driver, vTest test) throws Exception {
		var DataEv = new DataEvento(1);

		System.out.println(String.format("==>Tipo=%d", test.getTipoEnvento()) );
		
		driver.findElement(By.partialLinkText("Estoque (novo)")).click();
		Aguarde(500);
		
		var Comit = new Comitente(test,1);
		
	  ZoomControle(driver);
		   
		for (int x = 1; x <= loteImoveisQtd; x++) {
			var im = new ImovelMock(test);
			var endereco = new EnderecoMock(test);

			System.out.println(String.format("Bens: %d/%d", x, loteImoveisQtd));

			driver.findElement(By.partialLinkText("Cadastro")).click();
			Aguarde(500);

			new Select(driver.findElement(By.id("Holder_drp1FilialCod"))).selectByIndex(1);
			Aguarde(500);
			System.out.println(" ==>Comitente:" + Comit.Nome);
			driver.findElement(By.id("Holder_txt1Comitente")).sendKeys(Comit.Nome);
			Aguarde(1000);
			driver.findElement(By.id("Holder_txt1Comitente")).sendKeys(Keys.RETURN);

			// var esteprocesso = test.LeiaControleProcesso();

			var str = MaskFloat(im.Avaliado);
			driver.findElement(By.id("Holder_txt1Avaliacao")).sendKeys(str);
			Aguarde(250);

			driver.findElement(By.id("Holder_txt1DataEntrada")).sendKeys(DataEv.EmBreve);

			System.out.println("Categoria:" + im.Categoria.Categoria);
			new Select(driver.findElement(By.id("Holder_drp1CategoriaCod")))
					.selectByVisibleText(im.Categoria.Categoria.toUpperCase());
			Aguarde(500);
			System.out.println("subCategoria:" + im.Categoria.SubCategoria);

			new Select(driver.findElement(By.id("Holder_drp1SubCategoriaCod")))
					.selectByVisibleText(im.Categoria.SubCategoria);

			Aguarde(500);

			driver.findElement(By.id("Holder_txt1Titulo")).sendKeys(im.Nome);
			Aguarde(250);

			driver.findElement(By.id("Holder_txt2ContratoImovel")).sendKeys(test.Num);

			new Select(driver.findElement(By.id("Holder_drp2SituacaoCod"))).selectByVisibleText("Desocupado");

			Aguarde(250);

			driver.findElement(By.id("Holder_txt2Descricao")).sendKeys("Descrição: " + im.Nome);
			Aguarde(250);

			driver.findElement(By.id("Holder_txt1MaisInformacoes")).sendKeys("MaisInformações: " + im.Nome);
			Aguarde(250);

			driver.findElement(By.id("Holder_txt1CEP")).sendKeys(endereco.Cep);
			Aguarde(TestControleTempo * 2);

			driver.findElement(By.id("Holder_txt1Endereco")).click();

			Aguarde(1000);

			if (!driver.findElement(By.id("Holder_ctrEndereco_lblInformativo")).isDisplayed()) {

				System.out.println("Cep Não encontrado");

				if (driver.findElement(By.id("Holder_ctrEndereco_dvEndereco")).isDisplayed()) {

					Aguarde(TestControleTempo);

					driver.findElement(By.id("Holder_ctrEndereco_txt6Titulo")).sendKeys("Qualquer");
					driver.findElement(By.id("Holder_ctrEndereco_txt6Endereco")).sendKeys("Qualquer");
					driver.findElement(By.id("Holder_ctrEndereco_txt6Numero")).sendKeys("100");
					driver.findElement(By.id("Holder_ctrEndereco_txt6Bairro")).sendKeys("Qualquer");

					new Select(driver.findElement(By.id("Holder_ctrEndereco_drp6EstadoCod"))).selectByIndex(2);
					Aguarde(TestControleTempo / 4);
					new Select(driver.findElement(By.id("Holder_ctrEndereco_drp6CidadeCod"))).selectByIndex(1);

					driver.findElement(By.id("Holder_ctrEndereco_txt6Cidade")).sendKeys("Qualquer");

					System.out.println("Falou endereco=>" + test.Desc);
					driver.findElement(By.id("Holder_ctrEndereco_btn6IncluirEndereco")).click();// salva endereço
					Aguarde(TestControleTempo);
					System.out.println("Fim endereço");
				}

			}

			System.out.println("Cadastra Imovel");
			var ped = new Pedido("Imovel");

			ped.SetValor(im.LanceInicial);
			ped.SetAvaliado(im.Avaliado);

			Aguarde(TestControleTempo * 4);

			if (!driver.findElement(By.id("pDataleilao")).isDisplayed()) {
				driver.findElement(By.id("xpDataleilao")).click();
				Aguarde(TestControleTempo * 5);
			}

			driver.findElement(By.id("Holder_txt1CondicaoPagamento")).sendKeys("á vista");
			driver.findElement(By.id("Holder_txt1Praca1Minimo")).sendKeys(MaskFloat(ped.GetValor(0)));
			driver.findElement(By.id("Holder_txt1Praca1Percentual")).sendKeys(MaskFloat("0"));
			driver.findElement(By.id("Holder_txt1Praca2Minimo")).sendKeys(MaskFloat(ped.GetValor(1)));
			driver.findElement(By.id("Holder_txt1Praca2Percentual")).sendKeys(MaskFloat(ped.GetDesconto(1)));

			Aguarde(500);

			SalvarProdutoEdge(driver);
			Aguarde(TestControleTempo);
			var bem = driver.findElement(By.id("Holder_lblCodigo")).getText();
			bem = bem.substring(11);
	
			System.out.println("Item:" + bem);
			EnviarDocumentoBemEdge(driver, 0, test);
			EnviarDocumentoBemEdge(driver, 1, test);
			EnviarDocumentoBemEdge(driver, 2, test);

			var ListaFoto = im.ListaFotos;
			inserirFotosBensTodos(driver, FotoTipo.capaControle, ListaFoto, test.Num);
			Aguarde(TestControleTempo);

			SalvarProdutoEdge(driver);
			Aguarde(1000);
			SalvaBens(test.Leilao,bem);
			

		}

	

	}
	
	protected void Vip_PreencheBemVeiculoEdge(EdgeDriver driver, vTest test) throws Exception {
		var DataEv = new DataEvento(1);
		
		System.out.println(String.format("==>Tipo=%d", test.getTipoEnvento())  );
		
		driver.findElement(By.partialLinkText("Estoque (novo)")).click();
		Aguarde(500);
		
		var Comit = new Comitente(test,1);
		
	//	ZoomControle(driver);
		
		for (int x = 1; x <= loteImoveisQtd; x++) {
			var vcMock = new VeiculoMock(test);
			var vc = vcMock.getVeiculo(); 
			
			var endereco = new EnderecoMock(test);
			
			System.out.println(" ==>Comitente:" + Comit.Nome);
						System.out.println(String.format("Bens: %d/%d", x, loteImoveisQtd));
			System.out.println("Veiculo ==>:" + vc.Nome);
			
			
			driver.findElement(By.partialLinkText("Cadastro")).click();
			Aguarde(500);
			
			new Select(driver.findElement(By.id("Holder_drp1FilialCod"))).selectByIndex(1);
			Aguarde(500);
			driver.findElement(By.id("Holder_txt1Comitente")).sendKeys(Comit.Nome);
			Aguarde(1000);
			driver.findElement(By.id("Holder_txt1Comitente")).sendKeys(Keys.RETURN);
			
			// var esteprocesso = test.LeiaControleProcesso();
			Aguarde(1000);
			
			var str = MaskFloat(vc.Avaliado);
			driver.findElement(By.id("Holder_txt1Avaliacao")).sendKeys(str);
			Aguarde(250);
			
			driver.findElement(By.id("Holder_txt1DataEntrada")).sendKeys(DataEv.EmBreve);
			
			vc.Categoria.Categoria ="Veiculo";
			vc.Categoria.SubCategoria = "VEÍCULO LEVE";
			
			
			
			System.out.println("Categoria:" + vc.Categoria.Categoria);
			new Select(driver.findElement(By.id("Holder_drp1CategoriaCod")))
			.selectByVisibleText(vc.Categoria.Categoria.toUpperCase());
			Aguarde(500);
			System.out.println("subCategoria:" + vc.Categoria.SubCategoria);
			
			new Select(driver.findElement(By.id("Holder_drp1SubCategoriaCod")))
			.selectByVisibleText(vc.Categoria.SubCategoria);
			
			Aguarde(500);
			
			driver.findElement(By.id("Holder_txt1Titulo")).sendKeys(vc.Nome);
			Aguarde(250);
	
				
			driver.findElement(By.id("Holder_txt1CEP")).sendKeys(endereco.Cep);
			Aguarde(TestControleTempo * 2);
			
			driver.findElement(By.id("Holder_txt1Endereco")).click();
			
			Aguarde(1000);
			
			if (!driver.findElement(By.id("Holder_ctrEndereco_lblInformativo")).isDisplayed()) {
				
				System.out.println("Cep Não encontrado");
				
				if (driver.findElement(By.id("Holder_ctrEndereco_dvEndereco")).isDisplayed()) {
					
					Aguarde(TestControleTempo);
					
					driver.findElement(By.id("Holder_ctrEndereco_txt6Titulo")).sendKeys("Qualquer");
					driver.findElement(By.id("Holder_ctrEndereco_txt6Endereco")).sendKeys("Qualquer");
					driver.findElement(By.id("Holder_ctrEndereco_txt6Numero")).sendKeys("100");
					driver.findElement(By.id("Holder_ctrEndereco_txt6Bairro")).sendKeys("Qualquer");
					
					new Select(driver.findElement(By.id("Holder_ctrEndereco_drp6EstadoCod"))).selectByIndex(2);
					Aguarde(TestControleTempo / 4);
					new Select(driver.findElement(By.id("Holder_ctrEndereco_drp6CidadeCod"))).selectByIndex(1);
					
					driver.findElement(By.id("Holder_ctrEndereco_txt6Cidade")).sendKeys("Qualquer");
					
					System.out.println("Falou endereco=>" + test.Desc);
					driver.findElement(By.id("Holder_ctrEndereco_btn6IncluirEndereco")).click();// salva endereço
					Aguarde(TestControleTempo);
					System.out.println("Fim endereço");
				}
				
			}
			
			System.out.println("Cadastra Veículo");
			var ped = new Pedido("Veículo");
			
			ped.SetValor(vc.LanceInicial);
			ped.SetAvaliado(vc.Avaliado);
			
			Aguarde(TestControleTempo * 4);
			
			if (!driver.findElement(By.id("pDataleilao")).isDisplayed()) {
				driver.findElement(By.id("xpDataleilao")).click();
				Aguarde(TestControleTempo * 5);
			}
			
			driver.findElement(By.id("Holder_txt1CondicaoPagamento")).sendKeys("á vista");
			driver.findElement(By.id("Holder_txt1Praca1Minimo")).sendKeys(MaskFloat(ped.GetValor(0)));
			driver.findElement(By.id("Holder_txt1Praca1Percentual")).sendKeys(MaskFloat("0"));
			driver.findElement(By.id("Holder_txt1Praca2Minimo")).sendKeys(MaskFloat(ped.GetValor(1)));
			driver.findElement(By.id("Holder_txt1Praca2Percentual")).sendKeys(MaskFloat(ped.GetDesconto(1)));
			
			Aguarde(500);
			
			SalvarProdutoEdge(driver);
			Aguarde(TestControleTempo);
			var bem = driver.findElement(By.id("Holder_lblCodigo")).getText();
			bem = bem.substring(11);
			
			System.out.println("Item:" + bem);
			EnviarDocumentoBemEdge(driver, 0, test);
			EnviarDocumentoBemEdge(driver, 1, test);
			EnviarDocumentoBemEdge(driver, 2, test);
			
			var ListaFoto = vc.ListFotos;
			inserirFotosBensTodos(driver, FotoTipo.capaControle, ListaFoto, test.Num);
			Aguarde(TestControleTempo);
			
			SalvarProdutoEdge(driver);
			Aguarde(1000);
			SalvaBens(test.Leilao,bem);
			
			
		}
		
		
		
	}
	

	private void ZoomControle(EdgeDriver driver) {
		// TODO Auto-generated method stub
		if (true) return ;
		// controle de zomm  
	    //    driver.executeScript("document.body.style.zoom = '0.6'");
	    //    Aguarde(TestControleTempo*2);		
	   // 	System.out.println(String.format(" Zoom aplicado -->"));	

			driver.executeScript("document.body.style.zoom = '60%'");
		    Aguarde(TestControleTempo*2);	
		    var sUrl = driver.getCurrentUrl();
			driver.get(sUrl);
		    Aguarde(TestControleTempo*2);	   
			   	
		
		
		
	}


	protected void EnviarDocumentoBemEdge(EdgeDriver driver, int i, vTest test) {

		new Select(driver.findElement(By.id("Holder_drp3DocumentoCategoriaCod")))
				.selectByVisibleText(DescricaoArquivoBens[i]);
		driver.findElement(By.xpath("//*[@id=\"filestyle-0\"]")).sendKeys(DocumentoArquivoBens[i]);
		Aguarde(250);
		driver.findElement(By.id("Holder_txt3DocumentoDescricao")).clear();
		Aguarde(250);
		// driver.findElement(By.id("Holder_txt3DocumentoDescricao")).sendKeys(DescricaoArquivoBens[i]
		// + test.Desc);
		driver.findElement(By.id("Holder_btn3DocumentoEnvia")).click();
		Aguarde(5000);

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
	protected void SalvarProdutoEdge(EdgeDriver driver) {
		// TODO Auto-generated method stub
		driver.findElement(By.id("Holder_btnSalvar")).click();

	}

	
	protected void PreencheVipleilaoEdge(EdgeDriver driver, vTest test) {
		var DataEv = new DataEvento(1);

		driver.findElement(By.partialLinkText("Leilão (Novo)")).click();
		Aguarde(500);
		driver.findElement(By.partialLinkText("Cadastro")).click();

		Aguarde(500);

		new Select(driver.findElement(By.id("Holder_drp1Filial"))).selectByValue("96097");
		Aguarde(500);

		driver.findElement(By.id("Holder_txt1Edital")).sendKeys("TST" + test.Num);

		driver.findElement(By.id("Holder_txt1DataLeilao")).sendKeys(DataEv.InicioDisputaReal);

		driver.findElement(By.id("Holder_txt1HoraLeilao")).sendKeys(SeparaHora(DataEv.InicioDisputaReal));

		new Select(driver.findElement(By.id("Holder_drp1Modelo"))).selectByValue("903");
		Aguarde(250);

		driver.findElement(By.id("select2-Holder_drp1LeiloeiroSelect-container")).click();
		Aguarde(500);

		var Aux = "/html/body/span/span/span[1]/input";

		driver.findElement(By.xpath(Aux)).sendKeys("Erico");
		Aguarde(500);

		driver.findElement(By.xpath(Aux)).sendKeys(Keys.RETURN);
		Aguarde(250);

		driver.findElement(By.id("Holder_txt1Titulo")).sendKeys("Barramento" + test.Desc);
		Aguarde(250);

		new Select(driver.findElement(By.id("Holder_drp1Tipo"))).selectByVisibleText("Eletronico");
		Aguarde(250);
		new Select(driver.findElement(By.id("Holder_drp1Praca"))).selectByIndex(1);
		Aguarde(1000);

		driver.findElement(By.id("Holder_txt1Comitente")).sendKeys("Banco Triangulo");
		Aguarde(500);

		driver.findElement(By.id("Holder_txt1Comitente")).sendKeys(Keys.RETURN);
		Aguarde(250);

		driver.findElement(By.id("Holder_dv_chkDestaque")).click();
		Aguarde(250);
		driver.findElement(By.id("xpDataleilao")).click();
		Aguarde(1500);
		driver.findElement(By.id("Holder_txt1NomeGrupo")).sendKeys("Imóveis");

		VipSalvaLeilao(driver);

		EnviaEditalLeilaoEdge(driver);
		
		VipSalvaLeilao(driver);

		try {
			InserirFotoLeilao(driver, FotoTipo.capaControle, test.Num);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		driver.findElement(By.id("Holder_txt1EnderecoLeilao"))
				.sendKeys("FÓRUM LOCAL | Endereço: RUA CORONEL PATROCÍNIO");
		Aguarde(1000);
		driver.findElement(By.id("Holder_txt1EnderecoLeilao")).sendKeys(Keys.RETURN);
		Aguarde(250);

		VipSalvaLeilao(driver);

	}	
	protected void VipSalvaLeilao(EdgeDriver driver) {

		driver.findElement(By.id("Holder_btnSalvar")).click();
		Aguarde(3000);

	}
	protected void SelecioneProcesso(EdgeDriver driver, String cprocesso) {
		// TODO Auto-generated method stub
		driver.findElement(By.partialLinkText("Processo")).click();
		Aguarde(250);
		driver.findElement(By.partialLinkText("Cadastro")).click();
		Aguarde(250);

		System.out.println("Preenche processo");

		driver.findElement(By.id("Holder_txt1NumeroProcesso")).sendKeys(cprocesso);
		Aguarde(200);
		driver.findElement(By.id("Holder_txt1Comitente")).click();

		Aguarde(1500);

	}	
	protected void LotearBemEdge(EdgeDriver driver, vTest test) throws FileNotFoundException, IOException {

		driver.get("https://controleh.vipleiloes.com.br");

		Aguarde(8000);

		var nulote = 1;

		var listaBens = LeiaListaCodigoProduto(test.Leilao);
		var leilao = test.LeiaControleLeilao();

		System.out.println();
		if(listaBens.size() <1) {
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

	protected void EnviaEditalLeilaoEdge(EdgeDriver driver) {
		// TODO Auto-generated method stub
		driver.findElement(By.id("filestyle-0")).sendKeys(DocumentoArquivoEvento[0]);
		Aguarde(1500);
//		driver.findElement(By.id("Holder_txt3DocumentoDescricao")).sendKeys("Edital");
//		Aguarde(250);		
		driver.findElement(By.id("Holder_btn3DocumentoEnvia")).click();
		Aguarde(1500);

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
		System.out.print(lista);
	}
		
}
