package de.myreality.plox;

import com.badlogic.gdx.graphics.Texture;

import de.myreality.plox.screens.ScreenUtils;

public class PowerUp extends GameObject {
	
	private PowerUpStrategy strategy;
	
	private float veloX, veloY;
	
	private float maxSpeed = 0.1f;

	public PowerUp(float x, float y, int size, PowerUpStrategy strategy) {
		super(x, y, 10, size, size, Resources.get(strategy.getTextureID(), Texture.class), GameObjectType.POWERUP);
		this.strategy = strategy;
	}
	
	@Override
	public void update(float delta) {
		super.update(delta);
		
		if (!ScreenUtils.isOutOfScreen(this)) {
			 float factorX = 0, factorY = 0;
             
             if (Math.abs(veloX) < maxSpeed) {
                     factorX += (float) (Math.random() * 50f);
             }
             
             if (Math.abs(veloY) < maxSpeed) {
                     factorY += (float) (Math.random() * 50f);
             }
             
             factorX *= (Math.random() > 0.5f) ? 1 : -1;
             factorY *= (Math.random() > 0.5f) ? 1 : -1;
             
             veloX += factorX;
             veloY += factorY;
             
             setX((float) (getX() + veloX * delta));
             setY((float) (getY() + veloY * delta));
		} else {
			ScreenUtils.alignToScreen(this);
			veloX = 0;
			veloY = 0;
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
