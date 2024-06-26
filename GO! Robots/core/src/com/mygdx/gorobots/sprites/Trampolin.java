package com.mygdx.gorobots.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.gorobots.GoRobots;
import com.mygdx.gorobots.pantallas.PantallaNivel;

public class Trampolin extends ObjetoInteractivo {

//	public Trampolin(World mundo, TiledMap mapa, Rectangle limites) {
	public Trampolin(PantallaNivel pantalla, MapObject objeto) {
		super(pantalla, objeto);
		fixture.setUserData(this);
		setFiltroDeCategoria(GoRobots.TRAMPOLIN_BIT);
	}

	@Override
	public void colisionRuedas(Robot robot) {
		Gdx.app.log("Trampolin", "Colision");
		robot.cuerpo.applyLinearImpulse(new Vector2(0, 9.5f), robot.cuerpo.getWorldCenter(), true);

	}


	

}
