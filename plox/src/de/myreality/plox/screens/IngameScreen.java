package de.myreality.plox.screens;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import de.myreality.plox.GameObject;
import de.myreality.plox.GameObjectFactory;
import de.myreality.plox.GameObjectListener;
import de.myreality.plox.GameObjectType;
import de.myreality.plox.Planet;
import de.myreality.plox.PloxGame;
import de.myreality.plox.Resources;
import de.myreality.plox.ai.EnemyController;
import de.myreality.plox.graphics.ParticleRenderer;
import de.myreality.plox.input.GameControls;
import de.myreality.plox.tweens.GameObjectTween;
import de.myreality.plox.tweens.SpriteTween;

public class IngameScreen implements Screen {
	
	private Planet planet;
	
	private GameControls controls;
	
	private OrthographicCamera camera;
	
	private SpriteBatch batch;
	
	private List<GameObject> objects;
	
	private GameObjectFactory objectFactory;
	
	private GameObject player;
	
	private Sprite background;
	
	private EnemyController controller;
	
	private CollisionHandler collisionHandler;
	
	private PloxGame game;
	
	private ParticleRenderer particleRenderer;
	
	private TweenManager tweenManager;
	
	private Label pointLabel;
	
	private Sprite gameOver;
	
	private boolean over;
	
	private boolean fadeActivated;
	
	private int fadeCount = 0;
	
	public IngameScreen(PloxGame game) {
		this.game = game;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.141176471f, 0.188235294f, 0.278431373f, 1f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
			if (Gdx.app.getType().equals(ApplicationType.Desktop) && !over) {
			
			GameObject player = getPlayer();
			final int speed = (int) (580 * delta);
			
			if (Gdx.input.isKeyPressed(Keys.W)) {
				player.setY(player.getY() - speed);
			} else if (Gdx.input.isKeyPressed(Keys.S)) {
				player.setY(player.getY() + speed);
			}
			
			if (Gdx.input.isKeyPressed(Keys.A)) {
				player.setX(player.getX() - speed);
			} else if (Gdx.input.isKeyPressed(Keys.D)) {
				player.setX(player.getX() + speed);
			}
		}
			
		if (over && !fadeActivated) {
			fadeActivated = true;
			
			particleRenderer.clear();
			
			for (GameObject o : objects) {
				Tween.to(o, GameObjectTween.ALPHA, 2f)
		        .target(0.3f).setCallback(new TweenCallback() {
					@Override
					public void onEvent(int type, BaseTween<?> source) {
						fadeCount++;
					}		        	
		        })
		        .ease(TweenEquations.easeInOutQuad).start(tweenManager);
			}
			
			Tween.to(planet, GameObjectTween.ALPHA, 2f)
	        .target(0.3f).setCallback(new TweenCallback() {
				@Override
				public void onEvent(int type, BaseTween<?> source) {
					fadeCount++;
				}		        	
	        })
	        .ease(TweenEquations.easeInOutQuad).start(tweenManager);
			
			Tween.to(gameOver, SpriteTween.ALPHA, 2f)
	        .target(1.0f).ease(TweenEquations.easeInOutQuad).start(tweenManager);		
			Tween.to(gameOver, SpriteTween.BOUNCE, 2f)
	        .target(Gdx.graphics.getHeight() / 2f - gameOver.getHeight() / 2f)
	        .ease(TweenEquations.easeInOutBounce).start(tweenManager);
		}
			
		tweenManager.update(delta);
		
		if (!over) {
			controller.update(delta);
		}
		
		camera.update();
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		
		background.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		background.draw(batch);

		planet.draw(batch);
		
		for (GameObject o : objects) {
			
			if (!fadeActivated) {
				o.update(delta);
			}
			
			for (GameObject other : objects) {
				if (o.collidesWidth(other) && !fadeActivated) {
					collisionHandler.collide(o, other);
				}
			}			
			
			if (o.getX() + o.getWidth() > 0 && o.getX() < Gdx.graphics.getWidth() 
			 && o.getY() + o.getHeight() > 0 && o.getY() < Gdx.graphics.getHeight()) {			
				o.draw(batch);
			} else {
				remove(o);
			}
			
			if (o.getCurrentLife() < 1 && !fadeActivated) {
				
				for (GameObjectListener l : o.getListeners()) {
					l.onRemove(o);
				}
				
				remove(o);
			}
		}
		

		particleRenderer.render(batch, delta);
		
		if (fadeActivated) {
			gameOver.draw(batch);
		}
		
		batch.end();
		
		if (player.isDead() || planet.isDead()) {
			gameover();
		}
		
		if (fadeCount >= objects.size() && Gdx.input.isTouched()) {
			game.setScreen(new MenuScreen(game));
		}
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
		background = new Sprite(Resources.BACKGROUND_INGAME);
	}

	@Override
	public void show() {

		tweenManager = new TweenManager();
		objects = new CopyOnWriteArrayList<GameObject>();
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		controller = new EnemyController(this, tweenManager);
		objectFactory = new GameObjectFactory();		
		collisionHandler = new CollisionHandler();
		particleRenderer = new ParticleRenderer(this);
		float centerX = Gdx.graphics.getWidth() / 2f;
		float centerY = Gdx.graphics.getHeight() / 2f;
		gameOver = new Sprite(Resources.GAMEOVER);
		gameOver.flip(false, true);
		float scaleFactor = Gdx.graphics.getWidth() / 800f;
		gameOver.setBounds(0, 0, gameOver.getWidth() * scaleFactor, gameOver.getHeight() * scaleFactor);
		gameOver.setPosition(Gdx.graphics.getWidth() / 2 - gameOver.getWidth() / 2, 
				 			 0);
		gameOver.setColor(gameOver.getColor().r, gameOver.getColor().g, gameOver.getColor().b, 0f);
		
		player = objectFactory.createPlayer(0, 0);
		player.setX(centerX - player.getWidth() / 2f);
		player.setY(player.getHeight() / 2f);
		objects.add(player);
		
		planet = objectFactory.createPlanet(Math.round(centerX), Math.round(centerY), tweenManager);		
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
		object.addListener(particleRenderer);
		for (GameObjectListener l : object.getListeners()) {
			l.onAdd(object);
		}
	}
	
	public void remove(GameObject object) {
		objects.remove(object);
	}

	public void gameover() {
		over = true;
	}
		
	
	private class CollisionHandler {
		
		public void collide(GameObject a, GameObject b) {
			
			if (a.getType().equals(GameObjectType.SHOT) && !b.getType().equals(GameObjectType.SHOT)) {

				if (!b.getType().equals(GameObjectType.PLAYER)) {
					b.damage(40);
					remove(a);
					for (GameObjectListener l : a.getListeners()) {
						l.onRemove(a);
					}
				}
			}
			
			if (a.getType().equals(GameObjectType.ALIEN) && b.getType().equals(GameObjectType.PLAYER)) {
				remove(a);
				for (GameObjectListener l : a.getListeners()) {
					l.onRemove(a);
				}
				b.damage(50);
			}
		}
	}


	

}
