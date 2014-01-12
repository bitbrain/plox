package de.myreality.plox;

import com.badlogic.gdx.graphics.Texture;

public final class Resources {

	public static Texture PLANET_HEAL;
	public static Texture PLANET_BROKEN;
	public static Texture PLANET_DESTROYED;
	public static Texture ALIEN;
	public static Texture PLAYER;
	
	public static void load() {
		
		dispose();
		
		PLANET_HEAL = new Texture("data/planet-heal.png");
		PLANET_BROKEN = new Texture("data/planet-broken.png");
		PLANET_DESTROYED = new Texture("data/planet-destroyed.png");
		ALIEN = new Texture("data/alien.png");
		PLAYER = new Texture("data/player.png");
	}
	
	public static void dispose() {
		
		if (PLANET_HEAL != null) {
			PLANET_HEAL.dispose();
		}
		
		if (PLANET_BROKEN != null) {
			PLANET_BROKEN.dispose();
		}
		
		if (ALIEN != null) {
			//ALIEN.dispose();
		}
		
		if (PLANET_DESTROYED != null) {
			PLANET_DESTROYED.dispose();
		}
		
		
	}
}
