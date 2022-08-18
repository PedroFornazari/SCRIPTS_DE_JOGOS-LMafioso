package com.fornellogames.vivos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.fornellogames.fases.Camera;
import com.fornellogames.main.Jogo;
public class Bala extends Vivo {
	
	private int dx;
	private int dy;
	private double speedBala = 5;
	
	private int life = 50, curLife = 0;

	public Bala(int x, int y, int width, int height, BufferedImage sprite, int dx, int dy) {
		super(x, y, width, height, sprite);
		this.dx = dx;
		this.dy = dy;
	}

	public void rodar() {
		x+=dx*speedBala;
		y+=dy*speedBala;	
		curLife++;
		if(curLife == life) {
			Jogo.balas.remove(this);
			return;
		}
	}
	
	public void renderizar(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillOval(this.getX() - Camera.x, this.getY() - Camera.y, width, height);
		
	}
	
}
