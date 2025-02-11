package up_backoffice;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import mock.EnderecoMock;
import mock.GeralMock;
import mock.ImovelMock;
import mock.VeiculoMock;
import model.Comitente;
import model.Evento;
import model.Foto;
import model.FotoTipo;
import model.Login;
import model.Pedido;
import model.Veiculo;
import model.vTest;

public class BaseEventos extends Login {
	int controleTempo = TestControleTempo;
	public vTest test = new vTest(num_reuso);
	protected Comitente Comitente = new Comitente(test);

	protected Evento Evento = new Evento(test.getTipoEnvento());
	protected int nAux;

	protected boolean ExistEvento(WebDriver driver) {
		// TODO Auto-generated method stub

		Aguarde(controleTempo * 2);
		driver.findElement(By.partialLinkText("Eventos")).click();
		Aguarde(controleTempo * 2);
		driver.findElement(By.id("Filtro_Texto")).sendKeys(String.format("%d -", num_reuso));
		Aguarde(controleTempo * 2);
		driver.findElement(By.xpath("//*[@id=\"placeholder\"]/div[1]/div/div/button[1]")).click();
		Aguarde(controleTempo * 2);
		var str = driver.findElement(By.cssSelector("#placeholder>div.container-fluid > h2")).getText();

		var EventoNulo = (str.equals("Resultado da Pesquisa (0)"));

		if (!EventoNulo) {
			driver.findElement(By.xpath("//*[@id=\"placeholder\"]/div[2]/div/table/tbody/tr/td[1]/a")).click();
			Aguarde(controleTempo * 2);

			var este = new Select(driver.findElement(By.id("Evento_QuantidadeEventos"))).getFirstSelectedOption()
					.getAttribute("value");

			nAux = Integer.parseInt(este);
			Evento.IndexOpcaoDisputa = nAux;
			Evento.IndexQtdEventos = nAux;
			return true;

		} else {

			return false;

		}

	}

	protected GeralMock CadastrarGeral(WebDriver driver) throws Exception {
		System.out.println("***********************  CadastrarGeral  ********************");
		var ge = new GeralMock(test);
		var ped = new Pedido("Geral");

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

	private void PreecheGeral(WebDriver driver, GeralMock ge) {
		System.out.println("***********************  PreecheGeral  ********************");

		driver.findElement(By.id("Produto_Marca")).sendKeys(ge.Marca);
		driver.findElement(By.id("Produto_Modelo")).sendKeys(ge.Modelo);
	}

	protected void NovoProduto(WebDriver driver, Pedido ped) {
		System.out.println("***********************  Novo Produto  ********************");

		driver.findElement(By.linkText("Produtos")).click();
		Aguarde(controleTempo * 2);
		driver.findElement(By.partialLinkText("Novo")).click();
		Aguarde(controleTempo);
		driver.findElement(By.id("Produto_Nome")).click();
		driver.findElement(By.id("Produto_Nome")).sendKeys(ped.Descricao);
		Aguarde(controleTempo * 2);

		new Select(driver.findElement(By.id("Produto_Tipo"))).selectByVisibleText(ped.Tipo);
		Aguarde(controleTempo * 3);

		new Select(driver.findElement(By.id("Produto_SubCategoriaCategoriaId")))
				.selectByVisibleText(ped.Categoria.Categoria);
		Aguarde(controleTempo * 3);
		new Select(driver.findElement(By.id("Produto_SubCategoriaId"))).selectByVisibleText(ped.Categoria.SubCategoria);
		Aguarde(controleTempo * 2);
		var valor = ped.GetValor(0);

		driver.findElement(By.id("Produto_ValorPedido")).sendKeys(valor);
		// driver.findElement(By.id("Produto_ValorAvaliacao")).sendKeys(ped.sAvaliado);
		new Select(driver.findElement(By.id("Produto_ComitenteId"))).selectByIndex(Comitente.IndexComitente);
		// TODO tipo do evento
		if (test.getJudicial()) {
			driver.findElement(By.id("Produto_Judicial")).click();
			Aguarde(controleTempo * 2);
			new Select(driver.findElement(By.id("Produto_ProcessoJuridicoId"))).selectByVisibleText(ped.Processo);
		}
//TODO EnderecoMock proximo
		var Endereco = new EnderecoMock(test);

		driver.findElement(By.id("Produto_Local_Cep")).sendKeys(Endereco.Cep);
		driver.findElement(By.className("fa-search")).click();
		Aguarde(controleTempo * 2);

		driver.findElement(By.id("Produto_Local_Numero")).sendKeys("100");

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

	protected void PreencheDataFake(WebDriver driver, boolean datafake) {

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

		Aguarde(250);
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
			Aguarde(500);

		}

	}

	protected void ValidaProdutoSalvo(WebDriver driver) {
		assertEquals(
				driver.findElement(By.cssSelector("div.alert.alert-dismissible.fade.show.alert-success")).getText(),
				"Salvo com sucesso.");

	}

	private void EnviarDocumentoEvento(WebDriver driver, int i, vTest test) {

		/*
		 * 
		 * if (!test.isDocs || !test.getJudicial()) { return; }
		 * 
		 * 
		 */

		System.out.println("***********************  Envia documento Evento  ********************");

		driver.findElement(By.className("fa-file-medical")).click();
		Aguarde(TestControleTempo * 2);
		var descricao = "";

		if (i == 0) {
			descricao = "Edital/Regulamento";
		} else {
			descricao = "Outros";
		}

		driver.findElement(By.id("Documento_Nome")).clear();
		driver.findElement(By.id("Documento_Nome")).sendKeys(descricao);
		new Select(driver.findElement(By.id("Documento_Tipo"))).selectByVisibleText(descricao);
		Aguarde(TestControleTempo / 4);
		driver.findElement(By.id("Documento_Arquivo")).sendKeys(DocumentoArquivoEvento[i]);
		Aguarde(TestControleTempo * 2);

		driver.findElement(By.xpath("//*[@id='placeholder']/div[1]/div[1]/button[1]")).click();
		Aguarde(TestControleTempo * 4);

	}

	protected int BuscaProdutosCadastro(WebDriver driver, String value)

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

	protected List<String> BuscaProdutosCadastroItens(WebDriver driver, String value)

	{
		System.out.println("***********************  Busca Produto Cadastro  ********************");

		driver.findElement(By.linkText("Produtos")).click();
		Aguarde(controleTempo * 2);
		driver.findElement(By.id("Filtro_Texto")).sendKeys(value);
		driver.findElement(By.xpath("//*[@id=\"placeholder\"]/div[1]/div/div/button[1]")).click();
		Aguarde(controleTempo * 2);
		List<WebElement> rowsList = driver.findElements(By.tagName("tr"));
		List<WebElement> lista = new ArrayList<>();
		List<String> listaLink = new ArrayList<>();

		for (WebElement e : rowsList) {

			lista.addAll(e.findElements(By.tagName("a")));

		}

		for (WebElement e : lista) {
			String s = e.getAttribute("href");
			if (s != null) {
				listaLink.add(s);
			}
		}

		return listaLink;

	}

	protected boolean BuscaProdutoCodigo(WebDriver driver, String value)

	{
		System.out.println("***********************  Busca Produto Cadastro  ********************");
		driver.findElement(By.linkText("Produtos")).click();
		Aguarde(controleTempo * 2);
		driver.findElement(By.id("Filtro_Texto")).sendKeys(value);
		driver.findElement(By.xpath("//*[@id=\"placeholder\"]/div[1]/div/div/button[1]")).click();
		Aguarde(controleTempo * 2);
		return (driver.findElement(By.id("Produto_Judicial")).getAttribute("checked") != null);

	}

	protected boolean BuscaCodigoProduto(WebDriver driver, String value)

	{
		System.out.println("***********************  Busca Produto Cadastro  ********************");
		driver.findElement(By.linkText("Produtos")).click();
		Aguarde(controleTempo * 2);
		driver.findElement(By.id("Filtro_Texto")).sendKeys(value);
		driver.findElement(By.xpath("//*[@id=\"placeholder\"]/div[1]/div/div/button[1]")).click();
		Aguarde(controleTempo * 2);

		// *[@id="placeholder"]/div[2]/div/table/tbody/tr/td[1]/a/span

		driver.findElement(By.xpath("//*[@id=\"placeholder\"]/div[2]/div/table/tbody/tr/td[1]/a")).click();
		return (driver.findElement(By.id("Produto_Judicial")).getAttribute("checked") != null);

	}

	protected ImovelMock CadastrarImovel(WebDriver driver) throws Exception {
		System.out.println("***********************  Cadastra Imovel  ********************");

		var im = new ImovelMock(test);

		var ped = new Pedido("Imóvel");
		ped.Categoria = im.Categoria;
		ped.Descricao = im.Nome;
		ped.SetValor(im.LanceInicial);
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

	private void PreecheImovel(WebDriver driver, ImovelMock im) {
		System.out.println("***********************  Preenche Imovel  ********************");

		new Select(driver.findElement(By.id("Produto_SituacaoOcupacao"))).selectByIndex(im.IndexOcupacao);
		new Select(driver.findElement(By.id("Produto_EstagioObra"))).selectByIndex(im.IndexEstagio);
	}

	private void MudarAddFoto(WebDriver driver) {
		driver.findElement(By.xpath("//*[@id=\"placeholder\"]/div[4]/div[1]/button[2]")).click();
		Aguarde(controleTempo * 3);

	}

	private void SalvarProduto(WebDriver driver) {
		driver.findElement(By.xpath("//*[@id=\"placeholder\"]/div[3]/div[1]/button[1]/i")).click(); // Salvar
		Aguarde(controleTempo * 7);
	}

	protected Veiculo CadastrarVeiculo(WebDriver driver) throws Exception {
		System.out.println("***********************  Cadastra Produto  ********************");

		var vcMock = new VeiculoMock(test);
		var vc = vcMock.getVeiculo();

		var ped = new Pedido("Veículo");

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

	protected void PreencheVeiculo(WebDriver driver, Veiculo vc) {
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
	protected void NovoEvento(WebDriver driver) {
		System.out.println("***********************  Novo Evento  ********************");

		Aguarde(controleTempo * 2);
		driver.findElement(By.partialLinkText("Eventos")).click();
		Aguarde(controleTempo * 3);
		driver.findElement(By.partialLinkText("Novo")).click();
		Aguarde(controleTempo * 2);

	}

	protected void PreencheEvento(WebDriver driver, FotoTipo fotocapatipo) {

		System.out.println("***********************  Preenche Evento ********************");

		if (test.getJudicial())
			Evento.Remanecente = false;//

		driver.findElement(By.id("Evento_Nome")).clear();
		driver.findElement(By.id("Evento_Nome")).sendKeys(String.format("%s - %s", test.Num, test.tipoEventoDesc));
		new Select(driver.findElement(By.id("Evento_ApresentadorId"))).selectByIndex(1);

		driver.findElement(By.id("Evento_Matricula")).clear();
		driver.findElement(By.id("Evento_Matricula")).sendKeys(String.format("Matriícula - %s", test.Num));
		new Select(driver.findElement(By.id("Evento_CanalId"))).selectByValue("B6389A16-652B-439F-AE33-B26C00F1CF11");
		new Select(driver.findElement(By.id("Evento_Categoria"))).selectByIndex(test.IndexCategoria);

		System.out.println("=>PreencheEvento Judicial:" + test.getJudicial());
		System.out.println("=>PreencheEvento IndexCategoria:" + test.IndexCategoria);

		Aguarde(controleTempo * 2);
		driver.findElement(By.id("Evento_Segmento")).clear();
		driver.findElement(By.id("Evento_Segmento")).sendKeys(Evento.Segmento);
		driver.findElement(By.id("Evento_DescricaoGeral")).clear();
		driver.findElement(By.id("Evento_DescricaoGeral")).sendKeys("Venda de " + Evento.Segmento);
		Aguarde(controleTempo);
		new Select(driver.findElement(By.id("Evento_QuantidadeEventos"))).selectByIndex(Evento.IndexQtdEventos);
		Aguarde(controleTempo * 3);

		if (Evento.Remanecente) {
			// driver.findElement(By.id("Evento_Remanescente")).click();
			System.out.println("O Remanecente foi Ignorado");

			Aguarde(controleTempo);
		}

		new Select(driver.findElement(By.id("Evento_FormaOferta"))).selectByIndex(Evento.OfertaTipo.getIndexOferta());
		Aguarde(controleTempo * 2);

		if (Evento.Pagamento.Parcelado) {

			driver.findElement(By.id("Evento_Configuracao_PermiteDisputaEntrada")).click();
			Aguarde(controleTempo / 4);
			driver.findElement(By.id("Evento_Configuracao_PermiteDisputaParcelas")).click();

		}

		if (Evento.OfertaTipo.PropostaTexto) {
			// proposta por texto
			var propostaTextoLocal = "/html/body/div[1]/div[3]/form/div/div[19]/div[2]/div[1]/div/div/div/div[17]/div/div/div[2]/div/p";
			driver.findElement(By.xpath(propostaTextoLocal)).click();
			driver.findElement(By.xpath(propostaTextoLocal))
					.sendKeys("Sua proposta deve ser encaminhada com o valor da entrada");
			Aguarde(controleTempo);
			fotocapatipo = FotoTipo.capaImovelPropostaTexto;
		}

		if (Evento.OfertaTipo.PropostaEmail) {
			driver.findElement(By.id("Evento_ArquivoModeloProposta"))
					.sendKeys("D:\\ricsistemas\\Documents\\Placar\\Documentos.fake\\Proposta_Email.docx");
			Aguarde(controleTempo * 5);
			driver.findElement(By.xpath(
					"/html/body/div[1]/div[3]/form/div/div[19]/div[2]/div[1]/div/div/div/div[17]/div/div/div[2]/div/p"))
					.sendKeys(" Baixe o arquivo MODELO preenche e envie sua proposta por E-mail");

			fotocapatipo = FotoTipo.capaImovelPropostaEmail;

		}

		if (Evento.OfertaTipo.PropostaValor) {
			fotocapatipo = FotoTipo.capaImovelPropostaValor;

		}

		// foto Capa

		ScrollDown(driver, "Evento_Tipo");

		PreencheFotoEvento(driver, fotocapatipo);
		Aguarde(controleTempo);
		System.out.println("=>test.Judicial:%s".formatted(test.getJudicial()));

		new Select(driver.findElement(By.id("Evento_Tipo"))).selectByIndex(Evento.IndexTipo);
		Aguarde(controleTempo * 4);

		PreencheData(driver, "Evento_InicioExbicaoEm", Evento.DataEvento.HoraExibir);
		// PreencheData(driver, "Evento_FimExbicaoEm", Evento.DataEvento.HoraFimExibir);
		if (!Evento.Online) {
			new Select(driver.findElement(By.id("Evento_Configuracao_OpcaoDisputa")))
					.selectByIndex(Evento.IndexOpcaoDisputa);
			Aguarde(controleTempo * 3);

			// ScrollDown(driver, "Evento_Configuracao_DuracaoDisputa");
		}
		PreencheDataFake(driver, Evento.DataEvento.fake);

		if (Evento.OfertaAutomatica) {
			driver.findElement(By.id("Evento_Configuracao_PermiteOfertaAutomatica")).click();
		}

		if (Evento.ComDisputa && !Evento.Online) {
			driver.findElement(By.id("Evento_Configuracao_DuracaoDisputa")).clear();

			driver.findElement(By.id("Evento_Configuracao_DuracaoDisputa"))
					.sendKeys(String.valueOf(Evento.DataEvento.DuracaoDisputa));
		}

		if (Evento.DataEvento.AntDisputa > 1 && Evento.ComDisputa) {
			driver.findElement(By.id("Evento_Configuracao_AntecipacaoDisputa")).clear();
			driver.findElement(By.id("Evento_Configuracao_AntecipacaoDisputa"))
					.sendKeys(String.valueOf(Evento.DataEvento.AntDisputa));
		}
		if (Evento.DataEvento.AtrasoDisputa > 1 && Evento.ComDisputa) {
			driver.findElement(By.id("Evento_Configuracao_AtrasoDisputa")).clear();
			driver.findElement(By.id("Evento_Configuracao_AtrasoDisputa"))
					.sendKeys(String.valueOf(Evento.DataEvento.AtrasoDisputa));
		}

		SalvarEvento(driver, true); //

		if (Evento.DataEvento.fake) {

			ScrollUp(driver, "Evento_Tipo");
			PreencheDataFake(driver, false);
		}
/* Erro 
		if (!DocumentoExist(driver)) {
			EnviarDocumentoEvento(driver, 0, test);
			SalvarEvento(driver, true); // Salva sempre Documento
		}
		
	*/	
		

		if (Evento.DataEvento.fake || test.isDocs) {
			SalvarEvento(driver, true);
		}

		Aguarde(controleTempo);

	}

	protected void AddProdutoEvento(WebDriver driver, Pedido ped, boolean busca) {
		System.out.println("***********************  AddProduto  ********************");

		if (busca) {
			BuscaProdutosEvento(driver, ped.Descricao);
		}

		driver.findElement(By.id("Anuncio_PrimeiroValorInicial")).clear();
		driver.findElement(By.id("Anuncio_PrimeiroValorInicial")).sendKeys(ped.GetValor(0));

		Aguarde(controleTempo);
		driver.findElement(By.id("Anuncio_PrimeiroValorInicialDesconto")).clear();
		driver.findElement(By.id("Anuncio_PrimeiroValorInicialDesconto")).sendKeys(ped.Desconto);
		// Aguarde(controleTempo);

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

		Aguarde(5000);

		// driver.findElement(By.id("Anuncio_ValorAvaliado")).sendKeys(ped.sAvaliado);

		if (Evento.Pagamento.Quero_financiar) {
			driver.findElement(By.id("Anuncio_Financiavel")).click();
			Aguarde(1000);
		}

//		driver.findElement(By.id("Anuncio_Incremento")).sendKeys("100000");	

		driver.findElement(By.id("Anuncio_UriFinanciamentoExterno")).sendKeys(ped.Url);

		if (Evento.Pagamento.Parcelado) {
			Aguarde(3000);

			driver.findElement(By.id("Anuncio_Parcelamento")).click();
			Aguarde(3000);
			driver.findElement(By.id("Anuncio_MaximoParcelas")).sendKeys("36");
			Aguarde(250);
			driver.findElement(By.id("Anuncio_PercentualEntradaMinima")).sendKeys("10");
			Aguarde(250);
			System.out.println("Prioridade:" + Evento.Pagamento.Prioridade);

			new Select(driver.findElement(By.id("Anuncio_Prioridade")))
					.selectByVisibleText(Evento.Pagamento.Prioridade);
			Aguarde(1000);

			// driver.findElement(By.xpath("//*[@id=\"anuncioTab\"]/div/div/div[1]/div[25]/div/div/div[2]/div/p")).sendKeys("As
			// condições estão estabelecidas no editar, Favor leia o edital");

		}

		if (Evento.Pagamento.AVista) {
			// driver.findElement(By.id("Anuncio_AVista")).click();
		}

		if (ped.Num == 1) {
			PreencheDataExibicao(driver, "Anuncio_InicioDestaqueEm", Evento.DataEvento.HoraExibir);

		}

		driver.findElement(By.id("Anuncio_Incremento")).sendKeys(ped.Incremento);
		SalvarEvento(driver, false);
		// Aguarde(controleTempo * 2);

	}

	protected void AddCodigoProdutoEvento(WebDriver driver, String codigo) {
		System.out.println("***********************  AddProduto  ********************");

		BuscaProdutosEvento(driver, codigo);

		var ped = new Pedido("");
		ped.Num = nAux;
		ped.Url = Comitente.UrlFinaceira;
		ped.Descricao = codigo;

		ped.Incremento = "100000";

		Aguarde(1000);
		var avaliado = driver.findElement(By.id("Anuncio_ValorAvaliado")).getAttribute("value");

		try {
		  String semponto = avaliado.replaceAll("[()-.\"]", "");
			var valor =  Double.parseDouble(semponto);

		} catch (Exception ex) {
			avaliado = "10.000,00";

		}

		ped.SetValor(avaliado);
		AddProdutoEvento(driver, ped, false);

	}

	private void PreencheFotoEvento(WebDriver driver, FotoTipo fotocapatipo) {
		String arquivo = null;

		try {
			arquivo = getCapa(fotocapatipo, null, test.Num);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail("fotocapatipo=>Arquivo invalido:" + arquivo);
		}

		if (!CapaExiste(driver)) {
			driver.findElement(By.id("Evento_NovaFotoProduto")).sendKeys(arquivo);
			Aguarde(controleTempo * 4);
		}
		// foto banner
		if (!BannerExiste(driver)) {
			driver.findElement(By.id("Evento_NovaFotoBanner")).sendKeys(getBanner(fotocapatipo));
			Aguarde(controleTempo * 5);

		}

	}

	private void SalvarEvento(WebDriver driver, boolean EventoSalvar) {

		driver.findElement(By.xpath("//*[@id=\"placeholder\"]/div[1]/div[1]/button")).click();
		Aguarde(controleTempo * 4);
		// caso aparer pop menssagem de confirmação
		if ((Evento.IndexQtdEventos > 1) && EventoSalvar) {
			driver.findElement(By.xpath("//*[@id=\"ConfirmacaoModal\"]/div[1]/div/div/div[3]/button[1]")).click();
			Aguarde(controleTempo * 4);
		}

	}

	private void BuscaProdutosEvento(WebDriver driver, String value)

	{
		System.out.println("***********************  Busca Produto  ********************");

		driver.findElement(By.xpath("//*[@id=\"placeholder\"]/div[19]/div[1]/button[3]")).click();

		Aguarde(controleTempo * 2);
		driver.findElement(By.id("FiltroProduto_Texto")).sendKeys(value);
		Aguarde(controleTempo);
		driver.findElement(By.xpath("//*[@id=\"placeholder\"]/div[1]/div[1]/div/button")).click(); // botao Lupa
		Aguarde(controleTempo * 3);
		driver.findElement(By.xpath("//*[@id=\"produtosTab\"]/div/div/table/tbody/tr/td[1]/a/span")).click();
		Aguarde(controleTempo * 2);

	}

	protected void inserirFotos(WebDriver driver, FotoTipo fototipo, ArrayList<Foto> ListaFoto, String num)
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

	protected void NovoTest() {

		test = new vTest();// novo test
		Comitente = new Comitente(test);
		Evento = new Evento(test.getTipoEnvento());

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

		test.isDocs = trs.size() >= 1;
		// test.isDocs = true;
		return test.isDocs;
	}

}
