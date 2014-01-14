package de.myreality.plox;

import de.myreality.plox.graphics.ParticleRenderer;
import de.myreality.plox.ui.PopupManager;


public interface GameContext {

	Player getPlayer();
	
	Planet getPlanet();
	
	Scoreable getPlayerScore();
	
	void add(GameObject object);
	
	void remove(GameObject object);
	
	ParticleRenderer getParticleRenderer();

	PopupManager getPopupManager();
}
