package de.myreality.plox;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;

public final class Resources {

	public static Texture PLANET_HEAL;
	public static Texture PLANET_BROKEN;
	public static Texture PLANET_DESTROYED;
	public static Texture ALIEN;
	public static Texture PLAYER;
	public static Texture SHOT;
	public static Texture LOGO;
	public static Texture BUTTON;
	public static Texture BACKGROUND;
	public static Texture BACKGROUND_INGAME;
	
	public static ParticleEffect PARTICLES_BLUE;	
	public static ParticleEffect PARTICLES_SHOT;	
	public static ParticleEffect PARTICLES_EXPLOSION;	
	
	public static void load() {
		
		PLANET_HEAL = new Texture("data/planet-heal.png");
		PLANET_BROKEN = new Texture("data/planet-broken.png");
		PLANET_DESTROYED = new Texture("data/planet-destroyed.png");
		ALIEN = new Texture("data/alien.png");
		PLAYER = new Texture("data/player.png");
		SHOT = new Texture("data/shot.png");
		LOGO = new Texture("data/logo.png");
		BUTTON = new Texture("data/button.png");
		BACKGROUND = new Texture("data/background.png");
		BACKGROUND_INGAME = new Texture("data/background-ingame.png");
		
		PARTICLES_BLUE = new ParticleEffect();

		
		PARTICLES_BLUE.load(Gdx.files.internal("data/particle-blue"), 
                Gdx.files.internal("data"));
		
		PARTICLES_SHOT = new ParticleEffect();
		
		PARTICLES_SHOT.load(Gdx.files.internal("data/particle-shot"), 
                Gdx.files.internal("data"));
		
		PARTICLES_EXPLOSION = new ParticleEffect();
		
		PARTICLES_EXPLOSION.load(Gdx.files.internal("data/explosion"), 
                Gdx.files.internal("data"));
	}
	
	public static void dispose() {
		
		if (PLANET_HEAL != null) {
			PLANET_HEAL.dispose();
		}
		
		if (PLANET_BROKEN != null) {
			PLANET_BROKEN.dispose();
		}
		
		if (ALIEN != null) {
			ALIEN.dispose();
		}
		
		if (PLANET_DESTROYED != null) {
			PLANET_DESTROYED.dispose();
		}
		
		if (SHOT != null) {
			SHOT.dispose();
		}
		
		if (LOGO != null) {
			LOGO.dispose();
		}
		
		if (BUTTON != null) {
			BUTTON.dispose();
		}
		
		if (BACKGROUND != null) {
			BACKGROUND.dispose();
		}
		
		if (BACKGROUND_INGAME != null) {
			BACKGROUND_INGAME.dispose();
		}
		
		if (PARTICLES_BLUE != null) {
			PARTICLES_BLUE.dispose();
		}
		
		if (PARTICLES_SHOT != null) {
			PARTICLES_SHOT.dispose();
		}
		
		if (PARTICLES_EXPLOSION != null) {
			PARTICLES_EXPLOSION.dispose();
		}
		
	}
}
