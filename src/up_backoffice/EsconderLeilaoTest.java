package up_backoffice;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import model.Constants;
import model.vTest;

class EsconderLeilaoTest extends Constants {
	private String[] leiloes = {"10600"};

			/*

			"2120-201020-01", "2122-060120-35", "2122-030120-44", "2122-020120-26",
			"2121-020120-00", "2122-010120-40", "2122-311220-37", "2088-070220-00", "2054-311220-00", "2122-311220-00",
			"2122-311220-29", "2122-301220-15", "2122-301220-12", "2122-241220-55", "2122-231220-15", "2122-231220-44",
			"2122-221220-52", "2122-211220-25", "2122-211220-48", "2122-211220-34", "2122-211220-24", "2122-211220-21",
			"2122-201220-55", "2122-201220-12", "2122-201220-41", "2122-181220-49", "2122-181220-38", "2122-181220-23",
			"2122-181220-52", "2122-161220-33", "2122-131220-48", "2122-131220-54", "2122-151220-46", "2122-121220-55",
			"2122-111220-30", "2122-101220-33", "2122-101220-30", "2122-101220-07", "2122-101220-58", "2122-101220-41",
			"2122-101220-12", "2122-101220-00", "2054-011220-00" };
*/
	@Test
	void Esconda() {
		WebDriver driver = LoginTest.IniciaLogin();
		var teste = new vTest(821);
		int leilaoMax = leiloes.length;

		try {
			int lote = teste.Leia("EscondaLeilao");
			if (lote >= leilaoMax-1) {
				lote = 0;
				teste.Gravar("EscondaLeilao", lote);

			}

			for (int x = lote; x < leilaoMax; x++) {
				System.out.println("Lote:" + (x+1) + "/" + leilaoMax);

				driver.findElement(By.partialLinkText("Eventos")).click();

				Aguarde(TestControleTempo);

				driver.findElement(By.id("Filtro_Texto")).clear();
				driver.findElement(By.id("Filtro_Texto")).sendKeys(leiloes[x]);

				driver.findElement(By.xpath("//*[@id=\"placeholder\"]/div[1]/div/div/button[1]")).click();// Pesquisar
				Aguarde(TestControleTempo);

				driver.findElement(By.xpath("//*[@id=\"placeholder\"]/div[2]/div/table/tbody/tr/td[1]/a")).click();// Editar
				Aguarde(TestControleTempo);

				PreencheData(driver, "Evento_InicioExbicaoEm", "121220230800");
				PreencheData(driver, "Evento_FimExbicaoEm", "121220230801");
				driver.findElement(By.xpath("//*[@id=\"placeholder\"]/div[1]/div[1]/button")).click(); //Salvar

				Aguarde(TestControleTempo);
				teste.Gravar("EscondaLeilao", x);

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}

}
