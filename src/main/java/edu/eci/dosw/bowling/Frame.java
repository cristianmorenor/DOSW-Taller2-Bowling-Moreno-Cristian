package edu.eci.dosw.bowling;

import java.util.ArrayList;
import java.util.List;

public class Frame {

    private final List<Integer> rolls = new ArrayList<>();

    public void addRoll(int pins) {
        rolls.add(pins);
    }

    public List<Integer> getRolls() {
        return List.copyOf(rolls);
    }

    public int getPinsSum() {
        return rolls.stream().mapToInt(Integer::intValue).sum();
    }

    public boolean isComplete() {
        return rolls.size() >= 2;
    }
}