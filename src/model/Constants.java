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
import java.io.File;
import java.nio.file.*;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import util.Gerar;

public abstract class Constants {
	protected static String User = "";
	protected static String Senha = "";
	protected static String Url_site = "";
	protected static int tipoTest = 1; // 0 =Local 1=homolog
	protected static String processo;
	protected static String localTest = "";
	protected static int TestControleTempo = 0;
	protected int loteImoveisQtd = 1;
	protected int loteVeiculoQtd = 1;
	protected int loteGeralQtd = 1;
	protected int num_reuso = 0;
	private final String versao = "  1.6.86.5";

	protected final String url_site_controle = "https://controleh.hastavip.com.br/Login.aspx";
	
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

	public Constants() {
		if (tipoTest == 1) {

			Url_site = "https://hom-backoffice.leilaovip.com.br/conta/entrar?ReturnUrl=%2F";
			User = "ricardo.deoliveira@placcar.com.br";
			Senha = "Ra870312";
			processo = "0001572-21.2018.5.22.0002";// "0032949-14.2023.8.19.251";
			localTest = "homolog";
			TestControleTempo = 1600;

		} else {
			Url_site = "https://localhost:1476/conta/entrar?ReturnUrl=%2F";
			User = "Admin";
			Senha = "Up123456";
			processo = "0032949-14.2023.8.19.140";
			localTest = "local";
			TestControleTempo = 1000;

		}

	}

	protected void Aguarde(long tempo) {
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
		String arquivo = "D:\\ricsistemas\\Documents\\Placar\\fotos\\banner\\banner_diversos.jpg";

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

		String arquivo = Caminho + "~TempFoto.png";
		File file = new File(arquivo);
		ImageIO.write(textImage, "png", file);
		// Desktop.getDesktop().open(file);
		return arquivo;
	}

	protected void PreencheData(WebDriver driver, String campoid, String valor) {

		var element = driver.findElement(By.id(campoid));
		element.click();
		Gerar.Aguarde(1000);

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
		Gerar.Aguarde(1000);
	}

}