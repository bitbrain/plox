package de.myreality.plox.ai;

import com.badlogic.gdx.math.Vector2;

import de.myreality.plox.GameObject;
import de.myreality.plox.GameObjectStrategy;
import de.myreality.plox.Planet;

public class AlienStrategy implements GameObjectStrategy {
	
	private Planet planet;
	
	private float time;
	
	public AlienStrategy(GameObject player, Planet planet) {
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
		
		time += delta * 4;
		float bounceFactor = (float) Math.cos(time) * 2;
		
		target.setX(target.getX() + vec.x * speed * delta);
		target.setY(target.getY() + vec.y * speed * delta - bounceFactor);		
	}

}
