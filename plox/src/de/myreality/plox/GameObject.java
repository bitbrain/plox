package de.myreality.plox;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameObject {

	private float y;
	private float x;
	private int currentLife;
	private int maxLife;
	private int width;
	private int height;
	private Texture texture;
	private List<GameObjectStrategy> strategies;
	private float rotation;
	private GameObjectType type;
	private List<GameObjectListener> listeners;
	private Color color;
	private List<PowerUpStrategy> powerUps;
	private float millis, maxMillis;
	
	private float iced;
	
	private boolean indestructable;

	/**
	 * @param y
	 * @param x
	 * @param currentLife
	 * @param maxLife
	 * @param width
	 * @param height
	 * @param texture
	 */
	public GameObject(float x, float y, int maxLife,
			int width, int height, Texture texture, GameObjectType type) {
		super();
		this.x = x;
		this.y = y;
		this.currentLife = maxLife;
		this.maxLife = maxLife;
		this.width = width;
		this.height = height;
		this.texture = texture;
		this.type = type;
		powerUps = new ArrayList<PowerUpStrategy>();
		this.listeners = new ArrayList<GameObjectListener>();
		color = new Color(1f, 1f, 1f, 1f);
		strategies = new ArrayList<GameObjectStrategy>();
	}
	
	public GameObjectType getType() {
		return type;
	}

	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public List<PowerUpStrategy> getPowerUps() {
		return powerUps;
	}
	
	public void addPowerUp(PowerUpStrategy strategy) {
		powerUps.add(strategy);
	}
	
	public int getCurrentLife() {
		return currentLife;
	}
	
	public int getMaxLife() {
		return maxLife;
	}
	
	public float getRotation() {
		return rotation;
	}
	
	public void setRotation(float rotation) {
		this.rotation = rotation;
	}
	
	public void damage(int damage, GameObject cause) {
		
		if (!isIndestructable()) {
		
			currentLife -= damage;
			
			if (currentLife < 0) {
				currentLife = 0;
			}
			
			for (GameObjectListener l : listeners) {
				l.onDamage(this, cause);
			}
			
			if (damage == 0) {
				for (GameObjectListener l : listeners) {
					l.onKill(this);
				}
			}
		}
	}
	
	public float getCenterX() {
		return getX() + getWidth() / 2f;
	}
	
	public float getCenterY() {
		return getY() + getHeight() / 2f;
	}
	
	public void setIndestructable(float millis) {
		if (millis > 0) {
			this.indestructable = true;
			this.millis = 0;
			this.maxMillis = millis;
		}
	}
	
	public boolean isIndestructable() {
		return indestructable;
	}
	
	public Color getColor() {
		return color;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public void heal(int heal) {
		currentLife += heal;
		
		if (currentLife > maxLife) {
			currentLife = maxLife;
		}
	}
	
	public void setTexture(Texture texture) {
		this.texture = texture;
	}
	
	public float getIndestructablePercentage() {
		return 1f - millis / maxMillis;
	}
	
	public void update(float delta) {
		
		millis += delta;
		iced -= delta;
		
		if (iced < 0) {
			iced = 0;
		} 
		
		if (millis > maxMillis) {
			indestructable = false;
			millis = 0;
		}
		
		for (GameObjectStrategy strategy : strategies) {
			strategy.update(delta, this);
		}
	}
	
	public void draw(SpriteBatch batch) {
		
		if (!isIced()) {
			batch.setColor(color.r, color.g, color.b, color.a);
		} else {
			batch.setColor(new Color(0.5f, 0.7f, 1f, 1f));
		}
		batch.draw(texture, x, y, getWidth() / 2f, getHeight() / 2f, getWidth(), getHeight(), 1f, 1f, getRotation(), 0, 0, texture.getWidth(), texture.getHeight(), false, true);
		
	}
	
	public void setIced(float iceTime) {
		iced = iceTime;
	}
	
	public boolean isIced() {
		return iced > 0f;
	}
	
	public int getWidth() {
		return width;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public void addStrategy(GameObjectStrategy strategy) {
		strategies.add(strategy);
	}
	
	public void kill() {
		currentLife = 0;
		for (GameObjectListener l : listeners) {
			l.onKill(this);
		}
	}
	
	public void setX(float x) {
		this.x = x;
		for (GameObjectListener l : getListeners()) {
			l.onMove(this);
		}
	}
	
	public void setY(float y) {
		this.y = y;
		for (GameObjectListener l : getListeners()) {
			l.onMove(this);
		}
	}
	
	public int getHeight() {
		return height;
	}
	
	public boolean isDead() {
		return currentLife < 1;
	}
	
	public void setMaxLife(int maxLife) {
		this.maxLife = maxLife;
		if (this.currentLife != maxLife) {
			this.currentLife = maxLife;
		}
	}
	
	public List<GameObjectListener> getListeners() {
		return listeners;
	}
	
	public boolean collidesWidth(GameObject other) {
		 float right = getX() + getWidth();
         float bottom = getY() + getHeight();
         
         float otherRight = other.getX() + other.getWidth();
         float otherBottom = other.getY() + other.getHeight();
         
         boolean collisionX = otherRight >= getX() && other.getX() <= right;
         boolean collisionY = otherBottom >= getY() && other.getY() <= bottom;
         
         return collisionX && collisionY;
	}
	
	public void addListener(GameObjectListener listener) {
		listeners.add(listener);
	}
	
	
	public boolean contains(int x, int y) {
		return x >= getX() && x <= getX() + getWidth() &&
			   y >= getY() && y <= getY() + getHeight();
	}
}
