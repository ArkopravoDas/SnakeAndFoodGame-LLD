package org.example.UtilityClasses;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Snake {
    private Deque<Pair> body; //Snake Body as Deque of Positions
    private Map<Pair, Boolean> positionMap; // For O(1) collision check
    public Snake() {
        this.body = new LinkedList<>();
        this.positionMap = new HashMap<>();
        Pair initialPos = new Pair(0, 0);
        this.body.offerFirst(initialPos);
        this.positionMap.put(initialPos, true);
    }

}
