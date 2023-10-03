package up_backoffice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.*;
import java.awt.font.*;
import java.awt.image.BufferedImage;
import java.awt.geom.Rectangle2D;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.List;

import java.net.URL;
import java.io.File;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.bidi.log.Log;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import junit.framework.Assert;
import model.Comitente;
import model.DataEvento;
import model.Endereco;
import model.Foto;
import model.FotoTipo;
import model.Imovel;
import model.Veiculo;
import model.vTest;
import util.Gerar;

class EventoTest {

	int controleTempo = 1000;
	boolean finaliza = false;

	// private static final int tipoEventoCapaImovel = 4;

	public vTest nnum = new vTest();
	 int ncomitente = nnum.getComitente();
//

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {

	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void CadastarEvento_Unico_Veiculo_Deve_Retornar_sucesso() throws Exception {
	
		ArrayList<Veiculo> ListaVeiculos =  new ArrayList<>();
	
		WebDriver driver = LoginTest.IniciaLogin();
		for (var i = 0;i<2; i++) {
		    var vc =  CadastrarVeiculo(driver);
			 ListaVeiculos.add(vc);
			}
		
		// *************************** Cadastra Eventos *****************************************
		DataEvento dataEvento = new DataEvento();
        CadastrarEvento(driver,dataEvento);
		
		// Selecionar produto
		
		Salvar(driver);		
		// editar produto 
		ListaVeiculos.forEach(vc ->  {
	    EditarProduto(driver,vc.Nome );
		var comitente = new Comitente(ncomitente);
		driver.findElement(By.id("Anuncio_PrimeiroValorInicial")).sendKeys(vc.Valor);
		driver.findElement(By.id("Anuncio_PrimeiroValorInicialDesconto")).sendKeys("50");
		driver.findElement(By.id("Anuncio_ValorMinimo")).sendKeys(vc.Valor);	
		driver.findElement(By.id("Anuncio_Incremento")).sendKeys("100000");	
		driver.findElement(By.id("Anuncio_UriFinanciamentoExterno")).sendKeys(comitente.UrlFinaceira);	
		driver.findElement(By.id("Anuncio_Parcelamento")).click();
		driver.findElement(By.id("Anuncio_AVista")).click();
		PreencheData(driver, "Anuncio_InicioDestaqueEm", dataEvento.HoraExibir);
		Salvar(driver);
		});
		
		driver.findElement(By.xpath("//*[@id=\"placeholder\"]/div[13]/div[1]/button[1]")).click();
		
	}

	private Veiculo CadastrarVeiculo(WebDriver driver) throws Exception {
		
		var vc = new Veiculo();
		vc = vc.GetVeiculo(nnum);
		
		driver.findElement(By.linkText("Produtos")).click();
		Gerar.Aguarde(controleTempo * 2);
		driver.findElement(By.partialLinkText("Novo")).click();
		Gerar.Aguarde(controleTempo);
		driver.findElement(By.id("Produto_Nome")).click();
		driver.findElement(By.id("Produto_Nome")).sendKeys(vc.Nome);
		Gerar.Aguarde(controleTempo*2);
		new Select(driver.findElement(By.id("Produto_SubCategoriaCategoriaId"))).selectByVisibleText(vc.Categoria.Categoria);
		Gerar.Aguarde(controleTempo);	
		new Select(driver.findElement(By.id("Produto_SubCategoriaId"))).selectByVisibleText(vc.Categoria.SubCategoria);
		Gerar.Aguarde(controleTempo);
		new Select(driver.findElement(By.id("Produto_ComitenteId"))).selectByIndex(ncomitente);
		Gerar.Aguarde(controleTempo);
		
		driver.findElement(By.id("Produto_ValorPedido")).sendKeys(vc.Valor);
		driver.findElement(By.id("Produto_ValorAvaliacao")).sendKeys(vc.Valor);
		new Select(driver.findElement(By.id("Produto_ComitenteId"))).selectByIndex(ncomitente);
		driver.findElement(By.id("Produto_Judicial")).click();
		Gerar.Aguarde(controleTempo * 2);
		new Select(driver.findElement(By.id("Produto_ProcessoJuridicoId"))).selectByVisibleText("0032949-14.2023.8.19.5");
		driver.findElement(By.xpath("//*[@id=\"placeholder\"]/div[3]/div[1]/button")).sendKeys(vc.Nome); //Salvar
		Gerar.Aguarde(controleTempo * 2);	
		driver.findElement(By.id("Produto_Marca")).sendKeys(vc.Marca);
		driver.findElement(By.id("Produto_Modelo")).sendKeys(vc.Modelo);
		driver.findElement(By.id("Produto_Chassi")).sendKeys(vc.NumMotor);
		driver.findElement(By.id("Produto_Renavam")).sendKeys("0112121212");
		driver.findElement(By.id("Produto_AnoModelo")).sendKeys(vc.Ano);
		driver.findElement(By.id("Produto_AnoFabricacao")).sendKeys(vc.Ano);
		driver.findElement(By.id("Produto_PlacaNumero")).sendKeys(vc.Placa);
		new Select(driver.findElement(By.id("Produto_PlacaEstadoId"))).selectByVisibleText("PR");

		driver.findElement(By.id("Produto_Quilometragem")).sendKeys(vc.KM);
		Gerar.Aguarde(controleTempo);
		var ender = new Endereco();

		driver.findElement(By.id("Produto_Local_Cep")).sendKeys(ender.Cep);
		driver.findElement(By.id("Produto_Local_Numero")).click();
		Gerar.Aguarde(controleTempo);
		driver.findElement(By.id("Produto_Local_Numero")).sendKeys("100");
		Gerar.Aguarde(controleTempo);

		 EnderecoValide(driver);
	
		driver.findElement(By.xpath("//*[@id=\"placeholder\"]/div[4]/div[1]/button[2]")).click();
		Gerar.Aguarde(controleTempo * 3);

		var ListaFoto = vc.getListFotos();
		inserirFotos(driver, FotoTipo.Veiculo, ListaFoto, nnum.Num);

		Gerar.Aguarde(controleTempo * 3);
		driver.findElement(By.xpath("//*[@id=\"placeholder\"]/div[3]/div[1]/button[1]/i")).click(); // Salvar
		Gerar.Aguarde(controleTempo * 3);

		assertEquals(driver.findElement(By.cssSelector("div.alert.alert-dismissible.fade.show.alert-success")).getText(),
				"Salvo com sucesso.");
		return vc;

	}

	private void CadastrarEvento(WebDriver driver,DataEvento dataEvento ) throws Exception {
	
		Gerar.Aguarde(controleTempo);
		driver.findElement(By.partialLinkText("Eventos")).click();
		Gerar.Aguarde(controleTempo * 2);
		driver.findElement(By.partialLinkText("Novo")).click();
		Gerar.Aguarde(controleTempo);
		driver.findElement(By.id("Evento_Nome")).sendKeys(nnum.Num);
		new Select(driver.findElement(By.id("Evento_CanalId"))).selectByIndex(1);
		new Select(driver.findElement(By.id("Evento_Categoria"))).selectByIndex(1);
		Gerar.Aguarde(controleTempo);
		driver.findElement(By.id("Evento_DescricaoGeral")).sendKeys("Venda de de Produtos");
		new Select(driver.findElement(By.id("Evento_QuantidadeEventos"))).selectByIndex(1);
		new Select(driver.findElement(By.id("Evento_FormaOferta"))).selectByIndex(1);
		var arquivo = getCapa(FotoTipo.capaVeiculo, null, nnum.Num);
		Gerar.Aguarde(controleTempo);
		driver.findElement(By.id("Evento_NovaFoto")).sendKeys(arquivo);
		Gerar.Aguarde(controleTempo * 4);
		new Select(driver.findElement(By.id("Evento_Tipo"))).selectByIndex(1);
		Gerar.Aguarde(controleTempo * 2);
		PreencheData(driver, "Evento_InicioExbicaoEm", dataEvento.HoraExibir);
		PreencheData(driver, "Evento_FimExbicaoEm", dataEvento.HoraFimExibir);
		new Select(driver.findElement(By.id("Evento_Configuracao_OpcaoDisputa"))).selectByIndex(1);
		Gerar.Aguarde(controleTempo*2);
		PreencheData(driver, "Evento_PrimeiroIniciaOuEncerraEm", dataEvento.InicioDisputaFake);
		
	}

	private void EditarProduto(WebDriver driver,String value) {
	   
		Gerar.Aguarde(controleTempo);
		driver.findElement(By.xpath("//*[@id=\"placeholder\"]/div[13]/div[1]/button[3]")).click();
		Gerar.Aguarde(controleTempo);
		driver.findElement(By.id("FiltroProduto_Texto")).sendKeys(value);
		driver.findElement(By.xpath("//*[@id=\"placeholder\"]/div[1]/div[1]/div/button")).click();
		Gerar.Aguarde(controleTempo * 2);
		driver.findElement(By.xpath("//*[@id=\"produtosTab\"]/div/div/table/tbody/tr/td[1]/a/span")).click();
		Gerar.Aguarde(controleTempo * 2);
		
	}

	@Test
	void CadastarEvento_Unico_Imovel_Deve_Retornar_sucesso() throws Exception {

		WebDriver driver = LoginTest.IniciaLogin();
		ArrayList<Imovel> ListaImoveis =  new ArrayList<>();
		
		for (var i = 0;i<3; i++) {
		
		var im =  CadastroImovel(driver);
		ListaImoveis.add(im);
		}
	
 		// *************************** Evento Imovel ***********************************
		DataEvento dataEvento = new DataEvento();
		Gerar.Aguarde(controleTempo);
		driver.findElement(By.partialLinkText("Eventos")).click();
		Gerar.Aguarde(controleTempo * 3);
		driver.findElement(By.partialLinkText("Novo")).click();
		Gerar.Aguarde(controleTempo);

		driver.findElement(By.id("Evento_Nome")).sendKeys(nnum.Num);
		new Select(driver.findElement(By.id("Evento_ApresentadorId"))).selectByIndex(1);
		new Select(driver.findElement(By.id("Evento_CanalId"))).selectByIndex(1);
		new Select(driver.findElement(By.id("Evento_Categoria"))).selectByIndex(1);
		Gerar.Aguarde(controleTempo*2);
		new Select(driver.findElement(By.id("Evento_QuantidadeEventos"))).selectByIndex(1);
		Gerar.Aguarde(controleTempo);
		new Select(driver.findElement(By.id("Evento_FormaOferta"))).selectByIndex(1);
		Gerar.Aguarde(controleTempo);
		var arquivo = getCapa(FotoTipo.capaImovel, null, nnum.Num);
		Gerar.Aguarde(controleTempo);
		driver.findElement(By.id("Evento_NovaFoto")).sendKeys(arquivo);
		Gerar.Aguarde(controleTempo * 3);
		new Select(driver.findElement(By.id("Evento_Tipo"))).selectByIndex(1);
		Gerar.Aguarde(controleTempo);
		PreencheData(driver, "Evento_InicioExbicaoEm", dataEvento.HoraExibir);
		PreencheData(driver, "Evento_FimExbicaoEm", dataEvento.HoraFimExibir);
		new Select(driver.findElement(By.id("Evento_Configuracao_OpcaoDisputa"))).selectByIndex(1);
		Gerar.Aguarde(controleTempo*3);
		PreencheData(driver, "Evento_PrimeiroIniciaOuEncerraEm", dataEvento.InicioDisputaFake);
		driver.findElement(By.id("Evento_Configuracao_PodeCobrirPropriaOferta")).click();
		driver.findElement(By.id("Evento_Configuracao_PermiteOfertaAutomatica")).click();
		Gerar.Aguarde(controleTempo);
		Salvar(driver);	
		// *************************** Evento Imovel ***********************************
		
		ListaImoveis.forEach(im ->  {
		
	EditarProduto(driver,im.Nome);
		
	var comitente = new Comitente(ncomitente);
	
	driver.findElement(By.id("Anuncio_PrimeiroValorInicial")).sendKeys(im.VlPedido);
	driver.findElement(By.id("Anuncio_PrimeiroValorInicialDesconto")).sendKeys("50");
	driver.findElement(By.id("Anuncio_ValorMinimo")).sendKeys(im.VlPedido);	
	driver.findElement(By.id("Anuncio_Incremento")).sendKeys("100000");	
	driver.findElement(By.id("Anuncio_UriFinanciamentoExterno")).sendKeys(comitente.UrlFinaceira);	
	driver.findElement(By.id("Anuncio_Parcelamento")).click();
	driver.findElement(By.id("Anuncio_AVista")).click();
	
	PreencheData(driver, "Anuncio_InicioDestaqueEm", dataEvento.HoraExibir);
	
	Salvar(driver);
		});
	}

	private Imovel CadastroImovel(WebDriver driver) throws Exception {
		var im = new Imovel();
		 im = im.GetImovel(nnum);
		
		
		driver.findElement(By.linkText("Produtos")).click();
		Gerar.Aguarde(controleTempo * 2);
		driver.findElement(By.partialLinkText("Novo")).click();
		Gerar.Aguarde(controleTempo);
		driver.findElement(By.id("Produto_Nome")).click();
		driver.findElement(By.id("Produto_Nome")).sendKeys(im.Nome);
		Gerar.Aguarde(controleTempo*2);
		new Select(driver.findElement(By.id("Produto_SubCategoriaCategoriaId"))).selectByVisibleText(im.Categoria.Categoria);
		Gerar.Aguarde(controleTempo);
		new Select(driver.findElement(By.id("Produto_SubCategoriaId"))).selectByVisibleText(im.Categoria.SubCategoria);
		Gerar.Aguarde(controleTempo);
		driver.findElement(By.id("Produto_ValorPedido")).sendKeys(im.VlPedido);
		driver.findElement(By.id("Produto_ValorAvaliacao")).sendKeys(im.VlAvaliado);
		new Select(driver.findElement(By.id("Produto_ComitenteId"))).selectByIndex(ncomitente);
		driver.findElement(By.id("Produto_Judicial")).click();
		Gerar.Aguarde(controleTempo * 2);
		new Select(driver.findElement(By.id("Produto_ProcessoJuridicoId"))).selectByVisibleText("0032949-14.2023.8.19.5");
		driver.findElement(By.xpath("//*[@id=\"placeholder\"]/div[3]/div[1]/button")).sendKeys(im.Nome); //Salvar
		Gerar.Aguarde(controleTempo * 2);	
		new Select(driver.findElement(By.id("Produto_ProcessoJuridicoId")))
				.selectByVisibleText("0032949-14.2023.8.19.5");

		Gerar.Aguarde(controleTempo);
		driver.findElement(By.id("Produto_Local_Cep")).sendKeys(im.Endereco.Cep);
		driver.findElement(By.id("Produto_Local_Numero")).click();
		Gerar.Aguarde(controleTempo);
		EnderecoValide(driver);
		driver.findElement(By.id("Produto_Local_Numero")).sendKeys("100");
	
		var situacao = nnum.getOcupacao();
	 	new Select(driver.findElement(By.id("Produto_SituacaoOcupacao"))).selectByIndex(situacao);
		var estagio = nnum.getEstagio();
		new Select(driver.findElement(By.id("Produto_EstagioObra"))).selectByIndex(estagio);

		driver.findElement(By.xpath("//*[@id=\"placeholder\"]/div[4]/div[1]/button[2]")).click();
		Gerar.Aguarde(controleTempo * 3);
		var ListaFoto = im.getListFotos();
		inserirFotos(driver, FotoTipo.Imovel, ListaFoto, nnum.Num);

		Gerar.Aguarde(controleTempo * 5);
		driver.findElement(By.xpath("//*[@id=\"placeholder\"]/div[3]/div[1]/button[1]/i")).click();

		Gerar.Aguarde(controleTempo * 3);
	var	 texto = driver.findElement(By.cssSelector("div.alert.alert-dismissible.fade.show.alert-success")).getText();
		assertEquals(LoginTest.salvocomsucesso, texto);
		return im;
		
	}

	private void EnderecoValide(WebDriver driver) {
		
		Gerar.Aguarde(controleTempo * 2);
		String s = driver.findElement(By.id("Produto_Local_Logradouro")).getAttribute("value");
		var x = s.length();
		if (x == 0) {
			driver.findElement(By.id("Produto_Local_Logradouro")).sendKeys("Qualquer");
			driver.findElement(By.id("Produto_Local_Bairro")).sendKeys("Qualquer");
			driver.findElement(By.id("Produto_Local_Cidade")).sendKeys("Qualquer");
			new Select(driver.findElement(By.id("Produto_Local_EstadoId"))).selectByIndex(1);
			System.out.println("Falou endereco=>" + nnum.Desc);

		}

	
	}

	private void inserirFotos(WebDriver driver, FotoTipo fototipo, ArrayList<Foto> ListaFoto, String num)
			throws Exception {
		var nfoto = 0;
		for (Foto item : ListaFoto) {

			nfoto++;

			if (nfoto == 1) {
				var foto = getCapa(fototipo, item.local, num);

				driver.findElement(By.id("Multimidia_NovaImagem")).sendKeys(foto);

			} else
				driver.findElement(By.id("Multimidia_NovaImagem")).sendKeys(item.local);

			Gerar.Aguarde(controleTempo * 2);
		}

	}

	private  String getCapa(FotoTipo fototipo, String Texto, String num) throws Exception {
		// URL url = new URL("https://i.stack.imgur.com/Nqf3H.jpg");
		String Caminho = "D:\\ricsistemas\\Documents\\Placar\\fotos\\foto_capa\\";
		BufferedImage originalImage;
		BasicStroke stroke = new BasicStroke(2f);
		String Aquiv = null;
		int tam = 100;
		if (fototipo == FotoTipo.capaVeiculo) {
			Aquiv = Caminho + "capa-leilao-vazio-carro.jpg";
		}

		else if (fototipo == FotoTipo.capaImovel) {
			Aquiv = Caminho + "capa-leilao-vazio-imovel.jpg";
		} else

		if (fototipo == FotoTipo.Veiculo) {

			Aquiv = Texto;
			tam = 200;
			stroke = new BasicStroke(4f);

		} else

		if (fototipo == FotoTipo.Imovel) {

			Aquiv = Texto;
			tam = 280;
			stroke = new BasicStroke(6f);

		}

		var f = new File(Aquiv);
		originalImage = ImageIO.read(f);
		final BufferedImage textImage = new BufferedImage(originalImage.getWidth(), originalImage.getHeight(),
				BufferedImage.TYPE_INT_ARGB);
		// Gerar Imagem de Texto
		Graphics2D g = textImage.createGraphics();
		FontRenderContext frc = g.getFontRenderContext();
		Font font;
		GlyphVector gv;
		font = new Font(Font.SANS_SERIF, Font.BOLD, tam); // Tamanho do texto
		gv = font.createGlyphVector(frc, num);
		Rectangle2D box = gv.getVisualBounds();
		int x = ((originalImage.getWidth() - (int) box.getWidth()) / 2) - 8;
		int y = ((originalImage.getHeight() - (int) box.getY()) / 2) - 8;
		Shape shape = gv.getOutline(x, y);

		g.drawImage(originalImage, 0, 0, null);
		g.setClip(null);
		g.setStroke(stroke);

		g.setColor(Color.blue);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		// if (tipoEvento == tipoEventoFoto) g.fill(shape);

		g.draw(shape);
		g.dispose();

		String arquivo = Caminho + "~TempFoto.png";
		File file = new File(arquivo);
		ImageIO.write(textImage, "png", file);
		// Desktop.getDesktop().open(file);
		return arquivo;
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
private void Salvar(WebDriver driver) {
	
	driver.findElement(By.xpath("//*[@id=\"placeholder\"]/div[1]/div[1]/button")).click();
	Gerar.Aguarde(controleTempo * 3);

	
	
}
	
}
