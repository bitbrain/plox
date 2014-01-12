package de.myreality.plox.ai;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;

import de.myreality.plox.GameObject;
import de.myreality.plox.GameObjectFactory;
import de.myreality.plox.GameObjectListener;
import de.myreality.plox.screens.IngameScreen;
import de.myreality.plox.tweens.GameObjectTween;
import de.myreality.plox.util.Timer;

public class EnemyController {
	
	private Timer timer;
	
	private static final int INTERVAL = 2000;
	
	private IngameScreen screen;
	
	private TweenManager tweenManager;
	
	public EnemyController(IngameScreen screen, TweenManager tweenManager) {
		timer = new Timer();
		timer.start();
		this.screen = screen;
		this.tweenManager = tweenManager;
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
			alien.addListener(new EnemyShaker(alien));
			timer.reset();
		}
	}
	
	private class EnemyShaker implements GameObjectListener {
		
		private GameObject target;
		
		public EnemyShaker(GameObject target) {
			this.target = target;
		}

		@Override
		public void onRemove(GameObject object) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onKill(GameObject object) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onMove(GameObject object) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onAdd(GameObject object) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onDamage(GameObject object) {
			tweenManager.killTarget(target);
			float padding = 5;
			Tween.to(target, GameObjectTween.SHAKE_X, 0.02f)
	        .target(target.getX() + padding)
	        .ease(TweenEquations.easeInOutQuad)
	        .repeatYoyo(20, 0).start(tweenManager);
			
			Tween.to(target, GameObjectTween.SHAKE_Y, 0.01f)
	        .target(target.getY() + padding)
	        .ease(TweenEquations.easeInOutQuad)
	        .repeatYoyo(10, 0).start(tweenManager);
		}
		
	}
}
