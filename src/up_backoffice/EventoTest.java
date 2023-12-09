package up_backoffice;

import static org.junit.Assert.assertEquals;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByClassName;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import model.Comitente;
import model.Constants;
import model.Endereco;
import model.Evento;
import model.Foto;
import model.FotoTipo;
import model.Geral;
import model.Imovel;
import model.Pedido;
import model.Veiculo;
import model.vTest;
import util.Gerar;

class EventoTest extends Constants {

	int controleTempo = TestControleTempo;
	int num_reuso = 0;
	public vTest test = new vTest(num_reuso);
	private Comitente Comit = new Comitente(test);
	private int loteImoveisQtd = 1;
	private int loteVeiculoQtd = 1;
	private int loteGeralQtd = 1;

	private Evento Evento = new Evento(test);

	@Test
	void Aproveitar_Produto() throws Exception {

		FotoTipo tipoEvento = null;
		var EventoPerdido = "(" + num_reuso + ")";
		if (num_reuso == 0)
			return;

		WebDriver driver = LoginTest.IniciaLogin();
		ArrayList<Pedido> ListaPedidos = new ArrayList<>();
		var nrow = 99;
		var linhas = 0;
		while (nrow != 0) {
			linhas = BuscaProdutosCadastro(driver, EventoPerdido);
			if (nrow == 99) {
				nrow = linhas;
			}

			System.out.println("rows=>" + nrow);
			clickItem(driver, nrow);
			Gerar.Aguarde(controleTempo * 2);
			var ped = new Pedido();
			var valor = driver.findElement(By.id("Produto_ValorPedido")).getAttribute("value");

			ped.SetValor(valor);
			ped.Descricao = driver.findElement(By.id("Produto_Nome")).getAttribute("value");
			var isJusdicial = driver.findElement(By.cssSelector("#Produto_Judicial")).isSelected();

			var select = new Select(driver.findElement(By.id("Produto_Tipo")));

			WebElement option = select.getFirstSelectedOption();
			String Produto_Tipo = option.getText();

			if (Produto_Tipo.equals("Veículo")) {
				tipoEvento = FotoTipo.capaVeiculo;
			} else if (Produto_Tipo.equals("Imóvel")) {
				tipoEvento = FotoTipo.capaImovel;

			} else

			{
				tipoEvento = FotoTipo.capaGeral;
			}

			if (isJusdicial) {
				Evento.IndexCategoria = 1;
				System.out.println("Judicial =" + isJusdicial);
			}
			ListaPedidos.add(ped);
			nrow--;
		}

// Cria evento // 		

		if (ExistEvento(driver, tipoEvento))

		{

			System.out.println("Editar:" + num_reuso);

			PreencheEvento(driver, tipoEvento);

		} else {

			System.out.println("Novo:" + num_reuso);

			CadastrarEvento(driver, tipoEvento);
		}

		ListaPedidos.forEach(vc -> {
			var ped = new Pedido();
			ped.Descricao = vc.Descricao;
			ped.SetValor(vc.GetValor(0));
			ped.Url = Comit.UrlFinaceira;
			AddProdutoEvento(driver, ped);
		});

	}

	private boolean ExistEvento(WebDriver driver, FotoTipo fotocapatipo) {
		// TODO Auto-generated method stub

		Gerar.Aguarde(controleTempo * 2);
		driver.findElement(By.partialLinkText("Eventos")).click();
		Gerar.Aguarde(controleTempo * 2);
		driver.findElement(By.id("Filtro_Texto")).sendKeys(String.valueOf(num_reuso));
		Gerar.Aguarde(controleTempo * 2);
		driver.findElement(By.xpath("//*[@id=\"placeholder\"]/div[1]/div/div/button[1]")).click();
		Gerar.Aguarde(controleTempo * 2);
		var str = driver.findElement(By.cssSelector("#placeholder>div.container-fluid > h2")).getText();

		var isEvento = (str.equals("Resultado da Pesquisa (0)"));

		if (!isEvento) {
			driver.findElement(By.xpath("//*[@id=\"placeholder\"]/div[2]/div/table/tbody/tr/td[1]/a")).click();
			Gerar.Aguarde(controleTempo * 2);

			return true;

		} else {

			return false;

		}

	}

	@Test
	void CadastarEvento_Veiculo_Unico_Deve_Retornar_sucesso() throws Exception {

		if (num_reuso != 0)
			return;

		ArrayList<Veiculo> ListaVeiculos = new ArrayList<>();
		WebDriver driver = LoginTest.IniciaLogin();
		for (var i = 0; i < loteVeiculoQtd; i++) {
			var vc = CadastrarVeiculo(driver);
			ListaVeiculos.add(vc);
		}

		// *************************** Cadastra Eventos ****************

		CadastrarEvento(driver, FotoTipo.capaVeiculo);

		// editar produto
		ListaVeiculos.forEach(vc -> {
			var ped = new Pedido();
			ped.Descricao = vc.Nome;
			ped.SetValor(vc.LanceInicial);
			ped.Avaliado = vc.Avaliado;
			ped.Incremento =vc.Incremento;
			ped.Url = Comit.UrlFinaceira;
			AddProdutoEvento(driver, ped);

		});

	}

	@Test
	void CadastarEvento_Geral_Unico_Deve_Retornar_sucesso() throws Exception {

		if (num_reuso != 0)
			return;

		WebDriver driver = LoginTest.IniciaLogin();
		ArrayList<Geral> ListaGeral = new ArrayList<>();
		for (var i = 0; i < loteGeralQtd; i++) {
			var ge = CadastrarGeral(driver);
			ListaGeral.add(ge);
			System.out.println("Diversos=" + (i + 1) + "/" + loteGeralQtd);
		}

		// *************************** Evento Geral ***********************************

		CadastrarEvento(driver, FotoTipo.capaGeral);
		ListaGeral.forEach(ge -> {
			var ped = new Pedido();
			ped.Descricao = ge.Descricao;
			ped.Url = Comit.UrlFinaceira;
			ped.SetValor(ge.LanceInicial);
			AddProdutoEvento(driver, ped);

		});
		// *************************** Evento Geral ***********************************
	}

	private Geral CadastrarGeral(WebDriver driver) throws Exception {
		var ge = new Geral(test);

		var ped = new Pedido();
		ped.Categoria = ge.Categoria;
		ped.Descricao = ge.Descricao;
		ped.SetValor(ge.LanceInicial);

		NovoProduto(driver, ped);
		PreecheGeral(driver, ge);
		SalvarProduto(driver);
		// Mudar para Add foto
		MudarAddFoto(driver);
		var ListaFoto = ge.ListFotos;
		inserirFotos(driver, FotoTipo.Geral, ListaFoto, test.Num);
		SalvarProduto(driver);
		ValidaProdutoSalvo(driver);
		return ge;
	}

	private void PreecheGeral(WebDriver driver, Geral ge) {
		driver.findElement(By.id("Produto_Marca")).sendKeys(ge.Marca);
		driver.findElement(By.id("Produto_Modelo")).sendKeys(ge.Modelo);
	}

	@Test
	void CadastarEvento_Imovel_Unico_Deve_Retornar_sucesso() throws Exception {

		if (num_reuso != 0)
			return;

		WebDriver driver = LoginTest.IniciaLogin();
		ArrayList<Imovel> ListaImoveis = new ArrayList<>();
		for (var i = 0; i < loteImoveisQtd; i++) {
			var im = CadastrarImovel(driver);
			ListaImoveis.add(im);
			System.out.println("Imovel=" + (i + 1) + "/" + loteImoveisQtd);
		}

		// *************************** Evento Imovel ***********************************

		CadastrarEvento(driver, FotoTipo.capaImovel);

		ListaImoveis.forEach(im -> {
			var ped = new Pedido();
			ped.Descricao = im.Nome;
			ped.Url = Comit.UrlFinaceira;
			ped.SetValor(im.LancInicial);
			ped.Avaliado = im.Avaliado;
			ped.Incremento ="1.000,00";
			AddProdutoEvento(driver, ped);

		});
		// *************************** Evento Imovel ***********************************
	}

	// *************************** Funcoes ***********************************

	private int BuscaProdutosCadastro(WebDriver driver, String value)

	{

		driver.findElement(By.linkText("Produtos")).click();
		Gerar.Aguarde(controleTempo * 2);
		driver.findElement(By.id("Filtro_Texto")).sendKeys(value);
		driver.findElement(By.xpath("//*[@id=\"placeholder\"]/div[1]/div/div/button[1]")).click();
		Gerar.Aguarde(controleTempo * 2);
		List<WebElement> rowsList = driver.findElements(By.tagName("td"));
		return rowsList.size() / 6;

	}

	private void clickItem(WebDriver driver, int item) {
		var nRow = "//*[@id=\"placeholder\"]/div[2]/div/table/tbody/tr[%s]/td[1]".formatted(item);
		var row = driver.findElement(By.xpath(nRow)).getText();
		driver.findElement(By.linkText(row)).click();

	}

	private Imovel CadastrarImovel(WebDriver driver) throws Exception {
		var im = new Imovel(test);

		var ped = new Pedido();
		ped.Categoria = im.Categoria;
		ped.Descricao = im.Nome;
		ped.SetValor(im.LancInicial);
        ped.Incremento = "1.000,00";
        ped.Avaliado = im.Avaliado;
		NovoProduto(driver, ped);
		PreecheImovel(driver, im);
		SalvarProduto(driver);
		// Mudar para Add foto
		MudarAddFoto(driver);
		var ListaFoto = im.ListaFotos;
		inserirFotos(driver, FotoTipo.Imovel, ListaFoto, test.Num);
		SalvarProduto(driver);
		ValidaProdutoSalvo(driver);
		return im;

	}

	private void PreecheImovel(WebDriver driver, Imovel im) {
		new Select(driver.findElement(By.id("Produto_SituacaoOcupacao"))).selectByIndex(im.IndexOcupacao);
		new Select(driver.findElement(By.id("Produto_EstagioObra"))).selectByIndex(im.IndexEstagio);
	}

	private void MudarAddFoto(WebDriver driver) {
		driver.findElement(By.xpath("//*[@id=\"placeholder\"]/div[4]/div[1]/button[2]")).click();
		Gerar.Aguarde(controleTempo * 3);

	}

	private void NovoProduto(WebDriver driver, Pedido ped) {
		driver.findElement(By.linkText("Produtos")).click();
		Gerar.Aguarde(controleTempo * 2);
		driver.findElement(By.partialLinkText("Novo")).click();
		Gerar.Aguarde(controleTempo);
		driver.findElement(By.id("Produto_Nome")).click();
		driver.findElement(By.id("Produto_Nome")).sendKeys(ped.Descricao);
		Gerar.Aguarde(controleTempo * 2);

		new Select(driver.findElement(By.id("Produto_Tipo"))).selectByVisibleText(ped.Categoria.Categoria);
		Gerar.Aguarde(controleTempo * 3);

		new Select(driver.findElement(By.id("Produto_SubCategoriaCategoriaId")))
				.selectByVisibleText(ped.Categoria.Categoria);
		Gerar.Aguarde(controleTempo * 3);
		new Select(driver.findElement(By.id("Produto_SubCategoriaId"))).selectByVisibleText(ped.Categoria.SubCategoria);
		Gerar.Aguarde(controleTempo * 2);
	var valor = ped.GetValor(0);
		
		driver.findElement(By.id("Produto_ValorPedido")).sendKeys(valor);
		driver.findElement(By.id("Produto_ValorAvaliacao")).sendKeys(ped.Avaliado);
		new Select(driver.findElement(By.id("Produto_ComitenteId"))).selectByIndex(Comit.IndexComitente);
		// TODO tipo do evento
		if (Evento.Judicial) {
			driver.findElement(By.id("Produto_Judicial")).click();
			Gerar.Aguarde(controleTempo * 3);
			new Select(driver.findElement(By.id("Produto_ProcessoJuridicoId"))).selectByVisibleText(ped.Processo);
		}
//TODO Endereco proximo  		
		var Endereco = new Endereco(test);

		driver.findElement(By.id("Produto_Local_Cep")).sendKeys(Endereco.Cep);
		Gerar.Aguarde(controleTempo);
		driver.findElement(By.id("Produto_Local_Numero")).click();
		Gerar.Aguarde(controleTempo);
		driver.findElement(By.id("Produto_Local_Numero")).sendKeys("100");
		Gerar.Aguarde(controleTempo * 3);
		String s = driver.findElement(By.id("Produto_Local_Logradouro")).getAttribute("value");
		driver.findElement(By.id("Produto_Local_Numero")).click();
		Gerar.Aguarde(controleTempo);
		var x = s.length();
		if (x == 0) {
			driver.findElement(By.id("Produto_Local_Logradouro")).sendKeys("Qualquer");
			driver.findElement(By.id("Produto_Local_Bairro")).sendKeys("Qualquer");
			driver.findElement(By.id("Produto_Local_Cidade")).sendKeys("Qualquer");
			new Select(driver.findElement(By.id("Produto_Local_EstadoId"))).selectByIndex(1);
			System.out.println("Falou endereco=>" + test.Desc);

		}

	}

	private void SalvarProduto(WebDriver driver) {
		driver.findElement(By.xpath("//*[@id=\"placeholder\"]/div[3]/div[1]/button[1]/i")).click(); // Salvar
		Gerar.Aguarde(controleTempo * 7);
	}

	private Veiculo CadastrarVeiculo(WebDriver driver) throws Exception {

		var vc = new Veiculo(test);
		var ped = new Pedido();
		
		ped.Descricao = vc.Nome;
		ped.SetValor(vc.LanceInicial);
		ped.Categoria = vc.Categoria;
		ped.Incremento = vc.Incremento;
		ped.Avaliado = vc.Avaliado;
		
		NovoProduto(driver, ped);
		PreencheVeiculo(driver, vc);
		SalvarProduto(driver);
		var ListaFoto = vc.ListFotos;
		inserirFotos(driver, FotoTipo.Veiculo, ListaFoto, test.Num);
		SalvarProduto(driver);
		ValidaProdutoSalvo(driver);

		return vc;

	}

	private void ValidaProdutoSalvo(WebDriver driver) {
		assertEquals(
				driver.findElement(By.cssSelector("div.alert.alert-dismissible.fade.show.alert-success")).getText(),
				"Salvo com sucesso.");

	}

	private void PreencheVeiculo(WebDriver driver, Veiculo vc) {
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

	}

	// TODO Cadastra Evento
	private void CadastrarEvento(WebDriver driver, FotoTipo fotocapatipo) {

		Gerar.Aguarde(controleTempo * 2);
		driver.findElement(By.partialLinkText("Eventos")).click();
		Gerar.Aguarde(controleTempo * 2);
		driver.findElement(By.partialLinkText("Novo")).click();
		Gerar.Aguarde(controleTempo);
		PreencheEvento(driver, fotocapatipo);

	}

	private void PreencheEvento(WebDriver driver, FotoTipo fotocapatipo) {
		driver.findElement(By.id("Evento_Nome")).clear();
		driver.findElement(By.id("Evento_Nome")).sendKeys(test.Num);
		new Select(driver.findElement(By.id("Evento_ApresentadorId"))).selectByIndex(1);
		new Select(driver.findElement(By.id("Evento_CanalId"))).selectByIndex(1);
		new Select(driver.findElement(By.id("Evento_Categoria"))).selectByIndex(Evento.IndexCategoria);
		Gerar.Aguarde(controleTempo * 2);
		driver.findElement(By.id("Evento_DescricaoGeral")).clear();
		driver.findElement(By.id("Evento_DescricaoGeral")).sendKeys("Venda de de Produtos");
		Gerar.Aguarde(controleTempo);
		new Select(driver.findElement(By.id("Evento_QuantidadeEventos"))).selectByIndex(Evento.IndexQtdEventos);
		Gerar.Aguarde(controleTempo * 2);

		System.out.println("IndexOferta=" + Evento.OfertaTipo.IndexOferta);

		new Select(driver.findElement(By.id("Evento_FormaOferta"))).selectByIndex(Evento.OfertaTipo.IndexOferta);
		Gerar.Aguarde(controleTempo * 3);

		String arquivo = null;
		try {
			arquivo = getCapa(fotocapatipo, null, test.Num);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Gerar.Aguarde(controleTempo * 2);
		driver.findElement(By.id("Evento_NovaFoto")).sendKeys(arquivo);
		Gerar.Aguarde(controleTempo * 4);
		new Select(driver.findElement(By.id("Evento_Tipo"))).selectByIndex(1);
		Gerar.Aguarde(controleTempo * 3);
		PreencheData(driver, "Evento_InicioExbicaoEm", Evento.DataEvento.HoraExibir);
		PreencheData(driver, "Evento_FimExbicaoEm", Evento.DataEvento.HoraFimExibir);

		new Select(driver.findElement(By.id("Evento_Configuracao_OpcaoDisputa")))
				.selectByIndex(Evento.IndexOpcaoDisputa);
		Gerar.Aguarde(controleTempo * 3);
		PreencheData(driver, "Evento_PrimeiroIniciaOuEncerraEm", Evento.DataEvento.InicioDisputaFake);

		if (Evento.OfertaTipo.PropostaTexto && Evento.OfertaTipo.IndexOferta != 1) {
			// proposta por texto
			driver.findElement(By.xpath("//*[@id=\"eventoTab\"]/div/div/div/div[11]/div/div/div[2]/div"))
					.sendKeys("Sua proposta deve ser encaminhada com o valor da entrada");
			Gerar.Aguarde(controleTempo);

		} else if (Evento.OfertaTipo.PropostaEmail && Evento.OfertaTipo.IndexOferta != 1) {
			driver.findElement(By.id("Evento_ArquivoModeloProposta"))
					.sendKeys("D:\\ricsistemas\\Documents\\Placar\\Documentos.fake\\Proposta_Email.docx");
			Gerar.Aguarde(controleTempo * 5);
			driver.findElement(By.xpath("//*[@id=\"eventoTab\"]/div/div/div/div[11]/div/div/div[2]/div"))
					.sendKeys("Baixe o arquivo MODELO preenche e envie sua proposta por email");

		}

		// TODO Eventos Quantidade
		if (Evento.IndexQtdEventos >= 2) {
			driver.findElement(By.id("Evento_SegundoIniciaOuEncerraEm")).click();
			PreencheData(driver, "Evento_SegundoIniciaOuEncerraEm", Evento.DataEvento.SegundaData);
			// Gerar.Aguarde(controleTempo);

		}

		if (Evento.IndexQtdEventos >= 3) {
			driver.findElement(By.id("Evento_TerceiroIniciaOuEncerraEm")).click();
			PreencheData(driver, "Evento_TerceiroIniciaOuEncerraEm", Evento.DataEvento.TerceiraData);

		}

		Salvar(driver);
		Gerar.Aguarde(controleTempo);
		if (Evento.IndexQtdEventos > 1) {
			driver.findElement(By.xpath("//*[@id=\"ConfirmacaoModal\"]/div[1]/div/div/div[3]/button[1]")).click();
			Gerar.Aguarde(controleTempo * 2);
		}
		Gerar.Aguarde(controleTempo);

	}

	private void AddProdutoEvento(WebDriver driver, Pedido ped) {

		BuscaProdutosEvento(driver, ped.Descricao);

		driver.findElement(By.id("Anuncio_PrimeiroValorInicial")).clear();
		driver.findElement(By.id("Anuncio_PrimeiroValorInicial")).sendKeys(ped.GetValor(0));

		Gerar.Aguarde(controleTempo);
		driver.findElement(By.id("Anuncio_PrimeiroValorInicialDesconto")).clear();
		driver.findElement(By.id("Anuncio_PrimeiroValorInicialDesconto")).sendKeys(ped.GetDesconto(0));

		if (Evento.IndexQtdEventos == 1) {
			driver.findElement(By.id("Anuncio_ValorMinimo")).clear();
			driver.findElement(By.id("Anuncio_ValorMinimo")).sendKeys(ped.GetValor(0));
			

		} else {

			if (Evento.IndexQtdEventos > 1) {
				driver.findElement(By.id("Anuncio_SegundoValorInicial")).clear();
				driver.findElement(By.id("Anuncio_SegundoValorInicial")).sendKeys(ped.GetValor(1));
				driver.findElement(By.id("Anuncio_SegundoValorInicialDesconto")).sendKeys(ped.GetDesconto(1));

			}
			if (Evento.IndexQtdEventos > 2) {

				driver.findElement(By.id("Anuncio_TerceiroValorInicial")).clear();
				driver.findElement(By.id("Anuncio_TerceiroValorInicial")).sendKeys(ped.GetValor(2));
				driver.findElement(By.id("Anuncio_TerceiroValorInicialDesconto")).sendKeys(ped.GetDesconto(2));

			}
		}
		driver.findElement(By.id("Anuncio_Incremento")).sendKeys(ped.Incremento);
		driver.findElement(By.id("Anuncio_ValorAvaliado")).sendKeys(ped.Avaliado);
			
		
		

		if (Evento.Pagamento.Quero_financiar)
			driver.findElement(By.id("Anuncio_Financiavel")).click();

		driver.findElement(By.id("Anuncio_UriFinanciamentoExterno")).sendKeys(ped.Url);

		if (Evento.Pagamento.Parcelado)
			driver.findElement(By.id("Anuncio_Parcelamento")).click();

		if (Evento.Pagamento.AVista)
			driver.findElement(By.id("Anuncio_AVista")).click();

		PreencheData(driver, "Anuncio_InicioDestaqueEm", Evento.DataEvento.HoraExibir);
		Salvar(driver);

	}

	private void BuscaProdutosEvento(WebDriver driver, String value)

	{

		driver.findElement(By.xpath("//*[@id=\"placeholder\"]/div[14]/div[1]/button[3]/i")).click();
		Gerar.Aguarde(controleTempo * 2);
		driver.findElement(By.id("FiltroProduto_Texto")).sendKeys(value);
		driver.findElement(By.xpath("//*[@id=\"placeholder\"]/div[1]/div[1]/div/button")).click(); // botao Lupa
		Gerar.Aguarde(controleTempo * 3);
		driver.findElement(By.xpath("//*[@id=\"produtosTab\"]/div/div/table/tbody/tr/td[1]/a/span")).click();
		Gerar.Aguarde(controleTempo * 2);

	}

	private void inserirFotos(WebDriver driver, FotoTipo fototipo, ArrayList<Foto> ListaFoto, String num)
			throws Exception {
		var nfoto = 0;

		for (Foto item : ListaFoto) {
			Gerar.Aguarde(controleTempo * 2);
			nfoto++;
			var strDestino = redimensionaImg(item.local);

			if (nfoto == 1) {
				var foto = getCapa(fototipo, strDestino, num);
				driver.findElement(By.id("Multimidia_NovaImagem")).sendKeys(foto);
				Gerar.Aguarde(controleTempo * 2);

			} else {

				driver.findElement(By.id("Multimidia_NovaImagem")).sendKeys(strDestino);

				Gerar.Aguarde(controleTempo * 2);
			}
		}

	}

	private static String redimensionaImg(String arquivo) throws IOException {
		try {
			// int width = 868;
			// int height = 570;

			int width = 640;
			int height = 480;

			BufferedImage imagem = ImageIO.read(new File(arquivo));
			Path p = Paths.get(arquivo);
			Path folder = p.getParent();
			var strDestino = folder + "\\ReScale_" + p.getFileName();
			Image scaled = imagem.getScaledInstance(width, height, Image.SCALE_REPLICATE);
			BufferedImage bScaled = new BufferedImage(scaled.getWidth(null), scaled.getHeight(null),
					BufferedImage.TYPE_INT_RGB);
			bScaled.getGraphics().drawImage(scaled, 0, 0, null);

			File fDestino = new File(strDestino);
			ImageIO.write(bScaled, "jpg", fDestino);
			System.out.println("Arquivo=>" + strDestino);
			return strDestino;

		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

	private String getCapa(FotoTipo fototipo, String Texto, String num) throws Exception {
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
		} else if (fototipo == FotoTipo.capaGeral) {
			Aquiv = Caminho + "capa-leilao-diversos.jpg";
		} else

		if (fototipo == FotoTipo.Veiculo) {

			Aquiv = Texto;
			tam = 200;
			stroke = new BasicStroke(4f);

		} else

		if (fototipo == FotoTipo.Imovel) {

			Aquiv = Texto;
			tam = 200;
			stroke = new BasicStroke(6f);

		}

		else

		if (fototipo == FotoTipo.Geral) {

			Aquiv = Texto;
			tam = 200;
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
		Gerar.Aguarde(controleTempo);

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
		Gerar.Aguarde(controleTempo);
	}

	private void Salvar(WebDriver driver) {

		driver.findElement(By.xpath("//*[@id=\"placeholder\"]/div[1]/div[1]/button")).click();
		Gerar.Aguarde(controleTempo * 3);

	}

}
