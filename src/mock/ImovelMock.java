package mock;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.Categoria;
import model.Foto;
import model.vTest;

public class ImovelMock {
	public String Nome;
	public Categoria Categoria;
	public String LanceInicial;
	public String Avaliado;
	public String UrlMap;
	public int IndexOcupacao;
	public int IndexEstagio;
	public int nCategoria;
	public String Situacao;
	public ArrayList<Foto> ListaFotos = new ArrayList<>();

	private ImovelMock(Categoria categoria, String nome, String vlPedido, String vlAvaliado, ArrayList<Foto> fotos,
			String urlMap, vTest nnum) {
		Nome = nome + nnum.Desc;
		Categoria = categoria;
		LanceInicial = vlPedido;
		Avaliado = vlAvaliado;
		ListaFotos = fotos;
		UrlMap = urlMap;

	}

	public ImovelMock(vTest test) {
		
		 
		List<ImovelMock> ListaImovel = new ArrayList<>();

		String Caminho = "D:\\ricsistemas\\Documents\\Placar\\fotos\\Imoveis\\Comercial1\\";

		ListaImovel.add(new ImovelMock(new Categoria("Imóveis", "Imóvel Comercial"), "Imóvel Comercial - Centro",
				"250.000,50", "700.000,50",
				new ArrayList<>(Arrays.asList(new Foto(Caminho + "1.jpg"), new Foto(Caminho + "2.jpg"),
						new Foto(Caminho + "3.jpg"), new Foto(Caminho + "4.jpg"), new Foto(Caminho + "5.jpg"),
						new Foto(Caminho + "6.jpg"), new Foto(Caminho + "7.jpg"))),
				"url", test));
		Caminho = "D:\\ricsistemas\\Documents\\Placar\\fotos\\Imoveis\\Ap1\\";
		ListaImovel.add(new ImovelMock(new Categoria("Imóveis", "Apartamento"), "Apartamento - Liberdade", "130.000,50",
				"300.000,50",
				new ArrayList<>(Arrays.asList(new Foto(Caminho + "1.jpg"), new Foto(Caminho + "2.jpg"),
						new Foto(Caminho + "3.jpg"), new Foto(Caminho + "4.jpg"), new Foto(Caminho + "5.jpg"),
						new Foto(Caminho + "6.jpg"), new Foto(Caminho + "7.jpg"), new Foto(Caminho + "8.jpg"),
						new Foto(Caminho + "9.jpg"))),
				"url", test));
		Caminho = "D:\\ricsistemas\\Documents\\Placar\\fotos\\Imoveis\\Ap2\\";
		ListaImovel.add(new ImovelMock(new Categoria("Imóveis", "Apartamento"), "APARTAMENTO COM 45,83M² - BELA VISTA",
				"191.000,50", "300.000,50",
				new ArrayList<>(Arrays.asList(new Foto(Caminho + "1.jpg"), new Foto(Caminho + "2.jpg"),
						new Foto(Caminho + "3.jpg"), new Foto(Caminho + "4.jpg"), new Foto(Caminho + "5.jpg"),
						new Foto(Caminho + "6.jpg"), new Foto(Caminho + "7.jpg"), new Foto(Caminho + "8.jpg"))),
				"url", test));

		Caminho = "D:\\ricsistemas\\Documents\\Placar\\fotos\\Imoveis\\Predio1\\";
		ListaImovel.add(new ImovelMock(new Categoria("Imóveis", "Imóvel Comercial"), "Prédio Comercial - Santos Dumont",
				"129.000,50", "300.000,50",
				new ArrayList<>(Arrays.asList(new Foto(Caminho + "1.jpg"), new Foto(Caminho + "2.jpg"),
						new Foto(Caminho + "3.jpg"), new Foto(Caminho + "4.jpg"), new Foto(Caminho + "5.jpg"),
						new Foto(Caminho + "6.jpg")

				)), "url", test));

		Caminho = "D:\\ricsistemas\\Documents\\Placar\\fotos\\Imoveis\\Sala1\\";
		ListaImovel.add(
				new ImovelMock(new Categoria("Imóveis", "Imóvel Comercial"), "Sala Comercial", "130.000,50", "500.000,50",

						new ArrayList<>(Arrays.asList(new Foto(Caminho + "1.jpg"), new Foto(Caminho + "2.jpg"),
								new Foto(Caminho + "3.jpg"), new Foto(Caminho + "4.jpg"), new Foto(Caminho + "5.jpg"),
								new Foto(Caminho + "6.jpg"), new Foto(Caminho + "7.jpg"), new Foto(Caminho + "8.jpg"),
								new Foto(Caminho + "9.jpg"))),
						"url", test));

		Caminho = "D:\\ricsistemas\\Documents\\Placar\\fotos\\Imoveis\\Casa1\\";
		ListaImovel.add(new ImovelMock(new Categoria("Imóveis", "Casa"), "Casa - Vila", "900.000,50", "1300.000,50",
				new ArrayList<>(Arrays.asList(new Foto(Caminho + "1.jpg"), new Foto(Caminho + "2.jpg"),
						new Foto(Caminho + "3.jpg"), new Foto(Caminho + "4.jpg"), new Foto(Caminho + "5.jpg"),
						new Foto(Caminho + "6.jpg"), new Foto(Caminho + "7.jpg"), new Foto(Caminho + "8.jpg"),
						new Foto(Caminho + "9.jpg"), new Foto(Caminho + "10.jpg"), new Foto(Caminho + "11.jpg"),
						new Foto(Caminho + "12.jpg"), new Foto(Caminho + "13.jpg"))),
				"url", test));

		Caminho = "D:\\ricsistemas\\Documents\\Placar\\fotos\\Imoveis\\Rural1\\";
		ListaImovel.add(new ImovelMock(new Categoria("Imóveis", "Área Rural"), "Loteamento - Rancho Terra Nova",
				"149.000,50", "149.000,50",
				new ArrayList<>(Arrays.asList(new Foto(Caminho + "1.jpg"), new Foto(Caminho + "2.jpg"),
						new Foto(Caminho + "3.jpg"), new Foto(Caminho + "4.jpg"), new Foto(Caminho + "5.jpg"))),
				"url", test));

		Caminho = "D:\\ricsistemas\\Documents\\Placar\\fotos\\Imoveis\\Rural2\\";
		ListaImovel.add(new ImovelMock(new Categoria("Imóveis", "Área Rural"),
				"Chácara Incra 08 Alto Padrão 2,5 hectares", "2.500.000,50", "2.500.000,50",
				new ArrayList<>(Arrays.asList(new Foto(Caminho + "1.jpg"), new Foto(Caminho + "2.jpg"),
						new Foto(Caminho + "3.jpg"), new Foto(Caminho + "4.jpg"), new Foto(Caminho + "5.jpg"))),
				"url", test));

		Caminho = "D:\\ricsistemas\\Documents\\Placar\\fotos\\Imoveis\\Terreno1\\";
		ListaImovel.add(new ImovelMock(new Categoria("Imóveis", "Terreno"),
				"Lote de 2.500m² Aprox. 17.000m² de área verde.", "152.000,50", "152.000,50",
				new ArrayList<>(Arrays.asList(new Foto(Caminho + "1.jpg"), new Foto(Caminho + "2.jpg"),
						new Foto(Caminho + "3.jpg"), new Foto(Caminho + "4.jpg"), new Foto(Caminho + "5.jpg"))),
				"url", test));

		Caminho = "D:\\ricsistemas\\Documents\\Placar\\fotos\\Imoveis\\Terreno2\\";
		ListaImovel.add(new ImovelMock(new Categoria("Imóveis", "Terreno"), "Terreno área de 250,00 metros", "170.000,50",
				"170.000,50", new ArrayList<>(Arrays.asList(new Foto(Caminho + "1.jpg"), new Foto(Caminho + "2.jpg"),
						new Foto(Caminho + "3.jpg"))),
				"url", test));

		Caminho = "D:\\ricsistemas\\Documents\\Placar\\fotos\\Imoveis\\Industrial1\\";
		ListaImovel.add(new ImovelMock(new Categoria("Imóveis", "Industrial"), "Armazém com 1 Quarto - Cidade Industrial",
				"2.300.000,50", "2.300.000,50", new ArrayList<>(Arrays.asList(new Foto(Caminho + "1.jpg"),
						new Foto(Caminho + "2.jpg"), new Foto(Caminho + "3.jpg"), new Foto(Caminho + "4.jpg"))),
				"url", test));

		Caminho = "D:\\ricsistemas\\Documents\\Placar\\fotos\\Imoveis\\Industrial2\\";
		ListaImovel.add(new ImovelMock(new Categoria("Imóveis", "Galpão"), "Galpão / Depósito / Armazém 180m²",
				"980.000,50", "980.000,50",
				new ArrayList<>(Arrays.asList(new Foto(Caminho + "1.jpg"), new Foto(Caminho + "2.jpg"),
						new Foto(Caminho + "3.jpg"), new Foto(Caminho + "4.jpg"), new Foto(Caminho + "5.jpg"),
						new Foto(Caminho + "6.jpg"), new Foto(Caminho + "7.jpg"), new Foto(Caminho + "8.jpg"))),
				"url", test));

		Caminho = "D:\\ricsistemas\\Documents\\Placar\\fotos\\Imoveis\\Garagem1\\";
		ListaImovel.add(new ImovelMock(
				new Categoria("Imóveis", "Vaga de Garagem"), "Duas vagas cobertas e depósito privativo",
				"47.000,50", "47.000,50", new ArrayList<>(Arrays.asList(new Foto(Caminho + "1.jpg"),
						new Foto(Caminho + "2.jpg"), new Foto(Caminho + "3.jpg"), new Foto(Caminho + "4.jpg"))),
				"url", test));

		try {
			int nLoteImovel = test.Leia("LoteImovel");

			var im = ListaImovel.get(nLoteImovel);
			this.Nome = im.Nome;
			this.Categoria = im.Categoria;
			this.LanceInicial = im.LanceInicial;
			this.Avaliado = im.Avaliado;
			this.UrlMap = im.UrlMap;
			this.ListaFotos = im.ListaFotos;

			int LoteImovelMax = ListaImovel.size();

			int nCategoria = test.Leia("Categoria");
			int CategoriaMax = test.Leia("CategoriaMax");
			this.nCategoria = nCategoria;

			int nIndexOcupacao = test.Leia("OcupacaoIndex");
			IndexOcupacao = nIndexOcupacao;
			Situacao = "Ocupado";
			
			int IndexOcupacaoMax = test.Leia("OcupacaoIndexMax");

			int nEstagioIndex = test.Leia("EstagioIndex");
			this.IndexEstagio = nEstagioIndex;
			int EstagiIndexMax = test.Leia("EstagioIndexMax");

			Salva(test, "LoteImovel", nLoteImovel, LoteImovelMax);
			Salva(test, "Categoria", nCategoria, CategoriaMax);

			SalvaIndex(test, "OcupacaoIndex", nIndexOcupacao, IndexOcupacaoMax);
			SalvaIndex(test, "EstagioIndex", nEstagioIndex, EstagiIndexMax);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void Salva(vTest test, String campo, int valor, int maximo) {

		valor++;
		if (valor >= maximo) {
			valor = 0;
		}
		test.Gravar(campo, valor);

	}

	private void SalvaIndex(vTest test, String campo, int valor, int maximo) {

		valor++;
		if (valor >= maximo) {
			valor = 1;
		}
		test.Gravar(campo, valor);

	}

}
