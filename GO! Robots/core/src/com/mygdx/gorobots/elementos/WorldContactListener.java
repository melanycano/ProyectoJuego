package com.mygdx.gorobots.elementos;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.mygdx.gorobots.GoRobots;
import com.mygdx.gorobots.sprites.ObjetoInteractivo;
import com.mygdx.gorobots.sprites.Robot;

//SE LLAMA CUANDO DOS OBJETOS DE BOX2D COLISIONAN
public class WorldContactListener implements ContactListener{
	

	@Override
	public void beginContact(Contact contact) {
		Fixture fixtureA = contact.getFixtureA();
		Fixture fixtureB = contact.getFixtureB();
		
		int idColision = fixtureA.getFilterData().categoryBits | fixtureB.getFilterData().categoryBits;		//COMBINACION UNICA DE LOS BITS DE LOS DOS FIXTURES
		
		switch (idColision) {
		case GoRobots.ROBOT_RUEDAS_BIT | GoRobots.AGUA_BIT:
		case GoRobots.ROBOT_RUEDAS_BIT | GoRobots.SIERRA_BIT:
		case GoRobots.ROBOT_RUEDAS_BIT | GoRobots.TRAMPOLIN_BIT:
			
			 if(fixtureA.getFilterData().categoryBits == GoRobots.ROBOT_RUEDAS_BIT)
                 ((ObjetoInteractivo) fixtureB.getUserData()).colisionRuedas((Robot) fixtureA.getUserData());
			 	
             else
                 ((ObjetoInteractivo) fixtureA.getUserData()).colisionRuedas((Robot) fixtureB.getUserData());
			
			break;
			
		case GoRobots.ROBOT_RUEDAS_BIT | GoRobots.CHECKPOINT_BIT:
			
			break;

		default:
			break;
		}
		
	}
//		if(fixtureA.getUserData() == "ruedas" || fixtureB.getUserData() == "ruedas") {
//			Fixture ruedas = fixtureA.getUserData() == "ruedas" ? fixtureA : fixtureB;
//			Fixture objeto = ruedas == fixtureA ? fixtureB : fixtureA;
//			
//			if(objeto.getUserData() != null && ObjetoInteractivo.class.isAssignableFrom(objeto.getUserData().getClass())) {
//				Robot robot = (Robot) ruedas.getBody().getUserData();
//				((ObjetoInteractivo) objeto.getUserData()).colisionRuedas(robot);
//			}
//		}
		
		
		


	@Override
	public void endContact(Contact contact) {
		
		
		
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub
		
	}

}
