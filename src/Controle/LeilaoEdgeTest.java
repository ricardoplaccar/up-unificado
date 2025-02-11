package Controle;

import org.junit.jupiter.api.Test;

import model.vTest;
import up_backoffice.EventoTest;

class LeilaoEdgeTest extends BaseLeilaoEdge {

	@Test
	public void LeilaoCadastra_Bens_AutoamticoTestEdge() throws Exception {
		var test = new vTest(1);
		var driver = LoginEdge(urlVip);
		Vip_PreencheBemImovelEdge(driver, test);

		var test1 = new EventoTest();
		Aguarde(8000);
		test1.CadastrarEvento_Lotear_Produto();
		// test1.CadastrarEvento_Aproveitar_Produto();

	}

	@Test
	public void VipCriaLeilao_Edge() {

		var test = new vTest(1);
		var driver = LoginEdge(urlVip);
		PreencheVipleilaoEdge(driver, test);

		VipSalvaLeilao(driver);
		test.SalvaControleLeilao();

	}

	@Test
	public void Vip_LeilaoEletronico_BensImoveil_Edge() throws Exception {
		var test = new vTest(1);

		var driver = LoginEdge(urlVip);

		PreencheVipleilaoEdge(driver, test);

		Vip_PreencheBemImovelEdge(driver, test);
		LotearBemEdge(driver, test);

		test.SalvaControleLeilao();

	}

	@Test
	public void VipCadastraNovoBemImovel_Edge_Test() throws Exception {
		var test = new vTest(1);
		var driver = LoginEdge(urlVip);
		Vip_PreencheBemImovelEdge(driver, test);

	}

	@Test
	public void VipCadastraNovoBemVeiculo_Edge_Test() throws Exception {
		var test = new vTest(1);
		var driver = LoginEdge(urlVip);
		Vip_PreencheBemVeiculoEdge(driver, test);

	}

	@Test
	public void VipLotearBem_Edge_Test() throws Exception {
		var test = new vTest(1);
		var driver = LoginEdge(urlVip);

		LotearBemEdge(driver, test);

	}

}
