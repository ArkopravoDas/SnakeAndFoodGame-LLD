package org.example.GameObserver;

import org.example.UtilityClasses.Pair;

public abstract interface GameObserver {
    void onMoveMade(Pair newHeadPosition);
    void onFoodEaten(int foodIndex, int newScore);
    void onGameOver();
    void onScoreUpdate(int newScore);
}
