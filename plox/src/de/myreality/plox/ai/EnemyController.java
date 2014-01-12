package de.myreality.plox.ai;

import de.myreality.plox.GameObject;
import de.myreality.plox.GameObjectFactory;
import de.myreality.plox.screens.IngameScreen;
import de.myreality.plox.util.Timer;

public class EnemyController {
	
	private Timer timer;
	
	private static final int INTERVAL = 5000;
	
	private IngameScreen screen;
	
	public EnemyController(IngameScreen screen) {
		timer = new Timer();
		timer.start();
		this.screen = screen;
	}

	public void update(float delta) {
		if (timer.getTicks() > INTERVAL) {
			GameObjectFactory f = screen.getFactory();
			
			int x = 0;
			int y = 0;
			
			GameObject alien = f.createAlien(x, y, screen.getPlayer(), screen.getPlanet());	
			screen.add(alien);
			timer.reset();
		}
	}
}
