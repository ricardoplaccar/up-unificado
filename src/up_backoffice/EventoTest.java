package up_backoffice;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import mock.GeralMock;
import mock.ImovelMock;
import mock.VeiculoMock;
import model.FotoTipo;
import model.Pedido;
import model.Veiculo;

public class EventoTest extends BaseEventos {

//	@Test
	void DocumentoTest() throws Exception {
		var EventoPerdido = String.format("(%d)", num_reuso);
		if (num_reuso == 0) {
			Assert.fail("num_reuso = 0");
		}

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
		var isJuridico = (driver.findElement(By.id("Produto_Judicial")).getAttribute("checked") != null);
		System.out.println("Juridico =" + isJuridico);
		ScrollDown(driver, "Produto_Local_Cep");

		//

// Cria evento //

	}

	@Test
	public void CadastrarEvento_Lotear_Produto() throws Exception {

		FotoTipo tipoEvento = null;

		num_reuso = Integer.valueOf(test.LeiaControleLeilao());
		test.SetTest(num_reuso);
		test.setParcelamento(true);

		var EventoPerdido = String.format("(%s)", num_reuso);
		if (num_reuso == 0) {
			if (num_reuso == 0) {
				Assert.fail("num_reuso = 0");
			}
		}

		WebDriver driver = LoginTest.IniciaLogin();
		ArrayList<Pedido> ListaPedidos = new ArrayList<>();

		var listaProdutos = BuscaProdutosCadastroItens(driver, EventoPerdido);
		var nrow = 1;

		for (String linkProduto : listaProdutos) {

			System.out.println();
			System.out.println(String.format("Produto(s) %d/%d", nrow, listaProdutos.size()));
			System.out.println("--------------------------------------------------------------------");
			nrow++;

			// Troca de produto para leitura
			driver.get(linkProduto);
			// Aguarde(controleTempo);
			var ped = new Pedido();
			var lanceInicial = driver.findElement(By.id("Produto_ValorPedido")).getAttribute("value");
			var avaliado = driver.findElement(By.id("Produto_ValorAvaliacao")).getAttribute("value");
			var isJuridico = (driver.findElement(By.id("Produto_Judicial")).getAttribute("checked") != null);

			if (avaliado.length() < 2)
				avaliado = lanceInicial;

			System.out.println("isJudicial:%s".formatted(isJuridico));

			test.setJudicial(isJuridico);

			Evento.Pagamento.setPagamento(isJuridico);

			if (lanceInicial.length() < 2) {
				lanceInicial = avaliado;
				System.out.println("Lance Inicial Atualizado =" + lanceInicial);

			}

			var select = new Select(driver.findElement(By.id("Produto_Tipo")));
			WebElement option = select.getFirstSelectedOption();
			String Produto_Tipo = option.getText();

			ped.Descricao = driver.findElement(By.id("Produto_Nome")).getAttribute("value");
			ped.Tipo = Produto_Tipo;
			ped.SetValor(lanceInicial);
			ped.SetAvaliado(avaliado);
			ped.Incremento = "1000";

			if (Produto_Tipo.equals("Veículo")) {
				tipoEvento = FotoTipo.capaVeiculo;

			} else if (Produto_Tipo.equals("Imóvel")) {
				tipoEvento = FotoTipo.capaImovel;

			} else

			{
				tipoEvento = FotoTipo.capaGeral;

			}

			if (nrow == 1) {
				select = new Select(driver.findElement(By.id("Produto_SubCategoriaCategoriaId")));
				option = select.getFirstSelectedOption();
				Evento.Segmento = option.getText(); // seleciona texto
				ped.Num = 1;

			}

			ListaPedidos.add(ped);

		}

// Cria evento //

		if (ExistEvento(driver))

		{

			System.out.println("Evento Editar:" + num_reuso);
			Evento.DataEvento.fake = false;
			PreencheEvento(driver, tipoEvento);

		} else {

			System.out.println("Evento Novo:" + num_reuso);

			NovoEvento(driver);
			Evento.DataEvento.fake = true;
			PreencheEvento(driver, tipoEvento);

		}

		nAux = 1;
		ListaPedidos.forEach(vc -> {
			System.out.println(String.format("AddProduto(%d/%d)", nAux, ListaPedidos.size()));
			var ped = new Pedido("");
			ped.Num = nAux;
			ped.Descricao = vc.Descricao;
			ped.SetValor(vc.GetValor(0));
			ped.Incremento = "100000";
			ped.SetAvaliado(vc.sAvaliado);
			ped.Url = Comitente.UrlFinaceira;
			AddProdutoEvento(driver, ped, true);
			nAux++;

		});
		test.SalvaTipoEnvento();

		// test.SalvaControleLeilao();

	}

	@Test
	public void Evento_Novo_Lotear_CodigoProduto() throws Exception {

		FotoTipo tipoEvento = null;

		num_reuso = Integer.valueOf(test.LeiaControleLeilao());
		test.SetTest(num_reuso);
		Evento.setEvento(10);
		Evento.Pagamento.setPagamento(true);
		test.setTipoEnvento(10);
		
		var est = test.tipoEventoDesc;

		// test.setParcelamento(true);

		if (num_reuso == 0) {
			if (num_reuso == 0) {
				Assert.fail("num_reuso = 0");
			}
		}

		WebDriver driver = LoginTest.IniciaLogin();

		var listaCodigoProdutos = test.LeiaListaCodigoProduto(test.Leilao);
		var isJuridico = BuscaCodigoProduto(driver, listaCodigoProdutos.get(0));

		test.setJudicial(isJuridico);
		Evento.Pagamento.setPagamento(isJuridico);

		// Descobre Categoria de produto

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

		// Descobre Categoria de produto

		if (ExistEvento(driver))

		{

			System.out.println("Evento Editar:" + num_reuso);
			Evento.DataEvento.fake = false;

		} else {

			System.out.println("Evento Novo:" + num_reuso);

			NovoEvento(driver);
			Evento.DataEvento.fake = true;

		}

		PreencheEvento(driver, tipoEvento);
		nAux = 1;

		while (!listaCodigoProdutos.isEmpty()) {

			var vc = listaCodigoProdutos.get(0);

			System.out.println(String.format("%s ->AddProduto(%d/%d)", vc, nAux, listaCodigoProdutos.size()));
			AddCodigoProdutoEvento(driver, vc);
			nAux++;
			listaCodigoProdutos.remove(0); // Exclui sempre o primeiro código(acabnou de ser loteado)
			test.SalvaListaCodigoProduto(test.Leilao, listaCodigoProdutos); // Salva lista atualizada
		}
		test.SalvaTipoEnvento();

	}

	@Test
	public void Evento_Existente_Lotear_CodigoProduto() throws Exception {

		num_reuso = Integer.valueOf(test.LeiaControleLeilao());
		test.SetTest(num_reuso);
		test.setParcelamento(true);

		// var EventoPerdido = String.format("(%s)", num_reuso);

		if (num_reuso == 0) {
			if (num_reuso == 0) {
				Assert.fail("num_reuso = 0");
			}
		}

		// Evento.IndexQtdEventos = 3;

		WebDriver driver = LoginTest.IniciaLogin();
		var listaCodigoProdutos = test.LeiaListaCodigoProduto(test.Leilao);

		ExistEvento(driver);

		nAux = 1;
		while (!listaCodigoProdutos.isEmpty()) {

			var vc = listaCodigoProdutos.get(0);

			System.out.println(String.format("%s ->AddProduto(%d/%d)", vc, nAux, listaCodigoProdutos.size()));
			AddCodigoProdutoEvento(driver, vc);
			nAux++;
			listaCodigoProdutos.remove(0);
			test.SalvaListaCodigoProduto(test.Leilao, listaCodigoProdutos);

		}

		test.SalvaTipoEnvento();

	}

	@Test
	void AlteraEvento_Imovel() throws Exception {

		FotoTipo tipoEvento = FotoTipo.capaImovel;
		;
		if (num_reuso == 0) {
			if (num_reuso == 0) {
				Assert.fail("num_reuso = 0");
			}
		}

		WebDriver driver = LoginTest.IniciaLogin();

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
		test.SalvaTipoEnvento();
	}

	@Test
	public void CadastarEvento_Unico_Veiculo_Deve_Retornar_sucesso() throws Exception {

		if (num_reuso != 0) {
			Assert.fail("num_reuso != 0");
		}

		ArrayList<Veiculo> ListaVeiculos = new ArrayList<>();
		WebDriver driver = LoginTest.IniciaLogin();
		if (Evento.IndexQtdEventos == 2) {
			loteVeiculoQtd = 2;
		}
		if (Evento.IndexQtdEventos == 3) {
			loteVeiculoQtd = 3;
		}

		for (var i = 0; i < loteVeiculoQtd; i++) {
			var vc = CadastrarVeiculo(driver);
			if (i == 0) {
				Evento.Segmento = vc.Categoria.Segmento;
			}
			ListaVeiculos.add(vc);
		}

		// *************************** Cadastra Eventos ****************

		NovoEvento(driver);
		Evento.Pagamento.setPagamento(test.getJudicial());
		PreencheEvento(driver, FotoTipo.capaVeiculo);

		// editar produto
		ListaVeiculos.forEach(vc -> {
			var ped = new Pedido("Veículo");
			ped.Descricao = vc.Nome;
			ped.SetValor(vc.LanceInicial);
			ped.SetAvaliado(vc.Avaliado);
			ped.Incremento = vc.Incremento;
			ped.Url = Comitente.UrlFinaceira;
			AddProdutoEvento(driver, ped, true);

		});
		test.SalvaTipoEnvento();
	}

	@Test
	void CadastarEvento_Mult_Veiculo_Deve_Retornar_sucesso() throws Exception {

		if (num_reuso != 0) {
			Assert.fail("num_reuso != 0");
		}

		ArrayList<Veiculo> ListaVeiculos = new ArrayList<>();
		WebDriver driver = LoginTest.IniciaLogin();

		for (int x = 1; x <= EventoQtd; x++) {
			System.out.println(String.format("===> Multi Test(%s) (%d/%d)", test.Num, x, EventoQtd));

			for (var i = 0; i < loteVeiculoQtd; i++) {
				var vc = CadastrarVeiculo(driver);
				if (i == 0) {
					Evento.Segmento = vc.Categoria.Segmento;
				}
				ListaVeiculos.add(vc);
			}

			// *************************** Cadastra Eventos ****************

			NovoEvento(driver);
			Evento.Pagamento.setPagamento(test.getJudicial());
			PreencheEvento(driver, FotoTipo.capaVeiculo);

			// editar produto
			ListaVeiculos.forEach(vc -> {
				var ped = new Pedido("Veículo");
				ped.Descricao = vc.Nome;
				ped.SetValor(vc.LanceInicial);
				ped.SetAvaliado(vc.Avaliado);
				ped.Incremento = vc.Incremento;
				ped.Url = Comitente.UrlFinaceira;
				AddProdutoEvento(driver, ped, true);

			});
			test.SalvaTipoEnvento();
			NovoTest();
		}
	}

	@Test
	void CadastarEvento_Unico_Geral_Deve_Retornar_sucesso() throws Exception {

		if (num_reuso != 0) {
			Assert.fail("num_reuso != 0");
		}

		WebDriver driver = LoginTest.IniciaLogin();
		if (Evento.IndexQtdEventos == 2) {
			loteGeralQtd = 2;
		}
		if (Evento.IndexQtdEventos == 3) {
			loteGeralQtd = 3;
		}

		ArrayList<GeralMock> ListaGeral = new ArrayList<>();
		for (var i = 0; i < loteGeralQtd; i++) {
			var ge = CadastrarGeral(driver);
			ListaGeral.add(ge);
			System.out.println("Diversos=" + (i + 1) + "/" + loteGeralQtd);
			if (i == 0) {
				Evento.Segmento = ge.Categoria.Segmento;
			}
		}

		// *************************** Evento GeralMock

		NovoEvento(driver);
		Evento.Pagamento.setPagamento(test.getJudicial());
		PreencheEvento(driver, FotoTipo.capaGeral);

		ListaGeral.forEach(ge -> {
			var ped = new Pedido("Geral");
			ped.Descricao = ge.Descricao;
			ped.Url = Comitente.UrlFinaceira;
			ped.SetValor(ge.LanceInicial);
			ped.SetAvaliado(ge.Avaliado);
			ped.Incremento = ge.Incremento;

			AddProdutoEvento(driver, ped, true);

		});
		// *************************** Evento GeralMock
		// ***********************************
		test.SalvaTipoEnvento();
	}

	@Test
	void CadastarEvento_Mult_Geral_Deve_Retornar_sucesso() throws Exception {

		if (num_reuso != 0) {
			Assert.fail("num_reuso != 0");
		}

		WebDriver driver = LoginTest.IniciaLogin();
		if (Evento.IndexQtdEventos == 2) {
			loteGeralQtd = 2;
		}
		if (Evento.IndexQtdEventos == 3) {
			loteGeralQtd = 3;
		}

		for (int x = 1; x <= EventoQtd; x++) {
			System.out.println(String.format("===> Multi Test(%s) (%d/%d)", test.Num, x, EventoQtd));
			ArrayList<GeralMock> ListaGeral = new ArrayList<>();
			for (var i = 0; i < loteGeralQtd; i++) {
				var ge = CadastrarGeral(driver);
				ListaGeral.add(ge);
				System.out.println("Diversos=" + (i + 1) + "/" + loteGeralQtd);
				if (i == 0) {
					Evento.Segmento = ge.Categoria.Segmento;
				}
			}

			// *************************** Evento GeralMock
			// ***********************************

			NovoEvento(driver);
			Evento.Pagamento.setPagamento(test.getJudicial());
			PreencheEvento(driver, FotoTipo.capaGeral);
			nAux = 1;
			ListaGeral.forEach(ge -> {
				var ped = new Pedido("Geral");
				ped.Descricao = ge.Descricao;
				ped.Url = Comitente.UrlFinaceira;
				ped.SetValor(ge.LanceInicial);
				ped.SetAvaliado(ge.Avaliado);
				ped.Incremento = ge.Incremento;
				AddProdutoEvento(driver, ped, true);
				nAux++;

			});
			// *************************** Evento GeralMock
			test.SalvaTipoEnvento();
			NovoTest();
			// ***********************************

		}
	}

	@Test
	void CadastarEvento_Unico_Imovel_Deve_Retornar_sucesso() throws Exception {

		if (num_reuso != 0) {
			Assert.fail("num_reuso != 0");
		}

		WebDriver driver = LoginTest.IniciaLogin();

		ArrayList<ImovelMock> ListaImoveis = new ArrayList<>();
		for (var i = 0; i < GetLoteImoveisQtd(1); i++) {
			var im = CadastrarImovel(driver);
			ListaImoveis.add(im);
			System.out.println("Imovel = %d/%d".formatted((i + 1), GetLoteImoveisQtd(1)));
			if (i == 0) {
				Evento.Segmento = im.Categoria.Segmento;
			}
		}

		// *************************** Evento Imovel
		// ***********************************

		NovoEvento(driver);
		Evento.Pagamento.setPagamento(test.getJudicial());
		PreencheEvento(driver, FotoTipo.capaImovel);
		ListaImoveis.forEach(im -> {
			var ped = new Pedido("Imovel");
			ped.Descricao = im.Nome;
			ped.Url = Comitente.UrlFinaceira;
			ped.SetValor(im.LanceInicial);
			ped.SetAvaliado(im.Avaliado);
			ped.Incremento = "1.000,00";
			AddProdutoEvento(driver, ped, true);

		});
		// *************************** Evento Imovel
		// ***********************************
		test.SalvaTipoEnvento();
	}

	@Test
	void CadastarEvento_Mult_Imovel_Deve_Retornar_sucesso() throws Exception {

		if (num_reuso != 0) {
			Assert.fail("num_reuso != 0");
		}

		WebDriver driver = LoginTest.IniciaLogin();

		for (int x = 1; x <= EventoQtd; x++) {
			System.out.println(String.format("===> Multi Test(%s) (%d/%d)", test.Num, x, EventoQtd));

			ArrayList<ImovelMock> ListaImoveis = new ArrayList<>();
			for (var i = 0; i < loteImoveisQtd; i++) {
				var im = CadastrarImovel(driver);
				ListaImoveis.add(im);
				System.out.println("Imovel = %d/%d".formatted((i + 1), loteImoveisQtd));
				if (i == 0) {
					Evento.Segmento = im.Categoria.Segmento;
				}
			}

			// *************************** Evento Imovel
			// ***********************************

			NovoEvento(driver);
			Evento.Pagamento.setPagamento(test.getJudicial());
			PreencheEvento(driver, FotoTipo.capaImovel);
			ListaImoveis.forEach(im -> {
				var ped = new Pedido("Imovel");
				ped.Descricao = im.Nome;
				ped.Url = Comitente.UrlFinaceira;
				ped.SetValor(im.LanceInicial);
				ped.SetAvaliado(im.Avaliado);
				ped.Incremento = "1.000,00";
				AddProdutoEvento(driver, ped, true);

			});
			// *************************** Evento Imovel
			// ***********************************
			test.SalvaTipoEnvento();
			NovoTest();

		}
	}

	// *************************** Funcoes ***********************************

}
