package de.myreality.plox.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;

import de.myreality.plox.GameObject;
import de.myreality.plox.screens.IngameScreen;

public class GameControls extends Stage implements InputProcessor {
	
	private IngameScreen screen;
	
	/**
	 * @param width
	 * @param height
	 * @param keepAspectRatio
	 * @param batch
	 */
	public GameControls(float width, float height, boolean keepAspectRatio,
			SpriteBatch batch, IngameScreen screen) {
		super(width, height, keepAspectRatio, batch);
		this.screen = screen;
	}

	/**
	 * @param width
	 * @param height
	 * @param keepAspectRatio
	 */
	public GameControls(float width, float height, boolean keepAspectRatio, IngameScreen screen) {
		super(width, height, keepAspectRatio);
		this.screen = screen;
	}

	/**
	 * @param width
	 * @param height
	 */
	public GameControls(float width, float height, IngameScreen screen) {
		super(width, height);
		this.screen = screen;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {

		super.touchDragged(screenX, screenY, pointer);	
		
		if (pointer == 0) {
		
			GameObject player = screen.getPlayer();
			
			screenX -= player.getWidth() / 2f;
			screenY -= player.getHeight() / 2f;
			
			if (screenX - player.getWidth() /2f < 0) {
				screenX = 0;
			}
			
			if (screenY - player.getHeight() /2f < 0) {
				screenY = 0;
			}
			
			if (screenX > Gdx.graphics.getWidth()) {
				screenX = Gdx.graphics.getWidth();
			}
			
			if (screenY > Gdx.graphics.getHeight()) {
				screenY = Gdx.graphics.getHeight();
			}
			
			Vector2 vec = new Vector2((float)screenX - player.getX(), (float)screenY - player.getY());		
			
			final float speed = vec.len() / 4f;
			vec.nor();
			
			player.setX(player.getX() + vec.x * speed);
			player.setY(player.getY() + vec.y * speed);
		}
		
		return true;
	}
	
	
}
