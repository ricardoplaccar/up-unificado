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
	boolean finaliza = true;

	// private static final int tipoEventoCapaImovel = 4;

	public vTest nnum = new vTest();

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

		// WebDriver driver = LoginTest.IniciaLogin();
		var vc = new Veiculo();
		vc = vc.GetVeiculo(nnum);
		WebDriver driver = LoginTest.IniciaLogin();

		driver.findElement(By.linkText("Produtos")).click();
		Gerar.Aguarde(controleTempo * 2);
		driver.findElement(By.partialLinkText("Novo")).click();
		Gerar.Aguarde(controleTempo);
		driver.findElement(By.id("Produto_Nome")).click();
		driver.findElement(By.id("Produto_Nome")).sendKeys(vc.Nome);
		Gerar.Aguarde(controleTempo);
		new Select(driver.findElement(By.id("Produto_SubCategoriaCategoriaId"))).selectByVisibleText("Veículos");
		Gerar.Aguarde(controleTempo);
		new Select(driver.findElement(By.id("Produto_SubCategoriaId"))).selectByVisibleText("Carro de Passeio");
		Gerar.Aguarde(controleTempo);

		driver.findElement(By.id("Produto_ValorPedido")).sendKeys(vc.Valor);
		driver.findElement(By.id("Produto_ValorAvaliacao")).sendKeys(vc.Valor);

		new Select(driver.findElement(By.id("Produto_ComitenteId"))).selectByIndex(1);

		driver.findElement(By.id("Produto_Judicial")).click();
		Gerar.Aguarde(controleTempo * 2);

		new Select(driver.findElement(By.id("Produto_ProcessoJuridicoId")))
				.selectByVisibleText("0032949-14.2023.8.19.56");

		Gerar.Aguarde(controleTempo);

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
		Gerar.Aguarde(controleTempo * 6);
		driver.findElement(By.id("Produto_Local_Numero")).sendKeys("100");
		Gerar.Aguarde(controleTempo);

		var x = EnderecoValide(driver);
	//	assertTrue(x > 2);

		driver.findElement(By.xpath("//*[@id=\"placeholder\"]/div[4]/div[1]/button[2]")).click();
		Gerar.Aguarde(controleTempo * 3);

		var ListaFoto = vc.getListFotos();
		inserirFotos(driver, FotoTipo.Veiculo, ListaFoto, nnum.Num);

		Gerar.Aguarde(controleTempo * 3);
		driver.findElement(By.xpath("//*[@id=\"placeholder\"]/div[3]/div[1]/button[1]/i")).click(); // Salvar
		Gerar.Aguarde(controleTempo * 3);

		assertEquals(
				driver.findElement(By.cssSelector("div.alert.alert-dismissible.fade.show.alert-success")).getText(),
				"Salvo com sucesso.");

//		Gerar.Aguarde(controleTempo);
//		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();// 

		// *************************** produto *****************************************

		DataEvento dataEvento = new DataEvento();

		Gerar.Aguarde(controleTempo);
		driver.findElement(By.partialLinkText("Eventos")).click();
		Gerar.Aguarde(controleTempo * 2);
		driver.findElement(By.partialLinkText("Novo")).click();
		Gerar.Aguarde(controleTempo);

		driver.findElement(By.id("Evento_Nome")).sendKeys(nnum.Num);

		new Select(driver.findElement(By.id("Evento_CanalId"))).selectByIndex(1);
		new Select(driver.findElement(By.id("Evento_Categoria"))).selectByIndex(1);
		new Select(driver.findElement(By.id("Evento_QuantidadeEventos"))).selectByIndex(1);

		var arquivo = getCapa(FotoTipo.capaVeiculo, null, nnum.Num);

		Gerar.Aguarde(controleTempo);

		driver.findElement(By.id("Evento_NovaFoto")).sendKeys(arquivo);

		Gerar.Aguarde(controleTempo * 2);

		new Select(driver.findElement(By.id("Evento_Tipo"))).selectByIndex(1);

		Gerar.Aguarde(controleTempo * 2);

		PreencheData(driver, "Evento_InicioExbicaoEm", dataEvento.HoraExibir);
		PreencheData(driver, "Evento_FimExbicaoEm", dataEvento.HoraFimExibir);

		new Select(driver.findElement(By.id("Evento_Configuracao_OpcaoDisputa"))).selectByIndex(2);
		Gerar.Aguarde(controleTempo);
		PreencheData(driver, "Evento_PrimeiroIniciaEm", dataEvento.InicioDisputaFake);
		driver.findElement(By.id("Evento_Configuracao_DuracaoDisputa")).clear();
		driver.findElement(By.id("Evento_Configuracao_DuracaoDisputa")).sendKeys(dataEvento.DuracaoDisputa);

		driver.findElement(By.id("Evento_Configuracao_PermiteOfertasEmTodosAnunciosNaDisputa")).click();

		driver.findElement(By.id("Evento_Configuracao_InicioAutomaticoDoTempo")).click();
		Gerar.Aguarde(controleTempo);
		driver.findElement(By.id("Evento_Configuracao_IntervaloAntesDisputa")).clear();
		driver.findElement(By.id("Evento_Configuracao_IntervaloAntesDisputa")).sendKeys(dataEvento.Intervalo);

		driver.findElement(By.id("Evento_Configuracao_BloqueiaDurateIntervalo")).click();
		driver.findElement(By.id("Evento_Configuracao_PassagemAutomaticaAnuncios")).click();
		Gerar.Aguarde(controleTempo);
		driver.findElement(By.id("Evento_Configuracao_IntervaloAposDisputa")).clear();
		driver.findElement(By.id("Evento_Configuracao_IntervaloAposDisputa")).sendKeys(dataEvento.ItervaloPassagem);

		driver.findElement(By.id("Evento_Configuracao_PodeCobrirPropriaOferta")).click();
		Gerar.Aguarde(controleTempo);
		driver.findElement(By.id("Evento_Configuracao_TempoBloqueioAposOferta"))
				.sendKeys(dataEvento.BloqueioAposOferta);

		driver.findElement(By.id("Evento_Configuracao_TempoBloqueioAposOferta")).clear();
		driver.findElement(By.id("Evento_Configuracao_TempoBloqueioAposOferta"))
				.sendKeys(dataEvento.BloqueioAposOferta);

		driver.findElement(By.xpath("//*[@id=\"placeholder\"]/div[1]/div[1]/button")).click();

		Gerar.Aguarde(controleTempo * 3);

		driver.findElement(By.xpath("//*[@id=\"placeholder\"]/div[13]/div[1]/button[3]/i")).click();

		Gerar.Aguarde(controleTempo * 2);

		driver.findElement(By.xpath("//*[@id=\"FiltroProduto_Texto\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"FiltroProduto_Texto\"]")).sendKeys(vc.Nome);

		driver.findElement(By.xpath("//*[@id=\"placeholder\"]/div[1]/div[1]/div/button")).click();

		Gerar.Aguarde(controleTempo * 2);

		driver.findElement(By.xpath("//*[@id=\"produtosTab\"]/div/div/table/tbody/tr/td[1]/a/span")).click();

		Gerar.Aguarde(controleTempo * 2);

		driver.findElement(By.id("Anuncio_ValorInicial")).sendKeys(vc.Valor);
		driver.findElement(By.id("Anuncio_ValorMinimo")).sendKeys(vc.Valor);
		driver.findElement(By.id("Anuncio_Incremento")).sendKeys("100000");

		PreencheData(driver, "Anuncio_InicioDestaqueEm", dataEvento.HoraExibir);

		driver.findElement(By.id("Anuncio_Financiavel")).click();
		Gerar.Aguarde(controleTempo * 2);

// *************************************************************************************************		
		driver.findElement(By.xpath("//*[@id=\"placeholder\"]/div[1]/div[1]/button[1]")).click();

		Gerar.Aguarde(controleTempo * 2);
		driver.findElement(By.xpath("//*[@id=\"placeholder\"]/div[13]/div[1]/button[1]/i")).click();

	}

	@Test
	void CadastarEvento_Unico_Imovel_Deve_Retornar_sucesso() throws Exception {

		var im = new Imovel();
		im = im.GetImovel(nnum);

		WebDriver driver = LoginTest.IniciaLogin();

		driver.findElement(By.linkText("Produtos")).click();
		Gerar.Aguarde(controleTempo);
		driver.findElement(By.partialLinkText("Novo")).click();
		Gerar.Aguarde(controleTempo);
		driver.findElement(By.id("Produto_Nome")).click();
		driver.findElement(By.id("Produto_Nome")).sendKeys(im.Nome);
		Gerar.Aguarde(controleTempo);
		new Select(driver.findElement(By.id("Produto_SubCategoriaCategoriaId")))
				.selectByVisibleText(im.Categoria.Categoria);
		Gerar.Aguarde(controleTempo * 3);
		new Select(driver.findElement(By.id("Produto_SubCategoriaId"))).selectByVisibleText(im.Categoria.SubCategoria);
		Gerar.Aguarde(controleTempo);

		driver.findElement(By.id("Produto_ValorPedido")).sendKeys(im.VlPedido);
		driver.findElement(By.id("Produto_ValorAvaliacao")).sendKeys(im.VlAvaliado);

		new Select(driver.findElement(By.id("Produto_ComitenteId"))).selectByIndex(1);
		Gerar.Aguarde(controleTempo);
		driver.findElement(By.id("Produto_Judicial")).click();
		Gerar.Aguarde(controleTempo * 2);

		new Select(driver.findElement(By.id("Produto_ProcessoJuridicoId")))
				.selectByVisibleText("0032949-14.2023.8.19.56");

		Gerar.Aguarde(controleTempo);

		driver.findElement(By.id("Produto_Local_Cep")).sendKeys(im.Endereco.Cep);

		driver.findElement(By.id("Produto_Local_Numero")).click();
	
		Gerar.Aguarde(controleTempo);

		var x = EnderecoValide(driver);
	//	assertTrue(x > 2);

		driver.findElement(By.id("Produto_Local_Numero")).sendKeys("100");
		new Select(driver.findElement(By.id("Produto_SituacaoOcupacao"))).selectByIndex(1);
		new Select(driver.findElement(By.id("Produto_EstagioObra"))).selectByIndex(5);

		driver.findElement(By.xpath("//*[@id=\"placeholder\"]/div[4]/div[1]/button[2]")).click();
		Gerar.Aguarde(controleTempo * 3);

		var ListaFoto = im.getListFotos();
		inserirFotos(driver, FotoTipo.Imovel, ListaFoto, nnum.Num);

		Gerar.Aguarde(controleTempo * 5);
		driver.findElement(By.xpath("//*[@id=\"placeholder\"]/div[3]/div[1]/button[1]/i")).click();

		// *************************** Evento Imovel ***********************************
		

		DataEvento dataEvento = new DataEvento();

		Gerar.Aguarde(controleTempo);
		driver.findElement(By.partialLinkText("Eventos")).click();
		Gerar.Aguarde(controleTempo * 3);
		driver.findElement(By.partialLinkText("Novo")).click();
		Gerar.Aguarde(controleTempo);

		driver.findElement(By.id("Evento_Nome")).sendKeys(nnum.Num);

		new Select(driver.findElement(By.id("Evento_CanalId"))).selectByIndex(1);
		new Select(driver.findElement(By.id("Evento_Categoria"))).selectByIndex(1);
		new Select(driver.findElement(By.id("Evento_QuantidadeEventos"))).selectByIndex(1);

		var arquivo = getCapa(FotoTipo.capaImovel, null, nnum.Num);

		Gerar.Aguarde(controleTempo);

		driver.findElement(By.id("Evento_NovaFoto")).sendKeys(arquivo);

		Gerar.Aguarde(controleTempo * 2);

		new Select(driver.findElement(By.id("Evento_Tipo"))).selectByIndex(1);

		Gerar.Aguarde(controleTempo);

		PreencheData(driver, "Evento_InicioExbicaoEm", dataEvento.HoraExibir);
		PreencheData(driver, "Evento_FimExbicaoEm", dataEvento.HoraFimExibir);

		new Select(driver.findElement(By.id("Evento_Configuracao_OpcaoDisputa"))).selectByIndex(2);
		Gerar.Aguarde(controleTempo);
		PreencheData(driver, "Evento_PrimeiroIniciaEm", dataEvento.InicioDisputaFake);
		driver.findElement(By.id("Evento_Configuracao_DuracaoDisputa")).clear();
		driver.findElement(By.id("Evento_Configuracao_DuracaoDisputa")).sendKeys(dataEvento.DuracaoDisputa);

		driver.findElement(By.id("Evento_Configuracao_PermiteOfertasEmTodosAnunciosNaDisputa")).click();

		driver.findElement(By.id("Evento_Configuracao_InicioAutomaticoDoTempo")).click();
		Gerar.Aguarde(controleTempo);
		driver.findElement(By.id("Evento_Configuracao_IntervaloAntesDisputa")).clear();
		driver.findElement(By.id("Evento_Configuracao_IntervaloAntesDisputa")).sendKeys(dataEvento.Intervalo);

		driver.findElement(By.id("Evento_Configuracao_BloqueiaDurateIntervalo")).click();
		driver.findElement(By.id("Evento_Configuracao_PassagemAutomaticaAnuncios")).click();
		Gerar.Aguarde(controleTempo);
		driver.findElement(By.id("Evento_Configuracao_IntervaloAposDisputa")).clear();
		driver.findElement(By.id("Evento_Configuracao_IntervaloAposDisputa")).sendKeys(dataEvento.ItervaloPassagem);

		driver.findElement(By.id("Evento_Configuracao_PodeCobrirPropriaOferta")).click();
		Gerar.Aguarde(controleTempo);
		driver.findElement(By.id("Evento_Configuracao_TempoBloqueioAposOferta"))
				.sendKeys(dataEvento.BloqueioAposOferta);

		driver.findElement(By.id("Evento_Configuracao_TempoBloqueioAposOferta")).clear();
		driver.findElement(By.id("Evento_Configuracao_TempoBloqueioAposOferta"))
				.sendKeys(dataEvento.BloqueioAposOferta);

		driver.findElement(By.xpath("//*[@id=\"placeholder\"]/div[1]/div[1]/button")).click();

		Gerar.Aguarde(controleTempo * 4);

		driver.findElement(By.xpath("//*[@id=\"placeholder\"]/div[13]/div[1]/button[3]/i")).click();

		Gerar.Aguarde(controleTempo*3);

		driver.findElement(By.xpath("//*[@id=\"FiltroProduto_Texto\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"FiltroProduto_Texto\"]")).sendKeys(im.Nome);

		driver.findElement(By.xpath("//*[@id=\"placeholder\"]/div[1]/div[1]/div/button")).click();

		Gerar.Aguarde(controleTempo * 2);

		driver.findElement(By.xpath("//*[@id=\"produtosTab\"]/div/div/table/tbody/tr/td[1]/a/span")).click();

		Gerar.Aguarde(controleTempo * 2);

		driver.findElement(By.id("Anuncio_ValorInicial")).sendKeys(im.VlPedido);
		driver.findElement(By.id("Anuncio_ValorMinimo")).sendKeys(im.VlPedido);
		driver.findElement(By.id("Anuncio_Incremento")).sendKeys("100000");

		PreencheData(driver, "Anuncio_InicioDestaqueEm", dataEvento.HoraExibir);

		driver.findElement(By.id("Anuncio_Financiavel")).click();
		Gerar.Aguarde(controleTempo * 2);
		driver.findElement(By.id("Anuncio_Personalizado")).click();
		Gerar.Aguarde(controleTempo * 3);
		driver.findElement(By.id("Anuncio_Titulo1")).sendKeys(im.Nome);

// *************************************************************************************************		
		driver.findElement(By.xpath("//*[@id=\"placeholder\"]/div[1]/div[1]/button[1]")).click();

		Gerar.Aguarde(controleTempo * 3);
		driver.findElement(By.xpath("//*[@id=\"placeholder\"]/div[13]/div[1]/button[1]/i")).click();
		Gerar.Aguarde(controleTempo * 2);
		// Ajusta hora disputa

		PreencheData(driver, "Evento_PrimeiroIniciaEm", dataEvento.InicioDisputaReal);
		driver.findElement(By.xpath("//*[@id=\"placeholder\"]/div[1]/div[1]/button[1]")).click();

	}

	private int EnderecoValide(WebDriver driver) {

		String s = driver.findElement(By.id("Produto_Local_Logradouro")).getAttribute("value");
		var x = s.length();
		System.out.println("s=>" + s);
		System.out.println("x=>" + x);

		if (x == 0) {
			driver.findElement(By.id("Produto_Local_Logradouro")).sendKeys("Qualquer");
			driver.findElement(By.id("Produto_Local_Bairro")).sendKeys("Qualquer");
			driver.findElement(By.id("Produto_Local_Cidade")).sendKeys("Qualquer");
			new Select(driver.findElement(By.id("Produto_Local_EstadoId"))).selectByIndex(1);

		}

		return x;
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

	public static String getCapa(FotoTipo fototipo, String Texto, String num) throws Exception {
		// URL url = new URL("https://i.stack.imgur.com/Nqf3H.jpg");
		String Caminho = "D:\\ricsistemas\\Documents\\Placar\\Test\\fotos\\foto_capa\\";
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

}
