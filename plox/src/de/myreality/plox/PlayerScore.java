package de.myreality.plox;

public class PlayerScore implements Scoreable {
	

	private int score;
	
	@Override
	public int getScore() {
		return score;
	}

	@Override
	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public void addScore(int score) {
		this.score += score;
	}

}
