package de.myreality.plox.ai;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;

import de.myreality.plox.GameObject;
import de.myreality.plox.GameObjectStrategy;
import de.myreality.plox.Resources;

public class TargetStrategy implements GameObjectStrategy {
	
	private GameObject target;
	
	private float time;
	
	private float speed;
	
	public TargetStrategy(GameObject target, float speed) {
		this.target = target;
		this.speed = speed;
	}

	@Override
	public void update(float delta, GameObject source) {
		
		Vector2 vec = new Vector2(target.getCenterX() - source.getCenterX(), target.getCenterY() - source.getCenterY());
		
		if (vec.len() < 10) {
			target.damage(50);
			source.kill();
			Sound sound = Resources.SOUND_EXPLODE;
			sound.play(1f, (float)(0.3f + Math.random() * 0.6), (float)(1.0f + Math.random() * 0.4));
		}
		
		vec.nor();
		
		time += delta * 4;
		float bounceFactor = (float) Math.cos(time) * 2;
		
		source.setX(source.getX() + vec.x * speed * delta);
		source.setY(source.getY() + vec.y * speed * delta - bounceFactor);		
	}

}
