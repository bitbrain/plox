package de.myreality.plox.ui;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import de.myreality.plox.Scoreable;
import de.myreality.plox.tweens.LabelTween;

public class ScoreLabel extends Label {

	private TweenManager tweenManager;

	private Scoreable scoreable;

	private int currentPoints;

	private boolean fadingAllowed;

	public ScoreLabel(Scoreable scoreable, LabelStyle style) {
		this(scoreable, null, style);
	}

	public ScoreLabel(Scoreable scoreable, TweenManager tweenManager,
			LabelStyle style) {
		super("", style);
		this.scoreable = scoreable;
		this.tweenManager = tweenManager;
	}

	public void reset() {
		currentPoints = 0;
		tweenManager = null;
		getColor().a = 1f;
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {

		if (currentPoints < scoreable.getScore()) {
			if (fadingAllowed && tweenManager != null) {
				tweenManager.killTarget(this);
				Color c = getColor();
				setColor(c.r, c.g, c.b, 1f);
				Tween.to(this, LabelTween.ALPHA, 1).target(0.5f)
						.ease(TweenEquations.easeInOutQuad).start(tweenManager);
			}

			currentPoints += ((scoreable.getScore() - currentPoints) / 5) + 1;

			if (currentPoints > scoreable.getScore()) {
				currentPoints = scoreable.getScore();
			}

			fadingAllowed = false;

		} else {
			fadingAllowed = true;
		}

		setText("" + currentPoints);
		super.draw(batch, parentAlpha);
	}
}
