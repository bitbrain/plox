package de.myreality.plox.google;

public interface AchievementListener {
    
    void onAchieve(String achievementID, int indexX, int indexY);
    
    void onIncrementalAchieve(String achievementID, int indexX, int index);
}