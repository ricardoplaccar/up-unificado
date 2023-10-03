package model;

import util.Gerar;

public class Categoria {

	public String Categoria;
	public String SubCategoria;

	private String[] scategoria = { "Automóvel", "Caminhão", "Motocicleta", "Ônibus", "Caçamba" };
	private String[] subcategoria = { "Carro de Passeio", "Truch", "Moto 150", "Escolar", "Sem Placa" };

	public Categoria() {
	}


	public Categoria(vTest nnum, String Categ, String Sub) {

		Categoria = Categ;
		SubCategoria = Sub;
		if (nnum != null) {
			Categoria += nnum.Desc;
			SubCategoria += nnum.Desc;

		}
	
	}

	public Categoria(String Categ, String Sub) {

		Categoria = Categ;
		SubCategoria = Sub;

	}

	public Categoria(vTest nnum) {

		// nnum = Gerar.getTest();
		int ncategoria = Gerar.randomiza(5);

		Categoria = scategoria[ncategoria] + nnum.Desc;
		SubCategoria = subcategoria[ncategoria] + nnum.Desc;

	}

}
