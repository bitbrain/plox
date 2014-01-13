package de.myreality.plox;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import de.myreality.plox.google.DesktopInterface;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Plox v. 1.0";
		cfg.useGL20 = true;
		cfg.width = 1920;
		cfg.height = 1080;
		
		new LwjglApplication(new PloxGame(new DesktopInterface()), cfg);
	}
}
