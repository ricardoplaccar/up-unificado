package model;

public abstract class Constants {
	protected static String User = "";
	protected static String Senha = "";
	protected static String Url_site = "";
	protected static int tipoTest =0; // 0 =Local 1=homolog
	protected static String processo;
	protected static String localTest ="";
	protected static int  TestControleTempo =0;
	private final String versao = " 1.6.72.5";
	
	public Constants() {
		if (tipoTest == 1) {

			Url_site = "https://hom-backoffice.leilaovip.com.br/conta/entrar?ReturnUrl=%2F";
			User = "ricsistemas@gmail.com";
			Senha = "Ra870312";
			processo = "0032949-14.2023.8.19.251";
			localTest = "homolog";
			TestControleTempo = 1300;

		} else {
			Url_site = "https://localhost:1476/conta/entrar?ReturnUrl=%2F";
			User = "Admin";
			Senha = "Up123456";
			processo = "0032949-14.2023.8.19.140";
			localTest = "local";
			TestControleTempo =1000;
			
		}

	}

}