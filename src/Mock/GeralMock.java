package Mock;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.Categoria;
import model.Foto;
import model.vTest;

public class GeralMock {
	public Categoria Categoria;
	public String Descricao;
	public String Marca;
	public String Modelo;
	public String LanceInicial;
	public String Incremento;
	public String Avaliado;

	public ArrayList<Foto> ListFotos = new ArrayList<>();

	public GeralMock(vTest test) {
		List<GeralMock> ListaGeral = new ArrayList<>();

		String Caminho = "D:\\ricsistemas\\Documents\\Placar\\fotos\\GeralMock\\Geral1\\";
		//*****************************************************************************************************************

		ListaGeral.add(new GeralMock(new Categoria("Diversos", "Diversos"), "BENS DIVERSOS",
				"6.867,20", "3.433,60", "500,00",
				new ArrayList<>(Arrays.asList(new Foto(Caminho + "1.jpg"), new Foto(Caminho + "2.jpg"),
						new Foto(Caminho + "3.jpg"), new Foto(Caminho + "4.jpg"), new Foto(Caminho + "5.jpg"),
						new Foto(Caminho + "6.jpg"), new Foto(Caminho + "7.jpg"), new Foto(Caminho + "8.jpg"),
						new Foto(Caminho + "9.jpg"), new Foto(Caminho + "10.jpg"))),test));
	//*****************************************************************************************************************
		 Caminho = "D:\\ricsistemas\\Documents\\Placar\\fotos\\GeralMock\\Geral2\\";
		//*****************************************************************************************************************

		ListaGeral.add(new GeralMock(new Categoria("Diversos", "Energia Solar"), "KITS PARA AQUECIMENTO SOLAR",
				"9.200,00", "4.600,00", "500,00",
				new ArrayList<>(Arrays.asList(new Foto(Caminho + "1.jpg"), new Foto(Caminho + "2.jpg"),
						new Foto(Caminho + "3.jpg"), new Foto(Caminho + "4.jpg"), new Foto(Caminho + "5.jpg"))),test));
	//*****************************************************************************************************************


		 Caminho = "D:\\ricsistemas\\Documents\\Placar\\fotos\\GeralMock\\Geral3\\";
		//*****************************************************************************************************************
		 ListaGeral.add(new GeralMock(new Categoria("Diversos", "Diversos"), "MONITOR, FRIGOBAR E HOMETHEATER",
					"1.100,00", "1.100,00", "100,00",
					new ArrayList<>(Arrays.asList(new Foto(Caminho + "1.jpg"), new Foto(Caminho + "2.jpg"),
							new Foto(Caminho + "3.jpg"), new Foto(Caminho + "4.jpg"), new Foto(Caminho + "5.jpg"),
							new Foto(Caminho + "6.jpg"), new Foto(Caminho + "7.jpg"), new Foto(Caminho + "8.jpg"),
							new Foto(Caminho + "9.jpg"), new Foto(Caminho + "10.jpg"))),test));


	//*****************************************************************************************************************
		 Caminho = "D:\\ricsistemas\\Documents\\Placar\\fotos\\GeralMock\\Geral4\\";
		//*****************************************************************************************************************

		ListaGeral.add(new GeralMock(new Categoria("Diversos", "Diversos"), "PICADOR DE MADEIRA",
				"65.000,00", "33.150,00", "500,00",
				new ArrayList<>(Arrays.asList(new Foto(Caminho + "1.jpg"), new Foto(Caminho + "2.jpg"),
						new Foto(Caminho + "3.jpg"))),test));
	//*****************************************************************************************************************

		//*****************************************************************************************************************
		 Caminho = "D:\\ricsistemas\\Documents\\Placar\\fotos\\GeralMock\\Geral5\\";
		//*****************************************************************************************************************

		ListaGeral.add(new GeralMock(new Categoria("Diversos", "Diversos"), "MÁQUINA GRÁFICA",
				"90.000,00", "45.900,00", "1000,00",
				new ArrayList<>(Arrays.asList(new Foto(Caminho + "1.jpg"), new Foto(Caminho + "2.jpg"),
						new Foto(Caminho + "3.jpg"))),test));
	//*****************************************************************************************************************

		 Caminho = "D:\\ricsistemas\\Documents\\Placar\\fotos\\GeralMock\\Consorcio\\";
		//*****************************************************************************************************************
		ListaGeral.add(new GeralMock(new Categoria("Diversos", "Consórcio"), "Cartas Contempladas de Consórcio",
				"14.600,00", "14.600,00", "500,00",
				new ArrayList<>(Arrays.asList(new Foto(Caminho + "1.jpg"), new Foto(Caminho + "2.jpg"),
						new Foto(Caminho + "3.jpg"))),test));

	//*****************************************************************************************************************
		 Caminho = "D:\\ricsistemas\\Documents\\Placar\\fotos\\GeralMock\\Vestuario\\";
	//*****************************************************************************************************************
		ListaGeral.add(new GeralMock(new Categoria("Diversos", "Vestuário"), "Deneuve assinadas por Yves Saint Laurent",
					"15.000,00", "10.600,00", "500,00",
					new ArrayList<>(Arrays.asList(new Foto(Caminho + "1.jpg"))),test));
	//*****************************************************************************************************************


		//*****************************************************************************************************************
		 Caminho = "D:\\ricsistemas\\Documents\\Placar\\fotos\\Obra de Arte\\1\\";
	//*****************************************************************************************************************
		ListaGeral.add(new GeralMock(new Categoria("Diversos", "Obra de Arte"), "Óleo sobre Tela. Assinado e datado no verso: 66",
					"2000,00", "1000,00", "200,00",
					new ArrayList<>(Arrays.asList(new Foto(Caminho + "1.jpg"),new Foto(Caminho + "2.jpg"),new Foto(Caminho + "3.jpg"))),test));
	//*****************************************************************************************************************

		//*****************************************************************************************************************
		 Caminho = "D:\\ricsistemas\\Documents\\Placar\\fotos\\Obra de Arte\\2\\";
	//*****************************************************************************************************************
		ListaGeral.add(new GeralMock(new Categoria("Diversos", "Obra de Arte"), " \"Interlúdio\" Óleo sobre tela. Assinado C.I.E",
					"1.600,00", "1000,00", "500,00",
					new ArrayList<>(Arrays.asList(new Foto(Caminho + "1.jpg"),new Foto(Caminho + "2.jpg"))),test));
	//*****************************************************************************************************************

		 Caminho = "D:\\ricsistemas\\Documents\\Placar\\fotos\\Obra de Arte\\3\\";
	//*****************************************************************************************************************
		ListaGeral.add(new GeralMock(new Categoria("Diversos", "Obra de Arte"), "“Guardanapo”"	+ " Acrílica sobre tela. Med: 80x80 cm",
					"1.5000,00", "1.600,00", "200,00",
					new ArrayList<>(Arrays.asList(new Foto(Caminho + "1.jpg"),new Foto(Caminho + "2.jpg"))),test));
	//*****************************************************************************************************************



		try {

			int LoteGeralMax = ListaGeral.size();
			int LoteGeral = test.Leia("LoteGeral");
			var ge = ListaGeral.get(LoteGeral);

			this.Categoria = ge.Categoria;
			this.Descricao = ge.Descricao;
			this.Marca = ge.Marca;
			this.Modelo = ge.Modelo;
			this.LanceInicial = ge.LanceInicial;
			this.Incremento = ge.Incremento;
            this.Avaliado =ge.Avaliado;
			this.ListFotos = ge.ListFotos;
			LoteGeral++;
			if (LoteGeral >= LoteGeralMax) {
				LoteGeral = 0;
			}
			test.Gravar("LoteGeral", LoteGeral);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private GeralMock(Categoria categoria, String descricao,  String avaliado, String lanceinicial,
			String incremento, ArrayList<Foto> listafoto,vTest test) {

		// TODO Auto-generated constructor stub
		Categoria = categoria;
		Marca = Categoria.Categoria;
		Modelo = Categoria.SubCategoria;
	    Categoria.Segmento =  Categoria.SubCategoria;
  	    Descricao = descricao + test.Desc;
		Avaliado = avaliado;
		LanceInicial = lanceinicial;
		Incremento = incremento;
		ListFotos = listafoto;

	}

}
