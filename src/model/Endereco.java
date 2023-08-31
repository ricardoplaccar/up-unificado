package model;

import util.Gerar;

public class Endereco {
	
	private String[] ceps = { "88512-386","13070-061", "41745-001", "08391-712", "35060-970", "37026-550", "35660-116", "02045-970",
			"08391-712",  "82960-030" };
	public String Cep;

	public Endereco() {
		int nCep = Gerar.randomiza(10);
		Cep = ceps[nCep];

	}
}
