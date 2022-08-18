package com.fornellogames.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Menu {
	
	public String[] options = {"Jogar", "Sair"};
	
	public int currentOption = 0;
	public int maxOption = options.length - 1;
	
	public boolean up, down, enter;
	
	private boolean showMessageMenu = true;
	private int framesMenu = 0;
	
	public void rodar() {
		if(up) {
			up = false;
			currentOption--;
			if(currentOption < 0)
				currentOption = maxOption;
		}
		
		if(down) {
			down = false;
			currentOption++;
			if(currentOption > maxOption)
				currentOption = 0;
		}
		
		if(enter) {
			enter = false;
			if(options[currentOption] == "Jogar") {
				Jogo.gameState = "NORMAL";
			}else if(options[currentOption] == "Sair") {
				System.exit(1);
			}
			
		}
		
		this.framesMenu++;
		if(this.framesMenu == 50) {
			this.framesMenu = 0;
			if(this.showMessageMenu)
				this.showMessageMenu = false;
				else
					this.showMessageMenu = true;
		}
	}
	
	public void renderizar(Graphics g) {
		g.setColor(new Color(0, 0, 0));
		g.fillRect(0, 0, Jogo.WIDTH*Jogo.SCALE, Jogo.HEIGHT*Jogo.SCALE);
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial", Font.ITALIC, 150));
		g.drawString("LMafioso", 250, 200);
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial", Font.ITALIC, 75));
		g.drawString("Jogar", 300, 350);
		g.drawString("Sair", 300, 450);
		
		if(options[currentOption] == "Jogar" && showMessageMenu) {
			g.drawString("-->", 200, 350);
		}else if(options[currentOption] == "Sair" && showMessageMenu) {
			g.drawString("-->", 200, 450);
		}

		
	}

}
