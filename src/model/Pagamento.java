package model;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Pagamento {
	public Boolean Quero_financiar = false;
	public Boolean AVista = false;
	public Boolean Parcelado = false; 


	public Pagamento(int npag) {

			if (npag == 1)
				Quero_financiar = true;
			if (npag == 2)
				AVista = true;
			else
				Parcelado = true;

		

	}

}
