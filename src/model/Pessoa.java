package model;

import java.util.Locale;

import com.github.javafaker.Faker;

import util.Gerar;

public class Pessoa {
	public String Nome;
	public String Representante;
	
	public String CPF_Cnpj;
	public String RG;
	public String DT_Nasc;
	public Email Email = new Email();
	public Endereco endereco =new Endereco();
	public String Fone;
	public Pessoa(Tipo tipo) {

		Faker faker = new Faker(new Locale("pt-BR"));

		if (tipo == Tipo.Fisico) {
			Nome = faker.name().fullName();
			CPF_Cnpj = Gerar.cpf();
			RG = Gerar.rg(false);
			DT_Nasc = "25/06/1980";
			Fone = faker.phoneNumber().cellPhone();
			

		} else {
			Nome = faker.company().name();
			Representante = faker.name().fullName();
			CPF_Cnpj = Gerar.cnpj();
			RG = Gerar.rg(true);
			Fone = faker.phoneNumber().cellPhone();
		}

		Email.Email = faker.internet().emailAddress(CPF_Cnpj);

		Email.Apelido = faker.name().username();

	}

	public enum Classificacao {
		Usuario, Comitente, Leiloeiro

	}

	public enum Tipo {
		Fisico, Juridico

	}

	public class Email {
		public String Email;
		public String Apelido;

	}

}

	
	

