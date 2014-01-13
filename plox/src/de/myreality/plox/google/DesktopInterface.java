package de.myreality.plox.google;

public class DesktopInterface implements GoogleInterface {

    @Override
    public void login() {
            System.out.println("Desktop: would of logged in here");
    }

    @Override
    public void logout() {
            System.out.println("Desktop: would of logged out here");
    }

    @Override
    public boolean getSignedIn() {
            System.out.println("Desktop: getSignIn()");
            return false;
    }

    public void submitScore(int score) {
            System.out.println("Desktop: submitScore: " + score);
    }
    
    @Override
    public void getScoresData() {
            System.out.println("Desktop: getScoresData()");
    }

    @Override
    public void submitAchievement(String id) {
            System.out.println("Archieved: " + id);
    }

    @Override
    public boolean isConnected() {                
            return false;
    }

    @Override
    public void showAchievements() {
            System.out.println("Show achievements");
    }

    @Override
    public void showScores() {
            System.out.println("Desktop: getScores()");
    }

    @Override
    public void incrementAchievement(String id, int steps) {
            System.out.println("Increment achievement with id: " + id + " by " + steps);
    }
}