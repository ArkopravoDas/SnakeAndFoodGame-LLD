package org.example.Food.ConcreteFoodItems;

import org.example.Food.FoodItem;

public class BonusFood extends FoodItem {
    public BonusFood(int row, int col) {
        super(row, col);
        this.points = 3;
    }
}
