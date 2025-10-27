package org.example.GameObserver;

import org.example.UtilityClasses.Pair;

public class ConsoleGameObserver implements GameObserver {
    @Override
    public void onMoveMade(Pair newHeadPosition) {
        System.out.println("Snake moved to position: [" +
                newHeadPosition.getRow() + ", " +
                newHeadPosition.getCol() + "]");
    }

    @Override
    public void onFoodEaten(int foodIndex, int newScore) {
        System.out.println("Food eaten! Current score: " + newScore);
    }

    @Override
    public void onGameOver(int finalScore) {
        System.out.println("Game Over! Final score: " + finalScore);
    }

}
