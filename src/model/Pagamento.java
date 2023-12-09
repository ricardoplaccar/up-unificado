package model;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Pagamento {
	public Boolean Quero_financiar = false;
	public Boolean AVista = false;
	public Boolean Parcelado = false;
	

	public Pagamento(vTest test) {

		int npag = 0;
		int PagamentoMax = 3;

		try {
			npag = test.Leia("Pagamento");
			if (npag == 1)
				Quero_financiar = true;
			if (npag == 2)
				AVista = true;
			if (npag == 3)
				Parcelado = true;

			npag++;
			if (npag >= PagamentoMax)
				npag = 1;
			test.Gravar("Pagamento", npag);
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	

	}

}
