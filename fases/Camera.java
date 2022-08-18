package com.fornellogames.fases;

public class Camera {

	public static int x;
	public static int y;
	
	public static int clamp(int atual, int minimo, int maximo) {
		if(atual < minimo) {
			atual = minimo;
		}
		
		if(atual > maximo) {
			atual = maximo;
		}
		
		return atual;
	}
	
}
