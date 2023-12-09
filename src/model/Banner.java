package model;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Banner {
	public String Nome;
	public String Imagem;
	public String ImagemCelular;
	private String[] imagens = { "banner2.jpg", "1.png", "2.png", "3.jpeg", "4.jpeg", "5.png", "6.jpeg" };
	private String[] imagensCelular = { "BANNERCELULAR2.jpg", "1.png", "2.png", "3.jpeg", "4.png", "5.png", "6.jpeg" };
	private String Caminho = "D:\\ricsistemas\\Documents\\Placar\\fotos\\banner\\vipleiloes.com.br\\";
	private String CaminhoCelular = "D:\\ricsistemas\\Documents\\Placar\\fotos\\banner\\vipleiloes.com.br\\Celular\\";
	private int Banner;

	public Banner() {
	}

	public Banner(vTest test) {
		// TODO Auto-generated constructor stub

		try {
			int BannerMax = imagens.length;
			Banner = test.Leia("Banner");
			Nome = "Banner " + test.Desc;
			Imagem = Caminho + imagens[Banner];
			ImagemCelular = CaminhoCelular + imagensCelular[Banner];
			Banner++;
			if (Banner >= BannerMax)
				Banner = 0;
			test.Gravar("Banner", Banner);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public int getBanner() {

		return Banner;

	}

}
