package de.myreality.plox;

import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

import de.myreality.plox.ai.DirectionStrategy;
import de.myreality.plox.ai.RotationStrategy;

public class GameObjectFactory {
	
	
	public GameObject createAlien(float x, float y) {
		
		int size = Gdx.graphics.getHeight() / 6;
		
		GameObject object = new GameObject(x, y, 100, size, size, Resources.get(Resources.ALIEN, Texture.class), GameObjectType.ALIEN);		

		return object;
	}
	
	public GameObject createPlayer(float x, float y) {
		
		int size = Gdx.graphics.getHeight() / 6;
		GameObject object = new Player(x, y, size);
		object.addStrategy(new RotationStrategy());
		return object;
	}
	
	public GameObject createPowerUp(float x, float y, PowerUpStrategy strategy) {
		
		int size = Gdx.graphics.getWidth() / 20;
		
		x -= size / 2f;
		y -= size / 2f;
		
		return new PowerUp(x, y, size, strategy);
	}
	
	public GameObject createShot(float x, float y, int targetX, int targetY, int speed, int size, int damage) {	
		return createShot(x, y, size, damage, new DirectionStrategy(x, y, targetX, targetY, speed), false);
	}
	
	public GameObject createShot(float x, float y, int size, int damage, GameObjectStrategy strategy, boolean protector) {	
		
		GameObject object = new Shot(x, y, size, size, damage, protector);		
		object.addStrategy(strategy);
		
		// Sound effect here!
		Sound s = Resources.get(Resources.SOUND_SHOT, Sound.class);
		long id = s.play(0.2f);
		s.setPan(id, (float) (1.0f + Math.random()), 0.2f);
		s.setPitch(id, (float) (1.2f + Math.random() * 0.4f));
		
		return object;
	}
	
	public Planet createPlanet(float x, float y, TweenManager manager) {
		
		int size = Gdx.graphics.getHeight() / 3;
		
		x -= size / 2f;
		y -= size / 2f;
		
		return new Planet(x, y, size, Resources.get(Resources.PLANET_HEAL, Texture.class), manager);
	}
}
