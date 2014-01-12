package de.myreality.plox;

import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;

import de.myreality.plox.ai.AlienStrategy;
import de.myreality.plox.ai.RotationStrategy;
import de.myreality.plox.ai.TargetStrategy;

public class GameObjectFactory {
	
	
	public GameObject createAlien(int x, int y, GameObject player, Planet planet) {
		
		int size = Gdx.graphics.getHeight() / 6;
		
		GameObject object = new GameObject(x, y, 100, size, size, Resources.ALIEN, GameObjectType.ALIEN);		
		object.setStrategy(new AlienStrategy(player, planet));
		return object;
	}
	
	public GameObject createPlayer(int x, int y) {
		
		int size = Gdx.graphics.getHeight() / 6;
		GameObject object = new GameObject(x, y, 100, size, size, Resources.PLAYER, GameObjectType.PLAYER);
		object.setStrategy(new RotationStrategy());
		return object;
	}
	
	public GameObject createShot(int x, int y, int targetX, int targetY, float speed) {	
		
		int size = Gdx.graphics.getHeight() / 20;
		
		GameObject object = new GameObject(x, y, 100, size, size, Resources.SHOT, GameObjectType.SHOT);		
		object.setStrategy(new TargetStrategy(x, y, targetX, targetY, speed));
		return object;
	}
	
	public Planet createPlanet(int x, int y, TweenManager manager) {
		
		int size = Gdx.graphics.getWidth() / 3;
		
		x -= size / 2f;
		y -= size / 2f;
		
		return new Planet(x, y, size, Resources.PLANET_HEAL, manager);
	}
}
