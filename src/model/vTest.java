package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class vTest {
	public String Desc;
	public String Num;

	public vTest() {

		var arquivo = "d://Up_Test.txt";

		var numero = 0;

		Path path = Paths.get(arquivo);
		if (Files.notExists(path)) {
			Grave(arquivo, "0");
			Num = Integer.toString(numero);

		}
		if (Files.exists(path)) {

			var dado = Leia(arquivo);

			try {
				numero = Integer.parseInt(dado);
			} catch (NumberFormatException e) {
				numero = 1;
			}

			numero++;
			Grave(arquivo, Integer.toString(numero));
			Desc = " - Test(" + numero + ")";
			Num = Integer.toString(numero);

		}
	}

	private String Leia(String arquivo) {
		String line = "";

		try {
			FileReader reader = new FileReader(arquivo);
			BufferedReader bufferedReader = new BufferedReader(reader);
			line = bufferedReader.readLine();
			reader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return line;

	}

	private void Grave(String arquivo, String Dado) {

		try {
			FileWriter writer = new FileWriter(arquivo, false);
			writer.write(Dado);
			writer.write("\r\n"); // write new line
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
