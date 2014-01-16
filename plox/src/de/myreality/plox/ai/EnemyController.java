package de.myreality.plox.ai;

import java.util.Random;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import de.myreality.plox.GameObject;
import de.myreality.plox.GameObjectFactory;
import de.myreality.plox.GameObjectListener;
import de.myreality.plox.Resources;
import de.myreality.plox.powerups.HealPowerUp;
import de.myreality.plox.powerups.IndestructablePowerUp;
import de.myreality.plox.powerups.ProtectorPowerUp;
import de.myreality.plox.powerups.ShootDamagePowerUp;
import de.myreality.plox.powerups.ShootSizePowerUp;
import de.myreality.plox.powerups.ShootSpeedPowerUp;
import de.myreality.plox.screens.IngameScreen;
import de.myreality.plox.tweens.GameObjectTween;
import de.myreality.plox.util.Timer;

public class EnemyController {
	
	private Timer timer;
	
	private static final int INTERVAL = 2500;
	
	private static int currentInterval;
	
	private IngameScreen screen;
	
	private TweenManager tweenManager;
	
	private Random random;
	
	public EnemyController(IngameScreen screen, TweenManager tweenManager) {
		timer = new Timer();
		timer.start();
		currentInterval = INTERVAL;
		this.screen = screen;
		random = new Random(System.currentTimeMillis());
		this.tweenManager = tweenManager;
	}

	public void update(float delta) {
		
		int score = screen.getPlayerScore().getScore();
		currentInterval = INTERVAL - (score * 50 / INTERVAL);
		
		if (currentInterval < 500) {
			currentInterval = 500;
		}
		
		if (timer.getTicks() > currentInterval) {
			
			int amount = (int) (random.nextFloat() * 2f) + 1;
			
			if (random.nextFloat() < 0.3) {
				amount = (int) (Math.random() * 2 + 2);
			} else if (random.nextFloat() < 0.05) {
				amount = (int) (Math.random() * 4 + 2);
			}
			
			spawnAlien(amount);
			
			timer.reset();
		}
	}
	
	private void spawnAlien(int amount) {
			GameObjectFactory f = screen.getFactory();
			
			for (int i = 0; i < amount; ++i) {
				GameObject alien = f.createAlien(0, 0);
				alien.addListener(screen.getAchievementManager());
				
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
				modifyAlien(alien);
				screen.add(alien);
				alien.addListener(screen.getAchievementManager());
				alien.addListener(new EnemyShaker(alien));
		}
	}
	
	private void modifyAlien(GameObject alien) {

		double typeFactor = Math.random();
		int score = screen.getPlayerScore().getScore();
		if (typeFactor > 0.1) {
			alien.addStrategy(new TargetStrategy(screen.getPlanet(), (float) (50 + 50 * random.nextFloat())));
			alien.setMaxLife(score / 100 + 60);
		} else {
			alien.setTexture(Resources.get(Resources.ALIEN2, Texture.class));
			int size = Gdx.graphics.getHeight() / 8;
			alien.setWidth(size);
			alien.setHeight(size);
			alien.addStrategy(new TargetStrategy(screen.getPlayer(), (float) (400 + random.nextFloat() * (score / 100))));
			alien.setMaxLife(score / 500 + 50);
		}
		
		if (random.nextFloat() < 0.05) {
			alien.addPowerUp(new ShootSizePowerUp((int) (random.nextFloat() * 4 + 4)));
		} else if (random.nextFloat() < 0.03) {
			alien.addPowerUp(new ShootDamagePowerUp((int) (random.nextFloat() * 20 + 20)));
		} else if (random.nextFloat() < 0.05) {
			alien.addPowerUp(new ShootSpeedPowerUp((int) (random.nextFloat() * 2 + 2)));
		} else if (random.nextFloat() < 0.02) {
			alien.addPowerUp(new HealPowerUp((int) (random.nextFloat() * 50 + 50)));
		} else if (random.nextFloat() < 0.02) {
			alien.addPowerUp(new ProtectorPowerUp(alien.getMaxLife() * 2));
		} else if (random.nextFloat() < 0.01) {
			alien.addPowerUp(new IndestructablePowerUp(tweenManager, (float) (random.nextFloat() * 5 + 5)));
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
		public void onDamage(GameObject object, GameObject cause) {
			tweenManager.killTarget(target);
			float padding = 8;
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
