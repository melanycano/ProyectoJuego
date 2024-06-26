package com.mygdx.gorobots.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.gorobots.elementos.Imagen;
import com.mygdx.gorobots.elementos.Texto;
import com.mygdx.gorobots.io.KeyListener;
import com.mygdx.gorobots.utiles.Config;
import com.mygdx.gorobots.utiles.Recursos;
import com.mygdx.gorobots.utiles.Render;

public class PantallaMenu implements Screen {
	
	Imagen fondo;
	SpriteBatch b;
	Texto titulo;
	
	KeyListener entrada = new KeyListener(this);
	
	Texto opciones [] = new Texto[3];
	String textos [] = {"Inicio", "Opciones", "Salir"};
	
	public float tiempo = 0;
	int opcion = 1, cont = 0;
	boolean mouseArriba = false;

	@Override
	public void show() {
		
		fondo = new Imagen (Recursos.FONDO_MENU);
		fondo.setTamaño(Config.ANCHO, Config.ALTO);
		b = Render.sb;
		
		titulo = new Texto (Recursos.RUTA_FUENTE, 100, Color.valueOf("#D2704A"), false);
		titulo.setTexto("GO! Robots");
		titulo.setPosition((Config.ANCHO/2f) - (titulo.getAncho()/2f), 600);
		
		Gdx.input.setInputProcessor(entrada);
		
		int avance = 40;
		for (int i = 0; i < opciones.length; i++) {
			opciones[i] = new Texto(Recursos.RUTA_FUENTE, 30, Color.valueOf("#D2704A"), false);
			opciones[i].setTexto(textos[i]);
			opciones[i].setPosition((Config.ANCHO / 2f) - (opciones[i]. getAncho() / 2f), ((Config.ALTO/ 3.2f) + (opciones[0].getAlto()/ 3.2f)) - ((opciones[i].getAlto()*i) + (avance * i)));
		}
		
	}

	@Override
	public void render(float delta) {
			
		b.begin();
		
		fondo.dibujar();
		titulo.dibujar();
		for (int i = 0; i < opciones.length; i++) {
			opciones[i].dibujar();
		}
		
		b.end();
		
		tiempo += delta;
		
		if(entrada.isAbajo()) {
			if(tiempo>0.1f) {
				tiempo = 0;
				opcion++;
				if(opcion > 3) opcion = 1;
			}
		}
		
		if(entrada.isArriba()) {
			if(tiempo>0.1f) {
				tiempo = 0;
				opcion--;
				if(opcion<1) opcion = 3;
			}
		}
		
		for (int i = 0; i < opciones.length; i++) {
			if((entrada.getMouseX() >= opciones[i].getX()) && (entrada.getMouseX() <= (opciones[i].getX() + opciones[i].getAncho()))) {
				if((entrada.getMouseY() >= opciones[i].getY() - opciones[i].getAlto()) && (entrada.getMouseY()<= (opciones[i].getY()))) {
					opcion = i+1;
					cont++;
				} 
			}	
		}
		
		if (cont>0) mouseArriba = true;
		else mouseArriba = false;
		
		for (int i = 0; i < opciones.length; i++) {
			if(i == (opcion -1)) opciones[i].setColor(Color.YELLOW);
			else opciones[i].setColor(Color.valueOf("#D2704A"));
		}
		
		if((entrada.isEnter()) || (entrada.isClick())){
			if((opcion==1) && (entrada.isEnter()) ||((opcion == 1) && (entrada.isClick()) && (mouseArriba))) {
				Render.app.setScreen(new PantallaNivel());
			} else if (opcion == 3) {
				Gdx.app.exit();
			}
		}
		
		
		
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void dispose() {
		
	}

}
