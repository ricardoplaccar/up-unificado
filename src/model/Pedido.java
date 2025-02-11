package model;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class Pedido extends Constants {
	public String Descricao;
	public String Url;
	public Categoria Categoria;
	public int Num;
	public String Tipo;
	public String Processo;
	public String sAvaliado = "0";
	public String Incremento;
    public String Desconto="0.0";
	public Pagamento Pag;
	private Double[] Descontos = { 0.0, 6.5, 7.5, 12.0 };
	private Double[] Valor = {0.0,0.0,0.0,0.0};
	private DecimalFormat nf = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));

	public void SetValor(String value) {

		double v = 1.1;

		try {
			double l = NumberFormat.getNumberInstance().parse(value).doubleValue();
			v = l;
			System.out.println("setValor:" + nf.format(l));
			Valor[0] = v;
			Valor[1] = v - (v * Descontos[1]) / 100;
			Valor[2] = v - (v * (Descontos[1] + Descontos[2] )) / 100;
			Valor[3] = v - (v * (Descontos[1] + Descontos[2] +  Descontos[3])) / 100;

		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

	public void SetAvaliado(String value) {

		sAvaliado = value;
		double avaliado = 1000;

		try {

			avaliado = NumberFormat.getNumberInstance().parse(value).doubleValue();
			if (Valor[0] != 0.0)
			{
				var desconto =100 - ((Valor[0] /avaliado) * 100);
				Desconto = String.format("%.2f", desconto);
			}
		else
		{

			Valor[0] = avaliado;
		}


			System.out.println("==> Avaliacao =" + avaliado);
			System.out.println("==> Valor =" + Valor[0]);
			System.out.println("==> Desconto =" + Desconto);


		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

	public String GetDesconto(int n) {
		return String.format("%.2f", Descontos[n]);

	}

	public Pedido(String tipo ) {
	//	Processo = processo;
		Tipo =tipo;


	}


	public Pedido() {
		// TODO Auto-generated constructor stub
	///	Processo = processo;
	}

	public String GetValor(int i) {

		return nf.format(Valor[i]);

	}

}
