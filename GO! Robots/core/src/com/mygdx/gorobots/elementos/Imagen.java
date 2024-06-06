package com.mygdx.gorobots.elementos;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.gorobots.utiles.Render;

public class Imagen {

	private Texture t;
	private Sprite s;
	

	public Imagen (String ruta) {

		t = new Texture(ruta);
		s = new Sprite(t);

	}
	
	public void dibujar() {
		s.draw(Render.sb);
	}
	
	public void setTransparencia(float a) {
		s.setAlpha(a);
	}
	
	public void setPosicion(float x, float y) {
		s.setPosition(x, y);
	}
	
	public void setTamaño(float ancho, float alto) {
		s.setSize(ancho, alto);
	}

	public Sprite getS() {
		return s;
	}

	public float getImgAncho() {
		return s.getWidth();
	}
	
	public float getImgAlto() {
		return s.getHeight();
	}
	
}
