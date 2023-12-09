package model;

import java.io.IOException;

public class Evento {
	public Boolean Judicial;
	public Pagamento Pagamento;
	public DataEvento DataEvento;
	public Oferta OfertaTipo;
	public int IndexCategoria;
	public int IndexQtdEventos;
	public int IndexOpcaoDisputa = 1;
	public Double Desconto1 = 25.2;

	public Evento(vTest test) {
		Pagamento = new Pagamento(test);
		DataEvento = new DataEvento();
		OfertaTipo = new Oferta(test);

		try {
			int njudicial = test.Leia("Judicial");
			// Todo int njudicial= 1;

			int nQuantidade = test.Leia("EventosQtd");
			Judicial = (njudicial == 1);
			if (Judicial) {
				IndexCategoria = 1;
				test.Gravar("Judicial", 0);
			} else {
				IndexCategoria = 2;
				test.Gravar("Judicial", 1);
			}
			IndexQtdEventos = nQuantidade;
			// Todo IndexQtdEventos = 1;
			if (IndexQtdEventos > 1)
				OfertaTipo.IndexOferta = 1;
			nQuantidade++;
			if (nQuantidade >= 3)
				nQuantidade = 1;
			test.Gravar("EventosQtd", nQuantidade);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
