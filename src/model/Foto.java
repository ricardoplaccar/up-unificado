package model;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Foto {
	public String local;

	public Foto(String local) {

		this.local = local;
	} 

	@Override
	public String toString() {
		return "Foto [local=" + local + "]";
	}

	public Foto() {
	}

	


}
