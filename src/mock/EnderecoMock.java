package mock;

import java.io.FileNotFoundException;
import java.io.IOException;

import model.vTest;

public class EnderecoMock {

	/*
	private String[] ceps = { "88512-386", "13070-061", "41745-001", "08391-712", "35060-970", "37026-550", "35660-116",
			"02045-970","05407-002", "08391-712", "82960-030", "65095-602", "64000-580", "31555-190", "74921-365", "58037-695",
	"14802-580", "22765-240","88470-000 " ,"57000-000 ", "68950-000","62800-000","29100-000","65600-000", "78109-999","79002-363", "11348-230", "20541-371", "12460-730", "11320-100", "11410-190", "30110-001",
			"01249-040", "01153-000" };
	
	*/
	                                                 
	private String[] ceps = {"57490-970","68950-970","69044-765","63240-970","29500-970","78480-970","79680-970","68440-970",
			"26515-090","26515-090","59370-000","98907-970","76924-970","69312-433","89249-970","49003-292","77710-000"};
	
	
	public String Cep;

	public EnderecoMock(vTest test, int qtd) {
		var constCepPorLote = "CepPorLote";

		try {

			int cepMax = ceps.length;
			int nCep = test.Leia("Cep");
			Cep = ceps[nCep];

			int CepPorLote = test.Leia(constCepPorLote);
			if (CepPorLote >= qtd) {
				CepPorLote = 0;
				nCep++;
				if (nCep >= cepMax) {
					nCep = 0;
				}

				test.Gravar("Cep", nCep);

			}

			CepPorLote++;
			test.Gravar(constCepPorLote, CepPorLote);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public EnderecoMock(vTest test) {

		try {
			int cepMax = ceps.length;
			int nCep = test.Leia("Cep");
			Cep = ceps[nCep];
			nCep++;

			if (nCep >= cepMax) {
				nCep = 0;
			}
			test.Gravar("Cep", nCep);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
