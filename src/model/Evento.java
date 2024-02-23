package model;

public class Evento {

	public Pagamento Pagamento;
	public DataEvento DataEvento;
	public Oferta OfertaTipo;
	public int IndexQtdEventos = 1;
	public int IndexOpcaoDisputa = 1;
	public Double Desconto1 = 25.2;
	public int IndexTipo = 1;
	public int DuracaoDisputa = 120;
	public Boolean ComDisputa = false;
	


	public Evento(int tipo) {

		// int tipo = 10;
		switch (tipo) {
		case 2, 3, 4:
			// ****************** Proposta ********************
			OfertaTipo = new Oferta(tipo);
			Pagamento = new Pagamento(2);
			break;
		// ****************** Com Padrão sem disputa ********************
		case 5:
			OfertaTipo = new Oferta(1);
			IndexQtdEventos = 1;
			Pagamento = new Pagamento(2);

			break;
		case 6:
			OfertaTipo = new Oferta(1);
			IndexQtdEventos = 2;
			Pagamento = new Pagamento(1);
			break;

		case 7:
			OfertaTipo = new Oferta(1);
			IndexQtdEventos = 3;
			Pagamento = new Pagamento(2);
			break;
//  ******************   Com disputa	********************
		case 8:
			OfertaTipo = new Oferta(1);
			IndexQtdEventos = 1;
			IndexOpcaoDisputa = 3;
			ComDisputa = true;
			Pagamento = new Pagamento(3);
			break;
		case 9:
			OfertaTipo = new Oferta(1);
			IndexQtdEventos = 2;
			IndexOpcaoDisputa = 3;
			ComDisputa = true;
			Pagamento = new Pagamento(3);
			break;
		case 10:
			OfertaTipo = new Oferta(1);
			IndexQtdEventos = 3;
			IndexOpcaoDisputa = 3;
			ComDisputa = true;
			Pagamento = new Pagamento(2);
			break;
		default:

		}
		DataEvento = new DataEvento(IndexQtdEventos, 'c');

	}

}
