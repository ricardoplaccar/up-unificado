package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Geral {
	public Categoria Categoria;
	public String Descricao;
	public String Marca;
	public String Modelo;
	public String Valor;
	public String LanceInicial;
	public String Incremento;
	public ArrayList<Foto> ListFotos = new ArrayList<>();

	public Geral(vTest test) {
		List<Geral> ListaGeral = new ArrayList<>();

		String Caminho = "D:\\ricsistemas\\Documents\\Placar\\fotos\\Geral1\\";
		//*****************************************************************************************************************	
		
		ListaGeral.add(new Geral(new Categoria("Geral", "Diversos"), "BENS DIVERSOS", "Diversos", "Diversos",
				"6.867,20", "3.433,60", "500,00",
				new ArrayList<>(Arrays.asList(new Foto(Caminho + "1.jpg"), new Foto(Caminho + "2.jpg"),
						new Foto(Caminho + "3.jpg"), new Foto(Caminho + "4.jpg"), new Foto(Caminho + "5.jpg"),
						new Foto(Caminho + "6.jpg"), new Foto(Caminho + "7.jpg"), new Foto(Caminho + "8.jpg"),
						new Foto(Caminho + "9.jpg"), new Foto(Caminho + "10.jpg"))),test));

	//*****************************************************************************************************************	
	
		
		try {

			int LoteGeralMax = ListaGeral.size();
			int LoteGeral = test.Leia("LoteGeral");
			var ge = ListaGeral.get(LoteGeral);

			this.Categoria = ge.Categoria;
			this.Descricao = ge.Descricao;
			this.Marca = ge.Marca;
			this.Modelo = ge.Modelo;

			this.Valor = ge.Valor;
			this.LanceInicial = ge.LanceInicial;
			this.Incremento = ge.Incremento;

			this.ListFotos = ge.ListFotos;
			LoteGeral++;
			if (LoteGeral >= LoteGeralMax)
				LoteGeral = 0;
			test.Gravar("LoteGeral", LoteGeral);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private Geral(Categoria categoria, String descricao, String marca, String modelo, String valor, String lanceinicial,
			String incremento, ArrayList<Foto> listafoto,vTest test) {

		// TODO Auto-generated constructor stub
		Categoria = categoria;
  	    Descricao = descricao + test.Desc;
		Marca = marca;
		Modelo = modelo;
		Valor = valor;
		LanceInicial = lanceinicial;
		Incremento = incremento;
		ListFotos = listafoto; 

	}

}
