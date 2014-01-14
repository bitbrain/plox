package de.myreality.plox;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.myreality.plox.tweens.GameObjectTween;

public class Planet extends GameObject {
	
	public static final int LIFE_TOTAL = 200;

	private PlanetState state;
	
	private TweenManager manager;
	
	private Texture broken;

	private Texture destroyed;

	private Texture heal;
	
	/**
	 * @param y
	 * @param x
	 * @param maxLife
	 * @param width
	 * @param height
	 * @param texture
	 */
	public Planet(float x, float y, int size, Texture texture, TweenManager manager) {
		super(x, y, 300, size, size, texture, GameObjectType.PLANET);
		this.state = PlanetState.HEAL;
		this.manager = manager;
		this.broken = Resources.get(Resources.PLANET_BROKEN, Texture.class);
		this.destroyed = Resources.get(Resources.PLANET_DESTROYED, Texture.class);
		this.heal = Resources.get(Resources.PLANET_HEAL, Texture.class);
	}

	public PlanetState getState() {
		return state;
	}
	
	@Override
	public void damage(int damage, GameObject cause) {
		
		int oldLife = getCurrentLife();
		
		super.damage(damage, cause);
		
		if (getCurrentLife() <= 150 && oldLife > 150) {
			state = PlanetState.BROKEN;
		} else if (getCurrentLife() < 70) {
			state = PlanetState.DESTROYED;
		}
		
		manager.killTarget(this);
		float padding = 10;
		Tween.to(this, GameObjectTween.SHAKE_X, 0.1f)
        .target(getX() + padding)
        .ease(TweenEquations.easeInOutQuad)
        .repeatYoyo(20, 0).start(manager);
		
		Tween.to(this, GameObjectTween.SHAKE_Y, 0.05f)
        .target(getY() + padding)
        .ease(TweenEquations.easeInOutQuad)
        .repeatYoyo(10, 0).start(manager);
	}
	
	public void draw(SpriteBatch batch) {
		
		switch (state) {
			case BROKEN:
				setTexture(broken);
				break;
			case DESTROYED:
				setTexture(destroyed);
				break;
			case HEAL:
				setTexture(heal);
				break;
			default:
				break;		
		}
		
		super.draw(batch);
	}
	
	
	
	public static enum PlanetState {
		
		HEAL, BROKEN, DESTROYED;
	}
}
