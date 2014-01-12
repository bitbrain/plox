package de.myreality.plox.input;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class GameControls extends Stage implements InputProcessor {
	/**
	 * @param width
	 * @param height
	 * @param keepAspectRatio
	 * @param batch
	 */
	public GameControls(float width, float height, boolean keepAspectRatio,
			SpriteBatch batch) {
		super(width, height, keepAspectRatio, batch);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param width
	 * @param height
	 * @param keepAspectRatio
	 */
	public GameControls(float width, float height, boolean keepAspectRatio) {
		super(width, height, keepAspectRatio);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param width
	 * @param height
	 */
	public GameControls(float width, float height) {
		super(width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		boolean superTouch = super.touchDown(screenX, screenY, pointer, button);
		
		return superTouch; 
	}
}
