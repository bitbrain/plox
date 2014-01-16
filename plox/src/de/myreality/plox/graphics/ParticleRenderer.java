/* Acid - Provides a Java cell API to display fancy cell boxes.
 * Copyright (C) 2013  Miguel Gonzalez
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA
 */

package de.myreality.plox.graphics;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.myreality.plox.GameObject;
import de.myreality.plox.GameObjectListener;
import de.myreality.plox.GameObjectType;
import de.myreality.plox.PowerUp;
import de.myreality.plox.Resources;
import de.myreality.plox.screens.ScreenUtils;

/**
 * Listens to a snake to spawn particles on collisions
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public class ParticleRenderer  implements GameObjectListener{

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	
	private ParticleManager particleManager;
	
	private Map<GameObject, ParticleEffect> effects;
	
	private Map<GameObject, Integer> particleCounts;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public ParticleRenderer() {
		particleManager = new ParticleManager();
		effects = new HashMap<GameObject, ParticleEffect>();
		particleCounts = new HashMap<GameObject, Integer>();
	}

	// ===========================================================
	// Getters and Setters
	// ===========================================================

	// ===========================================================
	// Methods from Superclass
	// ===========================================================
	
	// ===========================================================
	// World methods
	// ===========================================================
	
	public void render(SpriteBatch batch, float delta) {
		particleManager.render(batch, delta);
	}
	
	public void clear() {
		particleManager.clear();
	}

	@Override
	public void onRemove(GameObject object) {
		ParticleEffect effect = effects.remove(object);
		particleCounts.remove(object);
		
		if (effect != null) {
			particleManager.setEndless(effect, false);
			effect.setDuration(0);
			
			if (object.getType().equals(GameObjectType.POWERUP)) {
				particleManager.unload(effect);
			} else if (!object.getType().equals(GameObjectType.SHOT)) {
				ParticleEffect eff = Resources.get(Resources.PARTICLES_EXPLOSION, ParticleEffect.class);
				particleManager.unload(effect);
				effect = particleManager.create(eff, false);
				particleManager.setParticleCount(effect, 50);
				effect.setPosition(object.getCenterX(), object.getCenterY());
				effect.start();
			} else if (!ScreenUtils.isOutOfScreen(object)){
				ParticleEffect eff = Resources.get(Resources.PARTICLES_EXPLOSION_SMALL, ParticleEffect.class);
				effect = particleManager.create(eff, false);
				effect.setPosition(object.getCenterX(), object.getCenterY());
				particleManager.setEndless(effect, false);
				effect.start();
			}
		}
	}

	@Override
	public void onKill(GameObject object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMove(GameObject object) {
		ParticleEffect effect = effects.get(object);
		Integer count = particleCounts.get(object);
		if (effect != null) {	
			
			if (object.isIced()) {
				if (particleManager.getParticleCount(effect) == count) {
					particleManager.setParticleCount(effect, count / 20);
				}
			} else {
				if (particleManager.getParticleCount(effect) != count) {
					particleManager.setParticleCount(effect, count);
				}
			}
			
			if (object.getType().equals(GameObjectType.ALIEN)) {				
				effect.setPosition(object.getCenterX(), object.getCenterY() + object.getHeight() / 3f);
			} else {
				effect.setPosition(object.getCenterX(), object.getCenterY());
			} 
		}
	}

	@Override
	public void onAdd(GameObject object) {
		if (object.getType().equals(GameObjectType.ALIEN)) {
			ParticleEffect eff = Resources.get(Resources.PARTICLES_BLUE, ParticleEffect.class);
			ParticleEffect effect = particleManager.create(eff, true);			
			effect.setPosition(object.getCenterX(), object.getCenterY() + object.getHeight() / 3f);
			effects.put(object, effect);
			particleCounts.put(object, particleManager.getParticleCount(effect));
		}
		
		if (object.getType().equals(GameObjectType.PLAYER)) {
			ParticleEffect eff = Resources.get(Resources.PARTICLES_SHOT, ParticleEffect.class);
			ParticleEffect effect = particleManager.create(eff, true);
			effect.setPosition(object.getCenterX(), object.getCenterY());
			effects.put(object, effect);
			particleCounts.put(object, particleManager.getParticleCount(effect));
		} else
		
		if (object.getType().equals(GameObjectType.SHOT)) {
			ParticleEffect eff = Resources.get(Resources.PARTICLES_SHOT, ParticleEffect.class);
			ParticleEffect effect = particleManager.create(eff, true);				
			effect.setPosition(object.getCenterX(), object.getCenterY());
			effects.put(object, effect);
			particleCounts.put(object, particleManager.getParticleCount(effect));
		} else
		
		if (object.getType().equals(GameObjectType.POWERUP)) {
			ParticleEffect eff = Resources.get(Resources.PARTICLES_POWERUP, ParticleEffect.class);
			ParticleEffect effect = particleManager.create(eff, true);				
			effect.setPosition(object.getCenterX(), object.getCenterY());

			particleCounts.put(object, particleManager.getParticleCount(effect));
			effects.put(object, effect);	
			
			// Change particle color
			PowerUp up = (PowerUp)object;
			float[] colors = up.getStrategy().getColors();
			float[] timeline = new float[]{0.0f, 0.4f, 0.8f};
			if (colors != null) {
				particleManager.setColor(effect, colors, timeline);
			}
		}
	}

	@Override
	public void onDamage(GameObject object, GameObject cause) {
		// TODO Auto-generated method stub
		
	}

	// ===========================================================
	// Inner classes
	// ===========================================================
}
