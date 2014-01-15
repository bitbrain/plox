package de.myreality.plox.ui;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import de.myreality.plox.GameObject;
import de.myreality.plox.Resources;
import de.myreality.plox.tweens.ActorTween;

public class LifeBar extends Actor {

	private GameObject target;

	private Sprite life, background;

	private float padding;

	private boolean fadingAllowed;

	private int currentPoints;

	private TweenManager tweenManager;

	public LifeBar(GameObject target, TweenManager tweenManager) {
		this.target = target;

		Texture lifeTexture = Resources.get(Resources.LIFE, Texture.class);

		currentPoints = target.getCurrentLife();
		this.tweenManager = tweenManager;

		Pixmap map = new Pixmap(20, 100, Format.RGBA8888);
		map.setColor(Color.BLACK);
		map.fill();

		Texture backgroundTexture = new Texture(map);
		map.dispose();
		life = new Sprite(lifeTexture);
		background = new Sprite(backgroundTexture);

		padding = 10;

		getColor().a = 0.5f;
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);

		if (target.isIndestructable() || currentPoints != target.getCurrentLife()) {
			if (target.isIndestructable() || fadingAllowed && tweenManager != null) {
				tweenManager.killTarget(this);
				Color c = getColor();
				setColor(c.r, c.g, c.b, 1f);
				Tween.to(this, ActorTween.ALPHA, 1).target(0.5f)
						.ease(TweenEquations.easeInOutQuad).start(tweenManager);
			}

			if (target.getCurrentLife() > currentPoints) {
				currentPoints += ((target.getCurrentLife() - currentPoints) / 5) + 1;

				if (currentPoints > target.getCurrentLife()) {
					currentPoints = target.getCurrentLife();
				}
			} else {
				currentPoints -= ((currentPoints - target.getCurrentLife()) / 5) + 1;

				if (currentPoints < 0) {
					currentPoints = 0;
				}
			}

			fadingAllowed = false;

		} else {
			fadingAllowed = true;
		}

		background.setColor(getColor());
		background.setBounds(getX(), getY(), getWidth(), getHeight());
		background.draw(batch);

		float percentage = (float) currentPoints / (float) target.getMaxLife();
		float lifeWidth = getWidth() * percentage - padding * 2;

		if (lifeWidth < 0) {
			lifeWidth = 0;
		}

		life.setColor(getColor());
		life.setBounds(getX() + padding, getY() + padding, lifeWidth,
				getHeight() - padding * 2);
		life.draw(batch);
		
		if (target.isIndestructable()) {
			percentage = target.getIndestructablePercentage();
			lifeWidth = getWidth() * percentage - padding * 2;
			if (lifeWidth < 0) {
				lifeWidth = 0;
			}
			life.setColor(0.6f, 0f, 1f, getColor().a);
			life.setBounds(getX() + padding, getY() + padding, lifeWidth,
					getHeight() - padding * 2);
			life.draw(batch);
		}

	}

}
