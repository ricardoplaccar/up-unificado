package model;

public class Evento {

	public Pagamento Pagamento;
	public DataEvento DataEvento;
	public Oferta OfertaTipo;
	public int IndexQtdEventos = 1;
	public int IndexOpcaoDisputa = 1;
	public Double Desconto1 = 25.2;
	public int IndexTipo = 1;
	
	public Boolean ComDisputa = false;
	public Boolean Remanecente = false;
	public Boolean OfertaAutomatica = false;
	public Boolean Online = false;
	
	
	public String Segmento = "Imóveis";
	public void setEvento(int tipo) {
      System.out.println("** >Tipo Evento= "+tipo );	   
		switch (tipo) {
		
		case 1:
			IndexTipo= 2;
			OfertaTipo = new Oferta(1);
			IndexQtdEventos = 3;
			IndexOpcaoDisputa = 3;
			ComDisputa = true;
			OfertaAutomatica =true;
			
				
		break;
		
		case 2, 3, 4:
			// ****************** Proposta ********************
			Online = false;
			OfertaTipo = new Oferta(tipo);
			break;
		// ******************  Padrão sem disputa ********************
		case 5:
			OfertaTipo = new Oferta(1);
			Online = false;
			IndexQtdEventos = 1;
			Remanecente =true;
			break;
		case 6:
			OfertaTipo = new Oferta(1);
			Online = false;
			IndexQtdEventos = 2;
			break;

		case 7:
			OfertaTipo = new Oferta(1);
			IndexQtdEventos = 3;
			break;
//  ******************   Com disputa	********************
		case 8:
			OfertaTipo = new Oferta(1);
			IndexQtdEventos = 1;
			IndexOpcaoDisputa = 3;
			ComDisputa = true;
			Remanecente =true;
			OfertaAutomatica =true;
			Online = false;
			break;
		case 9:
			OfertaTipo = new Oferta(1);
			IndexQtdEventos = 2;
			IndexOpcaoDisputa = 3;
			ComDisputa = true;
			OfertaAutomatica =true;
			Online = false;
			break;
		case 10:
			OfertaTipo = new Oferta(1);
			IndexQtdEventos = 3;
			IndexOpcaoDisputa = 3;
			ComDisputa = true;
			OfertaAutomatica =true;
			Online = false;
			break;
		case 11:
			OfertaTipo = new Oferta(1);
			IndexQtdEventos = 1;
			IndexOpcaoDisputa = 2;
			IndexTipo =2;
			ComDisputa = true;
			Remanecente =true;
			OfertaAutomatica =false;
		    Online = true;
			 
			break;
		case 12:
			OfertaTipo = new Oferta(1);
			IndexQtdEventos = 2;
			IndexOpcaoDisputa = 2;
			IndexTipo =2;
			ComDisputa = true;
			OfertaAutomatica =false;
			Online = true;
	
			break;
		case 13:
			OfertaTipo = new Oferta(1);
			IndexQtdEventos = 3;
			IndexOpcaoDisputa = 2;
			IndexTipo =2;
			ComDisputa = true;
			OfertaAutomatica =false;
			Online = true;

			break;
			
		default:

		}
		Pagamento = new Pagamento();
		DataEvento = new DataEvento(IndexQtdEventos);
	   
	   
   }
	public Evento(int tipo) {
		
		setEvento(tipo);

	}

}
