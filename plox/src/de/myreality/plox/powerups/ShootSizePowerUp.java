package de.myreality.plox.powerups;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

import de.myreality.plox.GameContext;
import de.myreality.plox.PowerUp;
import de.myreality.plox.PowerUpStrategy;
import de.myreality.plox.Resources;
import de.myreality.plox.ui.PopupManager;

public class ShootSizePowerUp implements PowerUpStrategy {
	
	private int sizeBoni;

	public ShootSizePowerUp(int sizeBoni) {
		this.sizeBoni = sizeBoni;
	}

	@Override
	public void onCollect(PowerUp powerup, GameContext context) {
		
		context.getPlayer().setShootSize(context.getPlayer().getShootSize() + sizeBoni);
		
		PopupManager popupManager = context.getPopupManager();
		
		LabelStyle style = new LabelStyle();
		style.font = Resources.get(Resources.BITMAP_FONT_REGULAR, BitmapFont.class);
		style.fontColor = new Color(0.1f, 0.5f, 1f, 1f);
		popupManager.popup(powerup.getCenterX(), powerup.getCenterY(), "+" + sizeBoni + " Shoot Size", style);
	}

	@Override
	public void onUse(PowerUp powerup, GameContext context) {
		
	}

	@Override
	public String getTextureID() {
		return Resources.POWERUP_SIZE;
	}

	@Override
	public boolean isUseable() {
		return false;
	}

}
