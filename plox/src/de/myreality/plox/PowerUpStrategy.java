package de.myreality.plox;


public interface PowerUpStrategy {

	void onCollect(PowerUp powerup, GameContext context);
	
	void onUse(PowerUp powerup, GameContext context);
	
	String getTextureID();
	
	boolean isUseable();
	
	float[] getColors();
}
