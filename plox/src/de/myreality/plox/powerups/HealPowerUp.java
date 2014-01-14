package de.myreality.plox.powerups;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

import de.myreality.plox.GameContext;
import de.myreality.plox.PowerUp;
import de.myreality.plox.PowerUpStrategy;
import de.myreality.plox.Resources;
import de.myreality.plox.ui.PopupManager;

public class HealPowerUp implements PowerUpStrategy {

	private int healBoni;

	public HealPowerUp(int healBoni) {
		this.healBoni = healBoni;
	}
	
	@Override
	public void onCollect(PowerUp powerup, GameContext context) {
		context.getPlayer().heal(healBoni);
		
		PopupManager popupManager = context.getPopupManager();
		
		LabelStyle style = new LabelStyle();
		style.font = Resources.get(Resources.BITMAP_FONT_REGULAR, BitmapFont.class);
		style.fontColor = new Color(0.5f, 1f, 0f, 1f);
		popupManager.popup(powerup.getCenterX(), powerup.getCenterY(), "+" + healBoni + " Heal", style);
	}

	@Override
	public void onUse(PowerUp powerup, GameContext context) {
		
	}

	@Override
	public String getTextureID() {
		return Resources.POWERUP_HEAL;
	}

	@Override
	public boolean isUseable() {
		return false;
	}

}
