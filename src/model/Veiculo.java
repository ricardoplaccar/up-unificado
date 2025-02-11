package model;

import java.util.ArrayList;

public class Veiculo {

	public Categoria Categoria;
	public String Nome;
	public String Marca;
	public String Modelo;
	public String Ano;
	public String Combustivel;
	public String Cor;
	public String KM;
	public String Avaliado;
	public String LanceInicial;
	public String Incremento;
	public String Placa;
	public String NumMotor;
	public ArrayList<Foto> ListFotos = new ArrayList<>();
	
	
	
	public Veiculo(Categoria categoria, String marca, String modelo, String ano, String combustivel, String cor,
			String km, String avaliado, String lanceinicial, String incremento, ArrayList<Foto> fotos, String placa,
			String nummotor, vTest nnum) {
		this.Categoria = categoria;
		this.Marca = marca;
		this.Modelo = modelo;
		this.Ano = ano;
		this.Combustivel = combustivel;
		this.Cor = cor;
		this.KM = km;
		this.Avaliado = avaliado;
		this.LanceInicial = lanceinicial;
		this.Incremento = incremento;
		this.ListFotos = fotos;
		this.Placa = placa;
		this.NumMotor = nummotor;
		this.Nome = Modelo + nnum.Desc;

	}
	

	@Override
	public String toString() {
		return "VeiculoMock [Marca=" + Marca + ", Modelo=" + Modelo + ", Ano=" + Ano + ", Combustivel=" + Combustivel
				+ ", Cor=" + Cor + ", KM=" + KM + ", Valor=" + Avaliado + ", ListFotos=" + ListFotos + "]";
	}

}
