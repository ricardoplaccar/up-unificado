package model;

import java.io.IOException;

public class Comitente {

	public int Id;
	public int IndexComitente;
	public String Nome;
	public String logo;
	public String UrlFinaceira;
	private String RepeUrl = "https://www49.bb.com.br/m/simular-credito-veiculo";

	private String[] nomes = { "Bradesco", "Itau", "Banco PAN", "Santander", "Sicred", "Tribunal de Justiça",
			"Banco do Brasil", "Localiza", "Porto Seguro", "Maravilhas -SC", "Tribunal de Alagoas", " MLV Partners",
			"Prefeitura Manaus", "Judiciario de Goiais", "Justica do Trabalho PE" };

	private String[] logos = { "bradesco-card.jpg", "banco-itau-card.jpg", "pan.png", "santander-card-new.jpg",
			"sicred.jpg", "leilao-tsj.jpg", "bb.jpg", "localiza.jpg", "leilao-porto-seguro-card.jpg",
			"maravilhas-sc.jpg", "tribunal_Alagoas.png", "mlv_partners.png", "prefeitura_manaus.png",
			"Judiciario_Goiais.jpg", "justica_trabalho_PEa.jpeg", "Judiciario_Goiais.jpg", "justica_trabalho_PEa.jpeg",
			"maravilhas-sc.jpg", "tribunal_Alagoas.png", "mlv_partners.png", "prefeitura_manaus.png", };
	private String[] urls = {
			"https://banco.bradesco/html/classic/produtos-servicos/emprestimo-e-financiamento/financiamento-veiculos.shtm",
			"https://www.itau.com.br/emprestimos-financiamentos/veiculos/simulacao",
			"https://www.mobiauto.com.br/financiamento?utm_campaign=feirao_mobiauto_pan_nov_21_cta2&utm_medium=site_pan&utm_source=bancopan&utm_date=1657286600685",
			"https://www.cliente.santanderfinanciamentos.com.br/portalcliente/?ori=SF&int_source=menu-consultar-financiamento&_ga=2.265601807.1846216327.1657665414-2151589623.7002930786&gclid=EAIaIQobChMI1ZLzoO_BgQMV38zCBB2WGAZOEAAYASABEgJIDvD_BwE&gclsrc=aw.ds#/login",
			"https://www.sicredi.com.br/site/credito/para-voce/financiamento-de-veiculos", RepeUrl, RepeUrl, RepeUrl,
			RepeUrl, RepeUrl, RepeUrl, RepeUrl, RepeUrl, RepeUrl, RepeUrl, RepeUrl, RepeUrl, RepeUrl, RepeUrl,
			RepeUrl };

	private String Caminho = "D:\\ricsistemas\\Documents\\Placar\\fotos\\logos\\";
	private int Comitente;

	public Comitente(vTest test, int num) {

		String[] extra = { "Bradesco", "Itau","BANCO TOPÁZIO S.A.", "Banco PAN", "BANCO SOFISA IMÓVEIS",
				"Banco do Brasil", "Porto Seguro", "TRIBUNAL DE JUSTIÇA DO PARÁ",
				"PREFEITURA DE CUBATÃO", "PREFEITURA MUNICIPAL DE FLORIANO", "URBPLAN DESENVOLVIMENTO URBANO",
				"PETROBRAS - PETRÓLEO BRASILEIRO S/A", "Bradesco", "BRB - BANCO DE BRASILIA" };

		String[] judicial = { "TRIBUNAL DE JUSTICA MARANHAO", "TRIBUNAL DE JUSTIÇA DO PARANÁ",
				 "TRIBUNAL DE JUSTIÇA DO PARÁ","TRIBUNAL DE JUSTIÇA DE SÃO PAULO",
				"TRIBUNAL REGIONAL DO TRABALHO DO PARANÁ", "JUSTIÇA DO TRABALHO DE BELO HORIZONTE",
				"JUSTIÇA FEDERAL MA", "TRIBUNAL DE JUSTIÇA DO PARÁ", "JUSTIÇA FEDERAL DE MINAS GERAIS",
				"JUSTIÇA DO TRABALHO DE BELO HORIZONTE", "JUSTIÇA FEDERAL MA", "TRIBUNAL DE JUSTICA MARANHAO",
				"TRIBUNAL DE JUSTIÇA DO PARÁ", "JUSTIÇA FEDERAL DE MINAS GERAIS" };

		if (num == 1) {
			leiaComitente(test, extra);

		} else {
			leiaComitente(test, judicial);

		}

	}

	public Comitente(vTest test) {

		leiaComitente(test, nomes);

	}

	private void leiaComitente(vTest test, String[] nomes) {

		try {
			Comitente = test.Leia("Comitente");
			int ComitenteMax = nomes.length;
			if (Comitente > ComitenteMax) {
				Comitente = ComitenteMax;
			}

			System.out.println("ComitenteMax=" + ComitenteMax);

			Id = Comitente;
			Nome = nomes[Comitente];
			logo = Caminho + logos[Comitente];
			UrlFinaceira = urls[Comitente];
			IndexComitente = Comitente;
			Comitente++;

			if (Comitente >= ComitenteMax - 1) {
				Comitente = 0;
			}
			test.Gravar("Comitente", Comitente);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
