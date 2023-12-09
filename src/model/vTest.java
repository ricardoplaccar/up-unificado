package model;

import java.io.File;
import java.io.FileInputStream;
//import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class vTest extends Constants {
	public String Desc;
	public String Num;
	public int quantidade = 0;
	public int test_num;

	public vTest() {
		
		geraTest(0);	
		
	}
	
	public vTest(int Value) {
		geraTest(Value);
		
	}

	private void geraTest(int Value) {
		if (Value != 0) {
			this.test_num = Value;
			Num = Integer.toString(Value);
			Desc = " - Test(" + Num + ")";
		} else {
			int ntest_num = -1;
			try {
				ntest_num = Leia("test_num");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			this.test_num = ntest_num;
			Num = Integer.toString(ntest_num);
			ntest_num++;

			Gravar("test_num", ntest_num);
			Desc = " - Test(" + Num + ")";

		}

		
	}	
	
	public int Leia(String campo) throws FileNotFoundException, IOException {

		var arquivo = "%s\\%s.ini".formatted(localTest, campo);

		if (!FileExist(arquivo)) {
			Gravar(campo, 0);
			return 0;

		} else {

			try {
				Properties prop = new Properties();
				prop.load(new FileInputStream("%s\\%s.ini".formatted(localTest, campo)));
				String valor = prop.getProperty(campo);
				System.out.println(campo + "=" + valor);
				return Integer.valueOf(valor);

			} catch (IOException ex) {

				Gravar("Campo", 1);

				ex.printStackTrace();
			}
		}
		return 0;

	}

	private boolean FileExist(String campo) {
		File f = new File(campo);
		if (f.exists() && !f.isDirectory()) {
			return true;
		}
		return false;
	}

	void Gravar(String campo, int valor) {

		try {

			FileOutputStream fos = new FileOutputStream("%s\\%s.ini".formatted(localTest, campo));
			Properties prop = new Properties();
			var str = Integer.toString(valor);
			prop.put(campo, str);
			prop.store(fos, "localhost");
			fos.flush();
			fos.close();

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}