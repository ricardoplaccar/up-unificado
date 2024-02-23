package up_backoffice;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.WheelInput;
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

class EventoTest extends Constants {

	int controleTempo = TestControleTempo;

	public vTest test = new vTest(num_reuso);
	private Comitente Comit = new Comitente(test);

	private Evento Evento = new Evento(test.tipoEnvento);
	private int nAux;

//	@Test
	void DocumentoTest() throws Exception {
		var EventoPerdido = "(" + num_reuso + ")";
		if (num_reuso == 0)
			Assert.fail("num_reuso = 0");

		WebDriver driver = LoginTest.IniciaLogin();
//		ArrayList<Pedido> ListaPedidos = new ArrayList<>();
		var nrow = 99;
		var linhas = 0;
		do {
			linhas = BuscaProdutosCadastro(driver, EventoPerdido);
			if (nrow == 99) {
				nrow = linhas;
				System.out.println("Linhas(%s)".formatted(nrow));

			}
			nrow--;

		} while (nrow != 0);
		System.out.println("row =" + nrow);
		clickItemCadastroProduto(driver, nrow + 1);

		Aguarde(controleTempo * 2);

		var isJuridico = (driver.findElement(By.id("Produto_Judicial")).getAttribute("checked") != null);
		System.out.println("Juridico =" + isJuridico);

		/*
		 * 
		 * WebElement iframe = driver.findElement(By.id("Produto_Local_Cep")); new
		 * Actions(driver) .scrollToElement(iframe) .perform();
		 * 
		 */

		ScrollDown(driver, "Produto_Local_Cep");

		//

// Cria evento //

	}

	private void ScrollDown(WebDriver driver, String id) {
		// TODO Auto-generated method stub
		WebElement iframe = driver.findElement(By.id(id));
		WheelInput.ScrollOrigin scrollOrigin = WheelInput.ScrollOrigin.fromElement(iframe);
		new Actions(driver).scrollFromOrigin(scrollOrigin, 0, 300).perform();

	}

	@Test
	void Aproveitar_Produto() throws Exception {

		FotoTipo tipoEvento = null;
		var EventoPerdido = "(" + num_reuso + ")";
		if (num_reuso == 0)
			if (num_reuso == 0)
				Assert.fail("num_reuso = 0");

		WebDriver driver = LoginTest.IniciaLogin();
		ArrayList<Pedido> ListaPedidos = new ArrayList<>();
		var nrow = 99;
		var linhas = 0;
		do {
			linhas = BuscaProdutosCadastro(driver, EventoPerdido);
			if (nrow == 99) {
				nrow = linhas;
				System.out.println("Linhas(%s)".formatted(nrow));

			}
			System.out.println("row =" + nrow);
			clickItemCadastroProduto(driver, nrow);
			Aguarde(controleTempo * 2);
			var ped = new Pedido();
			var lanceInicial = driver.findElement(By.id("Produto_ValorPedido")).getAttribute("value");
			var avaliado = driver.findElement(By.id("Produto_ValorAvaliacao")).getAttribute("value");
			var isJuridico = (driver.findElement(By.id("Produto_Judicial")).getAttribute("checked") != null);
			test.setJudicial(isJuridico);

			if (lanceInicial.length() < 2) {
				lanceInicial = avaliado;
				System.out.println("Lance Inicial Atualizado =" + lanceInicial);

			}

			var select = new Select(driver.findElement(By.id("Produto_Tipo")));
			ped.Descricao = driver.findElement(By.id("Produto_Nome")).getAttribute("value");
			ped.SetValor(lanceInicial);
			ped.SetAvaliado(avaliado);
			ped.Incremento = "1000,00";

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

			ListaPedidos.add(ped);
			nrow--; // começa com ate 0

		} while (nrow != 0);

// Cria evento //

		if (ExistEvento(driver))

		{

			System.out.println("Evento Editar:" + num_reuso);

			PreencheEvento(driver, tipoEvento);

		} else {

			System.out.println("Evento Novo:" + num_reuso);

			NovoEvento(driver);
			PreencheEvento(driver, tipoEvento);

		}

		nAux = 1;
		ListaPedidos.forEach(vc -> {
			System.out.println(String.format("AddProduto(%d/%d)", nAux, ListaPedidos.size()));
			var ped = new Pedido();
			ped.Descricao = vc.Descricao;
			ped.SetValor(vc.GetValor(0));
			ped.Incremento = vc.Incremento;
			ped.SetAvaliado(vc.sAvaliado);
			ped.Url = Comit.UrlFinaceira;
			AddProdutoEvento(driver, ped);
			nAux++;

		});
		test.SalvaTipoEnvento();
	}

	private boolean ExistEvento(WebDriver driver) {
		// TODO Auto-generated method stub

		Aguarde(controleTempo * 2);
		driver.findElement(By.partialLinkText("Eventos")).click();
		Aguarde(controleTempo * 2);
		driver.findElement(By.id("Filtro_Texto")).sendKeys(String.valueOf(num_reuso));
		Aguarde(controleTempo * 2);
		driver.findElement(By.xpath("//*[@id=\"placeholder\"]/div[1]/div/div/button[1]")).click();
		Aguarde(controleTempo * 2);
		var str = driver.findElement(By.cssSelector("#placeholder>div.container-fluid > h2")).getText();

		var isEvento = (str.equals("Resultado da Pesquisa (0)"));

		if (!isEvento) {
			driver.findElement(By.xpath("//*[@id=\"placeholder\"]/div[2]/div/table/tbody/tr/td[1]/a")).click();
			Aguarde(controleTempo * 2);

			return true;

		} else {

			return false;

		}

	}

	@Test
	void CadastarEvento_Veiculo_Unico_Deve_Retornar_sucesso() throws Exception {

		if (num_reuso != 0)
			Assert.fail("num_reuso != 0");

		ArrayList<Veiculo> ListaVeiculos = new ArrayList<>();
		WebDriver driver = LoginTest.IniciaLogin();
		for (var i = 0; i < loteVeiculoQtd; i++) {
			var vc = CadastrarVeiculo(driver);
			ListaVeiculos.add(vc);
		}

		// *************************** Cadastra Eventos ****************

		NovoEvento(driver);
		PreencheEvento(driver, FotoTipo.capaVeiculo);

		// editar produto
		ListaVeiculos.forEach(vc -> {
			var ped = new Pedido();
			ped.Descricao = vc.Nome;
			ped.SetValor(vc.LanceInicial);
			ped.SetAvaliado(vc.Avaliado);
			ped.Incremento = vc.Incremento;
			ped.Url = Comit.UrlFinaceira;
			AddProdutoEvento(driver, ped);

		});
		test.SalvaTipoEnvento();
	}

	@Test
	void CadastarEvento_Geral_Unico_Deve_Retornar_sucesso() throws Exception {

		if (num_reuso != 0)
			Assert.fail("num_reuso != 0");

		WebDriver driver = LoginTest.IniciaLogin();
		ArrayList<Geral> ListaGeral = new ArrayList<>();
		for (var i = 0; i < loteGeralQtd; i++) {
			var ge = CadastrarGeral(driver);
			ListaGeral.add(ge);
			System.out.println("Diversos=" + (i + 1) + "/" + loteGeralQtd);
		}

		// *************************** Evento Geral ***********************************

		NovoEvento(driver);
		PreencheEvento(driver, FotoTipo.capaGeral);

		ListaGeral.forEach(ge -> {
			var ped = new Pedido();
			ped.Descricao = ge.Descricao;
			ped.Url = Comit.UrlFinaceira;
			ped.SetValor(ge.LanceInicial);
			ped.SetAvaliado(ge.Avaliado);
			ped.Incremento = ge.Incremento;

			AddProdutoEvento(driver, ped);

		});
		// *************************** Evento Geral ***********************************
		test.SalvaTipoEnvento();
	}

	private Geral CadastrarGeral(WebDriver driver) throws Exception {
		System.out.println("***********************  CadastrarGeral  ********************");
		var ge = new Geral(test);
		var ped = new Pedido();

		ped.Categoria = ge.Categoria;
		ped.Descricao = ge.Descricao;
		ped.SetValor(ge.LanceInicial);
		ped.SetAvaliado(ge.Avaliado);
		ped.Incremento = ge.Incremento;

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
		System.out.println("***********************  PreecheGeral  ********************");

		driver.findElement(By.id("Produto_Marca")).sendKeys(ge.Marca);
		driver.findElement(By.id("Produto_Modelo")).sendKeys(ge.Modelo);
	}

	@Test
	void CadastarEvento_Imovel_Unico_Deve_Retornar_sucesso() throws Exception {

		if (num_reuso != 0)
			Assert.fail("num_reuso != 0");

		WebDriver driver = LoginTest.IniciaLogin();
		ArrayList<Imovel> ListaImoveis = new ArrayList<>();
		for (var i = 0; i < loteImoveisQtd; i++) {
			var im = CadastrarImovel(driver);
			ListaImoveis.add(im);
			System.out.println("Imovel= %d/%d".formatted((i + 1), loteImoveisQtd));
		}

		// *************************** Evento Imovel ***********************************

		NovoEvento(driver);
		PreencheEvento(driver, FotoTipo.capaImovel);

		ListaImoveis.forEach(im -> {
			var ped = new Pedido();
			ped.Descricao = im.Nome;
			ped.Url = Comit.UrlFinaceira;
			ped.SetValor(im.LancInicial);
			ped.SetAvaliado(im.Avaliado);
			ped.Incremento = "1.000,00";
			AddProdutoEvento(driver, ped);

		});
		// *************************** Evento Imovel ***********************************
		test.SalvaTipoEnvento();
	}

	// *************************** Funcoes ***********************************
	private void EnviarDocumentoEvento(WebDriver driver, int i, vTest test) {

		if (!test.isDocs)
			return;

		System.out.println("***********************  Envia documento Evento  ********************");

		driver.findElement(By.xpath("//*[@id=\"eventoTab\"]/div/div/div/h3[5]/button")).click();
		Aguarde(TestControleTempo * 2);
		var descricao = "";

		if (i == 0)
			descricao = "Edital/Regulamento";
		else
			descricao = "Outros";

		driver.findElement(By.id("Documento_Nome")).clear();
		driver.findElement(By.id("Documento_Nome")).sendKeys(descricao);
		new Select(driver.findElement(By.id("Documento_Tipo"))).selectByVisibleText(descricao);
		Aguarde(TestControleTempo / 4);
		driver.findElement(By.id("Documento_Arquivo")).sendKeys(DocumentoArquivoEvento[i]);
		Aguarde(TestControleTempo * 2);

		driver.findElement(By.xpath("//*[@id='placeholder']/div[1]/div[1]/button[1]")).click();
		Aguarde(TestControleTempo * 4);

	}

	private int BuscaProdutosCadastro(WebDriver driver, String value)

	{
		System.out.println("***********************  Busca Produto Cadastro  ********************");

		driver.findElement(By.linkText("Produtos")).click();
		Aguarde(controleTempo * 2);
		driver.findElement(By.id("Filtro_Texto")).sendKeys(value);
		driver.findElement(By.xpath("//*[@id=\"placeholder\"]/div[1]/div/div/button[1]")).click();
		Aguarde(controleTempo * 2);
		List<WebElement> rowsList = driver.findElements(By.tagName("tr"));
		return rowsList.size() - 1;

	}

	private void clickItemCadastroProduto(WebDriver driver, int item) {
		var nRow = "//*[@id=\"placeholder\"]/div[2]/div/table/tbody/tr[%s]/td[1]".formatted(item);
		var row = driver.findElement(By.xpath(nRow)).getText();
		driver.findElement(By.linkText(row)).click();

	}

	private Imovel CadastrarImovel(WebDriver driver) throws Exception {
		System.out.println("***********************  Cadastra Imovel  ********************");

		var im = new Imovel(test);

		var ped = new Pedido();
		ped.Categoria = im.Categoria;
		ped.Descricao = im.Nome;
		ped.SetValor(im.LancInicial);
		ped.Incremento = "1.000,00";
		ped.SetAvaliado(im.Avaliado);
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
		System.out.println("***********************  Preenche Imovel  ********************");

		new Select(driver.findElement(By.id("Produto_SituacaoOcupacao"))).selectByIndex(im.IndexOcupacao);
		new Select(driver.findElement(By.id("Produto_EstagioObra"))).selectByIndex(im.IndexEstagio);
	}

	private void MudarAddFoto(WebDriver driver) {
		driver.findElement(By.xpath("//*[@id=\"placeholder\"]/div[4]/div[1]/button[2]")).click();
		Aguarde(controleTempo * 3);

	}

	private void NovoProduto(WebDriver driver, Pedido ped) {
		System.out.println("***********************  Novo Produto  ********************");

		driver.findElement(By.linkText("Produtos")).click();
		Aguarde(controleTempo * 2);
		driver.findElement(By.partialLinkText("Novo")).click();
		Aguarde(controleTempo);
		driver.findElement(By.id("Produto_Nome")).click();
		driver.findElement(By.id("Produto_Nome")).sendKeys(ped.Descricao);
		Aguarde(controleTempo * 2);

		new Select(driver.findElement(By.id("Produto_Tipo"))).selectByVisibleText(ped.Categoria.Categoria);
		Aguarde(controleTempo * 3);

		new Select(driver.findElement(By.id("Produto_SubCategoriaCategoriaId")))
				.selectByVisibleText(ped.Categoria.Categoria);
		Aguarde(controleTempo * 3);
		new Select(driver.findElement(By.id("Produto_SubCategoriaId"))).selectByVisibleText(ped.Categoria.SubCategoria);
		Aguarde(controleTempo * 2);
		var valor = ped.GetValor(0);

		driver.findElement(By.id("Produto_ValorPedido")).sendKeys(valor);
		driver.findElement(By.id("Produto_ValorAvaliacao")).sendKeys(ped.sAvaliado);
		new Select(driver.findElement(By.id("Produto_ComitenteId"))).selectByIndex(Comit.IndexComitente);
		// TODO tipo do evento
		if (test.Judicial) {
			driver.findElement(By.id("Produto_Judicial")).click();
			Aguarde(controleTempo * 3);
			new Select(driver.findElement(By.id("Produto_ProcessoJuridicoId"))).selectByVisibleText(ped.Processo);
		}
//TODO Endereco proximo
		var Endereco = new Endereco(test);

		driver.findElement(By.id("Produto_Local_Cep")).sendKeys(Endereco.Cep);
		Aguarde(controleTempo);
		driver.findElement(By.id("Produto_Local_Numero")).click();
		Aguarde(controleTempo);
		driver.findElement(By.id("Produto_Local_Numero")).sendKeys("100");
		Aguarde(controleTempo * 3);
		String s = driver.findElement(By.id("Produto_Local_Logradouro")).getAttribute("value");
		driver.findElement(By.id("Produto_Local_Numero")).click();
		Aguarde(controleTempo);
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
		Aguarde(controleTempo * 7);
	}

	private Veiculo CadastrarVeiculo(WebDriver driver) throws Exception {
		System.out.println("***********************  Cadastra Produto  ********************");

		var vc = new Veiculo(test);
		var ped = new Pedido();

		ped.Descricao = vc.Nome;
		ped.SetValor(vc.LanceInicial);
		ped.Categoria = vc.Categoria;
		ped.Incremento = vc.Incremento;
		ped.SetAvaliado(vc.Avaliado);

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
		Aguarde(controleTempo);

	}

	// TODO Cadastra Evento
	private void NovoEvento(WebDriver driver) {
		System.out.println("***********************  Novo Evento  ********************");

		Aguarde(controleTempo * 2);
		driver.findElement(By.partialLinkText("Eventos")).click();
		Aguarde(controleTempo * 3);
		driver.findElement(By.partialLinkText("Novo")).click();
		Aguarde(controleTempo * 2);

	}

	private void PreencheEvento(WebDriver driver, FotoTipo fotocapatipo) {

		System.out.println("***********************  Preenche Evento ********************");

		driver.findElement(By.id("Evento_Nome")).clear();
		driver.findElement(By.id("Evento_Nome")).sendKeys(test.Num + " - " + test.tipoEventoDesc);
		new Select(driver.findElement(By.id("Evento_ApresentadorId"))).selectByVisibleText("VICENTE PAULO");
		new Select(driver.findElement(By.id("Evento_CanalId"))).selectByVisibleText("Público (1)");
		new Select(driver.findElement(By.id("Evento_Categoria"))).selectByIndex(test.IndexCategoria);
		Aguarde(controleTempo * 2);
		driver.findElement(By.id("Evento_Segmento")).clear();
		driver.findElement(By.id("Evento_Segmento")).sendKeys(getSegmento(fotocapatipo));
		driver.findElement(By.id("Evento_DescricaoGeral")).clear();
		driver.findElement(By.id("Evento_DescricaoGeral")).sendKeys("Venda de de Produtos");
		Aguarde(controleTempo);
		new Select(driver.findElement(By.id("Evento_QuantidadeEventos"))).selectByIndex(Evento.IndexQtdEventos);
		Aguarde(controleTempo * 3);
		new Select(driver.findElement(By.id("Evento_FormaOferta"))).selectByIndex(Evento.OfertaTipo.getIndexOferta());
		Aguarde(controleTempo * 4);

		if (Evento.OfertaTipo.PropostaTexto) {
			// proposta por texto
			driver.findElement(By.xpath(
					"/html/body/div[1]/div[3]/form/div/div[14]/div[2]/div[1]/div/div/div/div[12]/div/div/div[2]/div/p"))
					.click();
			driver.findElement(By.xpath(
					"/html/body/div[1]/div[3]/form/div/div[14]/div[2]/div[1]/div/div/div/div[12]/div/div/div[2]/div/p"))
					.sendKeys("Sua proposta deve ser encaminhada com o valor da entrada");

			Aguarde(controleTempo);
		}

		if (Evento.OfertaTipo.PropostaEmail) {
			driver.findElement(By.id("Evento_ArquivoModeloProposta"))
					.sendKeys("D:\\ricsistemas\\Documents\\Placar\\Documentos.fake\\Proposta_Email.docx");
			Aguarde(controleTempo * 5);
			driver.findElement(By.xpath(
					"/html/body/div[1]/div[3]/form/div/div[14]/div[2]/div[1]/div/div/div/div[12]/div/div/div[2]/div/p"))
					.sendKeys(" Baixe o arquivo MODELO preenche e envie sua proposta por email");
		}

		// foto Capa

		ScrollDown(driver, "Evento_Tipo");

		PreencheFotos(driver, fotocapatipo);

		new Select(driver.findElement(By.id("Evento_Tipo"))).selectByIndex(Evento.IndexTipo);
		Aguarde(controleTempo * 4);
		PreencheData(driver, "Evento_InicioExbicaoEm", Evento.DataEvento.HoraExibir);
		// PreencheData(driver, "Evento_FimExbicaoEm", Evento.DataEvento.HoraFimExibir);

		new Select(driver.findElement(By.id("Evento_Configuracao_OpcaoDisputa")))
				.selectByIndex(Evento.IndexOpcaoDisputa);
		Aguarde(controleTempo * 3);
		if (Evento.ComDisputa) {
			driver.findElement(By.id("Evento_Configuracao_DuracaoDisputa")).clear();

			driver.findElement(By.id("Evento_Configuracao_DuracaoDisputa"))
					.sendKeys(String.valueOf(Evento.DuracaoDisputa));

		}

		PreencheDataFake(driver, true);
		Salvar(driver, true); //

		if (!Evento.DataEvento.fake)
			PreencheDataFake(driver, false);

		if (!DocumentoExist(driver)) {

			EnviarDocumentoEvento(driver, 0, test);
			EnviarDocumentoEvento(driver, 2, test);
		}

		Salvar(driver, true);

		Aguarde(controleTempo);

	}

	private void PreencheDataFake(WebDriver driver, boolean datafake) {

		String DataInicioDisputa = null;
		String SegundaData = null;
		String TerceiraData = null;

		if (datafake) {
			DataInicioDisputa = Evento.DataEvento.InicioDisputaFake;
			SegundaData = Evento.DataEvento.SegundaDataFake;
			TerceiraData = Evento.DataEvento.TerceiraDataFake;
		} else {
			DataInicioDisputa = Evento.DataEvento.InicioDisputaReal;
			SegundaData = Evento.DataEvento.SegundaData;
			TerceiraData = Evento.DataEvento.TerceiraData;

		}

		PreencheData(driver, "Evento_PrimeiroIniciaOuEncerraEm", DataInicioDisputa);

		// TODO Eventos Quantidade
		if (Evento.IndexQtdEventos >= 2) {
			driver.findElement(By.id("Evento_SegundoIniciaOuEncerraEm")).click();
			PreencheData(driver, "Evento_SegundoIniciaOuEncerraEm", SegundaData);
			Aguarde(controleTempo);
			// Aguarde(controleTempo);

		}

		if (Evento.IndexQtdEventos >= 3) {
			driver.findElement(By.id("Evento_TerceiroIniciaOuEncerraEm")).click();
			PreencheData(driver, "Evento_TerceiroIniciaOuEncerraEm", TerceiraData);

		}

	}

	private void PreencheFotos(WebDriver driver, FotoTipo fotocapatipo) {
		String arquivo = null;

		try {
			arquivo = getCapa(fotocapatipo, null, test.Num);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail("Arquivo invalido:" + arquivo);
		}

		if (Evento.OfertaTipo.PropostaTexto)

		{
			// foto Capa
			if (!CapaExiste(driver)) {
				driver.findElement(By.xpath(
						"/html/body/div[1]/div[3]/form/div/div[14]/div[2]/div[1]/div/div/div/div[13]/div/div/div[2]/label/input"))

						.sendKeys(arquivo);
				Aguarde(controleTempo * 4);
			}

			// foto banner
			if (!BannerExiste(driver)) {
				driver.findElement(By.xpath(
						"/html/body/div[1]/div[3]/form/div/div[14]/div[2]/div[1]/div/div/div/div[14]/div/div/div[2]/label/input"))
						.sendKeys(getBanner(fotocapatipo));
				Aguarde(controleTempo * 5);

			}
		} else if (Evento.OfertaTipo.PropostaEmail) {
			if (!CapaExiste(driver)) {
				driver.findElement(By.xpath(
						"/html/body/div[1]/div[3]/form/div/div[14]/div[2]/div[1]/div/div/div/div[14]/div/div/div[2]/label/input"))

						.sendKeys(arquivo);
				Aguarde(controleTempo * 4);
			}

			// foto banner
			if (!BannerExiste(driver)) {
				driver.findElement(By.xpath(
						"/html/body/div[1]/div[3]/form/div/div[14]/div[2]/div[1]/div/div/div/div[15]/div/div/div[2]/label/input"))
						.sendKeys(getBanner(fotocapatipo));
				Aguarde(controleTempo * 5);

			}

		} else

		if (!CapaExiste(driver)) {

			driver.findElement(By.xpath(
					"/html/body/div/div[3]/form/div/div[14]/div[2]/div[1]/div/div/div/div[12]/div/div/div[2]/label/input"))
					.sendKeys(arquivo);
			Aguarde(controleTempo * 4);
		}
		// foto banner
		if (!BannerExiste(driver)) {

			driver.findElement(By.xpath(
					"/html/body/div/div[3]/form/div/div[14]/div[2]/div[1]/div/div/div/div[13]/div/div/div[2]/label/input"))
					.sendKeys(getBanner(fotocapatipo));
			Aguarde(controleTempo * 5);

		}

	}

	private boolean BannerExiste(WebDriver driver) {
		// TODO Auto-generated method stub
		var temBanner = driver.findElement(By.id("img-Evento_UriBanner")).getAttribute("src");
		return temBanner.length() > 1;
	}

	private boolean CapaExiste(WebDriver driver) {
		// TODO Auto-generated method stub

		var temFoto = driver.findElement(By.id("img-Evento_UriFoto")).getAttribute("src");
		return temFoto.length() > 1;
	}

	private boolean DocumentoExist(WebDriver driver) {
		List<WebElement> trs = driver.findElements(By.cssSelector("tbody > tr"));
		return trs.size() > 1;
	}

	private void AddProdutoEvento(WebDriver driver, Pedido ped) {
		System.out.println("***********************  AddProduto  ********************");

		BuscaProdutosEvento(driver, ped.Descricao);

		driver.findElement(By.id("Anuncio_PrimeiroValorInicial")).clear();
		driver.findElement(By.id("Anuncio_PrimeiroValorInicial")).sendKeys(ped.GetValor(0));

		Aguarde(controleTempo);
		driver.findElement(By.id("Anuncio_PrimeiroValorInicialDesconto")).clear();
		driver.findElement(By.id("Anuncio_PrimeiroValorInicialDesconto")).sendKeys(ped.Desconto);

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
		driver.findElement(By.id("Anuncio_ValorAvaliado")).sendKeys(ped.sAvaliado);

		if (Evento.Pagamento.Quero_financiar)
			driver.findElement(By.id("Anuncio_Financiavel")).click();

		driver.findElement(By.id("Anuncio_UriFinanciamentoExterno")).sendKeys(ped.Url);

		if (Evento.Pagamento.Parcelado)
			driver.findElement(By.id("Anuncio_Parcelamento")).click();

		if (Evento.Pagamento.AVista)
			driver.findElement(By.id("Anuncio_AVista")).click();

		PreencheData(driver, "Anuncio_InicioDestaqueEm", Evento.DataEvento.HoraExibir);
		Salvar(driver, false);
		// Aguarde(controleTempo * 2);

	}

	private void BuscaProdutosEvento(WebDriver driver, String value)

	{
		System.out.println("***********************  Busca Produto  ********************");

		driver.findElement(By.xpath("//*[@id=\"placeholder\"]/div[14]/div[1]/button[3]/i")).click();
		Aguarde(controleTempo * 2);
		driver.findElement(By.id("FiltroProduto_Texto")).sendKeys(value);
		driver.findElement(By.xpath("//*[@id=\"placeholder\"]/div[1]/div[1]/div/button")).click(); // botao Lupa
		Aguarde(controleTempo * 3);
		driver.findElement(By.xpath("//*[@id=\"produtosTab\"]/div/div/table/tbody/tr/td[1]/a/span")).click();
		Aguarde(controleTempo * 2);

	}

	private void inserirFotos(WebDriver driver, FotoTipo fototipo, ArrayList<Foto> ListaFoto, String num)
			throws Exception {
		var nfoto = 0;
		System.out.println("***********************  Inserir fotos  ********************");

		for (Foto item : ListaFoto) {
			Aguarde(controleTempo * 2);
			nfoto++;
			var strDestino = redimensionaImg(item.local);

			if (nfoto == 1) {
				var foto = getCapa(fototipo, strDestino, num);
				driver.findElement(By.id("Multimidia_NovaImagem")).sendKeys(foto);
				Aguarde(controleTempo * 3);

			} else {

				driver.findElement(By.id("Multimidia_NovaImagem")).sendKeys(strDestino);

				Aguarde(controleTempo * 3);
			}
		}

	}

	private void Salvar(WebDriver driver, boolean EventoSalvar) {

		driver.findElement(By.xpath("//*[@id=\"placeholder\"]/div[1]/div[1]/button")).click();
		Aguarde(controleTempo * 4);
		// caso aparer pop menssagem de confirmação
		if ((Evento.IndexQtdEventos > 1) && EventoSalvar) {

			driver.findElement(By.xpath("//*[@id=\"ConfirmacaoModal\"]/div[1]/div/div/div[3]/button[1]")).click();
			Aguarde(controleTempo * 2);
		}

	}

}
