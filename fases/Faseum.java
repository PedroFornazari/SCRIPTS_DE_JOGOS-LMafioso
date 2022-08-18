package com.fornellogames.fases;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.fornellogames.graficos.Fundos;
import com.fornellogames.graficos.Spritesheet;
import com.fornellogames.main.Jogo;
import com.fornellogames.vivos.Cura;
import com.fornellogames.vivos.Dinheiro;
import com.fornellogames.vivos.Inimigo;
import com.fornellogames.vivos.Jogador;
import com.fornellogames.vivos.Municao;
import com.fornellogames.vivos.Pistola;
import com.fornellogames.vivos.Vivo;

public class Faseum {

	public static Tile[] tiles;
	public static int WIDTH, HEIGHT;
	public static final int TILE_SIZE = 16;
	
	public Faseum(String path) {
		try {
			BufferedImage mapaum = ImageIO.read(getClass().getResource(path));
			int [] pixels = new int [mapaum.getWidth() * mapaum.getHeight()];
			WIDTH = mapaum.getWidth();
			HEIGHT = mapaum.getHeight();
			tiles = new Tile[mapaum.getWidth() * mapaum.getHeight()];
			mapaum.getRGB(0, 0, mapaum.getWidth(), mapaum.getHeight(), pixels, 0, mapaum.getWidth());
			for(int xx = 0; xx < mapaum.getWidth(); xx++) {
				for(int yy = 0; yy < mapaum.getHeight(); yy++) {
					int pixelAtual = pixels [xx + (yy*mapaum.getWidth())];
					
					if (pixelAtual == 0xFF009600) {
						//Grama
						tiles[xx + (yy * WIDTH)] = new Pisos(xx*16, yy*16, Tile.TILE_GRAMA);
					}else if (pixelAtual == 0xFF000000) {
						//Rua
						tiles[xx + (yy * WIDTH)] = new Pisos(xx*16, yy*16, Tile.TILE_RUA);
					}else if (pixelAtual == 0xFFF5FF00) {
						//Rua Amarela
						tiles[xx + (yy * WIDTH)] = new Pisos(xx*16, yy*16, Tile.TILE_FAIXA);
					}else if (pixelAtual == 0xFF6D6D6D) {
						//Barra da Rua
						tiles[xx + (yy * WIDTH)] = new Bloqueios(xx*16, yy*16, Tile.TILE_BARRA);
					}else if (pixelAtual == 0xFF004100) {
						//Arbusto
						tiles[xx + (yy * WIDTH)] = new Bloqueios(xx*16, yy*16, Tile.TILE_ARBUSTO);
					}else if (pixelAtual == 0xFF00FF00) {
						//Grama Diferente
						tiles[xx + (yy * WIDTH)] = new Pisos(xx*16, yy*16, Tile.TILE_GRAMADIF);
					}else if (pixelAtual == 0xFFFF5500) {
						//Muro 
						tiles[xx + (yy * WIDTH)] = new Bloqueios(xx*16, yy*16, Tile.TILE_MURO);
					}else if (pixelAtual == 0xFF953A00) {
						//Porta
						tiles[xx + (yy * WIDTH)] = new Bloqueios(xx*16, yy*16, Tile.TILE_PORTAS);
					}else if (pixelAtual == 0xFF3A3A3A) {
						//Bloqueio
						tiles[xx + (yy * WIDTH)] = new Bloqueios(xx*16, yy*16, Tile.TILE_BLOCK);
					}else if (pixelAtual == 0xFF0000FF) {
						//Jogador
						Jogo.jogador.setX(xx*16);
						Jogo.jogador.setY(yy*16);
						tiles[xx + (yy * WIDTH)] = new Pisos(xx*16, yy*16, Tile.TILE_GRAMA);
					}else if (pixelAtual == 0xFFFF0000) {
						//Inimigos
						Jogo.vivos.add(new Inimigo(xx*16, yy*16, 16, 16, Vivo.INIMIGO_EN));
						tiles[xx + (yy*WIDTH)] = new Pisos(xx*16, yy*16, Tile.TILE_GRAMA);
					}else if (pixelAtual == 0xFF616ECB) {
						//Pistola
						Jogo.vivos.add(new Pistola(xx*16, yy*16, 16, 16, Vivo.PISTOLA_EN));
						tiles[xx + (yy * WIDTH)] = new Pisos(xx*16, yy*16, Tile.TILE_GRAMA);
					}else if (pixelAtual == 0xFF2CACB2) {
						//Municao
						Jogo.vivos.add(new Municao(xx*16, yy*16, 16, 16, Vivo.MUNICAO_EN));
						tiles[xx + (yy * WIDTH)] = new Pisos(xx*16, yy*16, Tile.TILE_GRAMA);
					}else if (pixelAtual == 0xFFEE13B8) {
						//Cura
						Jogo.vivos.add(new Cura(xx*16, yy*16, 16, 16, Vivo.CURA_EN));
						tiles[xx + (yy * WIDTH)] = new Pisos(xx*16, yy*16, Tile.TILE_GRAMA);
					}else if (pixelAtual == 0xFFFFBA00) {
						//Dinheiro
						Jogo.vivos.add(new Dinheiro(xx*16, yy*16, 16, 16, Vivo.DINHEIRO_EN));
						tiles[xx + (yy * WIDTH)] = new Pisos(xx*16, yy*16, Tile.TILE_GRAMA);
					}else {
						//Grama
						tiles[xx + (yy * WIDTH)] = new Pisos(xx*16, yy*16, Tile.TILE_GRAMA);
					}
					
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static boolean isFree(int xnext, int ynext) {
		int x1 = xnext / TILE_SIZE;
		int y1 = ynext / TILE_SIZE;
		
		int x2 = (xnext + TILE_SIZE - 1) / TILE_SIZE;
		int y2 = ynext / TILE_SIZE;
		
		int x3 = xnext / TILE_SIZE;
		int y3 = (ynext + TILE_SIZE - 1) / TILE_SIZE;
		
		int x4 = (xnext + TILE_SIZE - 1) / TILE_SIZE;
		int y4 = (ynext + TILE_SIZE - 1) / TILE_SIZE;
		
		return ! ((tiles[x1 + (y1*Faseum.WIDTH)] instanceof Bloqueios) ||
				(tiles[x2 + (y2*Faseum.WIDTH)] instanceof Bloqueios) ||
				(tiles[x3 + (y3*Faseum.WIDTH)] instanceof Bloqueios) ||
				(tiles[x4 + (y4*Faseum.WIDTH)] instanceof Bloqueios));
	}
	
	public static void restartGame() {
		Jogo.vivos.clear();
		Jogo.vivos = new ArrayList<Vivo>();
		Jogo.fundos = new Fundos("/fundos.png");
		Jogo.spritesheet = new Spritesheet("/jogadoritens1.png");
		Jogo.jogador = new Jogador(0, 0, 16, 16, Jogo.spritesheet.getSprite(0, 0, 16, 16));
		Jogo.vivos.add(Jogo.jogador);
		Jogo.faseum = new Faseum("/mapa1.png");
		return;
	}
			
	public void renderizar(Graphics g) {
		int xstart = Camera.x/16;
		int ystart = Camera.y/16;
		
		int xfinal = xstart + (Jogo.WIDTH / 16) + 1;
		int yfinal = ystart + (Jogo.HEIGHT / 16);
		
		for(int xx = xstart; xx <= xfinal; xx++) {
			for(int yy = ystart; yy <= yfinal; yy++) {
				if(xx < 0 || yy < 0 || xx >= WIDTH || yy >= HEIGHT)
					continue;
				Tile tile = tiles [xx + (yy*WIDTH)];
				tile.renderizar(g);
				
			}
			
		}
		
	}
			
	
}
