package de.myreality.plox.powerups;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

import de.myreality.plox.GameContext;
import de.myreality.plox.GameObject;
import de.myreality.plox.GameObjectFactory;
import de.myreality.plox.Player;
import de.myreality.plox.PowerUp;
import de.myreality.plox.PowerUpStrategy;
import de.myreality.plox.Resources;
import de.myreality.plox.ai.OrbitStrategy;
import de.myreality.plox.ui.PopupManager;

public class ProtectorPowerUp implements PowerUpStrategy {

	private int protectorDamage;
	
	private GameObjectFactory factory;

	public ProtectorPowerUp(int protectorDamage) {
		this.protectorDamage = protectorDamage;
		factory = new GameObjectFactory();
	}
	
	@Override
	public void onCollect(PowerUp powerup, GameContext context) {
		
		Player player = context.getPlayer();
		
		int size = Gdx.graphics.getWidth() / 50;
		final int PADDING = 30;
		
		float pointer = player.getHeight() / 2f + PADDING - size / 2;
		float angle = (float) (Math.random() * 360f);
		
		float x = (float) (player.getCenterX() + Math.cos(Math.toRadians(angle)) * pointer);
		float y = (float) (player.getCenterY() + Math.sin(Math.toRadians(angle)) * pointer);
		
		GameObject shot = factory.createShot(x, y, size, protectorDamage, new OrbitStrategy(player, angle, pointer));
		context.add(shot);
		
		PopupManager popupManager = context.getPopupManager();		
		LabelStyle style = new LabelStyle();
		style.font = Resources.get(Resources.BITMAP_FONT_REGULAR, BitmapFont.class);
		style.fontColor = new Color(0.5f, 1f, 0f, 1f);
		popupManager.popup(powerup.getCenterX(), powerup.getCenterY(), "Protector", style);
	}

	@Override
	public void onUse(PowerUp powerup, GameContext context) {
		
	}

	@Override
	public String getTextureID() {
		return Resources.POWERUP_PROTECTOR;
	}

	@Override
	public boolean isUseable() {
		return false;
	}

	@Override
	public float[] getColors() {
		// TODO Auto-generated method stub
		return null;
	}

}
