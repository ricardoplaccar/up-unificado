package model;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Locale;

public class Pedido extends Constants {
	public String Descricao;
	public String Url;
	public Categoria Categoria;
	public String Processo;
	public String Avaliado;
	public String Incremento;
	
	public Pagamento Pag;
	private Double[] Desconto = { 0.0, 10.5, 35.3, 50.4 };
	private Double[] Valor = new Double[4];
	private DecimalFormat nf = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));

	public void SetValor(String value) {

		double v = 0.0;
		try {
			double l = DecimalFormat.getNumberInstance().parse(value).doubleValue();
			v = l;
			System.out.println("setValor:"+nf.format(l));

		} catch (ParseException e) {
			e.printStackTrace();
		}

		Valor[0] = v;
		Valor[1] = v - (v * Desconto[1]) / 100;
		Valor[2] = v - (v * Desconto[2]) / 100;
		Valor[3] = v - (v * Desconto[3]) / 100;

	}

	public String GetDesconto(int n) {
		return String.format("%.2f", Desconto[n]);

	}

	public Pedido() {
		Processo = processo;
	}

	public String GetValor(int i) {

		return nf.format(Valor[i]);

	}

	
}
