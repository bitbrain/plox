package de.myreality.plox.ai;

import com.badlogic.gdx.math.Vector2;

import de.myreality.plox.GameObject;
import de.myreality.plox.GameObjectStrategy;
import de.myreality.plox.Planet;

public class AlienStrategy implements GameObjectStrategy {
	
	private GameObject player;
	
	private Planet planet;
	
	public AlienStrategy(GameObject player, Planet planet) {
		this.player = player;
		this.planet = planet;
	}

	@Override
	public void update(float delta, GameObject target) {
		
		Vector2 vec = new Vector2(planet.getCenterX() - target.getCenterX(), planet.getCenterY() - target.getCenterY());
		
		final int speed = 100;
		
		if (vec.len() < 10) {
			planet.damage(50);
			target.kill();
		}
		
		vec.nor();
		
		target.setX(target.getX() + vec.x * speed * delta);
		target.setY(target.getY() + vec.y * speed * delta);		
	}

}
