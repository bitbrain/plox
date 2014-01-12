package de.myreality.plox.ai;

import de.myreality.plox.GameObject;
import de.myreality.plox.GameObjectStrategy;

public class AlienStrategy implements GameObjectStrategy {
	
	private GameObject player;
	
	public AlienStrategy(GameObject player) {
		this.player = player;
	}

	@Override
	public void update(float delta, GameObject target) {
		
	}

}
