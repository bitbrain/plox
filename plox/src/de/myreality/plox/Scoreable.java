package de.myreality.plox;

public interface Scoreable {

	int getScore();
	
	void setScore(int score);
	
	void addScore(int score);
	
	void addListener(ScoreableListener listener);
}
