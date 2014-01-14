package de.myreality.plox.ai;

import com.badlogic.gdx.math.Vector2;

import de.myreality.plox.GameObject;
import de.myreality.plox.GameObjectStrategy;

public class DirectionStrategy implements GameObjectStrategy {
	
	private float speed;
	private Vector2 velocity;
	
	public DirectionStrategy(int x, int y, int targetX, int targetY, float speed) {
		this.speed = speed;		
		velocity = new Vector2(targetX - x, targetY - y);
		velocity.nor();
	}

	@Override
	public void update(float delta, GameObject target) {
		target.setX(target.getX() + velocity.x * speed * delta);
		target.setY(target.getY() + velocity.y * speed * delta);
	}

}
