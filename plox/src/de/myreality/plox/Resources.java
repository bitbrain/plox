package de.myreality.plox;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;

public final class Resources {

	public static Texture PLANET_HEAL;
	public static Texture PLANET_BROKEN;
	public static Texture PLANET_DESTROYED;
	public static Texture ALIEN;
	public static Texture ALIEN2;
	public static Texture PLAYER;
	public static Texture SHOT;
	public static Texture LOGO;
	public static Texture BUTTON_RANK;
	public static Texture BUTTON_ACHIEVEMENTS;
	public static Texture BACKGROUND;
	public static Texture BACKGROUND_INGAME;
	public static Texture GAMEOVER;
	
	public static ParticleEffect PARTICLES_BLUE;	
	public static ParticleEffect PARTICLES_SHOT;	
	public static ParticleEffect PARTICLES_EXPLOSION;
	public static ParticleEffect PARTICLES_EXPLOSION_SMALL;
	
	public static BitmapFont BITMAP_FONT_REGULAR;
	
	public static Music MUSIC_THEME;
	
	public static Sound SOUND_SHOT;	
	public static Sound SOUND_IMPACT;
	public static Sound SOUND_EXPLODE;
	
	public static void load() {
		
		PLANET_HEAL = new Texture("data/planet-heal.png");
		PLANET_BROKEN = new Texture("data/planet-broken.png");
		PLANET_DESTROYED = new Texture("data/planet-destroyed.png");
		ALIEN = new Texture("data/alien.png");
		ALIEN2 = new Texture("data/alien2.png");
		PLAYER = new Texture("data/player.png");
		SHOT = new Texture("data/shot.png");
		LOGO = new Texture("data/logo.png");
		BUTTON_RANK = new Texture("data/button-rank.png");
		BUTTON_ACHIEVEMENTS = new Texture("data/button-achievements.png");
		BACKGROUND = new Texture("data/background.png");
		BACKGROUND_INGAME = new Texture("data/background-ingame.png");
		GAMEOVER = new Texture("data/gameover.png");
		PARTICLES_BLUE = new ParticleEffect();

		
		PARTICLES_BLUE.load(Gdx.files.internal("data/particle-blue"), 
                Gdx.files.internal("data"));
		
		PARTICLES_SHOT = new ParticleEffect();		
		PARTICLES_SHOT.load(Gdx.files.internal("data/particle-shot"), 
                Gdx.files.internal("data"));
		
		PARTICLES_EXPLOSION = new ParticleEffect();
		
		PARTICLES_EXPLOSION.load(Gdx.files.internal("data/explosion"), 
                Gdx.files.internal("data"));
		
PARTICLES_EXPLOSION_SMALL = new ParticleEffect();		
PARTICLES_EXPLOSION_SMALL.load(Gdx.files.internal("data/explosion-small"), 
                Gdx.files.internal("data"));
		
		BITMAP_FONT_REGULAR = new BitmapFont(Gdx.files.internal("data/medium.fnt"), false);
		
		MUSIC_THEME = Gdx.audio.newMusic(Gdx.files.internal("data/plox.mp3"));
		
		SOUND_SHOT = Gdx.audio.newSound(Gdx.files.internal("data/shot.mp3"));
		SOUND_IMPACT = Gdx.audio.newSound(Gdx.files.internal("data/impact.mp3"));
		SOUND_EXPLODE = Gdx.audio.newSound(Gdx.files.internal("data/explode.mp3"));
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
		
		if (ALIEN2 != null) {
			ALIEN2.dispose();
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
		
		if (GAMEOVER != null) {
			GAMEOVER.dispose();
		}
		
		if (BUTTON_RANK != null) {
			BUTTON_RANK.dispose();
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
		
		if (PARTICLES_EXPLOSION_SMALL != null) {
			PARTICLES_EXPLOSION_SMALL.dispose();
		}
		
		if (BITMAP_FONT_REGULAR != null) {
			BITMAP_FONT_REGULAR.dispose();
		}
		
		if (MUSIC_THEME != null) {
			MUSIC_THEME.dispose();
		}
		
		if (SOUND_SHOT != null) {
			SOUND_SHOT.dispose();
		}
		
		if (SOUND_IMPACT != null) {
			SOUND_IMPACT.dispose();
		}
		
		if (SOUND_IMPACT != null) {
			SOUND_EXPLODE.dispose();
		}
		
	}
}
