package model;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



import util.Gerar;

public class Veiculo {
	public Categoria Categoria;
	public String Nome;
	public String Marca;
	public String Modelo;
	public String Ano;
	public String Combustivel;
	public String Cor;
	public String KM;
	public String Valor;
    public String LanceInicial;
    public String Incremento;
    public String Placa;
    public String NumMotor;
  	
	private ArrayList<Foto> ListFotos = new ArrayList<>();
	
	public ArrayList<Foto> getListFotos() {
		return ListFotos;
	}
	public Veiculo() {}
	
	
	public Veiculo(Categoria categoria, String marca, String modelo, String ano, String combustivel, String cor, String km, String valor, String lance,String incremento, 
			ArrayList<Foto> fotos, String placa,String nummotor,vTest nnum) {
		
		this.Marca = marca;
		this.Modelo = modelo;
		this.Ano = ano;
		this.Combustivel = combustivel;
		this.Cor = cor;
		this.KM = km;
		this.Valor = valor;
		this.LanceInicial = lance;
		this.Incremento = incremento;
		this.ListFotos = fotos;
		this.Placa = placa;
		this.NumMotor = nummotor;
		this.Nome = Modelo + nnum.Desc;

	}

	public Veiculo GetVeiculo(vTest NNum) {
		List<Veiculo> ListaVeiculos = new ArrayList<>();
	
//**************** Veículo 0 ***********************************************

		String Caminho = "D:\\ricsistemas\\Documents\\Placar\\Test\\fotos\\Veiculos\\AUDI R8 SPYDER 2012\\";
		ListaVeiculos.add(new Veiculo(new Categoria( "Automóvel", "Carro de Passeio" ) ,   "Audi", "R8 Spyder 5.2 Quattro R-tronic/S-tronic", "2012", "Flex", "Branca",
				"16000", "62090000","600000","10000",
				new ArrayList<>(Arrays.asList(new Foto(Caminho + "175201825240230.jpg"),
						new Foto(Caminho + "177296223212824.jpg"), new Foto(Caminho + "173269465242498.jpg"),
						new Foto(Caminho + "170241103145489.jpg"), new Foto(Caminho + "171240703815621.jpg"),
						new Foto(Caminho + "170241103145489.jpg")

				)),"AAA-0001","10000",NNum));
		

		// **************** Veículo 1 ***********************************************
		Caminho = "D:\\ricsistemas\\Documents\\Placar\\Test\\fotos\\Veiculos\\Bmw 740i E38\\";
		ListaVeiculos.add(new Veiculo(new Categoria( "Automóvel", "Carro de Passeio" ),"Bmw", "740iA", "2000", "Flex", "Prata", "60000", "4400000","40000","5000",
						new ArrayList<>(Arrays.asList(new Foto(Caminho + "480225110903533.jpg"),
						new Foto(Caminho + "487217232145323.jpg"), new Foto(Caminho + "482288357665476.jpg"),
						new Foto(Caminho + "488293358520695.jpg"))),"AAA-0002","15000",NNum));

		// **************** Veículo 2 ***********************************************
		Caminho = "D:\\ricsistemas\\Documents\\Placar\\Test\\fotos\\Veiculos\\Chevette ( Tubarão ) 1976\\";
		ListaVeiculos.add(new Veiculo(new Categoria( "Automóvel", "Carro de Passeio" ),"Chevrolet", "Chevette L / SL / SL/e / DL / SE 1.6", "1993", "Gasolina", "Prata",
				"20000", "2500000","20000","1000",
				new ArrayList<>(Arrays.asList(new Foto(Caminho + "959379852186266.jpg"),
						new Foto(Caminho + "955377854233471.jpg"), new Foto(Caminho + "954368616277274.jpg"),
						new Foto(Caminho + "955304131594809.jpg"), new Foto(Caminho + "954353618707648.jpg"),
						new Foto(Caminho + "957371251060481.jpg")

				)),"AAA-0002","20000",NNum));

		// **************** Veículo 3 ***********************************************
		Caminho = "D:\\ricsistemas\\Documents\\Placar\\Test\\fotos\\Veiculos\\Fiat Argo\\";
		ListaVeiculos
				.add(new Veiculo(new Categoria( "Automóvel", "Carro de Passeio" ),"Fiat", "AR DRIVE S-DESIGN 1.3 8V Flex", "2022", "Flex", "Prata", "10000", "5600000","50000","5000",
						new ArrayList<>(Arrays.asList(new Foto(Caminho + "9.jpg"), new Foto(Caminho + "8.jpg"),
								new Foto(Caminho + "7.jpg"), new Foto(Caminho + "4.jpg"), new Foto(Caminho + "3.jpg"),
								new Foto(Caminho + "2.jpg"), new Foto(Caminho + "1.jpg")

						)),"AAA-0003","3000",NNum));

		// **************** Veículo 4 ***********************************************
		Caminho = "D:\\ricsistemas\\Documents\\Placar\\Test\\fotos\\Veiculos\\Fusca 1966\\";
		ListaVeiculos.add(new Veiculo(new Categoria( "Automóvel", "Carro de Passeio" ),"Volkswagen - VW", "Fusca", "1985", "Flex", "Vermelha", "500000", "2700000","20000","1500",
				new ArrayList<>(Arrays.asList(new Foto(Caminho + "962335254126182.jpg"),
						new Foto(Caminho + "966308250603430.jpg"), new Foto(Caminho + "967356494102981.jpg"),
						new Foto(Caminho + "961319737761016.jpg"), new Foto(Caminho + "961352495602863.jpg"),
						new Foto(Caminho + "964311738382478.jpg")

				)),"AAA-0004","4000",NNum));

		// **************** Veículo 5 ***********************************************
		Caminho = "D:\\ricsistemas\\Documents\\Placar\\Test\\fotos\\Veiculos\\Honda Civic\\";
		ListaVeiculos.add(new Veiculo(new Categoria( "Automóvel", "Carro de Passeio" ),"Honda", "Civic Coupe Si 2.4 16V 206cv Mec. 2p", "2015", "Flex", "Cinza Claro",
				"10000", "14500000","140000","5000",				
				new ArrayList<>(Arrays.asList(new Foto(Caminho + "831364482498496.jpg"),
						new Foto(Caminho + "838334602681315.jpg"), new Foto(Caminho + "839379603958780.jpg"),
						new Foto(Caminho + "836376242666052.jpg"), new Foto(Caminho + "834309607482666.jpg"),
						new Foto(Caminho + "838311240904676.jpg"), new Foto(Caminho + "830361602482099.jpg")

				)),"AAA-0005","5000",NNum));

		int nVc = NNum.getLoteVeiculo();
	    var vc = ListaVeiculos.get(nVc);
		return vc;

	}

	@Override
	public String toString() {
		return "Veiculo [Marca=" + Marca + ", Modelo=" + Modelo + ", Ano=" + Ano + ", Combustivel=" + Combustivel
				+ ", Cor=" + Cor + ", KM=" + KM + ", Valor=" + Valor + ", ListFotos=" + ListFotos + "]";
	}

}
