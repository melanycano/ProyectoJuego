package com.mygdx.gorobots.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.gorobots.pantallas.PantallaNivel;
import com.mygdx.gorobots.utiles.Config;

public abstract class ObjetoInteractivo {
	
	protected World mundo;
	protected TiledMap mapa;
//	protected TiledMapTile tile;
	protected Rectangle limites;
	protected Body cuerpo;
	protected Fixture fixture;
	
	protected PantallaNivel pantalla;
	protected MapObject objeto;
	
//	public ObjetoInteractivo(World mundo, TiledMap mapa, Rectangle limites) {
	public ObjetoInteractivo(PantallaNivel pantalla, MapObject objeto) {
		this.objeto = objeto;
		this.pantalla = pantalla;
		this.mundo = pantalla.getMundo();
		this.mapa = pantalla.getMapa();
		this.limites = ((RectangleMapObject) objeto).getRectangle();
		
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
		fixture = cuerpo.createFixture(fd);
		
	}
	
	public abstract void colisionRuedas(Robot robot);
	
	public void setFiltroDeCategoria(short filBit) {
		Filter filter = new Filter();
		filter.categoryBits = filBit;
		fixture.setFilterData(filter);
	}

	
	

}
