package org.example.Food.ConcreteFoodItems;

import org.example.Food.FoodItem;

public class PoisonFood extends FoodItem {
    public PoisonFood(int row, int col) {
        super(row, col);
        this.points = -1;
    }
}
