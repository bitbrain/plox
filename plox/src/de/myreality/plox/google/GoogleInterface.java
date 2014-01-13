package de.myreality.plox.google;

public interface GoogleInterface {

    public void login();

    public void logout();

    // get if client is signed in to Google+
    public boolean getSignedIn();

    // submit a score to a leaderboard
    public void submitScore(int score);
    
    // submit a new archivement
    public void submitAchievement(String id);
    
    public void incrementAchievement(String id, int steps);

    // gets the scores and displays them threw googles default widget
    public void showScores();

    // gets the score and gives access to the raw score data
    public void getScoresData();
    
    boolean isConnected();
    
    void showAchievements();
}