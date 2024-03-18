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
	public boolean Judicial;
	public int quantidade = 0;
	public int test_num;
	public int tipoEnvento;
	public int IndexCategoria = 1; // 1 =Judicial,2 = Extrajudicial
	public Boolean isDocs;
	public Boolean Remanecente = false;
	public Boolean OfertaAutomatica = false;


	public String tipoEventoDesc;
	private String[] TipoDesc = { "Padrão", "Proposta Valor", "Proposta Texto", "Proposta E-mail",
			"Padrão sem disputa 1", "Padrão sem disputa 2", "Padrão sem disputa 3", "Padrão com disputa 1",
			"Padrão com disputa 2", "Padrão com disputa 3" };

	public void setCategoria(boolean jud) {

		IndexCategoria = jud ? 1:2;
		Judicial = jud;
		isDocs = Judicial;
		Remanecente = !Judicial;
		OfertaAutomatica = !Judicial;


	}



	public vTest() {

		geraTest(0);

	}


	public vTest(int Value) {
		geraTest(Value);

	}

	public void SalvaTipoEnvento() {
		tipoEnvento++;
		if (tipoEnvento > 10) {
			tipoEnvento = 5; // =2
			IndexCategoria = Judicial ? 2 : 1;

		}

		Gravar("tipo", tipoEnvento);
		Gravar("IndexCategoria", IndexCategoria);

	}

	private void geraTest(int Value) {
		if (Value != 0) {
			this.test_num = Value;
			Num = Integer.toString(Value);
			Desc = " - Test(" + Num + ")";

			try {
				tipoEnvento = Leia("tipo");
				IndexCategoria = Leia("IndexCategoria");
				Judicial = IndexCategoria==1;
				isDocs = Judicial;


			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				tipoEnvento = 2;
				IndexCategoria = 1; // Judicial
			}

		} else {
			int ntest_num = 1;
			try {
				ntest_num = Leia("test_num");
				tipoEnvento = Leia("tipo");
				IndexCategoria = Leia("IndexCategoria");
				Judicial = IndexCategoria==1;
				isDocs = Judicial;

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
				tipoEnvento = 1;
			}
			this.test_num = ntest_num;
			Num = Integer.toString(ntest_num);
			ntest_num++;

			Gravar("test_num", ntest_num);
			Desc = " - Test(" + Num + ")";

		}

		tipoEventoDesc = TipoDesc[tipoEnvento - 1];
		System.out.println(tipoEventoDesc);

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

	public void Gravar(String campo, int valor) {

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