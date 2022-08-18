package com.fornellogames.fases;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.fornellogames.main.Jogo;

public class Tile {

	public static BufferedImage TILE_GRAMA = Jogo.fundos.getSprite(0, 0, 16, 16);
	public static BufferedImage TILE_RUA = Jogo.fundos.getSprite(16, 0, 16, 16);
	public static BufferedImage TILE_FAIXA = Jogo.fundos.getSprite(32, 0, 16, 16);
	public static BufferedImage TILE_BARRA = Jogo.fundos.getSprite(48, 0, 16, 16);
	public static BufferedImage TILE_ARBUSTO = Jogo.fundos.getSprite(64, 0, 16, 16);
	public static BufferedImage TILE_GRAMADIF = Jogo.fundos.getSprite(80, 0, 16, 16);
	public static BufferedImage TILE_MURO = Jogo.fundos.getSprite(96, 0, 16, 16);
	public static BufferedImage TILE_PORTAS = Jogo.fundos.getSprite(112, 0, 16, 16);
	public static BufferedImage TILE_BLOCK = Jogo.fundos.getSprite(128, 0, 16, 16);
	
	private BufferedImage sprite;
	private int x,y;
	
	public Tile(int x, int y, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
		
	}
	
	public void renderizar(Graphics g) {
		g.drawImage(sprite, x - Camera.x, y - Camera.y, null);
		
	}
	
}
