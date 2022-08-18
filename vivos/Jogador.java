package com.fornellogames.vivos;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.fornellogames.fases.Camera;
import com.fornellogames.fases.Faseum;
import com.fornellogames.graficos.Fundos;
import com.fornellogames.graficos.Spritesheet;
import com.fornellogames.main.Jogo;

public class Jogador extends Vivo {
	
	
	public boolean up, down, right, left;
	public int direct_right = 0, direct_left = 1;
	public int direct = direct_right;
	public double speed = 0.8;
	
	private int frames = 0, maxFrames = 20, index = 0, maxIndex = 1;
	private boolean moveu = false;
	private BufferedImage[] animadireita;
	private BufferedImage[] animaesquerda;
	
	private int grames = 0, maxGrames = 20, ondex = 0, maxOndex = 1;
	private boolean parado = true;
	private BufferedImage[] paradodireita;
	private BufferedImage[] paradoesquerda;
	
	private boolean armado = false;
	
	public boolean atirando = false;
	
	public static double balas = 0;
	
	public static double grana = 0;
	
	public double vida = 50; 
	
	public Jogador(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		
		animadireita = new BufferedImage[2];
		animaesquerda = new BufferedImage[2];
		paradodireita = new BufferedImage[2];
		paradoesquerda = new BufferedImage[2];
		
		for(int i =0; i < 2; i++) {
			animadireita[i] = Jogo.spritesheet.getSprite(16 + (i*16), 16, 16, 16);
		}

		for(int i =0; i < 2; i++) {
			animaesquerda[i] = Jogo.spritesheet.getSprite(16 + (i*16), 32, 16, 16);
		}
		
		for(int i =0; i < 2; i++) {
			paradodireita[i] = Jogo.spritesheet.getSprite(0 + (i*16), 0, 16, 16);
		}
		
		for(int i =0; i < 2; i++) {
			paradoesquerda[i] = Jogo.spritesheet.getSprite(32 + (i*16), 0, 16, 16);
		}
		
	}
	
	public void rodar() {
		moveu = false;
		parado = true; 
		
		if(right && Faseum.isFree((int)(x+speed), this.getY())) {
			moveu  = true;
			parado = false;
			direct = direct_right;
			x+=speed;
		}	
		else if(left && Faseum.isFree((int)(x-speed), this.getY())) {
			moveu  = true;
			parado = false;
			direct = direct_left;
			x-=speed;
		}
		if(up && Faseum.isFree(this.getX(), (int)(y-speed))) {
			moveu  = true;
			parado = false;
			direct = direct_right;
			y-=speed; 
		}
		else if(down && Faseum.isFree(this.getX(), (int)(y+speed))) {
			moveu  = true;
			parado = false;
			direct = direct_right;
			y+=speed;
		}
		
		if(moveu) {
			frames++;
			if(frames == maxFrames) {
				frames = 0;
				index++;
				if(index > maxIndex) {
					index = 0;
				}
			}
		}

		if(parado) {
			grames++;
			if(grames == maxGrames) {
				grames = 0;
				ondex++;
				if(ondex > maxOndex) {
					ondex = 0;
				}
			}
		}
		
		this.checkArma();
		this.checkCura();
		this.checkMunicao();
		this.checkDinheiro();
		this.collidingBalas();
		
		if(atirando) {
			atirando = false;
			if(armado && balas > 0) {
			balas--;
			int dx = 0;
			int px = 0;
			int py = 6;
			if(left || direct == direct_left) {
				px = -8;
				dx = -1;
			}else {
				px = 18;
				dx = 1;
			}
			
			Bala bala = new Bala(this.getX()+px, this.getY()+py, 3, 3, null, dx, 0);
			Jogo.balas.add(bala);
			}
		}
		
		if(vida <= 0) {
			Jogo.gameState = "GAME_OVER";
			vida = 0;
		}
		
		Camera.x = Camera.clamp(this.getX() - (Jogo.WIDTH/2) + 1, 0, Faseum.WIDTH*16 - Jogo.WIDTH);
		Camera.y = Camera.clamp(this.getY() - (Jogo.HEIGHT/2) + 1, 0, Faseum.HEIGHT*16 - Jogo.HEIGHT);
		
	}
	
	public void checkArma() {
		for(int i = 0; i < Jogo.vivos.size(); i++) {
			Vivo atual = Jogo.vivos.get(i);
			if(atual instanceof Pistola) {
				if(Vivo.isColliding(this, atual)) {
					armado = true;
					Jogo.vivos.remove(atual);
				}
			}
		}	
	}
	
	public void checkCura() {
		for(int i = 0; i < Jogo.vivos.size(); i++) {
			Vivo atual = Jogo.vivos.get(i);
			if(atual instanceof Cura) {
				if(Vivo.isColliding(this, atual)) {
					vida+=10;
					if(vida > 50) 
						vida = 50;
					Jogo.vivos.remove(atual);
				}
			}
		}	
	}
	
	public void checkMunicao() {
		for(int i = 0; i < Jogo.vivos.size(); i++) {
			Vivo atual = Jogo.vivos.get(i);
			if(atual instanceof Municao) {
				if(Vivo.isColliding(this, atual)) {
					balas+=100;
					if(balas > 600) 
						balas = 600;
					Jogo.vivos.remove(atual);
				}
			}
		}	
	}
	
	public void checkDinheiro() {
		for(int i = 0; i < Jogo.vivos.size(); i++) {
			Vivo atual = Jogo.vivos.get(i);
			if(atual instanceof Dinheiro) {
				if(Vivo.isColliding(this, atual)) {
					grana+=1000;
					Jogo.vivos.remove(atual);
				}
			}
		}	
	}
	
	public void collidingBalas() {
		for(int i = 0; i < Jogo.balas.size(); i++) {
			Vivo atual = Jogo.balas.get(i);
			if(atual instanceof Bala) {
				if(Vivo.isColliding(this, atual)) {
					vida--;
					Jogo.balas.remove(i);
					return;
				}
			}
		}
		
	}
	
	public void renderizar(Graphics g) {
		if(right && moveu) {
			g.drawImage(animadireita[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
			if(armado) {
				g.drawImage(Vivo.PISTOLADIR_EN, this.getX() - Camera.x+8, this.getY() - Camera.y, null);
			}
		}else if(left && moveu) {
			g.drawImage(animaesquerda[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
			if(armado) {
				g.drawImage(Vivo.PISTOLA_EN, this.getX() - Camera.x-8, this.getY() - Camera.y, null);
			}
		}
		
		if(direct == direct_right && parado) {
			g.drawImage(paradodireita[ondex], this.getX() - Camera.x, this.getY() - Camera.y, null);
			if(armado) {
				g.drawImage(Vivo.PISTOLADIR_EN, this.getX() - Camera.x+8, this.getY() - Camera.y, null);
			}
		}else if(direct == direct_left && parado) {
			g.drawImage(paradoesquerda[ondex], this.getX() - Camera.x, this.getY() - Camera.y, null);
			if(armado) {
				g.drawImage(Vivo.PISTOLA_EN, this.getX() - Camera.x-8, this.getY() - Camera.y, null);
			}
		}
		
		if(down) {
			g.drawImage(animadireita[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
			if(armado) {
				g.drawImage(Vivo.PISTOLADIR_EN, this.getX() - Camera.x+8, this.getY() - Camera.y, null);
			}
		}else if(up) {
			g.drawImage(animadireita[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
			if(armado) {
				g.drawImage(Vivo.PISTOLADIR_EN, this.getX() - Camera.x+8, this.getY() - Camera.y, null);
			}
			}
		
		}
			}

