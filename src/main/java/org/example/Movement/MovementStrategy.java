package org.example.Movement;

import org.example.UtilityClasses.Pair;

public interface MovementStrategy {
    Pair getNextPosition(Pair currentHead, String direction);
}
