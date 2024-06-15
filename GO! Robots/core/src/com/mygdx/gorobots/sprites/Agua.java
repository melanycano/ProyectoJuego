package com.mygdx.gorobots.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.mygdx.gorobots.GoRobots;
import com.mygdx.gorobots.pantallas.PantallaNivel;

public class Agua extends ObjetoInteractivo {
	
	
//	public Agua(World mundo, TiledMap mapa, Rectangle limites) {
	public Agua(PantallaNivel pantalla, MapObject objeto) {
	super(pantalla, objeto);
		fixture.setUserData(this);
		setFiltroDeCategoria(GoRobots.AGUA_BIT);
		
	}
	
	@Override
	public void colisionRuedas(Robot robot) {
		Gdx.app.log("Aguas", "Colision");
		robot.setEstaMuerto(true);
		
		
	
	}




	
}
