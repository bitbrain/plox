package de.myreality.plox.google;

import de.myreality.plox.GameObject;
import de.myreality.plox.GameObjectListener;
import de.myreality.plox.GameObjectType;
import de.myreality.plox.PlayerScore;
import de.myreality.plox.ScoreableListener;
import de.myreality.plox.Shot;

public class AchievementManager implements ScoreableListener, GameObjectListener {
	
	private long collectedPoints;
	
	private GoogleInterface google;
	
	private int killCount;
	
	public AchievementManager(GoogleInterface google) {
		collectedPoints = 0;
		this.google = google;
	}

	@Override
	public void onGainPoints(PlayerScore score, int points) {
		collectedPoints += points;
		
		int multiplier = (int) Math.floor(collectedPoints / 100);
		collectedPoints = collectedPoints % 100;
		
		if (multiplier > 0) {
			google.incrementAchievement(Achievements.DEFENDER, multiplier);
			google.incrementAchievement(Achievements.DESTROYER, multiplier);
			google.incrementAchievement(Achievements.VETERAN, multiplier);
			google.incrementAchievement(Achievements.LEGEND, multiplier);
		}
	}

	@Override
	public void onRemove(GameObject object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onKill(GameObject object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMove(GameObject object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAdd(GameObject object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDamage(GameObject object, GameObject cause) {
		if (cause.getType().equals(GameObjectType.SHOT)) {
			Shot shot = (Shot)cause;
			
			if (shot.isProtector() && object.isDead()) {
				google.incrementAchievement(Achievements.PROTECTOR_I, 1);
				google.incrementAchievement(Achievements.PROTECTOR_II, 1);
				google.incrementAchievement(Achievements.PROTECTOR_III, 1);
			}
		} else if (cause.getType().equals(GameObjectType.PLAYER)) {
			
			if (object.isDead() && cause.isIndestructable()) {
				killCount++;
			} else if (!cause.isIndestructable()) {
				killCount = 0;
			} 			
			
			if (killCount >= 30) {
				google.submitAchievement(Achievements.KAMIKAZE);
			}
		}
	}
	
}
