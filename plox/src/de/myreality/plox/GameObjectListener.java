package de.myreality.plox;

public interface GameObjectListener {

	void onRemove(GameObject object);
	
	void onKill(GameObject object);
	
	void onMove(GameObject object);
	
	void onAdd(GameObject object);
	
	void onDamage(GameObject object, GameObject cause);
}
