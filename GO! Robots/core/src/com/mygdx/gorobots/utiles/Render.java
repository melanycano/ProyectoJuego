package com.mygdx.gorobots.utiles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.gorobots.GoRobots;
import com.mygdx.gorobots.pantallas.PantallaNivel;
import com.mygdx.gorobots.sprites.Robot;

public class Render {

	public static SpriteBatch sb;
	public static GoRobots app;
//	public static PantallaNivel pantallaNivel;
//	public static Robot robot;
	
	public static void LimpiarPantalla () {
		ScreenUtils.clear(1, 1, 1, 1);
	}
	
	public static void begin () {
		sb.begin();
	}
	
	public static void end () {
		sb.end();
	}
}
