package de.myreality.plox.powerups;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;
import de.myreality.plox.GameContext;
import de.myreality.plox.GameObject;
import de.myreality.plox.Player;
import de.myreality.plox.PowerUp;
import de.myreality.plox.PowerUpStrategy;
import de.myreality.plox.Resources;
import de.myreality.plox.tweens.GameObjectTween;
import de.myreality.plox.ui.PopupManager;

public class IndestructablePowerUp implements PowerUpStrategy {

	private TweenManager tweenManager;
	
	private float seconds;

	public IndestructablePowerUp(TweenManager tweenManager, float seconds) {
		this.tweenManager = tweenManager;
		this.seconds = seconds;
	}

	@Override
	public void onCollect(PowerUp powerup, GameContext context) {
		Player player = context.getPlayer();
		player.setIndestructable(seconds);
		
		PopupManager popupManager = context.getPopupManager();	
		animate(player, popupManager);
			
		LabelStyle style = new LabelStyle();
		style.font = Resources.get(Resources.BITMAP_FONT_REGULAR, BitmapFont.class);
		style.fontColor = new Color(0.6f, 0.0f, 1f, 1f);
		popupManager.popup(powerup.getCenterX(), powerup.getCenterY(), "Immortality", style);
	}

	private void animate(final GameObject o, final PopupManager manager) {
		o.getColor().a = 0.7f;
		Tween.to(o, GameObjectTween.ALPHA, 0.7f).target(0.4f)
				.ease(TweenEquations.easeInOutQuad)
				.setCallback(new TweenCallback() {

					@Override
					public void onEvent(int type, BaseTween<?> source) {
						if (o.isIndestructable()) {
							animate(o, manager);
						} else {
							LabelStyle style = new LabelStyle();
							style.font = Resources.get(Resources.BITMAP_FONT_REGULAR, BitmapFont.class);
							style.fontColor = new Color(0.6f, 0.0f, 1f, 1f);
							manager.popup(o.getCenterX(), o.getCenterY(), "Immortality (expired)", style);
						}
					}

				}).setCallbackTriggers(TweenCallback.COMPLETE).repeatYoyo(1, 0)
				.start(tweenManager);

	}

	@Override
	public void onUse(PowerUp powerup, GameContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getTextureID() {
		return Resources.POWERUP_IMMORTAL;
	}

	@Override
	public boolean isUseable() {
		return false;
	}

	@Override
	public float[] getColors() {
		return new float[]{
				0f, 0f, 0.5f,
				0.2f, 0f, 0.7f,
				0.6f, 0f, 1.0f
		};
	}

}
