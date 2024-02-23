package model;

public class Oferta {
	private int IndexOferta;
	public Boolean Padrao;
	public Boolean OfertaValor;
	public Boolean PropostaTexto;
	public Boolean PropostaEmail;

	public int getIndexOferta() {
		return IndexOferta;
				
		
	}
	
	public Oferta(int Oferta) {
			IndexOferta = Oferta;
			Padrao = (IndexOferta == 1);
			OfertaValor = (IndexOferta == 2);
			PropostaTexto = (IndexOferta == 3);
			PropostaEmail = (IndexOferta == 4);
			System.out.println("IndexOferta="+ IndexOferta );

	}

}
