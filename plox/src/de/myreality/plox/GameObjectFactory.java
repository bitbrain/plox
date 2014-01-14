package de.myreality.plox;

import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

import de.myreality.plox.ai.DirectionStrategy;
import de.myreality.plox.ai.RotationStrategy;

public class GameObjectFactory {
	
	
	public GameObject createAlien(int x, int y) {
		
		int size = Gdx.graphics.getHeight() / 6;
		
		GameObject object = new GameObject(x, y, 100, size, size, Resources.ALIEN, GameObjectType.ALIEN);		

		return object;
	}
	
	public GameObject createPlayer(int x, int y) {
		
		int size = Gdx.graphics.getHeight() / 6;
		GameObject object = new GameObject(x, y, 100, size, size, Resources.PLAYER, GameObjectType.PLAYER);
		object.addStrategy(new RotationStrategy());
		return object;
	}
	
	public GameObject createShot(int x, int y, int targetX, int targetY, float speed) {	
		
		int size = Gdx.graphics.getHeight() / 20;
		
		GameObject object = new GameObject(x, y, 100, size, size, Resources.SHOT, GameObjectType.SHOT);		
		object.addStrategy(new DirectionStrategy(x, y, targetX, targetY, speed));
		
		// Sound effect here!
		Sound s = Resources.SOUND_SHOT;
		long id = s.play(0.2f);
		s.setPan(id, (float) (1.0f + Math.random()), 0.2f);
		s.setPitch(id, (float) (1.2f + Math.random() * 0.4f));
		
		return object;
	}
	
	public Planet createPlanet(int x, int y, TweenManager manager) {
		
		int size = Gdx.graphics.getWidth() / 3;
		
		x -= size / 2f;
		y -= size / 2f;
		
		return new Planet(x, y, size, Resources.PLANET_HEAL, manager);
	}
}
