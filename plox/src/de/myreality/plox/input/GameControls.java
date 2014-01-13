package de.myreality.plox.input;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;

import de.myreality.plox.GameObject;
import de.myreality.plox.GameObjectFactory;
import de.myreality.plox.screens.IngameScreen;
import de.myreality.plox.util.Timer;

public class GameControls extends Stage implements InputProcessor {
	
	private IngameScreen screen;
	
	private Timer timer;
	
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
		 Gdx.input.setCatchBackKey(true);
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
	public boolean keyDown(int keyCode) {
		boolean value = super.keyDown(keyCode);
		
		switch (keyCode) {
			 // ABORT GAME
	        case Keys.BACK: case Keys.ESCAPE:
	                screen.gameover();
	                return true;
		}
		
		return value;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {

		super.touchDragged(screenX, screenY, pointer);	
		
		if (!screen.isOver() && pointer == 0 && !Gdx.app.getType().equals(ApplicationType.Desktop)) {
		
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
		} else if (!screen.isOver() && Gdx.app.getType().equals(ApplicationType.Desktop) || pointer == 1) {
			
			// Shooting
			final int INTERVAL = 400;
			boolean shoot = false;
			
			if (timer != null && timer.isRunning()) {
				if (timer.getTicks() > INTERVAL) {
					shoot = true;
					timer.reset();
				}
			} else {
				timer = new Timer();
				shoot = true;
				timer.start();
			}
			
				
			if (shoot) {
				GameObjectFactory f = screen.getFactory();
				GameObject p = screen.getPlayer();
				GameObject shot = f.createShot(
						(int)(p.getX() + p.getWidth() / 2f),
						(int)(p.getY() + p.getHeight() / 2f), 
						screenX, screenY, 1600f);
				screen.add(shot);
			}
		}
		
		return true;
	}
	
	
}
