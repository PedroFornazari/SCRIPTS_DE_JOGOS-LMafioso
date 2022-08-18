package com.fornellogames.graficos;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Fundos {

	private BufferedImage fundos;
	
	public Fundos(String path) {
		try {
			fundos = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public BufferedImage getSprite(int x, int y, int width, int height) {
		return fundos.getSubimage(x, y, width, height);
		
	}
	
}
