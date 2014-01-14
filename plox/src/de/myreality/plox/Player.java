package de.myreality.plox;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Player extends GameObject {
	
	private float shootSpeed;
	
	private int shootDamage;
	
	private int shootSize;

	public Player(float x, float y, int size) {
		super(x, y, 200, size, size, Resources.get(Resources.PLAYER, Texture.class), GameObjectType.PLAYER);
		this.shootSpeed = 10;
		this.shootDamage = 40;
		this.shootSize = Gdx.graphics.getWidth() / 25;
	}

	public float getShootSpeed() {
		return shootSpeed;
	}

	public void setShootSpeed(float shootSpeed) {
		this.shootSpeed = shootSpeed;
	}

	public int getShootDamage() {
		return shootDamage;
	}

	public void setShootDamage(int shootDamage) {
		this.shootDamage = shootDamage;
	}

	public int getShootSize() {
		return shootSize;
	}

	public void setShootSize(int shootSize) {
		this.shootSize = shootSize;
	}
	
	

}
