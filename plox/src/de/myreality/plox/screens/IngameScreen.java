package de.myreality.plox.screens;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.myreality.plox.GameObject;
import de.myreality.plox.GameObjectFactory;
import de.myreality.plox.GameObjectType;
import de.myreality.plox.Planet;
import de.myreality.plox.ai.EnemyController;
import de.myreality.plox.input.GameControls;

public class IngameScreen implements Screen {
	
	private Planet planet;
	
	private GameControls controls;
	
	private OrthographicCamera camera;
	
	private SpriteBatch batch;
	
	private List<GameObject> objects;
	
	private GameObjectFactory objectFactory;
	
	private GameObject player;
	
	private Sprite stars;
	
	private EnemyController controller;
	
	private CollisionHandler collisionHandler;

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.141176471f, 0.188235294f, 0.278431373f, 1f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		controller.update(delta);
		camera.update();
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		
		stars.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		stars.draw(batch);
		
		planet.draw(batch);
		
		for (GameObject o : objects) {
			o.update(delta);
			
			for (GameObject other : objects) {
				if (o.collidesWidth(other)) {
					collisionHandler.collide(o, other);
				}
			}			
			
			if (o.getX() + o.getWidth() > 0 && o.getX() < Gdx.graphics.getWidth() 
			 && o.getY() + o.getHeight() > 0 && o.getY() < Gdx.graphics.getHeight()) {			
				o.draw(batch);
			} else {
				remove(o);
			}
			
			if (o.getCurrentLife() < 1) {
				remove(o);
			}
		}
		batch.end();
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
		stars = new Sprite(generateStars(width, height));
	}

	@Override
	public void show() {
		objects = new CopyOnWriteArrayList<GameObject>();
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		controller = new EnemyController(this);
		objectFactory = new GameObjectFactory();		
		collisionHandler = new CollisionHandler();
		
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
	
	public Planet getPlanet() {
		return planet;
	}
	
	public GameObjectFactory getFactory() {
		return objectFactory;
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
	
	public void add(GameObject object) {
		objects.add(object);
	}
	
	public void remove(GameObject object) {
		objects.remove(object);
	}
	
	
	private Texture generateStars(int width, int height) {
		
		Pixmap map = new Pixmap(width, height, Format.RGBA8888);
		map.setColor(Color.WHITE);
		int starCount = 100;
		
		for (int i = 0; i < starCount; ++i) {
		
			int x = (int) Math.round((Math.random() * width));
			int y = (int) Math.round((Math.random() * height));
			int size = (int) (Math.random() * Gdx.graphics.getWidth() / 200);
			map.fillRectangle(x, y, size, size);			
		}		
		
		Texture texture = new Texture(map);
		map.dispose();
		return texture;
		
	}
	
	
	private class CollisionHandler {
		
		public void collide(GameObject a, GameObject b) {
			
			if (a.getType().equals(GameObjectType.SHOT) && !a.getType().equals(GameObjectType.SHOT)) {
				b.damage(20);
				remove(a);
			}
		}
	}

}
