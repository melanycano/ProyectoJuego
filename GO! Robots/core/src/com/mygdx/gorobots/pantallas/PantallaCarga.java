package com.mygdx.gorobots.pantallas;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.gorobots.elementos.Imagen;
import com.mygdx.gorobots.utiles.Config;
import com.mygdx.gorobots.utiles.Recursos;
import com.mygdx.gorobots.utiles.Render;

public class PantallaCarga implements Screen{
	
	Imagen fondo;
	SpriteBatch b;
	
	float transparencia = 0; 										//Equivale a 100% transparente
	boolean fadeEntradaTerminado = false, fadeTerminado = false; 	//Controlar el desvanecimiento de la img	
	float contTiempo = 0, tiempoDeEspera = 5;
	float contTiempoAlTerminar = 0, tiempoAlTerminar = 5;
	float centroX, centroY;

	@Override
	public void show() {
		
		fondo = new Imagen (Recursos.LOGO);
		
		centroX = Config.ANCHO/ 2f - (fondo.getS().getWidth() / 2f);
		centroY = Config.ALTO / 2f - (fondo.getS().getHeight() / 2f);
		
		fondo.setPosicion(centroX, centroY);
		b = Render.sb;		
		fondo.setTransparencia(0);
	}

	@Override
	public void render(float delta) {
		procesarFade();
		Render.LimpiarPantalla();
		b.begin();
		fondo.dibujar();
		b.end();
	}

	//CONTROL DE LA TRANSPARENCIA
	private void procesarFade() { 
		fondo.setTransparencia(transparencia);	//ESTABLECE LA TRANSPARENCIA ACTUAL DEL FONDO
		
		// (!fadeEntradaTerminado) COMO fade = false, !fade = true, ENTONCES EL CÓDIGO SE EJECUTA PORQUE SE CUMPLE LA CONDICIÓN 
		if (!fadeEntradaTerminado) {			//SI EL DESVANECIMIENTO DE ENTRADA NO A TERMINADO
			transparencia += 0.01f;
			
			if (transparencia > 1) {
				transparencia = 1;
				fadeEntradaTerminado = true;
			}	
		} else {								//SI EL DESVANECIMIENTO DE ENTRADA A TERMINADO
			contTiempo += 0.1f;
			if (contTiempo > tiempoDeEspera) {
				transparencia -= 0.01f;
				if (transparencia < 0) {
					transparencia = 0;
					fadeTerminado = true;
				}
			}
		}
		
		if (fadeTerminado) {					//TIEMPO DE ESPERA HASTA PASAR A LA SIGUIENTE PANTALLA
			contTiempoAlTerminar += 0.04f;
			if(contTiempoAlTerminar > tiempoAlTerminar) Render.app.setScreen(new PantallaMenu());
		}
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	
	
}
