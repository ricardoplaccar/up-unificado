package model;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Endereco {

	private String[] ceps = { "88512-386", "13070-061", "41745-001", "08391-712", "35060-970", "37026-550", "35660-116",
			"02045-970", "08391-712", "82960-030", "65095-602", "64000-580", "31555-190", "74921-365", "58037-695",
			"14802-580", "22765-240", "11348-230", "20541-371", "12460-730", "11320-100" };
	public String Cep;

	public Endereco(vTest test) {

		try {
			int cepMax = ceps.length;
			int nCep = test.Leia("Cep");
			Cep = ceps[nCep];
			nCep++;

			if (nCep >= cepMax)
				nCep = 0;
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
