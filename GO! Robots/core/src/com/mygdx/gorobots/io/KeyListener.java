package com.mygdx.gorobots.io;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.mygdx.gorobots.pantallas.PantallaMenu;
import com.mygdx.gorobots.pantallas.PantallaNivel;
import com.mygdx.gorobots.utiles.Config;

public class KeyListener implements InputProcessor{
	
	private boolean izquierda = false, derecha = false, arriba = false, abajo = false; 
	
//	private boolean izqPlayer2 = false, derPlayer2 = false;
	
	private boolean enter = false, click = false;
	
	private int mouseX = 0, mouseY = 0;
	
	private PantallaMenu app;
	private PantallaNivel nivel;
	
	
	
	public KeyListener(PantallaMenu app) {
		this.app = app;
	}
	
	public KeyListener(PantallaNivel nivel) {
		this.nivel = nivel;
	}
	
	public boolean isIzquierda() {
		return izquierda;
	}

	public boolean isDerecha() {
		return derecha;
	}

	public boolean isArriba() {
		return arriba;
	}
	
	public boolean isAbajo() {
		return abajo;
	}

//	public boolean isIzqPlayer2() {
//		return izqPlayer2;
//	}
//
//	public boolean isDerPlayer2() {
//		return derPlayer2;
//	}

	public int getMouseX() {
		return mouseX;
	}

	public int getMouseY() {
		return mouseY;
	}

	public boolean isEnter() {
		return enter;
	}

	public boolean isClick() {
		return click;
	}

	@Override
	public boolean keyDown(int keycode) {
		
	//	app.tiempo = 0.08f;
		
		
		if(keycode == Keys.DOWN) {
			abajo = true;
		} else if( keycode == Keys.UP) {
			arriba = true;
		}
		
		if(keycode == Keys.ENTER) enter = true;
		
		if(keycode == Keys.LEFT) {
			izquierda = true;
		} else if (keycode == Keys.RIGHT) {
			derecha = true;
		}
		
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		
		if(keycode == Keys.DOWN) {
			abajo = false;
		} else if (keycode == Keys.UP) {
			arriba = false;			
		}
		
		if(keycode == Keys.LEFT) {
			izquierda = false;
		} else if (keycode == Keys.RIGHT) {
			derecha = false;
		}
		
		
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		mouseX = screenX;
		mouseY = Config.ALTO - screenY;
		return false;
	}

	@Override
	public boolean scrolled(float amountX, float amountY) {
		return false;
	}

}
