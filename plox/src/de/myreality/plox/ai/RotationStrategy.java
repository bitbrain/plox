package de.myreality.plox.ai;

import de.myreality.plox.GameObject;
import de.myreality.plox.GameObjectStrategy;

public class RotationStrategy implements GameObjectStrategy {

	@Override
	public void update(float delta, GameObject target) {
		target.setRotation(target.getRotation() + 100f * delta);
	}

}
