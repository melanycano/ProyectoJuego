package com.mygdx.gorobots.sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.gorobots.pantallas.PantallaNivel;
import com.mygdx.gorobots.utiles.Config;
import com.mygdx.gorobots.utiles.Render;

public class Robot extends Sprite{
	
	
	public World mundo;
	public Body cuerpo;
	
	//VARIABLE DONDE ALMACENAREMOS EL CONJUTO DE SPRITES
	private TextureRegion robotQuieto;
	
	//ENUMERADOAR PARA LOS ESTADOS DEL ROBOT
	public EstadosRobot estadoActual;
	public EstadosRobot estadoAnterior;
	
	//ANIMACIONES PARA LOS MOVIMIENTOS DEL ROBOT
	private Animation robotAndando;
	private Animation robotSaltando;
	
	private float tiempoEstado = 0;
	private boolean porDerecha = true;
	
	
	
	public Robot (World mundo, String sprite) {
		
		super(Render.pantallaNivel.getAtlas().findRegion(sprite));
		this.mundo = mundo;
		
		estadoActual = EstadosRobot.QUIETO;
		estadoAnterior = EstadosRobot.QUIETO;
		
//		if (nroJugador == 1) {
			 
			//ANIMACION ANDANDO
			Array<TextureRegion> frames = new Array <TextureRegion>();		//ARRAY PARA ALMACENAR LAS TEXTURAS/SPRITES
			
			TextureRegion frame1 = new TextureRegion(getTexture(), 1, 151, 180, 150); 	//robot_redDrive1
			TextureRegion frame2 = new TextureRegion(getTexture(), 199, 319, 180, 146);	//robot_redDrive2
			
			frames.add(frame1);
			frames.add(frame2);
			
			robotAndando = new Animation<>(0.1f, frames, Animation.PlayMode.LOOP);
			frames.clear();
			
			//ANIMACION SALTANDO
			TextureRegion frame3 = new TextureRegion(getTexture(), 1, 303, 196, 162);	//robot_redJump
			frames.add(frame3);
			robotSaltando = new Animation<>(0.1f, frames, Animation.PlayMode.NORMAL);
			
//		}
			
		robotQuieto = new TextureRegion(getTexture(), 1, 151, 180, 150);
		estructuraRobot();
		setBounds(1 / Config.PPM, 151 / Config.PPM, 180 / Config.PPM, 150 / Config.PPM);
		setRegion(robotQuieto);
		setSize(0.5f,0.4f);

		
		
		
	}

	
	public void update (float d) {
		setPosition(cuerpo.getPosition().x - getWidth() / 2, cuerpo.getPosition().y - getHeight() / 2);
		setRegion(getFrame(d));
	}

	private TextureRegion getFrame(float d) {
		
		estadoActual = getEstado();
		TextureRegion region = robotQuieto;	
//		
		switch (estadoActual) {
			case SALTANDO:
				region = (TextureRegion) robotSaltando.getKeyFrame(tiempoEstado);
				break;
//				
			case ANDANDO:
				region = (TextureRegion) robotAndando.getKeyFrame(tiempoEstado, true);
				break;
//				
			case CAYENDO:
				break;
//				
			case QUIETO:
			//	region = robotQuieto;	
				break;
		
		}

		
//		DEPENDIENDO DE LA DIRECCION DEL MOVIMIENTO
		if((cuerpo.getLinearVelocity().x < 0 || !porDerecha) && !region.isFlipX()) {
			region.flip(true, false);
			porDerecha = false;
		} else if ((cuerpo.getLinearVelocity().x > 0 || porDerecha) && region.isFlipX()) {
			region.flip(true, false);
			porDerecha = true;
		}
		
		tiempoEstado = (estadoActual == estadoAnterior)? tiempoEstado + d: 0;
		estadoAnterior = estadoActual;
		
		return region;
		

	}

	private EstadosRobot getEstado() {

		if(cuerpo.getLinearVelocity().y > 0 || (cuerpo.getLinearVelocity().y < 0 && estadoAnterior == EstadosRobot.SALTANDO)) 
			return EstadosRobot.SALTANDO;
		
		else if(cuerpo.getLinearVelocity().y < 0)
			return EstadosRobot.CAYENDO;
		
		else if(cuerpo.getLinearVelocity().x != 0)
			return EstadosRobot.ANDANDO;
		
		else 
			return EstadosRobot.QUIETO;
		
	}
	
	private void estructuraRobot() {
		BodyDef bd = new BodyDef();
		bd.position.set(5 / Config.PPM, 100 / Config.PPM); // 5, 100
		bd.type = BodyDef.BodyType.DynamicBody;
		cuerpo = mundo.createBody(bd);
		
		FixtureDef fd = new FixtureDef();
		
		CircleShape shape = new CircleShape();
		shape.setRadius(20 / Config.PPM);
		
		fd.shape = shape;
		cuerpo.createFixture(fd);
		
		
	}

}
