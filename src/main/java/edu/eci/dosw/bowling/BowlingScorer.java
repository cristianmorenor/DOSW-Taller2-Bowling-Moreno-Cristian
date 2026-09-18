package edu.eci.dosw.bowling;

import java.util.ArrayList;
import java.util.List;

public class BowlingScorer {

    public int calculate(List<Frame> frames) {
        List<Integer> allRolls = flattenRolls(frames);
        int total = 0;
        int rollIndex = 0;

        for (Frame frame : frames) {
            FrameType type = frame.getType();
            if (type == FrameType.STRIKE) {
                total += 10 + sumNext(allRolls, rollIndex + 1, 2);
                rollIndex += 1;
            } else if (type == FrameType.SPARE) {
                total += 10 + sumNext(allRolls, rollIndex + 2, 1);
                rollIndex += 2;
            } else {
                total += frame.getPinsSum();
                rollIndex += frame.getRolls().size();
            }
        }
        return total;
    }

    private List<Integer> flattenRolls(List<Frame> frames) {
        List<Integer> rolls = new ArrayList<>();
        for (Frame frame : frames) {
            rolls.addAll(frame.getRolls());
        }
        return rolls;
    }

    private int sumNext(List<Integer> rolls, int fromIndex, int count) {
        int sum = 0;
        for (int i = fromIndex; i < fromIndex + count && i < rolls.size(); i++) {
            sum += rolls.get(i);
        }
        return sum;
    }
}