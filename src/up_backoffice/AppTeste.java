package up_backoffice;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Locale;

import javax.imageio.ImageIO;

import model.Comitente;
import model.vTest;

public class AppTeste {


	
	
	
	public static void main(String[] args) throws Exception {
	
		 Double[] Desconto = { 0.0, 10.5, 35.3, 50.4 };
		 Double[] Valor = new Double[4];
		
	
		String value = "3.433,60";
	
		DecimalFormat nf = new DecimalFormat ("#,##0.00", new DecimalFormatSymbols (new Locale ("pt", "BR")));
        double v =0.0;
	
		try {
			double l = DecimalFormat.getNumberInstance().parse(value).doubleValue();
			v =l;
			System.out.println(l); 
			System.out.println(nf.format (l)); 
	
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		double valor =  v;
		
		Valor[0] = v;
		Valor[1] = v - (v * Desconto[1]) / 100;
		Valor[2] = v - (v * Desconto[2]) / 100;
		Valor[3] = v - (v * Desconto[3]) / 100;
		
		System.out.println (nf.format (Valor[0]));
		System.out.println (nf.format (Valor[1]));
		System.out.println (nf.format (Valor[2]));
		System.out.println (nf.format (Valor[3]));
			
		
		
		
		
	
	
	}

}
