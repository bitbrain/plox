package de.myreality.plox;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Planet extends GameObject {
	
	public static final int LIFE_TOTAL = 200;

	private PlanetState state;
	
	/**
	 * @param y
	 * @param x
	 * @param maxLife
	 * @param width
	 * @param height
	 * @param texture
	 */
	public Planet(float x, float y, int size, Texture texture) {
		super(x, y, 200, size, size, texture);
		this.state = PlanetState.HEAL;
	}

	public PlanetState getState() {
		return state;
	}
	
	@Override
	public void damage(int damage) {
		
		int oldLife = getCurrentLife();
		
		super.damage(damage);
		
		if (getCurrentLife() <= 50 && oldLife > 50) {
			state = PlanetState.BROKEN;
		} else if (getCurrentLife() < 10) {
			state = PlanetState.DESTROYED;
		}
	}
	
	public void draw(SpriteBatch batch) {
		
		switch (state) {
			case BROKEN:
				setTexture(Resources.PLANET_BROKEN);
				break;
			case DESTROYED:
				setTexture(Resources.PLANET_DESTROYED);
				break;
			case HEAL:
				setTexture(Resources.PLANET_HEAL);
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
