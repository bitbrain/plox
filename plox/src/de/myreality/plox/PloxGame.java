package de.myreality.plox;

import aurelienribon.tweenengine.Tween;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.Sprite;

import de.myreality.plox.screens.MenuScreen;
import de.myreality.plox.tweens.SpriteTween;

public class PloxGame extends Game {

	@Override
	public void create() {
		Resources.load();
		
		Tween.registerAccessor(Sprite.class, new SpriteTween());
		
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
