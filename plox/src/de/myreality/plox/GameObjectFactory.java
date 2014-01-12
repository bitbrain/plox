package de.myreality.plox;

import com.badlogic.gdx.Gdx;

import de.myreality.plox.ai.AlienStrategy;
import de.myreality.plox.ai.RotationStrategy;

public class GameObjectFactory {
	
	
	public GameObject createAlien(int x, int y, GameObject player) {
		
		int size = Gdx.graphics.getHeight() / 6;
		
		GameObject object = new GameObject(x, y, 100, size, size, Resources.ALIEN);		
		object.setStrategy(new AlienStrategy(player));
		return object;
	}
	
	public GameObject createPlayer(int x, int y) {
		
		int size = Gdx.graphics.getHeight() / 6;
		GameObject object = new GameObject(x, y, 100, size, size, Resources.PLAYER);
		object.setStrategy(new RotationStrategy());
		return object;
	}
	
	public Planet createPlanet(int x, int y) {
		
		int size = Gdx.graphics.getWidth() / 3;
		
		x -= size / 2f;
		y -= size / 2f;
		
		return new Planet(x, y, size, Resources.PLANET_HEAL);
	}
}
