package up_backoffice;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import model.vTest;
import util.Gerar;

class ProcessosTest {

	int controleTempo = 1000;
	boolean finaliza = true;

	private vTest nnum = new vTest();
	WebDriver driver = LoginTest.IniciaLogin();

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {

	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
		if (!finaliza) return;
		driver.close();
		driver.quit();

	}

	@Test
	public void CadastroProcesos_Deve_Retorna_Sucesso() throws Exception {

		Gerar.Aguarde(controleTempo);
		driver.findElement(By.partialLinkText("Processos Jurídicos")).click();
		Gerar.Aguarde(controleTempo);
		driver.findElement(By.partialLinkText("Novo")).click();
		driver.findElement(By.id("ProcessoJuridico_Numero")).click();
		driver.findElement(By.id("ProcessoJuridico_Numero")).sendKeys("0032949-14.2023.8.19." + nnum.Num);
		driver.findElement(By.id("ProcessoJuridico_Uri"))
				.sendKeys("https://www3.tjrj.jus.br/consultaprocessual/#/consultapublica#porNumero");
		driver.findElement(By.xpath("//*[@id=\"placeholder\"]/div[1]/div[1]/button[1]")).click();

		Gerar.Aguarde(controleTempo * 2);

		String texto = driver.findElement(By.cssSelector("div.alert.alert-dismissible.fade.show.alert-success"))
				.getText();

		assertEquals(LoginTest.salvocomsucesso, texto);

	}

}
