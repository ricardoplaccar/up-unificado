package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Imovel {
	public String Nome;
	public Categoria Categoria;
	public String LancInicial;
	public String Avaliado;
	public String UrlMap;
	public int IndexOcupacao;
	public int IndexEstagio;
	public int nCategoria;
	public ArrayList<Foto> ListaFotos = new ArrayList<>();

	
	private Imovel(Categoria categoria, String nome, String vlPedido, String vlAvaliado, 
			ArrayList<Foto> fotos, String urlMap, vTest nnum) {
		Nome = nome + nnum.Desc;
		Categoria = categoria;
		LancInicial = vlPedido;
		Avaliado = vlAvaliado;
		ListaFotos = fotos;
		UrlMap = urlMap;

	} 

	public Imovel(vTest test) {

		List<Imovel> ListaImovel = new ArrayList<>();

		String Caminho = "D:\\ricsistemas\\Documents\\Placar\\fotos\\Imoveis\\Comercial1\\";

		ListaImovel.add(new Imovel(new Categoria("Imóvel", "Imóvel Comercial"), "Imóvel Comercial - Centro", "250.000,00",
				"700.000,00",
				new ArrayList<>(Arrays.asList(new Foto(Caminho + "1.jpg"), new Foto(Caminho + "2.jpg"),
						new Foto(Caminho + "3.jpg"), new Foto(Caminho + "4.jpg"), new Foto(Caminho + "5.jpg"),
						new Foto(Caminho + "6.jpg"), new Foto(Caminho + "7.jpg"))),
				"url", test));
		Caminho = "D:\\ricsistemas\\Documents\\Placar\\fotos\\Imoveis\\Ap1\\";
		ListaImovel.add(new Imovel(new Categoria("Imóvel", "Apartamento"), "Apartamento - Liberdade", "130.000,00",
				"500.000,00",
				new ArrayList<>(Arrays.asList(new Foto(Caminho + "1.jpg"), new Foto(Caminho + "2.jpg"),
						new Foto(Caminho + "3.jpg"), new Foto(Caminho + "4.jpg"), new Foto(Caminho + "5.jpg"),
						new Foto(Caminho + "6.jpg"), new Foto(Caminho + "7.jpg"), new Foto(Caminho + "8.jpg"),
						new Foto(Caminho + "9.jpg"))),
				"url", test));
		Caminho = "D:\\ricsistemas\\Documents\\Placar\\fotos\\Imoveis\\Ap2\\";
		ListaImovel.add(new Imovel(new Categoria("Imóvel", "Apartamento"), "APARTAMENTO COM 45,83M² - BELA VISTA", "191.000,00",
				"500.000,00",
				new ArrayList<>(Arrays.asList(new Foto(Caminho + "1.jpg"), new Foto(Caminho + "2.jpg"),
						new Foto(Caminho + "3.jpg"), new Foto(Caminho + "4.jpg"), new Foto(Caminho + "5.jpg"),
						new Foto(Caminho + "6.jpg"), new Foto(Caminho + "7.jpg"), new Foto(Caminho + "8.jpg"))),
				"url", test));


		
		Caminho = "D:\\ricsistemas\\Documents\\Placar\\fotos\\Imoveis\\Predio1\\";
		ListaImovel.add(new Imovel(new Categoria("Imóvel", "Prédio"), "Prédio Comercial - Santos Dumont", "129.000,00",
				"500.000,00",  new ArrayList<>(Arrays.asList(new Foto(Caminho + "1.jpg"),
						new Foto(Caminho + "2.jpg"), new Foto(Caminho + "3.jpg"), new Foto(Caminho + "4.jpg"), new Foto(Caminho + "5.jpg"), new Foto(Caminho + "6.jpg")

				)), "url", test));

		Caminho = "D:\\ricsistemas\\Documents\\Placar\\fotos\\Imoveis\\Sala1\\";
		ListaImovel.add(new Imovel(new Categoria("Imóvel", "Sala Comercial"), "Sala Comercial", "130.000,00", "500.000,00",
				
				new ArrayList<>(Arrays.asList(new Foto(Caminho + "1.jpg"), new Foto(Caminho + "2.jpg"),
						new Foto(Caminho + "3.jpg"), new Foto(Caminho + "4.jpg"), new Foto(Caminho + "5.jpg"),
						new Foto(Caminho + "6.jpg"), new Foto(Caminho + "7.jpg"), new Foto(Caminho + "8.jpg"),
						new Foto(Caminho + "9.jpg"))),
				"url", test));

		Caminho = "D:\\ricsistemas\\Documents\\Placar\\fotos\\Imoveis\\Casa1\\";
		ListaImovel
				.add(new Imovel(new Categoria("Imóvel", "Casa"), "Casa - Vila", "900.000,00", "1.300,000,00", 
						new ArrayList<>(Arrays.asList(new Foto(Caminho + "1.jpg"), new Foto(Caminho + "2.jpg"),
								new Foto(Caminho + "3.jpg"), new Foto(Caminho + "4.jpg"), new Foto(Caminho + "5.jpg"),
								new Foto(Caminho + "6.jpg"), new Foto(Caminho + "7.jpg"), new Foto(Caminho + "8.jpg"),
								new Foto(Caminho + "9.jpg"), new Foto(Caminho + "10.jpg"), new Foto(Caminho + "11.jpg"),
								new Foto(Caminho + "12.jpg"), new Foto(Caminho + "13.jpg"))),
						"url", test));

		try {
			int nLoteImovel = test.Leia("LoteImovel");

			var im = ListaImovel.get(nLoteImovel);
			this.Nome = im.Nome;
			this.Categoria = im.Categoria;
			this.LancInicial = im.LancInicial;
			this.Avaliado = im.Avaliado;
			this.UrlMap = im.UrlMap;
			this.ListaFotos = im.ListaFotos;

			int LoteImovelMax = ListaImovel.size();

			int nCategoria = test.Leia("Categoria");
			int CategoriaMax = test.Leia("CategoriaMax");
			this.nCategoria = nCategoria;

			int nIndexOcupacao = test.Leia("OcupacaoIndex");
			IndexOcupacao = nIndexOcupacao;
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
		if (valor >= maximo)
			valor = 0;
		test.Gravar(campo, valor);

	}
	private void SalvaIndex(vTest test, String campo, int valor, int maximo) {

		valor++;
		if (valor >= maximo)
			valor = 1;
		test.Gravar(campo, valor);

	}
	
	
	
}
