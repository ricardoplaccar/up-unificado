package model;

import java.io.IOException;

public class Comitente {

	public int Id;
	public int IndexComitente;
	public String Nome;
	public String logo;
	public String UrlFinaceira;

	private String[] nomes = { "Bradesco", "Itaú", "Banco PAN", "Santander", "Sicred", "Tribunal de Justiça",
			"Banco do Brasil", "Localiza", "Porto Seguro", "Maravilhas -SC", "Tribunal de Alagoas", " MLV Partners",
			"Prefeitura Manaus", "Judiciario de Goiais", "Justica do Trabalho PE" };

	private String[] logos = { "bradesco-card.jpg", "banco-itau-card.jpg", "pan.png", "santander-card-new.jpg",
			"sicred.jpg", "leilao-tsj.jpg", "bb.jpg", "localiza.jpg", "leilao-porto-seguro-card.jpg",
			"maravilhas-sc.jpg", "tribunal_Alagoas.png", "mlv_partners.png", "prefeitura_manaus.png",
			"Judiciario_Goiais.jpg", "justica_trabalho_PEa.jpeg" };
	private String[] urls = {
			"https://banco.bradesco/html/classic/produtos-servicos/emprestimo-e-financiamento/financiamento-veiculos.shtm",
			"https://www.itau.com.br/emprestimos-financiamentos/veiculos/simulacao",
			"https://www.mobiauto.com.br/financiamento?utm_campaign=feirao_mobiauto_pan_nov_21_cta2&utm_medium=site_pan&utm_source=bancopan&utm_date=1657286600685",
			"https://www.cliente.santanderfinanciamentos.com.br/portalcliente/?ori=SF&int_source=menu-consultar-financiamento&_ga=2.265601807.1846216327.1657665414-2151589623.7002930786&gclid=EAIaIQobChMI1ZLzoO_BgQMV38zCBB2WGAZOEAAYASABEgJIDvD_BwE&gclsrc=aw.ds#/login",
			"https://www.sicredi.com.br/site/credito/para-voce/financiamento-de-veiculos",
			"https://www.correiodopovo.com.br/not%C3%ADcias/geral/justi%C3%A7a-confirma-fim-da-obrigatoriedade-de-simulador-para-cnh-b-no-rs-1.893977",
			"https://www49.bb.com.br/m/simular-credito-veiculo", "https://www49.bb.com.br/m/simular-credito-veiculo",
			"https://www49.bb.com.br/m/simular-credito-veiculo", "https://www49.bb.com.br/m/simular-credito-veiculo",
			"https://www49.bb.com.br/m/simular-credito-veiculo", "https://www49.bb.com.br/m/simular-credito-veiculo",
			"https://www49.bb.com.br/m/simular-credito-veiculo", "https://www49.bb.com.br/m/simular-credito-veiculo",
			"https://www49.bb.com.br/m/simular-credito-veiculo", "https://www49.bb.com.br/m/simular-credito-veiculo",
			"https://www49.bb.com.br/m/simular-credito-veiculo", "https://www49.bb.com.br/m/simular-credito-veiculo",
			"https://www49.bb.com.br/m/simular-credito-veiculo", "https://www49.bb.com.br/m/simular-credito-veiculo",

	};

	private String Caminho = "D:\\ricsistemas\\Documents\\Placar\\fotos\\logos\\";
	private int Comitente;


	public Comitente() {
	}

	public Comitente(vTest test) {

		try {
			Comitente = test.Leia("Comitente");
			int ComitenteMax = nomes.length;
			Id = Comitente;
			Nome = nomes[Comitente];
			logo = Caminho + logos[Comitente];
			UrlFinaceira = urls[Comitente];
			Comitente++;
			IndexComitente = Comitente;
			if (Comitente >= ComitenteMax) {
				Comitente = 0;
			}
			test.Gravar("Comitente", Comitente);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
