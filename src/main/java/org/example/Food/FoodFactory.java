package org.example.Food;

import org.example.Food.ConcreteFoodItems.BonusFood;
import org.example.Food.ConcreteFoodItems.NormalFood;

public class FoodFactory {
    public static FoodItem createFood(int[] position, String type) {
        if("bonus".equals(type)){
            return new BonusFood(position[0], position[1]);
        }
        return new NormalFood(position[0], position[1]);
    }
}
