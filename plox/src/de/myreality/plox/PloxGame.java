package de.myreality.plox;

import com.badlogic.gdx.Game;

import de.myreality.plox.screens.IngameScreen;

public class PloxGame extends Game {

	@Override
	public void create() {
		Resources.load();
		setScreen(new IngameScreen());
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
