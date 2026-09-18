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
        return isStrike() || rolls.size() >= 2;
    }

    public FrameType getType() {
        if (isStrike()) {
            return FrameType.STRIKE;
        }
        if (isSpare()) {
            return FrameType.SPARE;
        }
        return FrameType.NORMAL;
    }

    private boolean isStrike() {
        return !rolls.isEmpty() && rolls.get(0) == 10;
    }

    private boolean isSpare() {
        return rolls.size() == 2 && getPinsSum() == 10;
    }
}