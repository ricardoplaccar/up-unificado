package model;

import java.io.File;
import java.io.FileInputStream;
//import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

public class vTest extends Constants {

	private boolean Judicial = true;
	private final int CategoriaJudicial = 2;
	public String Desc;
	public String _Parcelamento = "";

	public String Num;
	public String Leilao;
	public String processo;

	private final String ControleLeilaoNum = "ControleLeilaoNum";
	private final String ControleLeilaoProcessoNum = "ControleProcessoNum";
	public int quantidade = 0;
	public int test_num;
	private int tipoEnvento;
	public int IndexCategoria = CategoriaJudicial; // 2 =Judicial, 1 = Extrajudicial
	public Boolean isDocs;
	public Boolean Remanecente = false;
	public Boolean Parcela = null;

	public String tipoEventoDesc;
	private String[] TipoDesc = {"Automático", "Proposta Valor", "Proposta Texto", "Proposta E-mail",
			"Padrão sem disputa 1", "Automático sem disputa 2", "Automático sem disputa 3", "Automático com disputa 1",
			"Automático com disputa 2", "Automático com disputa 3", "Online/Presencial com disputa 1","Online/Presencial com disputa 2","Online/Presencial com disputa 3",   };
private String[] spag = {" - Entrada/Parcelamento"," - Parcelamento/Entrada" };
	public boolean getJudicial() {
		return Judicial;

	}

	public void setParcelamento(Boolean pag) {
		tipoEventoDesc = TipoDesc[tipoEnvento -1] +  pag != null ?spag[0]:spag[1] ;
		Parcela =pag;
		
	
	}

	
	public void setTipoEnvento(int value) {
		tipoEnvento = value;	
		tipoEventoDesc = TipoDesc[tipoEnvento-1];
		
		tipoEventoDesc += Parcela != null ?spag[0]:spag[1] ;
	
	}
	
	public int getTipoEnvento(){
		return tipoEnvento;
		
		
	} 	
	
	
	
	
	public void setJudicial(boolean jud) {

		IndexCategoria = jud ? CategoriaJudicial : 1;
		Judicial = jud;
		isDocs = Judicial;
		Remanecente = !Judicial;

		System.out.println("SetCategoria: Judicial:" + jud);
		System.out.println("SetCategoria: IndexCategoria:" + IndexCategoria);

		// OfertaAutomatica = !Judicial;

	}

	public vTest() {
		geraTest(0);

	}

	public vTest(int Value) {

		if (Value == 1) {

			try {
				String test_num = LeiaControleLeilao();
				Value = Integer.valueOf(test_num);
				Num = test_num;
				Leilao = test_num;

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				tipoEnvento = 2;
				IndexCategoria = CategoriaJudicial; // Judicial
			}

		}

		SetTest(Value);

	}

	public String LeiaControleLeilao() throws FileNotFoundException, IOException {

		var arquivo = "%s\\%s.ini".formatted(localTest, ControleLeilaoNum);
		
		Path path = Paths.get(arquivo);
		System.out.println("  Arquivo =>>"+path.toRealPath());
				
		
		if (!FileExist(arquivo)) {
			SalvaControleLeilao();
			return "0";

		} else {

			try {
				Properties prop = new Properties();
				prop.load(new FileInputStream("%s\\%s.ini".formatted(localTest, ControleLeilaoNum)));
				String valor = prop.getProperty(ControleLeilaoNum);
				System.out.println(ControleLeilaoNum + "=" + valor);
				Leilao = valor;
								
				return valor;

			} catch (IOException ex) {

				SalvaControleLeilao();

				ex.printStackTrace();
			}
		}
		return "100";

	}

	public void SetTest(int Value) {

		this.test_num = Value;
		Num = Integer.toString(Value);
		Desc = " - Test(" + Num + ")";
		processo = LeiaControleProcesso();

		try {
			tipoEnvento = Leia("tipo");
			tipoEventoDesc = TipoDesc[tipoEnvento - 1];
			IndexCategoria = Leia("IndexCategoria");
			setJudicial(IndexCategoria == CategoriaJudicial);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			tipoEnvento = 2;
			IndexCategoria = CategoriaJudicial; // Judicial
		}

	}

	public void SalvaControleProcesso(String processo) {

		try {

			FileOutputStream fos = new FileOutputStream("%s\\%s.ini".formatted(localTest, ControleLeilaoProcessoNum));
			Properties prop = new Properties();

			prop.put(ControleLeilaoProcessoNum, processo);
			prop.store(fos, "localhost");
			fos.flush();
			fos.close();

		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	public String LeiaControleProcesso() {

		var arquivo = "%s\\%s.ini".formatted(localTest, ControleLeilaoProcessoNum);

		if (!FileExist(arquivo)) {
			SalvaControleProcesso("000.000.000.000");
			return "000.000.000.000";

		} else {

			try {
				Properties prop = new Properties();
				prop.load(new FileInputStream("%s\\%s.ini".formatted(localTest, ControleLeilaoProcessoNum)));
				String valor = prop.getProperty(ControleLeilaoProcessoNum);
				System.out.println(ControleLeilaoProcessoNum + "=" + valor);
				return valor;

			} catch (IOException ex) {

				ex.printStackTrace();
			}
		}
		return "000.000.000.000";

	}

	public String GeraProcesso() {

		SimpleDateFormat DateFor = new SimpleDateFormat(".YYYY.MM.dd.");
		Calendar calendar = Calendar.getInstance();
		String stringData = DateFor.format(calendar.getTime());
		processo = "0032949-29" + stringData + Num;

		SalvaControleProcesso(processo);
		return processo;
	}

	public void SalvaControleLeilao() {

		test_num++;
		try {

			FileOutputStream fos = new FileOutputStream("%s\\%s.ini".formatted(localTest, ControleLeilaoNum));
			Properties prop = new Properties();
			prop.put(ControleLeilaoNum, Integer.toString(test_num));
			prop.store(fos, "ricsistemas");
			fos.flush();
			fos.close();

		} catch (IOException ex) {
			ex.printStackTrace();
		}

		GeraProcesso();

	}

	public void SalvaTipoEnvento() {
		tipoEnvento++;
		IndexCategoria = CategoriaJudicial;
		if (tipoEnvento > 10) {
			tipoEnvento = 5;// 5; // =2
			IndexCategoria = 1;
			// setCategoria(Judicial);
		}

		Gravar("tipo", tipoEnvento);
		Gravar("IndexCategoria", IndexCategoria);

	}

	private void geraTest(int Value) {

		int ntest_num = 1;
		try {
			ntest_num = Leia("test_num");
			tipoEnvento = Leia("tipo");
			tipoEventoDesc = TipoDesc[tipoEnvento - 1];
			IndexCategoria = Leia("IndexCategoria");
			setJudicial(IndexCategoria == CategoriaJudicial);

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

		System.out.println(String.format("tipoEvento =%d", tipoEnvento - 1));
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