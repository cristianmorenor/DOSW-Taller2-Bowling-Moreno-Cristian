package edu.eci.dosw.bowling;

import java.util.ArrayList;
import java.util.List;

public class Frame {

    private final List<Integer> rolls = new ArrayList<>();
    private final boolean tenthFrame;

    public Frame() {
        this(false);
    }

    public Frame(boolean tenthFrame) {
        this.tenthFrame = tenthFrame;
    }

    public void addRoll(int pins) {
        rolls.add(pins);
    }

    public List<Integer> getRolls() {
        return List.copyOf(rolls);
    }

    public int getPinsSum() {
        return rolls.stream().mapToInt(Integer::intValue).sum();
    }

    public boolean isTenthFrame() {
        return tenthFrame;
    }

    public boolean isComplete() {
        if (tenthFrame) {
            return isTenthFrameComplete();
        }
        return isStrike() || rolls.size() >= 2;
    }

    public FrameType getType() {
        if (tenthFrame) {
            return FrameType.TENTH;
        }
        if (isStrike()) {
            return FrameType.STRIKE;
        }
        if (isSpare()) {
            return FrameType.SPARE;
        }
        return FrameType.NORMAL;
    }

    private boolean isTenthFrameComplete() {
        if (rolls.size() < 2) {
            return false;
        }
        if (rolls.size() >= 3) {
            return true;
        }
        boolean earnedBonus = isStrike() || getPinsSum() >= 10;
        return !earnedBonus;
    }

    private boolean isStrike() {
        return !rolls.isEmpty() && rolls.get(0) == 10;
    }

    private boolean isSpare() {
        return rolls.size() == 2 && getPinsSum() == 10;
    }
}