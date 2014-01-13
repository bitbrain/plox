package de.myreality.plox;

import aurelienribon.tweenengine.Tween;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import de.myreality.plox.google.GoogleInterface;
import de.myreality.plox.screens.MenuScreen;
import de.myreality.plox.tweens.GameObjectTween;
import de.myreality.plox.tweens.LabelTween;
import de.myreality.plox.tweens.SpriteTween;

public class PloxGame extends Game {
	
	private GoogleInterface google;
	
	public PloxGame(GoogleInterface google) {
		this.google = google;
		google.login();
	}
	
	public GoogleInterface getGoogle() {
		return google;
	}

	@Override
	public void create() {
		Resources.load();
		
		Tween.registerAccessor(Sprite.class, new SpriteTween());
		Tween.registerAccessor(Label.class, new LabelTween());
		Tween.registerAccessor(GameObject.class, new GameObjectTween());
		
		setScreen(new MenuScreen(this));
	}

	@Override
	public void dispose() {
		super.dispose();
		Resources.dispose();
	}

	@Override
	public void resume() {
		super.resume();
		Resources.load();
	}
	
	
	
}
