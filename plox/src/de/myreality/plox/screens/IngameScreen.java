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
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

import de.myreality.plox.GameObject;
import de.myreality.plox.GameObjectFactory;
import de.myreality.plox.GameObjectListener;
import de.myreality.plox.GameObjectType;
import de.myreality.plox.Planet;
import de.myreality.plox.PlayerScore;
import de.myreality.plox.PloxGame;
import de.myreality.plox.Resources;
import de.myreality.plox.Scoreable;
import de.myreality.plox.ai.EnemyController;
import de.myreality.plox.google.GoogleInterface;
import de.myreality.plox.graphics.ParticleRenderer;
import de.myreality.plox.input.GameControls;
import de.myreality.plox.tweens.GameObjectTween;
import de.myreality.plox.tweens.SpriteTween;
import de.myreality.plox.ui.PopupManager;
import de.myreality.plox.ui.ScoreLabel;

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
	
	private Scoreable playerScore;

	private Sprite gameOver;

	private boolean over;
	
	private PopupManager popupManager;

	private boolean fadeActivated;

	private int fadeCount = 0;

	public IngameScreen(PloxGame game) {
		this.game = game;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.141176471f, 0.188235294f, 0.278431373f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT | (Gdx.graphics.getBufferFormat().coverageSampling?GL20.GL_COVERAGE_BUFFER_BIT_NV:0));

		
		if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
			gameover();
		}

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
				Tween.to(o, GameObjectTween.ALPHA, 2f).target(0.3f)
						.setCallback(new TweenCallback() {
							@Override
							public void onEvent(int type, BaseTween<?> source) {
								fadeCount++;
							}
						}).ease(TweenEquations.easeInOutQuad)
						.start(tweenManager);
			}
			((ScoreLabel)pointLabel).reset();
			pointLabel.setFontScale(4);
			pointLabel.setPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 3);

			Tween.to(planet, GameObjectTween.ALPHA, 2f).target(0.3f)
					.setCallback(new TweenCallback() {
						@Override
						public void onEvent(int type, BaseTween<?> source) {
							fadeCount++;
						}
					}).ease(TweenEquations.easeOutQuad).start(tweenManager);

			Tween.to(gameOver, SpriteTween.ALPHA, 0.6f).target(1.0f)
					.ease(TweenEquations.easeInOutQuad).start(tweenManager);
			Tween.to(gameOver, SpriteTween.BOUNCE, 0.6f)
					.target(Gdx.graphics.getHeight() / 3f
							- gameOver.getHeight() / 2f)
					.ease(TweenEquations.easeOutBounce).start(tweenManager);
		}

		tweenManager.update(delta);
		controls.act(delta);
		
		if (!over) {
			controller.update(delta);
		} else {
			pointLabel.setColor(Color.WHITE);
			pointLabel.setPosition(Gdx.graphics.getWidth() / 2 - pointLabel.getPrefWidth() / 2f, Gdx.graphics.getHeight() / 3);
		}

		camera.update();

		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		background.setBounds(0, 0, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());
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

			if (!ScreenUtils.isOutOfScreen(o)) {
				o.draw(batch);
			} else {
				remove(o);
				
				for (GameObjectListener l : o.getListeners()) {
					l.onRemove(o);
				}
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
		controls.draw();

		if (player.isDead() || planet.isDead()) {
			gameover();
		}

		if (fadeCount >= objects.size() && (Gdx.input.isTouched() || Gdx.input.isKeyPressed(Keys.BACK))) {
			GoogleInterface google = game.getGoogle();
			google.submitScore(playerScore.getScore());
			game.setScreen(new MenuScreen(game));
		}
	}

	@Override
	public void resize(int width, int height) {
		if (controls != null) {
			controls.setViewport(width, height);
		} else {
			controls = new GameControls(width, height, this);
			
			LabelStyle labelStyle = new LabelStyle();
			labelStyle.font = Resources.get(Resources.BITMAP_FONT_REGULAR, BitmapFont.class);
			labelStyle.fontColor = new Color(1f, 1f, 1f, 1f);
			pointLabel = new ScoreLabel(playerScore, tweenManager, labelStyle);
			pointLabel.setPosition(80, Gdx.graphics.getHeight() - pointLabel.getHeight() - 80);
			pointLabel.setFontScale(2f);
			controls.addActor(pointLabel);
			
			Gdx.input.setInputProcessor(controls);
			

			LabelStyle style = new LabelStyle();
			style.font = Resources.get(Resources.BITMAP_FONT_REGULAR, BitmapFont.class);
			style.fontColor = Color.valueOf("ffffff");
			popupManager = new PopupManager(controls, tweenManager, style);
		}

		camera.setToOrtho(true, width, height);

	}
	
	public boolean isOver() {
		return over;
	}

	@Override
	public void show() {

		tweenManager = new TweenManager();
		objects = new CopyOnWriteArrayList<GameObject>();
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		controller = new EnemyController(this, tweenManager);
		Resources.getManager().finishLoading();
		background = new Sprite(Resources.get(Resources.BACKGROUND_INGAME, Texture.class));
		objectFactory = new GameObjectFactory();
		collisionHandler = new CollisionHandler();
		particleRenderer = new ParticleRenderer();
		playerScore = new PlayerScore();
		float centerX = Gdx.graphics.getWidth() / 2f;
		float centerY = Gdx.graphics.getHeight() / 2f;
		gameOver = new Sprite(Resources.get(Resources.GAMEOVER, Texture.class));
		gameOver.flip(false, true);
		float scaleFactor = Gdx.graphics.getWidth() / 800f;
		gameOver.setBounds(0, 0, gameOver.getWidth() * scaleFactor,
				gameOver.getHeight() * scaleFactor);
		gameOver.setPosition(Gdx.graphics.getWidth() / 2 - gameOver.getWidth()
				/ 2, 0);
		gameOver.setColor(gameOver.getColor().r, gameOver.getColor().g,
				gameOver.getColor().b, 0f);

		player = objectFactory.createPlayer(0, 0);
		player.setX(centerX - player.getWidth() / 2f);
		player.setY(player.getHeight() / 2f);
		objects.add(player);

		planet = objectFactory.createPlanet(Math.round(centerX),
				Math.round(centerY), tweenManager);
		
	}

	public GameObject getPlayer() {
		return player;
	}
	
	public Scoreable getPlayerScore() {
		return playerScore;
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
		controls.dispose();
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

			if (a.getType().equals(GameObjectType.SHOT)
					&& !b.getType().equals(GameObjectType.SHOT)) {

				if (!b.getType().equals(GameObjectType.PLAYER)) {
					
					b.damage(40);
					
					Sound sound = Resources.get(Resources.SOUND_IMPACT, Sound.class);
					sound.play(0.4f, (float)(1.0f + Math.random() * 0.4), (float)(1.0f + Math.random() * 0.4));
					
					if (b.isDead()) {		
						sound = Resources.get(Resources.SOUND_EXPLODE, Sound.class);
						sound.play(1f, (float)(0.3f + Math.random() * 0.6), (float)(1.0f + Math.random() * 0.4));
						playerScore.addScore(50);
						popupManager.popup(b.getCenterX(), b.getCenterY(), "50");
					} else {
						playerScore.addScore(10);
						popupManager.popup(b.getCenterX(), b.getCenterY(), "10");
					}
					remove(a);
					for (GameObjectListener l : a.getListeners()) {
						l.onRemove(a);
					}
				}
			}

			if (a.getType().equals(GameObjectType.ALIEN)
					&& b.getType().equals(GameObjectType.PLAYER)) {
				remove(a);
				Sound sound = Resources.get(Resources.SOUND_EXPLODE, Sound.class);
				sound.play(1f, (float)(0.3f + Math.random() * 0.6), (float)(1.0f + Math.random() * 0.4));
				for (GameObjectListener l : a.getListeners()) {
					l.onRemove(a);
				}
				b.damage(50);
				
				tweenManager.killTarget(b);
				float padding = 5;
				Tween.to(b, GameObjectTween.SHAKE_X, 0.02f)
		        .target(b.getX() + padding)
		        .ease(TweenEquations.easeInOutQuad)
		        .repeatYoyo(20, 0).start(tweenManager);
				
				Tween.to(b, GameObjectTween.SHAKE_Y, 0.01f)
		        .target(b.getY() + padding)
		        .ease(TweenEquations.easeInOutQuad)
		        .repeatYoyo(10, 0).start(tweenManager);
			}
		}
	}

}
