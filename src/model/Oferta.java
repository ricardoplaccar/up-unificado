package model;

import java.io.IOException;

public class Oferta {
	public int IndexOferta;
	public Boolean Padrao;
	public Boolean OfertaValor;
	public Boolean PropostaTexto;
	public Boolean PropostaEmail;

	public Oferta(vTest test) {

		try {
			int FormaOfertaMax = test.Leia("FormaOfertaMax");
		
			int nFormaOferta = test.Leia("FormaOfertaIndex");
		
	//	int nFormaOferta =3;
			
			IndexOferta = nFormaOferta;
			Padrao = (nFormaOferta == 1);
			OfertaValor = (nFormaOferta == 2);
			PropostaTexto = (nFormaOferta == 3);
			PropostaEmail = (nFormaOferta == 4);
			nFormaOferta++;
			if (nFormaOferta > FormaOfertaMax)
				nFormaOferta = 1;
			test.Gravar("FormaOfertaIndex", nFormaOferta);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
