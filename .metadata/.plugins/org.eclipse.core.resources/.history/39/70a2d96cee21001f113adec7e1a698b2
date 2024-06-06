package com.mygdx.gorobots;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.gorobots.pantallas.PantallaNivel;
import com.mygdx.gorobots.utiles.Render;

public class GoRobots extends Game {
	
	public static final int vWidth = 400;	//PRUEBAS PARA EL HUB
	public static final int vHeight = 208;	//PRUEBAS PARA EL HUB
	
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
