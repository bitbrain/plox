package de.myreality.plox;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;

public final class Resources {

	private static AssetManager manager;
	
	public static final String PATH = "data/";

	public static final String PLANET_HEAL = PATH + "planet-heal.png";
	public static final String PLANET_BROKEN = PATH + "planet-broken.png";
	public static final String PLANET_DESTROYED = PATH + "planet-destroyed.png";
	public static final String ALIEN = PATH + "alien.png";
	public static final String ALIEN2 = PATH + "alien2.png";
	public static final String PLAYER = PATH + "player.png";
	public static final String SHOT = PATH + "shot.png";
	public static final String LOGO = PATH + "logo.png";
	public static final String BUTTON_RANK = PATH + "button-rank.png";
	public static final String BUTTON_ACHIEVEMENTS = PATH + "button-achievements.png";
	public static final String BACKGROUND = PATH + "background.png";
	public static final String BACKGROUND_INGAME = PATH + "background-ingame.png";
	public static final String GAMEOVER = PATH + "gameover.png";
	public static final String POWERUP_HEAL = PATH + "powerup-heal.png";
	public static final String POWERUP_DAMAGE = PATH + "powerup-damage.png";
	public static final String POWERUP_SIZE = PATH + "powerup-size.png";
	public static final String POWERUP_SPEED = PATH + "powerup-speed.png";
	public static final String POWERUP_PROTECTOR = PATH + "powerup-protector.png";
	
	public static final String PARTICLES_BLUE = PATH + "particle-blue";
	public static final String PARTICLES_SHOT = PATH + "particle-shot";
	public static final String PARTICLES_EXPLOSION = PATH + "explosion";
	public static final String PARTICLES_EXPLOSION_SMALL = PATH + "explosion-small";
	public static final String PARTICLES_POWERUP = PATH + "powerup";
	
	public static final String BITMAP_FONT_REGULAR = PATH + "medium.fnt";

	public static final String MUSIC_THEME = PATH + "plox.mp3";

	public static final String SOUND_SHOT = PATH + "shot.mp3";
	public static final String SOUND_IMPACT = PATH + "impact.mp3";
	public static final String SOUND_EXPLODE = PATH + "explode.mp3";
	public static final String SOUND_POWERUP = PATH + "powerup.mp3";
	
	public static <T> T get(String id, Class<T> clazz) {
		manager.finishLoading();
		return manager.get(id, clazz);
	}

	public static void load() {
		
		manager = new AssetManager();
		manager.setLoader(ParticleEffect.class, new ParticleLoader(new InternalFileHandleResolver()));

		manager.load(PLANET_HEAL, Texture.class);
		manager.load(PLANET_BROKEN, Texture.class);
		manager.load(PLANET_DESTROYED, Texture.class);
		manager.load(ALIEN, Texture.class);
		manager.load(ALIEN2, Texture.class);
		manager.load(PLAYER, Texture.class);
		manager.load(SHOT, Texture.class);
		manager.load(LOGO, Texture.class);
		manager.load(BUTTON_RANK, Texture.class);
		manager.load(BUTTON_ACHIEVEMENTS, Texture.class);
		manager.load(BACKGROUND, Texture.class);
		manager.load(BACKGROUND_INGAME, Texture.class);
		manager.load(GAMEOVER, Texture.class);
		manager.load(POWERUP_HEAL, Texture.class);
		manager.load(POWERUP_DAMAGE, Texture.class);
		manager.load(POWERUP_SIZE, Texture.class);
		manager.load(POWERUP_SPEED, Texture.class);
		manager.load(POWERUP_PROTECTOR, Texture.class);
		
		manager.load(PARTICLES_BLUE, ParticleEffect.class);
		manager.load(PARTICLES_SHOT, ParticleEffect.class);
		manager.load(PARTICLES_EXPLOSION, ParticleEffect.class);
		manager.load(PARTICLES_EXPLOSION_SMALL, ParticleEffect.class);
		manager.load(PARTICLES_POWERUP, ParticleEffect.class);
		
		manager.load(MUSIC_THEME, Music.class);
		
		manager.load(BITMAP_FONT_REGULAR, BitmapFont.class);

		manager.load(SOUND_SHOT, Sound.class);
		manager.load(SOUND_IMPACT, Sound.class);
		manager.load(SOUND_EXPLODE, Sound.class);
		manager.load(SOUND_POWERUP, Sound.class);
	}

	public static void dispose() {
		manager.dispose();
	}
}
