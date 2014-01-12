package de.myreality.plox;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Planet {
	
	public static final int LIFE_TOTAL = 200;

	private PlanetState state;
	
	private int currentLife, maxLife;
	
	public Planet() {
		maxLife = LIFE_TOTAL;
		currentLife = maxLife;
		this.state = PlanetState.HEAL;
	}
	
	public PlanetState getState() {
		return state;
	}
	
	public int getCurrentLife() {
		return currentLife;
	}
	
	public int getMaxLife() {
		return maxLife;
	}
	
	public void damage(int damage) {
		
		int oldLife = currentLife;
		
		currentLife -= damage;
		
		if (currentLife <= 50 && oldLife > 50) {
			state = PlanetState.BROKEN;
		} else if (currentLife < 10) {
			state = PlanetState.DESTROYED;
		}
	}
	
	public void draw(SpriteBatch batch) {
		
		Texture texture = null;
		
		switch (state) {
		case BROKEN:
			texture = Resources.PLANET_BROKEN;
			break;
		case DESTROYED:
			texture = Resources.PLANET_DESTROYED;
			break;
		case HEAL:
			texture = Resources.PLANET_HEAL;
			break;
		default:
			break;
		
		}
		
		float x = 0;
		float y = 0;
		
		if (texture != null) {
			batch.draw(texture, x, y);
		}
	}
	
	
	
	public static enum PlanetState {
		
		HEAL, BROKEN, DESTROYED;
	}
}
