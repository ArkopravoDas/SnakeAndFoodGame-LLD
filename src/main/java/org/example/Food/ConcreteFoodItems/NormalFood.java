package org.example.Food.ConcreteFoodItems;

import org.example.Food.FoodItem;

public class NormalFood extends FoodItem {
    public NormalFood(int row, int col) {
        super(row, col);
        this.points = 1;
    }
}
