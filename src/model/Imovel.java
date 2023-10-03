package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Imovel {
	public String Nome;
	public Categoria Categoria;
	public String VlPedido;
	public String VlAvaliado;
	public Endereco Endereco;
	public String UrlMap;
	private ArrayList<Foto> ListFotos = new ArrayList<>();

	public ArrayList<Foto> getListFotos() {
		return ListFotos;
	}

	public Imovel() {

	}

	public Imovel(model.Categoria categoria, String nome, String vlPedido, String vlAvaliado, model.Endereco endereco,
			ArrayList<Foto> fotos, String urlMap, vTest nnum) {
		this.Nome = nome + nnum.Desc;
		Categoria = categoria;
		VlPedido = vlPedido;
		VlAvaliado = vlAvaliado;
		Endereco = endereco;
		ListFotos = fotos;
		UrlMap = urlMap;

	}

	public Imovel GetImovel(vTest NNum) {
		List<Imovel> ListaImovel = new ArrayList<>();
		
		String Caminho = "D:\\ricsistemas\\Documents\\Placar\\fotos\\Imoveis\\Comercial1\\";

		ListaImovel.add(new Imovel(new Categoria( "Imóvel", "Imóvel Comercial"), "Imóvel Comercial - Centro",
				"25000000", "98000000", new Endereco(),
				new ArrayList<>(Arrays.asList(new Foto(Caminho + "1.jpg"), new Foto(Caminho + "2.jpg"),
						new Foto(Caminho + "3.jpg"), new Foto(Caminho + "4.jpg"), new Foto(Caminho + "5.jpg"),
						new Foto(Caminho + "6.jpg"), new Foto(Caminho + "7.jpg"))),
				"url", NNum));
		Caminho = "D:\\ricsistemas\\Documents\\Placar\\fotos\\Imoveis\\Ap1\\";
		ListaImovel.add(new Imovel(new Categoria("Imóvel", "Apartamento"), "Apartamento - Liberdade", "13000000",
				"50000000", new Endereco(),
				new ArrayList<>(Arrays.asList(new Foto(Caminho + "1.jpg"), new Foto(Caminho + "2.jpg"),
						new Foto(Caminho + "3.jpg"), new Foto(Caminho + "4.jpg"), new Foto(Caminho + "5.jpg"),
						new Foto(Caminho + "6.jpg"), new Foto(Caminho + "7.jpg"), new Foto(Caminho + "8.jpg"),
						new Foto(Caminho + "9.jpg"))),
				"url", NNum));

		Caminho = "D:\\ricsistemas\\Documents\\Placar\\fotos\\Imoveis\\Predio1\\";
		ListaImovel.add(new Imovel(new Categoria("Imóvel", "Prédio"), "Prédio Comercial - Santos Dumont",
				" 129300000", "5000000", new Endereco(), new ArrayList<>(Arrays.asList(new Foto(Caminho + "1.jpg"),
						new Foto(Caminho + "2.jpg"), new Foto(Caminho + "3.jpg")

				)), "url", NNum));

		Caminho = "D:\\ricsistemas\\Documents\\Placar\\fotos\\Imoveis\\Sala1\\";
		ListaImovel.add(new Imovel(new Categoria("Imóvel", "Sala Comercial"), "Sala Comercial", "13000000",
				"50000000", new Endereco(),
				new ArrayList<>(Arrays.asList(new Foto(Caminho + "1.jpg"), new Foto(Caminho + "2.jpg"),
						new Foto(Caminho + "3.jpg"), new Foto(Caminho + "4.jpg"), new Foto(Caminho + "5.jpg"),
						new Foto(Caminho + "6.jpg"), new Foto(Caminho + "7.jpg"), new Foto(Caminho + "8.jpg"),
						new Foto(Caminho + "9.jpg"))),
				"url", NNum));

		Caminho = "D:\\ricsistemas\\Documents\\Placar\\fotos\\Imoveis\\Casa1\\";
		ListaImovel.add(new Imovel(new Categoria("Imóvel", "Casa"), "Casa - Vila", "90000000", "180000000",
				new Endereco(),
				new ArrayList<>(Arrays.asList(new Foto(Caminho + "1.jpg"), new Foto(Caminho + "2.jpg"),
						new Foto(Caminho + "3.jpg"), new Foto(Caminho + "4.jpg"), new Foto(Caminho + "5.jpg"),
						new Foto(Caminho + "6.jpg"), new Foto(Caminho + "7.jpg"), new Foto(Caminho + "8.jpg"),
						new Foto(Caminho + "9.jpg"), new Foto(Caminho + "10.jpg"), new Foto(Caminho + "11.jpg"),
						new Foto(Caminho + "12.jpg"), new Foto(Caminho + "13.jpg"))),
				"url", NNum));

		int nVc = NNum.getLoteImovel();
		var vc = ListaImovel.get(nVc);
		return vc;

	}

}
