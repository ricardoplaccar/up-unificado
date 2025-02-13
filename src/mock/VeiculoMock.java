package mock;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.Categoria;
import model.Foto;
import model.Veiculo;
import model.vTest;

public class VeiculoMock {
	private Veiculo veiculo;

	public Veiculo getVeiculo() {
     return veiculo;

	}

	public VeiculoMock(vTest test) {
		List<Veiculo> ListaVeiculos = new ArrayList<>();

//**************** Veículo 0 ***********************************************

		String Caminho = "D:\\ricsistemas\\Documents\\Placar\\fotos\\Veiculos\\AUDI R8 SPYDER 2012\\";
		ListaVeiculos
				.add(new Veiculo(new Categoria("Veículos", "Carros"), "Audi", "R8 Spyder 5.2 Quattro R-tronic/S-tronic",
						"2012", "Flex", "Branca", "50", "87.000,00", "67.000,00", "10000",
						new ArrayList<>(Arrays.asList(new Foto(Caminho + "1.jpg"), new Foto(Caminho + "2.jpg"),
								new Foto(Caminho + "3.jpg"), new Foto(Caminho + "4.jpg"), new Foto(Caminho + "5.jpg"),
								new Foto(Caminho + "6.jpg")

						)), "AAA-0001", "10000", test));

		// **************** Veículo 1 ***********************************************
		Caminho = "D:\\ricsistemas\\Documents\\Placar\\fotos\\Veiculos\\Bmw 740i E38\\";
		ListaVeiculos.add(new Veiculo(new Categoria("Veículos", "Carros"), "Bmw", "740iA", "2000", "Flex", "Prata",
				"60", "87.000,00", "40.000,00", "5.000,00", new ArrayList<>(Arrays.asList(new Foto(Caminho + "1.jpg"),
						new Foto(Caminho + "2.jpg"), new Foto(Caminho + "3.jpg"))),
				"AAA-0002", "15000", test));

		// **************** Veículo 2 ***********************************************
		Caminho = "D:\\ricsistemas\\Documents\\Placar\\fotos\\Veiculos\\Chevette ( Tubarão ) 1976\\";
		ListaVeiculos.add(
				new Veiculo(new Categoria("Veículos", "Carros"), "Chevrolet", "Chevette L / SL / SL/e / DL / SE 1.6",
						"1993", "Gasolina", "Prata", "200", "60.000,00", "29.000,00", "1000",
						new ArrayList<>(Arrays.asList(new Foto(Caminho + "1.jpg"), new Foto(Caminho + "2.jpg"),
								new Foto(Caminho + "3.jpg"), new Foto(Caminho + "4.jpg"), new Foto(Caminho + "5.jpg"),
								new Foto(Caminho + "6.jpg")

						)), "AAA-0002", "20000", test));

		// **************** Veículo 3 ***********************************************
		Caminho = "D:\\ricsistemas\\Documents\\Placar\\fotos\\Veiculos\\Fiat Argo\\";
		ListaVeiculos.add(new Veiculo(new Categoria("Veículos", "Carros"), "Fiat", "AR DRIVE S-DESIGN 1.3 8V Flex",
				"2022", "Flex", "Prata", "100", "115.000,00", "87.000,00", "1.000,00",
				new ArrayList<>(Arrays.asList(new Foto(Caminho + "9.jpg"), new Foto(Caminho + "8.jpg"),
						new Foto(Caminho + "7.jpg"), new Foto(Caminho + "4.jpg"), new Foto(Caminho + "3.jpg"),
						new Foto(Caminho + "2.jpg"), new Foto(Caminho + "1.jpg")

				)), "AAA-0003", "3000", test));

		// **************** Veículo 4 ***********************************************
		Caminho = "D:\\ricsistemas\\Documents\\Placar\\fotos\\Veiculos\\Fusca 1966\\";
		ListaVeiculos.add(new Veiculo(new Categoria("Veículos", "Carros"), "Volkswagen - VW", "Fusca", "1985", "Flex",
				"Vermelha", "500", "50.000,00", "20.000,00", "1.500,00",
				new ArrayList<>(Arrays.asList(new Foto(Caminho + "1.jpg"), new Foto(Caminho + "2.jpg"),
						new Foto(Caminho + "3.jpg"), new Foto(Caminho + "4.jpg"), new Foto(Caminho + "5.jpg"),
						new Foto(Caminho + "6.jpg")

				)), "AAA-0004", "4000", test));

		// **************** Veículo 5 ***********************************************
		Caminho = "D:\\ricsistemas\\Documents\\Placar\\fotos\\Veiculos\\Honda Civic\\";
		ListaVeiculos
				.add(new Veiculo(new Categoria("Veículos", "Carros"), "Honda", "Civic Coupe Si 2.4 16V 206cv Mec. 2p",
						"2015", "Flex", "Cinza Claro", "47", "280.000,00", "140.000,00", "5.000,00",
						new ArrayList<>(Arrays.asList(new Foto(Caminho + "1.jpg"), new Foto(Caminho + "2.jpg"),
								new Foto(Caminho + "3.jpg"), new Foto(Caminho + "4.jpg"), new Foto(Caminho + "5.jpg"),
								new Foto(Caminho + "6.jpg"), new Foto(Caminho + "7.jpg"), new Foto(Caminho + "8.jpg"),
								new Foto(Caminho + "9.jpg"), new Foto(Caminho + "10.jpg"), new Foto(Caminho + "11.jpg"),
								new Foto(Caminho + "12.jpg")

						)), "AAA-0005", "5000", test));

		try {

			int LoteVeiculoMax = ListaVeiculos.size();
			int LoteVeiculo = test.Leia("LoteVeiculo");
			veiculo = ListaVeiculos.get(LoteVeiculo);
			LoteVeiculo++;
			if (LoteVeiculo >= LoteVeiculoMax) {
				LoteVeiculo = 0;
			}
			test.Gravar("LoteVeiculo", LoteVeiculo);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
