package de.myreality.plox.screens;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.myreality.plox.GameObject;
import de.myreality.plox.GameObjectFactory;
import de.myreality.plox.Planet;
import de.myreality.plox.input.GameControls;

public class IngameScreen implements Screen {
	
	private Planet planet;
	
	private GameControls controls;
	
	private OrthographicCamera camera;
	
	private SpriteBatch batch;
	
	private List<GameObject> objects;
	
	private GameObjectFactory objectFactory;
	
	private GameObject player;

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.141176471f, 0.188235294f, 0.278431373f, 1f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		
		planet.draw(batch);
		
		for (GameObject o : objects) {
			o.update(Gdx.graphics.getDeltaTime());
			
			if (o.getX() + o.getWidth() > 0 && o.getX() < Gdx.graphics.getWidth() 
			 && o.getY() + o.getHeight() > 0 && o.getY() < Gdx.graphics.getHeight()) {			
				o.draw(batch);
			}
		}
		batch.end();
		
		planet.damage(1);
	}

	@Override
	public void resize(int width, int height) {
		if (controls != null) {
			controls.setViewport(width, height);
		} else {
			controls = new GameControls(width, height, this);

			Gdx.input.setInputProcessor(controls);
		}
		
		camera.setToOrtho(true, width, height);
	}

	@Override
	public void show() {
		objects = new ArrayList<GameObject>();
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		objectFactory = new GameObjectFactory();
		
		float centerX = Gdx.graphics.getWidth() / 2f;
		float centerY = Gdx.graphics.getHeight() / 2f;
		
		player = objectFactory.createPlayer(0, 0);
		player.setX(centerX - player.getWidth() / 2f);
		player.setY(centerY - player.getHeight() / 2f);
		objects.add(player);
		
		planet = objectFactory.createPlanet(Math.round(centerX), Math.round(centerY));		
	}
	
	public GameObject getPlayer() {
		return player;
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
