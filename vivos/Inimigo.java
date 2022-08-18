package com.fornellogames.vivos;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.fornellogames.fases.Camera;
import com.fornellogames.fases.Faseum;
import com.fornellogames.main.Jogo;

public class Inimigo extends Vivo {

	private double speed = 1;
	
	private int vida = 10;
	
	private boolean atirar = false;
	
	private int framesTiro = 0, maxFramesTiro = 20;
	
	private int maskx = 8;
	private int masky = 8;
	private int maskw = 5;
	private int maskh = 5;
	
	private int frames = 0, maxFrames = 20, index = 0, maxIndex = 1;
	private boolean moveu = false;
	private BufferedImage[] atacando;
	
	private int grames = 0, maxGrames = 20, ondex = 0, maxOndex = 1;
	private boolean parou = true;
	private BufferedImage[] parado;
	
	public Inimigo(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		
		parado = new BufferedImage[2];
		parado[0] = Jogo.spritesheet.getSprite(48, 16, 16, 16);
		parado[1] = Jogo.spritesheet.getSprite(64, 16, 16, 16);
		
		atacando = new BufferedImage[2];
		atacando[0] = Jogo.spritesheet.getSprite(48, 32, 16, 16);
		atacando[1] = Jogo.spritesheet.getSprite(64, 32, 16, 16);
		
	}
	
	public void rodar() {
		if(Jogo.rand.nextInt(100) < 75) {
			
		if((int)x > Jogo.jogador.getX() + 100 || (int)x < Jogo.jogador.getX() - 100) {
			atirar = false;
			moveu = false;
			parou = true;
		}
		else if((int)y > Jogo.jogador.getY() + 100 || (int)y < Jogo.jogador.getY() - 100) {
			atirar = false;
			moveu = false;
			parou = true;
		}
		else {
			if(colidindoComJogador() == false) {
			if((int)x < Jogo.jogador.getX() && Faseum.isFree((int)(x+speed), this.getY())) {
				moveu = true;
				parou = false;
				x+=speed;
				framesTiro++;
				if(framesTiro == maxFramesTiro) {
					atirar = true;
					framesTiro = 0;
				}else {
					atirar = false;
			}
			}
			else if((int)x > Jogo.jogador.getX() && Faseum.isFree((int)(x-speed), this.getY())){
				moveu = true;
				parou = false;
				x-=speed;
				framesTiro++;
				if(framesTiro == maxFramesTiro) {
					atirar = true;
					framesTiro = 0;
				}else {
					atirar = false;
			}
			}
			else if((int)y < Jogo.jogador.getY() && Faseum.isFree(this.getX(), (int)(y+speed))) {
				moveu = true;
				parou = false;
				y+=speed;
				framesTiro++;
					if(framesTiro == maxFramesTiro) {
						atirar = true;
						framesTiro = 0;
					}else {
						atirar = false;
				}
			}
			else if((int)y > Jogo.jogador.getY() && Faseum.isFree(this.getX(), (int)(y-speed))){
				moveu = true;
				parou = false;
				y-=speed;
				framesTiro++;
				if(framesTiro == maxFramesTiro) {
					atirar = true;
					framesTiro = 0;
				}else {
					atirar = false;
			}
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
		}else {
			if(Jogo.rand.nextInt(100) < 25) {
				Jogo.jogador.vida--;
			}
		}
			
			if(parou) {
				grames++;
				if(grames == maxGrames) {
					grames = 0;
					ondex++;
					if(ondex > maxOndex) {
						ondex = 0;
						}
					}
				
		}
			}
			}
		
			collidingBalas();
			
			if(atirar) {		
				int dx = -1;
				int px = -8;
				int py = 6;
				Bala bala = new Bala(this.getX()+px, this.getY()+py, 3, 3, null, dx, 0);
				Jogo.balas.add(bala);
				}
			
			if(atirar) {		
				int dx = 1;
				int px = 18;
				int py = 6;
				Bala bala = new Bala(this.getX()+px, this.getY()+py, 3, 3, null, dx, 0);
				Jogo.balas.add(bala);
				}
			
			if(vida <= 0) {
				Jogo.vivos.remove(this);
				return;
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
	
	public boolean colidindoComJogador() {
		Rectangle inimigoColisao = new Rectangle(this.getX() + maskx, this.getY() + masky, maskw, maskh);
		Rectangle jogador = new Rectangle(Jogo.jogador.getX(), Jogo.jogador.getY(), 16, 16);
		
		return inimigoColisao.intersects(jogador);
	}
	
	public void renderizar(Graphics g) {
		if(parou) {
			g.drawImage(parado[ondex], this.getX() - Camera.x, this.getY() - Camera.y, null);
		}else if(moveu) {
			g.drawImage(atacando[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
		}
		
	}

}
