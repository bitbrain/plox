package de.myreality.plox;

import com.badlogic.gdx.Game;

import de.myreality.plox.screens.IngameScreen;

public class PloxGame extends Game {

	@Override
	public void create() {
		setScreen(new IngameScreen());
	}
	
}
