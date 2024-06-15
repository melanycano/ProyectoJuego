package com.mygdx.gorobots;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.gorobots.pantallas.PantallaNivel;
import com.mygdx.gorobots.utiles.Render;

public class GoRobots extends Game {
	
	public static final int vWidth = 400;	//PRUEBAS PARA EL HUB
	public static final int vHeight = 208;	//PRUEBAS PARA EL HUB
	
	public static final short DEFAULT_BIT = 1;
	public static final short ROBOT_BIT = 2;
	public static final short AGUA_BIT = 4;
	public static final short SIERRA_BIT = 8;
	public static final short CHECKPOINT_BIT = 16;
	public static final short TRAMPOLIN_BIT = 32;
	public static final short CHECKPOINT_ACTIVADO_BIT = 64;
	public static final short ROBOT_RUEDAS_BIT = 128;
	
	public void create () {
		Render.app = this;
		Render.sb = new SpriteBatch();
		this.setScreen(new PantallaNivel());
	}

	
	@Override
	public void render () {
		super.render();
	}
	

	public void dispose () {
		Render.sb.dispose();
		this.getScreen();
	}
}
