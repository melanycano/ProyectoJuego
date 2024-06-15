package com.mygdx.gorobots.sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.gorobots.GoRobots;
import com.mygdx.gorobots.pantallas.PantallaNivel;
import com.mygdx.gorobots.utiles.Config;
import com.mygdx.gorobots.utiles.Recursos;
import com.mygdx.gorobots.utiles.Render;

public class Robot extends Sprite{
	
	
	public World mundo;
	public Body cuerpo;
	
	//VARIABLE DONDE ALMACENAREMOS EL CONJUTO DE SPRITES
	private TextureRegion robotQuieto;
//	private TextureRegion robotMuerto;
	
	//ENUMERADOAR PARA LOS ESTADOS DEL ROBOT
	public EstadosRobot estadoActual;
	public EstadosRobot estadoAnterior;
	
	//ANIMACIONES PARA LOS MOVIMIENTOS DEL ROBOT
	private Animation robotAndando;
	private Animation robotSaltando;
	private Animation robotMuerto;
	
	private float tiempoEstado = 0;
	private boolean porDerecha = true;

	private boolean muerto = false;
	public boolean estaMuriendo = false;
	private float tiempoMuerto = 0;
	
	
//	public Robot (World mundo, String sprite) {
	public Robot (PantallaNivel pantalla) {
	
		
		super(pantalla.getAtlas().findRegion(Recursos.SPRITE_ROBOT_1));
		this.mundo = pantalla.getMundo();
		
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
			frames.clear();
			//ANIMACION MURIENDO (FALTAAAAAAAAAA)
			
	//		robotMuerto = new TextureRegion(getTexture(), 562, 337, 179, 128);
			TextureRegion frame4 = new TextureRegion(getTexture(), 562, 337, 179, 128);	//robot_redDamage1
			TextureRegion frame5 = new TextureRegion(getTexture(), 743, 337, 179, 128);	//robot_redDamage2
			frames.add(frame4);
			frames.add(frame5);
			robotMuerto = new Animation<>(0.2f, frames, Animation.PlayMode.NORMAL);
			
		robotQuieto = new TextureRegion(getTexture(), 1, 151, 180, 150);
		estructuraRobot();
		setBounds(1 / Config.PPM, 151 / Config.PPM, 180 / Config.PPM, 150 / Config.PPM);
		setRegion(robotQuieto);
		setSize(0.5f,0.4f);
		
		
	}

	
	public void update (float d) {
		
//		setPosition(cuerpo.getPosition().x - getWidth() / 2, cuerpo.getPosition().y - getHeight() / 2);
//		setRegion(getFrame(d));
//		
		 if(muerto) {
	            tiempoMuerto += d;
	            if (tiempoMuerto >= 1.0f) { //TIEMPO DE ESPERA ANTES DE REAPARECER
	                reaparicion();
	                tiempoMuerto = 0;
	            }
	        } else {
	            setPosition(cuerpo.getPosition().x - getWidth() / 2, cuerpo.getPosition().y - getHeight() / 2);
	        }
		 
		 setRegion(getFrame(d));
		
		
	}

	private TextureRegion getFrame(float d) {
		
		estadoActual = getEstado();
		TextureRegion region = robotQuieto;	
//		
		switch (estadoActual) {
		
			case MUERTO: 
				region = (TextureRegion) robotMuerto.getKeyFrame(tiempoEstado);
				break;
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
				
			case QUIETO:
				region = robotQuieto;	
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

		if(muerto) {
			return EstadosRobot.MUERTO;
		}
		else if(cuerpo.getLinearVelocity().y > 0 || (cuerpo.getLinearVelocity().y < 0 && estadoAnterior == EstadosRobot.SALTANDO)) 
			return EstadosRobot.SALTANDO;
		
		else if(cuerpo.getLinearVelocity().y < 0)
			return EstadosRobot.CAYENDO;
		
		else if(cuerpo.getLinearVelocity().x != 0)
			return EstadosRobot.ANDANDO;
		
		else 
			return EstadosRobot.QUIETO;
		
	}

	//METODOS PARA LA SECUENCIA DE MUERTE DEL PERSONAJE
	public boolean isMuerto() { return muerto; }

	public void setEstaMuerto(boolean muerto) {
		this.muerto = muerto;
		if(muerto) iniciarMuerte();
	}
		
	public void iniciarMuerte() {
		
		cuerpo.setLinearVelocity(0, 0);
//		estaMuriendo = true;
//  	estadoActual = EstadosRobot.MUERTO;
//      tiempoMuerto = 0; 
       		
	}

	public void reaparicion() {

		cuerpo.setTransform(new Vector2(5 / Config.PPM, 100 / Config.PPM), 0);
		cuerpo.setLinearVelocity(0, 0);
		estadoActual = EstadosRobot.QUIETO;
		muerto = false;
		estaMuriendo = false;
		
	}
	
	


	private void estructuraRobot() {
		//DEFINICION DE LAS PROPIEDADES DEL CUERPO
		BodyDef bd = new BodyDef();
		bd.position.set(5 / Config.PPM, 100 / Config.PPM); 		//POSICION INICAL DEL CUERPO EN EL MUNDO
		bd.type = BodyDef.BodyType.DynamicBody;					//TIPO DE CUERPO /AFECTADO POR LA GRAVEDAD Y COALISIONES
		cuerpo = mundo.createBody(bd);							//CREO EL CUERPO EN EL MUNDO BOX2D CON LAS PROPIEDADES DEFINIDAS

		//DEFINICION DEL FIXTURE/FORMA ADJUNTA AL CUERPO
		FixtureDef fd = new FixtureDef();
		
		CircleShape shape = new CircleShape();					//FORMA 
		shape.setRadius(20 / Config.PPM);						//RADIO (ORIGINAL: 20)
		fd.filter.categoryBits = GoRobots.ROBOT_BIT;			//LE DAMOS UNA CATEGORIA
		fd.filter.maskBits = GoRobots.DEFAULT_BIT | GoRobots.AGUA_BIT | GoRobots.SIERRA_BIT | GoRobots.CHECKPOINT_BIT | GoRobots.TRAMPOLIN_BIT;		
		
		fd.shape = shape;										
		cuerpo.createFixture(fd).setUserData(this);								//ADJUNTA EL FIXTURE AL CUERPO
		

		//DEFINICION DE BORDES PARA EL SENSOR
		EdgeShape ruedas = new EdgeShape();
		ruedas.set(new Vector2( -22 / Config.PPM, -22 / Config.PPM), new Vector2( 22 / Config.PPM, -22 / Config.PPM));	//PUNTO DE INICIO Y FINAL DE LA LINEA V2(X,Y), V2(X,Y)
		fd.filter.categoryBits = Render.app.ROBOT_RUEDAS_BIT;
		fd.shape = ruedas;
		fd.isSensor = true;
		
//		cuerpo.createFixture(fd).setUserData("ruedas");
		cuerpo.createFixture(fd).setUserData(this);
		
		
	}

}
