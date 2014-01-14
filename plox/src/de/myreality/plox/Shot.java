package de.myreality.plox;

import com.badlogic.gdx.graphics.Texture;

public class Shot extends GameObject {
	
	private int damage;

	public Shot(float x, float y, int width, int height, int damage) {
		super(x, y, 1, width, height, Resources.get(Resources.SHOT, Texture.class), GameObjectType.SHOT);
		setDamage(damage);
	}
	
	public int getDamage() {
		return damage;
	}
	
	public void setDamage(int damage) {
		this.damage = damage;
	}

}
