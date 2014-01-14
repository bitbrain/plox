package de.myreality.plox.ai;

import de.myreality.plox.GameObject;
import de.myreality.plox.GameObjectStrategy;

public class OrbitStrategy implements GameObjectStrategy {
	
	private float angle;
	
	private float length;
	
	private GameObject target;
	
	public OrbitStrategy(GameObject target, float startAngle, float length) {
		angle = startAngle;
		this.length = length;
		this.target = target;
	}

	@Override
	public void update(float delta, GameObject source) {
		
		angle -= 100 * delta;
		
		if (angle > 360f) {
			angle = 0;
		}
		
		float x = (float) (target.getCenterX() + Math.cos(Math.toRadians(angle)) * length);
		float y = (float) (target.getCenterY() + Math.sin(Math.toRadians(angle)) * length);
		
		source.setX(x);
		source.setY(y);
		
	}

}
