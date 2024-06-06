package com.mygdx.gorobots;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygdx.gorobots.GoRobots;
import com.mygdx.gorobots.utiles.Config;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle(Config.NOMBRE);
		config.setWindowedMode(Config.ANCHO, Config.ALTO);
		config.setResizable(true);
		
		new Lwjgl3Application(new GoRobots(), config);
	}
}
