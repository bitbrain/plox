package de.myreality.plox;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import de.myreality.plox.screens.ScreenUtils;

public class PowerUp extends GameObject {
	
	private PowerUpStrategy strategy;
	
	private float veloX, veloY;

	public PowerUp(float x, float y, PowerUpStrategy strategy) {
		super(x, y, 10, Gdx.graphics.getWidth() / 10, Gdx.graphics.getWidth() / 10, Resources.get(strategy.getTextureID(), Texture.class), GameObjectType.POWERUP);
	}
	
	@Override
	public void update(float delta) {
		super.update(delta);
		
		if (!ScreenUtils.isOutOfScreen(this)) {
			
			
			
		} else {
			ScreenUtils.alignToScreen(this);
		}
	}



	public void onCollect(GameContext context) {
		strategy.onCollect(this, context);
	}
	
	public void onUse(GameContext context) {
		strategy.onUse(this, context);
	}
	
	public PowerUpStrategy getStrategy() {
		return strategy;
	}
	
	public boolean isUseable() {
		return strategy.isUseable();
	}
}
