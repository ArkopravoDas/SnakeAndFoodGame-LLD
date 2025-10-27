package org.example.Controller;

import org.example.Food.FoodItem;
import org.example.GameObserver.GameObserver;
import org.example.Movement.HumanPlayerMovementStrategy;
import org.example.Movement.MovementStrategy;
import org.example.UtilityClasses.GameBoard;
import org.example.UtilityClasses.Pair;
import org.example.UtilityClasses.Snake;

import java.util.*;

public class SnakeGame {
    private GameBoard board;
    public Deque<Pair> snake;
    private Map<Pair, Boolean> snakeMap;
    private int[][] food;
    private int foodIndex;
    private MovementStrategy movementStrategy;
    private List<GameObserver> observers;

    public SnakeGame(int width, int height, int[][] food) {
        this.board = GameBoard.getInstance(width, height);
        this.food = food;
        this.foodIndex = 0;

        this.snake = new LinkedList<>();
        this.snakeMap = new HashMap<>();
        Pair initialPos = new Pair(0, 0);
        this.snake.offerFirst(initialPos);
        this.snakeMap.put(initialPos, true);

        this.movementStrategy = new HumanPlayerMovementStrategy();
    }

    // Human or AI
    public void setMovementStrategy(MovementStrategy strategy) {
        this.movementStrategy = strategy;
    }

    // Add observer for game events
    public void addObserver(GameObserver observer) {
        observers.add(observer);
    }

    // Notify observers of move made
    private void notifyMoveMade(Pair newHead) {
        for (GameObserver observer : observers) {
            observer.onMoveMade(newHead);
        }
    }
    // Notify observers of food eaten
    private void notifyFoodEaten(int foodIndex, int newScore) {
        for (GameObserver observer : observers) {
            observer.onFoodEaten(foodIndex, newScore);
        }
    }
    // Notify observers of game over
    private void notifyGameOver(int finalScore) {
        for (GameObserver observer : observers) {
            observer.onGameOver(finalScore);
        }
    }

    // Returns new score or -1 if game is over
    public int move(String direction) {
        // Get current head
        Pair currentHead = this.snake.peekFirst();

        // Get next position using strategy pattern
        Pair newHead = this.movementStrategy.getNextPosition(currentHead, direction);
        int newHeadRow = newHead.getRow();
        int newHeadColumn = newHead.getCol();

        // Check boundary conditions
        boolean crossesBoundary = newHeadRow < 0 || newHeadRow >= this.board.getHeight() || newHeadColumn < 0 || newHeadColumn >= this.board.getWidth();

        // Get current tail for checking collision
        Pair currentTail = this.snake.peekLast();

        // Check if snake bites itself
        boolean bitesItself = this.snakeMap.containsKey(newHead) && !(newHead.getRow() == currentTail.getRow() && newHead.getCol() == currentTail.getCol());

        // Game over conditions
        if(crossesBoundary || bitesItself) {
            // Notify observers
            notifyGameOver(this.snake.size()-1);
            return -1;
        }

        // Check if snake eats food
        boolean ateFood = (this.foodIndex < this.food.length) && (this.food[this.foodIndex][0] == newHeadRow) && (this.food[this.foodIndex][1] == newHeadColumn);

        if(ateFood) {
            // Increment food index to move to next food
            this.foodIndex++;
            // Notify observers
            int newScore = this.snake.size();
            notifyFoodEaten(this.foodIndex - 1, newScore);
        } else {
            // If no food eaten, remove tail
            this.snake.pollLast();
            this.snakeMap.remove(currentTail);
        }

        // Add new head
        this.snake.addFirst(newHead);
        this.snakeMap.put(newHead, true);

        // Notify observers
        notifyMoveMade(newHead);

        // Calculate ans return score
        int score = this.snake.size() - 1;
        return score;
    }

    public Deque<Pair> getSnake() {
        return snake;
    }
}
