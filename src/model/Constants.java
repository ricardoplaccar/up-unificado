package model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.WheelInput;

public abstract class Constants {
	protected final String urlHasta = "https://controleh.hastavip.com.br/default.aspx";
	protected final String urlVip = "https://centralh.leilaovip.com.br/Default.aspx";
	protected final String url_site_controle = "https://controleh.hastavip.com.br/Login.aspx";
	protected final String url_controle_barramento = "https://naveh.vipleiloes.com.br/vip/estoque?menu=1";

	protected final String urlProxy = "https://app-identitymanagement-hom.azurewebsites.net/?returnUrl=controle-blazor-vip-hom.vipleiloes.com.br/login/proxy/entrar";

	protected static String User = "";
	protected static String senhaSite = "";
	protected static String senhaBackOffice = "";
	protected static String Url_site = "";
	protected static String Url_backOffice;
	protected static int tipoTest = 1; // 0 =Local 1=homolog
//	protected static String processo;
	protected static String localTest = "";
	protected static int TestControleTempo = 0;
	protected int EventoQtd = 3;
	protected int loteImoveisQtd = 3;

	protected int loteVeiculoQtd = 1;
	protected int loteGeralQtd = 1;
	protected int num_reuso = 0;
	private final String versao = "v1.6.94.5";
	protected String localLeiloes = "D:\\ricsistemas\\Documents\\Placar\\up\\java\\workspace\\upOnLine\\homolog\\leiloes\\TST%s.txt";
	protected final String[] DocumentoArquivoEvento = {
			"D:\\ricsistemas\\Documents\\Placar\\Documentos.fake\\Edital.pdf",
			"D:\\ricsistemas\\Documents\\Placar\\Documentos.fake\\Matricula.pdf",
			"D:\\ricsistemas\\Documents\\Placar\\Documentos.fake\\condicoes.pdf",
			"D:\\ricsistemas\\Documents\\Placar\\Documentos.fake\\Catalago.pdf" };
	protected final String[] DescricaoArquivoEvento = { "Edital", "Matricula", "Condições - Venda", "Catálogo" };

	protected final String[] ImagemArquivoEvento = {
			"D:\\ricsistemas\\Documents\\Placar\\fotos\\foto_capa\\capa-leilao-vazio.jpg",
			"D:\\ricsistemas\\Documents\\Placar\\fotos\\banner\\banner.png" };
	protected final String[] ImagemDescricao = { "Foto Capa", "Banner" };

	protected final String[] DescricaoArquivoBens = { "Laudo", "Matricula", "Documento" };

	protected final String[] DocumentoArquivoBens = { "D:\\ricsistemas\\Documents\\Placar\\Documentos.fake\\Laudo.pdf",
			"D:\\ricsistemas\\Documents\\Placar\\Documentos.fake\\Matricula.pdf",
			"D:\\ricsistemas\\Documents\\Placar\\Documentos.fake\\condicoes.pdf" };

	protected int GetLoteImoveisQtd(int IndexQtdEventos) {

		if (IndexQtdEventos == 2) {
			loteImoveisQtd = 2;
		}
		if (IndexQtdEventos == 3) {
			loteImoveisQtd = 3;
		}
		return loteImoveisQtd;

	}

	public Constants() {
		if (tipoTest == 1) {

			// Url_site =
			// "https://hom-backoffice.leilaovip.com.br/conta/entrar?ReturnUrl=%2F";
	//		Url_site2 = "https://up-leilaovip-backoffice-hom.azurewebsites.net/conta/entrar?ReturnUrl=%2F";
			Url_backOffice ="https://hom-backoffice.vipleiloes.com.br/conta/entrar?ReturnUrl=%2F";
			User = "ricardo.deoliveira@placcar.com.br";
			// Senha = "vip12345";
			senhaSite = "Rao@870312";
			senhaBackOffice = "vip12345";
			// processo = "0001572-21.2018.5.22.0002";// "0032949-14.2023.8.19.251";
			localTest = "homolog";
			TestControleTempo = 1000; // old = 1600

		} else {
			Url_site = "https://localhost:1476/conta/entrar?ReturnUrl=%2F";
			User = "Admin";
			senhaBackOffice = "Up123456";
			// processo = "0032949-14.2023.8.19.140";
			localTest = "local";
			TestControleTempo = 1600;
			/*
			 * backoffice: https://hom-backoffice.leilaovip.com.br/ usuário:
			 * LeilaoEdgeTest-admin@vipleiloes.com.br senha: Vip123456
			 */

		}

	}

	
	protected static boolean ValidaLogin( WebDriver  driver, String urlHasta) {
		var achei =false;
		System.out.println("Valida Login.");
		var max = 13;
		for (int x = 0; x < max; x++) {
			var sUrl = driver.getCurrentUrl();
			Aguarde(1000);
			System.out.print(".");
		   achei = sUrl.contains(urlHasta);
		   if (achei) x=max;
		   
		}
		return achei;

	}	
	
	
	
	protected void AguardeUrl(ChromeDriver driver, String cUrl) {
		var achei = false;
		var fim = 20000;
		System.out.print("Aguardando:");
		while (!achei) {

			var sUrl = driver.getCurrentUrl();
			Aguarde(500);
			achei = sUrl.contains(cUrl);
			if (achei) {
				System.out.println("");
				System.out.println("=>Achei..");
				System.out.println("=>ciclos:" + fim);
			}

			fim--;
			if (fim < 0) {
				System.out.println("=>>Tempo esgotado..");
				achei = true;
			}
			System.out.print(".");
			if (fim == 1000)
				System.out.println("Prox.");

		}
		System.out.println("Saindo do loop..");

	}

	protected static void Aguarde(long tempo) {
		try {
			Thread.sleep(tempo);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected String redimensionaImg(String arquivo) throws IOException {
		try {
			// int width = 868;
			// int height = 570;

			int width = 640;
			int height = 480;

			BufferedImage imagem = ImageIO.read(new File(arquivo));
			Path p = Paths.get(arquivo);
			Path folder = p.getParent();
			var strDestino = folder + "\\ReScale_" + p.getFileName();
			Path path = Paths.get(strDestino);
			if (Files.exists(path)) {
				System.out.println("Existe=>" + strDestino);
				return strDestino;
			}

			Image scaled = imagem.getScaledInstance(width, height, Image.SCALE_REPLICATE);
			BufferedImage bScaled = new BufferedImage(scaled.getWidth(null), scaled.getHeight(null),
					BufferedImage.TYPE_INT_RGB);
			bScaled.getGraphics().drawImage(scaled, 0, 0, null);

			File fDestino = new File(strDestino);
			ImageIO.write(bScaled, "jpg", fDestino);
			System.out.println("Novo=>" + strDestino);
			return strDestino;

		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

	protected String getBanner(FotoTipo fototipo) {
		String arquivo = "D:\\ricsistemas\\Documents\\Placar\\fotos\\banner\\Banner_diversos3.jpg";

		if (fototipo == FotoTipo.capaImovel) {
			arquivo = "D:\\ricsistemas\\Documents\\Placar\\fotos\\banner\\banner_imovel.png";
		} else

		if (fototipo == FotoTipo.capaVeiculo)

		{
			arquivo = "D:\\ricsistemas\\Documents\\Placar\\fotos\\banner\\banner_carro.jpg";
		}

		return arquivo;

	}

	protected String getSegmento(FotoTipo fototipo) {
		String Res = "Diversos";
		if (fototipo == FotoTipo.capaVeiculo) {
			Res = "Veículos";
		} else if (fototipo == FotoTipo.capaImovel) {
			Res = "Imóveis";
		} else if (fototipo == FotoTipo.capaGeral) {
			Res = "Bens Diversos ";
		} else if (fototipo == FotoTipo.capaControle) {
			Res = "Barramento - Controle";
		}
		return Res;

	}

	protected String getCapa(FotoTipo fototipo, String Texto, String num) throws Exception {
		// URL url = new URL("https://i.stack.imgur.com/Nqf3H.jpg");
		String Caminho = "D:\\ricsistemas\\Documents\\Placar\\fotos\\foto_capa\\";
		BufferedImage originalImage;
		BasicStroke stroke = new BasicStroke(2f);
		String Aquiv = null;
		int tam = 100;

		if (fototipo == FotoTipo.capaVeiculo) {
			Aquiv = Caminho + "capa-leilao-vazio-carro.jpg";
		}

		else if (fototipo == FotoTipo.capaImovel) {
			Aquiv = Caminho + "capa-leilao-vazio-imovel.jpg";
		} else if (fototipo == FotoTipo.capaGeral) {
			Aquiv = Caminho + "capa-leilao-diversos.jpg";
		} else if (fototipo == FotoTipo.capaControle) {
			Aquiv = Caminho + "capa-leilao-controle.jpg";
		} else if (fototipo == FotoTipo.capaImovelPropostaTexto) {
			Aquiv = Caminho + "capa-leilao-imovelPropostaTexto.png";

		} else if (fototipo == FotoTipo.capaImovelPropostaValor) {
			Aquiv = Caminho + "capa-leilao-imovelPropostaValor.png";

		} else if (fototipo == FotoTipo.capaImovelPropostaEmail) {
			Aquiv = Caminho + "capa-leilao-imovelPropostaEmail.png";

		} else

		if (fototipo == FotoTipo.Veiculo) {

			Aquiv = Texto;
			tam = 140;
			stroke = new BasicStroke(4f);

		} else

		if (fototipo == FotoTipo.Imovel) {

			Aquiv = Texto;
			tam = 160;
			stroke = new BasicStroke(6f);

		}

		else

		if (fototipo == FotoTipo.Geral) {

			Aquiv = Texto;
			tam = 140;
			stroke = new BasicStroke(6f);

		}

		var f = new File(Aquiv);
		originalImage = ImageIO.read(f);
		final BufferedImage textImage = new BufferedImage(originalImage.getWidth(), originalImage.getHeight(),
				BufferedImage.TYPE_INT_ARGB);
		// Gerar Imagem de Texto
		Graphics2D g = textImage.createGraphics();
		FontRenderContext frc = g.getFontRenderContext();
		Font font;
		GlyphVector gv;
		font = new Font(Font.SANS_SERIF, Font.BOLD, tam); // Tamanho do texto
		gv = font.createGlyphVector(frc, num);
		Rectangle2D box = gv.getVisualBounds();
		int x = ((originalImage.getWidth() - (int) box.getWidth()) / 2) - 8;
		int y = ((originalImage.getHeight() - (int) box.getY()) / 2) - 8;
		Shape shape = gv.getOutline(x, y);

		g.drawImage(originalImage, 0, 0, null);
		g.setClip(null);
		g.setStroke(stroke);

		g.setColor(Color.blue);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		// if (tipoEvento == tipoEventoFoto) g.fill(shape);

		g.draw(shape);
		g.dispose();

		String arquivo = Caminho + "leiloes\\" + num + ".png";

		File file = new File(arquivo);
		ImageIO.write(textImage, "png", file);
		// Desktop.getDesktop().open(file);
		return arquivo;
	}

	protected void PreencheData(WebDriver driver, String campoid, String valor) {

		var element = driver.findElement(By.id(campoid));
		element.click();
		Aguarde(1000);

		var tam = valor.length() + 1;
		var index = 1;
		for (int x = 0; x < tam - 1; x++) {
			var este = new Actions(driver);
			var parte = valor.substring(x, index);
			index++;
	//		if (x == 8) {
		//		new Actions(driver).keyDown(Keys.ARROW_RIGHT).perform();

		//	}
			
			
			este.sendKeys(parte).build().perform();
		}
		Aguarde(1000);
	}

	protected void PreencheDataExibicao(WebDriver driver, String campoid, String valor) {
		
		var element = driver.findElement(By.id(campoid));
		element.click();
		Aguarde(1000);
		
		var tam = valor.length() + 1;
		var index = 1;
		for (int x = 0; x < tam - 1; x++) {
			var este = new Actions(driver);
			var parte = valor.substring(x, index);
			index++;
					if (x == 8) {
					new Actions(driver).keyDown(Keys.ARROW_RIGHT).perform();
			
				}
			
			
			este.sendKeys(parte).build().perform();
		}
		Aguarde(1000);
	}
	
	protected void AguardeEdge(WebDriver driver, String cUrl) {
		var achei = false;
		var fim = 1000;

		System.out.print("Aguardando:");
		while (!achei) {

			var sUrl = driver.getCurrentUrl();
			Aguarde(250);
			achei = sUrl.contains(cUrl);
			if (achei) {
				System.out.println("");
				System.out.println("=>Achei..");
				System.out.println("=>Ciclos: " + fim);
			}

			fim--;
			if (fim < 0) {
				System.out.println("=>>Tempo esgotado..");
				achei = true;
			}
			System.out.print(".");
		}
		System.out.println("Saindo do loop..");

	}

	protected void ScrollUp(WebDriver driver, String id) {
		// TODO Auto-generated method stub
		WebElement iframe = driver.findElement(By.id(id));
		WheelInput.ScrollOrigin scrollOrigin = WheelInput.ScrollOrigin.fromElement(iframe);
		new Actions(driver).scrollFromOrigin(scrollOrigin, 0, -600).perform();

	}

	protected void ScrollDown(WebDriver driver, String id) {
		// TODO Auto-generated method stub
		WebElement iframe = driver.findElement(By.id(id));
		WheelInput.ScrollOrigin scrollOrigin = WheelInput.ScrollOrigin.fromElement(iframe);
		new Actions(driver).scrollFromOrigin(scrollOrigin, 0, 400).perform();

	}

	protected void InserirFotoLeilao(WebDriver driver, FotoTipo fototipo, String num) throws Exception {

		var foto = getCapa(fototipo, "", num);
		driver.findElement(By.id("filestyle-2")).sendKeys(foto);
		Aguarde(TestControleTempo * 4);
		driver.findElement(By.id("Holder_btn3DocumentoEnvia2")).click();
		Aguarde(TestControleTempo * 2);

		System.out.print(foto);
	}

	protected String MaskFloat(String Value) {

		try {
			var avaliado = NumberFormat.getNumberInstance().parse(Value).doubleValue();

			DecimalFormat df = new DecimalFormat("####.##");
			return df.format(avaliado);

		} catch (

		ParseException e) {
			e.printStackTrace();
			return "0";
		}

	}

	public ArrayList<String> LeiaListaCodigoProduto(String leilao) {

		var lista = new ArrayList<String>();
		

		String filePath = String.format(localLeiloes, leilao);
		
		System.out.println("=>"+filePath);
		
		
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = reader.readLine()) != null) {
				lista.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return lista;

	}

	protected void SalvaListaBens(String leilao, ArrayList<String> ListaBens) {

		String filePath = String.format(localLeiloes, leilao);

		try {
			File file = new File(filePath);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			// This will add a new line to the file content
			for (String str : ListaBens) {
				System.out.println("Item:" + str);
				pw.println(str);
			}

			pw.close();
			System.out.println("Os Códigos dos Bens foram salvos com suscesso!");

		} catch (IOException ioe) {
			System.out.println("Exception occurred:");
			ioe.printStackTrace();
		}
	}
	public void SalvaListaCodigoProduto(String leilao, ArrayList<String> ListaCodigoProdutos) {
		
		String filePath = String.format(localLeiloes, leilao);
		
		try {
			File file = new File(filePath);
			file.createNewFile();
			FileWriter fw = new FileWriter(file, false);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			// This will add a new line to the file content
			for (String str : ListaCodigoProdutos) {
				System.out.println("Item:" + str);
				pw.println(str);
			}
			
			pw.close();
			System.out.println("Os Códigos dos Bens foram salvos com suscesso!");
			
		} catch (IOException ioe) {
			System.out.println("Exception occurred:");
			ioe.printStackTrace();
		}
	}
	

	protected void SalvaBens(String leilao, String Bens) {

		String filePath = String.format(localLeiloes, leilao);

		try {
			File file = new File(filePath);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			// This will add a new line to the file content
			System.out.println("Item:" + Bens);
			pw.println(Bens);
			pw.close();
			System.out.println("Os Códigos dos Bens foram salvos com suscesso!");

		} catch (IOException ioe) {
			System.out.println("Exception occurred:");
			ioe.printStackTrace();
		}
	}

	protected String SeparaHora(String horaExibir) {
		// TODO Auto-generated method stub
		return horaExibir.substring(8);

	}

}
