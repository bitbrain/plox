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

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Handles particle systems
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public class ParticleManager {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	
	private Map<ParticleEffect, Boolean> effects;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public ParticleManager() {
		effects = new ConcurrentHashMap<ParticleEffect, Boolean>();
	}

	// ===========================================================
	// Getters and Setters
	// ===========================================================

	// ===========================================================
	// Methods from Superclass
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
	
	public ParticleEffect create(ParticleEffect original, boolean endless) {
		ParticleEffect copy = new ParticleEffect(original);		
		effects.put(copy, endless);
		return copy;
	}
	
	public void setColor(ParticleEffect effect, float[] colors, float[] timeline) {
		for (ParticleEmitter emitter : effect.getEmitters()) {
			emitter.getTint().setTimeline(timeline);
			emitter.getTint().setColors(colors);
		}
	}
	
	public void render(SpriteBatch batch, float delta) {
		
		for (Entry<ParticleEffect, Boolean> entries : effects.entrySet()) {
			
			if (!entries.getValue() && entries.getKey().isComplete()) {
				ParticleEffect effect = entries.getKey();
				effects.remove(effect);
			} else {				
				entries.getKey().draw(batch, delta);
			}
		}
	}
	
	public void unload(ParticleEffect effect) {
		effects.remove(effect);
	}
	
	public void setEndless(ParticleEffect effect, boolean endless) {
		
		if (effect != null) {
			effects.put(effect, endless);
			
			for (ParticleEmitter emitter : effect.getEmitters()) {
				emitter.setContinuous(endless);
			}
		}
	}
	
	public void clear() {
		for (Entry<ParticleEffect, Boolean> entries : effects.entrySet()) {
			entries.getKey().setDuration(0);
		}
		
		effects.clear();
	}

	// ===========================================================
	// Inner classes
	// ===========================================================
}
