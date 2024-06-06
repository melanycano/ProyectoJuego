package com.mygdx.gorobots.sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.gorobots.utiles.Config;

public abstract class ObjetoInteractivo {
	
	protected World mundo;
	protected TiledMap mapa;
	protected TiledMapTile tile;
	protected Rectangle limites;
	protected Body cuerpo;
	
	public ObjetoInteractivo(World mundo, TiledMap mapa, Rectangle limites) {
		this.mundo = mundo;
		this.mapa = mapa;
		this.limites = limites;
		
		crearObjeto();
		
	}

	public void crearObjeto() {
		
		BodyDef bd = new BodyDef();
		FixtureDef fd = new FixtureDef();
		PolygonShape shape = new PolygonShape();
		
		bd.type = BodyDef.BodyType.StaticBody;
		bd.position.set((limites.getX() + limites.getWidth() / 2) / Config.PPM , (limites.getY() + limites.getHeight() / 2) / Config.PPM);
		
		cuerpo = mundo.createBody(bd);
		
		shape.setAsBox(limites.getWidth() / 2 / Config.PPM, limites.getHeight() / 2 / Config.PPM);
		fd.shape = shape;
		cuerpo.createFixture(fd);
		
	}
	

}