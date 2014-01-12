package de.myreality.plox.input;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class GameControls extends Stage implements InputProcessor {

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		boolean superTouch = super.touchDown(screenX, screenY, pointer, button);
		
		return superTouch; 
	}
}
