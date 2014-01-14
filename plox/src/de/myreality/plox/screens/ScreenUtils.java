package de.myreality.plox.screens;

import com.badlogic.gdx.Gdx;

import de.myreality.plox.GameObject;

public final class ScreenUtils {
	
	public static boolean isOutOfScreen(GameObject o) {
		return !(o.getX() + o.getWidth() > 0
		&& o.getX() < Gdx.graphics.getWidth()
		&& o.getY() + o.getHeight() > 0
		&& o.getY() < Gdx.graphics.getHeight());
	}
	
	public static void alignToScreen(GameObject o) {
		// TODO
	}
}
