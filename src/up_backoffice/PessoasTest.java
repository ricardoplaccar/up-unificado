package up_backoffice;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import model.Comitente;
import model.Endereco;
import model.Pessoa;
import model.Pessoa.Tipo;
import model.vTest;
import util.Gerar;

public class PessoasTest {

	private int controleTempo = 1000;
	private boolean finaliza = false;
	private vTest test = new vTest();
	private Comitente comitent = new Comitente(test);
	private String ntest = test.Num;

	LoginTest Login = new LoginTest();
	private WebDriver driver = Login.IniciaLogin();

	private void AddDos(final WebDriver driver) {
		driver.findElement(By.xpath("//*[@id=\"tipoPessoa\"]/h3[3]/button")).click();
		Gerar.Aguarde(controleTempo * 4);
		new Select(driver.findElement(By.id("Documento_Tipo"))).selectByVisibleText("Contrato Social");
		driver.findElement(By.id("Documento_Arquivo"))
				.sendKeys("D:\\ricsistemas\\Documents\\Placar\\Test\\Documentos.fake\\Modelo_Basico_de_Contrato.pdf");
		driver.findElement(By.xpath("//*[@id=\"placeholder\"]/div[1]/div[1]/button[1]")).click();
		Gerar.Aguarde(controleTempo * 12);

	}

	@After
	public void tearDown() throws Exception {
		if (!finaliza)
			return;

		driver.close();
		driver.quit();
	}

	public void setUp() throws Exception {

	}

	@Test
	public void Cadastro_Juridico_Deve_Retornar_Valido() throws IOException {

		var pes = new Pessoa(Tipo.Juridico);

		driver.findElement(By.linkText("Pessoas")).click();
		Gerar.Aguarde(2000);

		driver.findElement(By.partialLinkText("Novo")).click();
		Gerar.Aguarde(1500);
		driver.findElement(By.id("Pessoa_IdExterno")).click();

		driver.findElement(By.id("Pessoa_IdExterno")).sendKeys(ntest);
		//
		driver.findElement(By.id("Pessoa_Tipo_chosen")).click();
		driver.findElement(By.xpath("//*[@id=\"Pessoa_Tipo_chosen\"]/div/div/input")).sendKeys("Jurídica");
		driver.findElement(By.xpath("//*[@id=\"Pessoa_Tipo_chosen\"]/div/div/input")).sendKeys(Keys.ENTER);

		Gerar.Aguarde(controleTempo * 2);

		driver.findElement(By.id("Pessoa_RazaoSocial")).click();
		driver.findElement(By.id("Pessoa_RazaoSocial")).sendKeys(pes.Nome);

		driver.findElement(By.id("Pessoa_Representante")).click();
		driver.findElement(By.id("Pessoa_Representante")).sendKeys(pes.Representante);

		Gerar.Aguarde(200);
		driver.findElement(By.id("Pessoa_Apelido")).sendKeys(pes.Email.Apelido);

		driver.findElement(By.id("Pessoa_Situacao_chosen")).click();

		driver.findElement(By.xpath("//*[@id=\"Pessoa_Situacao_chosen\"]/div/div/input")).sendKeys("Aprovado");
		driver.findElement(By.xpath("//*[@id=\"Pessoa_Situacao_chosen\"]/div/div/input")).sendKeys(Keys.ENTER);

		Gerar.Aguarde(200);

		driver.findElement(By.id("Pessoa_Perfil_chosen")).click();
		driver.findElement(By.xpath("//*[@id=\"Pessoa_Perfil_chosen\"]/div/div/input")).sendKeys("Cliente");
		driver.findElement(By.xpath("//*[@id=\"Pessoa_Perfil_chosen\"]/div/div/input")).sendKeys(Keys.ENTER);
		Gerar.Aguarde(controleTempo);

		// AddDos(driver);

		driver.findElement(By.id("Pessoa_Email")).sendKeys(pes.Email.Email);

		driver.findElement(By.id("Pessoa_EmailConfirmado")).click();

		driver.findElement(By.id("Pessoa_TelefoneNumero")).click();

		driver.findElement(By.id("Pessoa_TelefoneNumero")).sendKeys(pes.Fone);

		var tam = pes.CPF_Cnpj.length();
		var cnpj = pes.CPF_Cnpj;

		for (int x = 0; x < tam; x++) {

			char s = cnpj.charAt(x);
			var r = Character.toString(s);

			driver.findElement(By.id("Pessoa_CnpjNumero")).sendKeys(r);
		}

		CadastraEndereco(driver,pes);


		driver.findElement(By.xpath("//*[@id=\"placeholder\"]/div[1]/div[1]/button")).click();

		Gerar.Aguarde(controleTempo*3);

		String texto = driver.findElement(By.cssSelector("div.alert.alert-dismissible.fade.show.alert-success"))
				.getText();

		assertEquals(LoginTest.salvocomsucesso, texto);

	}

	@Test
	public void Cadastro_Fisica_Comitente_Deve_Retornar_Valido() {
		// Regra deve Salvar sem reclamar dos campos de endereços

//		var pes = new Pessoa(Tipo.Fisico);

		// var driver = Login.IniciaLogin();

		driver.findElement(By.linkText("Pessoas")).click();
		Gerar.Aguarde(controleTempo);

		driver.findElement(By.partialLinkText("Novo")).click();
		Gerar.Aguarde(controleTempo / 2);

		driver.findElement(By.id("Pessoa_IdExterno")).click();
		driver.findElement(By.id("Pessoa_IdExterno")).sendKeys(ntest);

		driver.findElement(By.id("Pessoa_Tipo_chosen")).click();
		driver.findElement(By.xpath("//*[@id=\"Pessoa_Tipo_chosen\"]/div/div/input")).sendKeys("Física");
		driver.findElement(By.xpath("//*[@id=\"Pessoa_Tipo_chosen\"]/div/div/input")).sendKeys(Keys.ENTER);

		Gerar.Aguarde(controleTempo);

		driver.findElement(By.id("Pessoa_Nome")).click();
		driver.findElement(By.id("Pessoa_Nome")).sendKeys(comitent.Nome);

		driver.findElement(By.id("Pessoa_Situacao_chosen")).click();
		driver.findElement(By.xpath("//*[@id=\"Pessoa_Situacao_chosen\"]/div/div/input")).sendKeys("Aprovado");
		driver.findElement(By.xpath("//*[@id=\"Pessoa_Situacao_chosen\"]/div/div/input")).sendKeys(Keys.ENTER);

		Gerar.Aguarde(controleTempo / 4);

		driver.findElement(By.id("Pessoa_Perfil_chosen")).click();
		driver.findElement(By.xpath("//*[@id=\"Pessoa_Perfil_chosen\"]/div/div/input")).sendKeys("Cliente");
		driver.findElement(By.xpath("//*[@id=\"Pessoa_Perfil_chosen\"]/div/div/input")).sendKeys(Keys.ENTER);

		driver.findElement(By.id("Pessoa_Comitente")).click();

		driver.findElement(By.id("Pessoa_NovaImagem")).sendKeys(comitent.logo);



		try {
			Thread.sleep(controleTempo *4);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



		// AddDos(driver);
	//	driver.findElement(By.xpath("//*[@id=\"placeholder\"]/div[1]/div[1]/button")).click();

		Gerar.Aguarde(controleTempo);

		String texto = driver.findElement(By.cssSelector("div.alert.alert-dismissible.fade.show.alert-success"))
				.getText();

		Gerar.Aguarde(controleTempo);
		assertEquals(LoginTest.salvocomsucesso, texto);

	}

	@Test
	public void Cadastro_Fisica_Deve_Retornar_Valido() throws IOException {

		var pes = new Pessoa(Tipo.Fisico);

		// var driver = Login.IniciaLogin();

		driver.findElement(By.linkText("Pessoas")).click();
		Gerar.Aguarde(2000);

		driver.findElement(By.partialLinkText("Novo")).click();
		Gerar.Aguarde(controleTempo);

		driver.findElement(By.id("Pessoa_IdExterno")).click();

		driver.findElement(By.id("Pessoa_IdExterno")).sendKeys(ntest);

		driver.findElement(By.id("Pessoa_Tipo_chosen")).click();
		driver.findElement(By.xpath("//*[@id=\"Pessoa_Tipo_chosen\"]/div/div/input")).sendKeys("Física");
		driver.findElement(By.xpath("//*[@id=\"Pessoa_Tipo_chosen\"]/div/div/input")).sendKeys(Keys.ENTER);

		Gerar.Aguarde(controleTempo * 2);

		driver.findElement(By.id("Pessoa_Nome")).click();
		driver.findElement(By.id("Pessoa_Nome")).sendKeys(pes.Nome);

		Gerar.Aguarde(200);
		driver.findElement(By.id("Pessoa_Apelido")).sendKeys(pes.Email.Apelido);

		driver.findElement(By.id("Pessoa_Situacao_chosen")).click();

		driver.findElement(By.xpath("//*[@id=\"Pessoa_Situacao_chosen\"]/div/div/input")).sendKeys("Aprovado");
		driver.findElement(By.xpath("//*[@id=\"Pessoa_Situacao_chosen\"]/div/div/input")).sendKeys(Keys.ENTER);

		Gerar.Aguarde(200);

		driver.findElement(By.id("Pessoa_Perfil_chosen")).click();
		driver.findElement(By.xpath("//*[@id=\"Pessoa_Perfil_chosen\"]/div/div/input")).sendKeys("Cliente");
		Gerar.Aguarde(500);
		driver.findElement(By.xpath("//*[@id=\"Pessoa_Perfil_chosen\"]/div/div/input")).sendKeys(Keys.ENTER);

		// AddDos(driver);

		driver.findElement(By.id("Pessoa_Email")).sendKeys(pes.Email.Email);

		driver.findElement(By.id("Pessoa_EmailConfirmado")).click();

		driver.findElement(By.id("Pessoa_TelefoneNumero")).sendKeys(pes.Fone);

		var tam = pes.CPF_Cnpj.length();
		var cnpj = pes.CPF_Cnpj;

		for (int x = 0; x < tam; x++) {

			char s = cnpj.charAt(x);
			var r = Character.toString(s);

			driver.findElement(By.id("Pessoa_CpfNumero")).sendKeys(r);
		}

		CadastraEndereco(driver, pes);
		driver.findElement(By.xpath("//*[@id=\"placeholder\"]/div[1]/div[1]/button")).click();

		Gerar.Aguarde(controleTempo);

		String texto = driver.findElement(By.cssSelector("div.alert.alert-dismissible.fade.show.alert-success"))
				.getText();

		Gerar.Aguarde(controleTempo);
		assertEquals(LoginTest.salvocomsucesso, texto);

	}

	private void CadastraEndereco(WebDriver driver, Pessoa pes) {

		var ender = new Endereco(test);


		driver.findElement(By.id("Pessoa_Endereco_Cep")).sendKeys(ender.Cep);
		driver.findElement(By.id("Pessoa_Endereco_Numero")).click();
		Gerar.Aguarde(1500);
		driver.findElement(By.id("Pessoa_Endereco_Numero")).sendKeys("100");
		Gerar.Aguarde(controleTempo * 3);
		String s = driver.findElement(By.id("Pessoa_Endereco_Logradouro")).getAttribute("value");
		driver.findElement(By.id("Pessoa_Endereco_Numero")).click();
		Gerar.Aguarde(controleTempo);
		var x = s.length();
		if (x == 0) {
			driver.findElement(By.id("Pessoa_Endereco_Logradouro")).sendKeys("Qualquer");
			driver.findElement(By.id("Pessoa_Endereco_Bairro")).sendKeys("Qualquer");
			driver.findElement(By.id("Pessoa_Endereco_Cidade")).sendKeys("Qualquer");
			new Select(driver.findElement(By.id("Pessoa_Endereco_EstadoId"))).selectByIndex(1);
			System.out.println("Falou endereco=>" + test.Desc);

		}

	}

}
