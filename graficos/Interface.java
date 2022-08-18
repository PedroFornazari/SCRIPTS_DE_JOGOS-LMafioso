package com.fornellogames.graficos;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.fornellogames.main.Jogo;
import com.fornellogames.vivos.Jogador;

public class Interface {

	public void renderizar(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(210, 10, 50, 10);
		g.setColor(Color.BLUE);
		g.fillRect(210, 10, (int) ((Jogo.jogador.vida/50)*50), 10);
		g.setColor(Color.WHITE);
		g.setFont(new Font("comic sans pro", Font.PLAIN, 9));
		g.drawString((int)Jogo.jogador.vida+"/"+(int)50, 210, 18);
		
		g.setFont(new Font("comic sans pro", Font.PLAIN, 9));
		g.drawString((int)Jogador.balas+"/"+(int)600, 220, 30);
		
		g.setColor(Color.YELLOW);
		g.fillOval(220, 35, 30, 30);
		g.setColor(Color.GRAY);
		g.setFont(new Font("comic sans pro", Font.PLAIN, 8));
		g.drawString("$ "+(int)Jogador.grana, 222, 55);
		
	}
	
}
