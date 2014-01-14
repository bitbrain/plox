package de.myreality.plox.powerups;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

import de.myreality.plox.GameContext;
import de.myreality.plox.PowerUp;
import de.myreality.plox.PowerUpStrategy;
import de.myreality.plox.Resources;
import de.myreality.plox.ui.PopupManager;

public class ShootSpeedPowerUp implements PowerUpStrategy {
	
	private int speedBoni;

	public ShootSpeedPowerUp(int speedBoni) {
		this.speedBoni = speedBoni;
	}

	@Override
	public void onCollect(PowerUp powerup, GameContext context) {
		context.getPlayer().setShootSpeed(context.getPlayer().getShootSpeed() + speedBoni);
		
		PopupManager popupManager = context.getPopupManager();
		
		LabelStyle style = new LabelStyle();
		style.font = Resources.get(Resources.BITMAP_FONT_REGULAR, BitmapFont.class);
		style.fontColor = new Color(0.5f, 0.2f, 0.6f, 1f);
		popupManager.popup(powerup.getCenterX(), powerup.getCenterY(), "+" + speedBoni + " Shoot Speed", style);
	}

	@Override
	public void onUse(PowerUp powerup, GameContext context) {
		
	}

	@Override
	public String getTextureID() {
		return Resources.POWERUP_SPEED;
	}

	@Override
	public boolean isUseable() {
		return false;
	}

}
