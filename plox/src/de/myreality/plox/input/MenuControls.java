package de.myreality.plox.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

import de.myreality.plox.PloxGame;
import de.myreality.plox.screens.IngameScreen;

public class MenuControls extends Stage implements InputProcessor {
	
	private PloxGame game;

	/**
	 * @param width
	 * @param height
	 * @param keepAspectRatio
	 * @param batch
	 */
	public MenuControls(float width, float height, boolean keepAspectRatio,
			SpriteBatch batch, PloxGame game) {
		super(width, height, keepAspectRatio, batch);
		Gdx.input.setCatchBackKey(true);
		this.game = game;
	}

	/**
	 * @param width
	 * @param height
	 * @param keepAspectRatio
	 */
	public MenuControls(float width, float height, boolean keepAspectRatio, PloxGame game) {
		super(width, height, keepAspectRatio);
		Gdx.input.setCatchBackKey(true);
		this.game = game;
	}

	/**
	 * @param width
	 * @param height
	 */
	public MenuControls(float width, float height, PloxGame game) {
		super(width, height);
		Gdx.input.setCatchBackKey(true);
		this.game = game;
	}

	@Override
	public boolean keyDown(int keycode) {
		super.keyDown(keycode);
		if (keycode == Keys.BACK || keycode == Keys.ESCAPE) {
			Gdx.app.exit();
			return true;
		}
		return false;
	}
	
	@Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            if (!super.touchDown(screenX, screenY, pointer, button)) {
                    game.setScreen(new IngameScreen(game));
            }
            
            return true;
    }

}
