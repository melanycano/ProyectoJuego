package com.mygdx.gorobots.elementos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.mygdx.gorobots.utiles.Render;

public class Texto {
	BitmapFont fuente;
	private float x=0, y=0;
	private String texto= "";
	GlyphLayout layout;
	
	public Texto (String rutaFuente, int dimension, Color color, boolean sombra) {
		generarTexto(rutaFuente, dimension,color, sombra);
		
	}

	private void generarTexto(String rutaFuente, int dimension, Color color, boolean sombra) {
		FreeTypeFontGenerator generador = new FreeTypeFontGenerator(Gdx.files.internal(rutaFuente));  //para poner la fuente
		FreeTypeFontParameter parametros = new FreeTypeFontGenerator.FreeTypeFontParameter();  //edicion del texto
		parametros.size = dimension;
		parametros.color = color;
		if(sombra) {
			parametros.shadowColor = Color.BLACK;
			parametros.shadowOffsetX = 1;
			parametros.shadowOffsetY = 1;
		}
		fuente = generador.generateFont(parametros);
		layout = new GlyphLayout();
	}
	
	public void dibujar () {
		fuente.draw(Render.sb,texto,x,y);
	}
	
	public void setColor(Color color) {
		fuente.setColor(color);
	}
	
	public void setPosition (float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public float getX () {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}
	
	public void setY(float y) {
		this.y = y;
	}

	public void setTexto(String texto) {
		this.texto = texto;
		layout.setText(fuente, texto);
	}
	
	public float getAncho() {
		return layout.width;
	}
	
	public float getAlto() {
		return layout.height;
	}
	
}
