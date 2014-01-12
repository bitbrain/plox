package de.myreality.plox.ai;

import com.badlogic.gdx.Gdx;

import de.myreality.plox.GameObject;
import de.myreality.plox.GameObjectFactory;
import de.myreality.plox.screens.IngameScreen;
import de.myreality.plox.util.Timer;

public class EnemyController {
	
	private Timer timer;
	
	private static final int INTERVAL = 2000;
	
	private IngameScreen screen;
	
	public EnemyController(IngameScreen screen) {
		timer = new Timer();
		timer.start();
		this.screen = screen;
	}

	public void update(float delta) {
		if (timer.getTicks() > INTERVAL) {
			GameObjectFactory f = screen.getFactory();
			GameObject alien = f.createAlien(0, 0, screen.getPlayer(), screen.getPlanet());	
			int x = 0;
			int y = 0;
			
			int dir = (int) (Math.random() * 3);
			
			switch (dir) {
				case 0: // LEFT EDGE
					y = (int) (Math.random() * Gdx.graphics.getHeight());
					break;
				case 1:
					x = Gdx.graphics.getWidth() - alien.getWidth();
					y = (int) (Math.random() * Gdx.graphics.getHeight());
					break;
				case 2: // BOTTOM EDGE
					x = (int) (Math.random() * Gdx.graphics.getWidth());
					y = Gdx.graphics.getHeight() - alien.getHeight();
					break;
			}
			
			alien.setX(x);
			alien.setY(y);
			screen.add(alien);
			timer.reset();
		}
	}
}
