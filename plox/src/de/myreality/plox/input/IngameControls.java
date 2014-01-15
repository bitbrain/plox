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
import de.myreality.plox.Player;
import de.myreality.plox.PloxGame;
import de.myreality.plox.screens.IngameScreen;
import de.myreality.plox.screens.MenuScreen;
import de.myreality.plox.util.Timer;

public class IngameControls extends Stage implements InputProcessor {
	
	private IngameScreen screen;
	
	private Timer timer;
	
	private PloxGame game;
	
	/**
	 * @param width
	 * @param height
	 * @param keepAspectRatio
	 * @param batch
	 */
	public IngameControls(float width, float height, boolean keepAspectRatio,
			SpriteBatch batch, IngameScreen screen, PloxGame game) {
		super(width, height, keepAspectRatio, batch);
		this.screen = screen;		
		this.game = game;
		Gdx.input.setCatchBackKey(true);
	}

	/**
	 * @param width
	 * @param height
	 * @param keepAspectRatio
	 */
	public IngameControls(float width, float height, boolean keepAspectRatio, IngameScreen screen, PloxGame game) {
		super(width, height, keepAspectRatio);
		this.screen = screen;
		Gdx.input.setCatchBackKey(true);
		this.game = game;
	}

	/**
	 * @param width
	 * @param height
	 */
	public IngameControls(float width, float height, IngameScreen screen, PloxGame game) {
		super(width, height);
		this.screen = screen;
		Gdx.input.setCatchBackKey(true);
		this.game = game;
	}
	
	

	@Override
	public boolean keyDown(int keyCode) {
		boolean value = super.keyDown(keyCode);
		
		switch (keyCode) {
			 // ABORT GAME
	        case Keys.BACK: case Keys.ESCAPE:
	        		if (!screen.isOver()) {
	        			screen.gameover();
	        		} else {
	        			game.setScreen(new MenuScreen(game));
	        		}
	                return true;
		}
		
		return value;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		boolean state = super.touchDown(screenX, screenY, pointer, button);
		
		if (screen.isOver()) {
			game.setScreen(new MenuScreen(game));
			return true;
		}
		
		return state;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		boolean touched = super.touchDragged(screenX, screenY, pointer);	
		
		if (!touched && !screen.isOver() && pointer == 0 && !Gdx.app.getType().equals(ApplicationType.Desktop)) {
		
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
		} else if (!touched && !screen.isOver() && Gdx.app.getType().equals(ApplicationType.Desktop) || pointer == 1) {
			
			Player player = screen.getPlayer();
			
			boolean shoot = false;
			
			
			int currentInterval = (int) (5000 / player.getShootSpeed());
			
			if (timer != null && timer.isRunning()) {
				if (timer.getTicks() > currentInterval) {
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
						screenX, screenY, 1600, 
						player.getShootSize(),
						player.getShootDamage());
				screen.add(shot);
			}
		}
		
		return touched;
	}
	
}
