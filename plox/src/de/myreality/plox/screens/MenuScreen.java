package de.myreality.plox.screens;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

import de.myreality.plox.PloxGame;
import de.myreality.plox.Resources;
import de.myreality.plox.tweens.SpriteTween;

public class MenuScreen implements Screen {
	
	private PloxGame game;

	private SpriteBatch batch;
	private Sprite background;
	private Sprite logo;
	private float logoWidth, logoHeight;
	private TweenManager tweenManager;
	private boolean lastTouched = true;
	private Stage stage;
	private boolean connected;
	
	public MenuScreen(PloxGame game) {
		this.game = game;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		showGoogleButtons();
		
		tweenManager.update(delta);
		
		batch.begin();
		background.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		background.draw(batch);
		
		logo.draw(batch);
		batch.end();

		stage.draw();
		
		if (!lastTouched && Gdx.input.isTouched()) {
			game.setScreen(new IngameScreen(game));
		}
		
		lastTouched = Gdx.input.isTouched();
	}

	@Override
	public void resize(int width, int height) {
		if (stage != null) {
			stage.setViewport(width, height);
		} else {
			stage = new Stage();
			
			LabelStyle style = new LabelStyle();
			style.font = Resources.BITMAP_FONT_REGULAR;
			style.fontColor = Color.WHITE;
			Label text = new Label("Touch to start", style);
			text.setX(width / 2 - text.getWidth() / 2);
			text.setY(height / 5);
			stage.addActor(text);
			animateLabel(text);
			showGoogleButtons();
			
		}
	}

	@Override
	public void show() {
		
		tweenManager = new TweenManager();
		background = new Sprite(Resources.BACKGROUND);
		logo = new Sprite(Resources.LOGO);
		logoWidth = logo.getWidth();
		logoHeight = logo.getHeight();
		batch = new SpriteBatch();
		
		float scaleFactor = Gdx.graphics.getWidth() / 800f;
		
		logo.setBounds(0, 0, scaleFactor * logoWidth, scaleFactor * logoHeight);
		
		logo.setX(Gdx.graphics.getWidth() / 2f - logo.getWidth() / 2f);
		logo.setY(Gdx.graphics.getHeight() - logo.getHeight() - 70);
		
		animateLogo();
	}
	
	private void animateLabel(final Label label) {
		Tween.to(label, SpriteTween.BOUNCE, 0.5f)
        .target(0f)
        .ease(TweenEquations.easeInOutQuad)
        .setCallback(new TweenCallback() {

			@Override
			public void onEvent(int type, BaseTween<?> source) {
				animateLabel(label);
			}
        	
        })
        .setCallbackTriggers(TweenCallback.COMPLETE)
        .repeatYoyo(1, 0).start(tweenManager);
	}
	
	private void showGoogleButtons() {
		if (game.getGoogle().isConnected() && !connected) {
			connected = true;
			Image imgWorldlist = new Image(Resources.BUTTON_RANK);			
			stage.addActor(imgWorldlist);
			imgWorldlist.setScale(4f);
            imgWorldlist.setPosition(80, 80);            
            Image imgAchievements = new Image(Resources.BUTTON_ACHIEVEMENTS);			
			stage.addActor(imgAchievements);
			imgAchievements.setScale(4f);
			imgAchievements.setPosition(Gdx.graphics.getWidth() - 80 - imgAchievements.getWidth() * 4, 80);
	
		}
	}
	
	private void animateLogo() {
		Tween.to(logo, SpriteTween.BOUNCE, 1)
        .target(Gdx.graphics.getHeight() - logo.getHeight() - 30)
        .ease(TweenEquations.easeInOutQuad)
        .setCallback(new TweenCallback() {

			@Override
			public void onEvent(int type, BaseTween<?> source) {
				animateLogo();
			}
        	
        })
        .setCallbackTriggers(TweenCallback.COMPLETE)
        .repeatYoyo(1, 0).start(tweenManager);
		
		
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
