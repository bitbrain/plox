package de.myreality.plox;

import com.badlogic.gdx.graphics.Texture;

public class Shot extends GameObject {
	
	private int damage;
	
	private boolean protector;
	
	public Shot(float x, float y, int width, int height, int damage) {
		this(x, y, width, height, damage, false);
	}

	public Shot(float x, float y, int width, int height, int damage, boolean protector) {
		super(x, y, 1, width, height, Resources.get(Resources.SHOT, Texture.class), GameObjectType.SHOT);
		setDamage(damage);
		this.protector = protector;
	}
	
	public int getDamage() {
		return damage;
	}
	
	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	public boolean isProtector() {
		return protector;
	}

}
