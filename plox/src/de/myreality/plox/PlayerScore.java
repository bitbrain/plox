package de.myreality.plox;

import java.util.ArrayList;
import java.util.List;

public class PlayerScore implements Scoreable {
	

	private int score;
	
	private List<ScoreableListener> listeners = new ArrayList<ScoreableListener>();
	
	@Override
	public int getScore() {
		return score;
	}

	@Override
	public void setScore(int score) {
		addScore(score - this.score);
	}

	@Override
	public void addScore(int score) {
		this.score += score;
		
		for (ScoreableListener l : listeners) {
			l.onGainPoints(this, score);
		}
	}
	
	@Override
	public void addListener(ScoreableListener listener) {
		this.listeners.add(listener);
	}

}
