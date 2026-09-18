package edu.eci.dosw.bowling;

import java.util.List;

public class BowlingScorer {

    public int calculate(List<Frame> frames) {
        int total = 0;
        for (int i = 0; i < frames.size(); i++) {
            Frame frame = frames.get(i);
            total += frame.getPinsSum();
            if (frame.getType() == FrameType.SPARE) {
                total += nextRollPins(frames, i);
            }
        }
        return total;
    }

    private int nextRollPins(List<Frame> frames, int frameIndex) {
        if (frameIndex + 1 >= frames.size()) {
            return 0;
        }
        List<Integer> nextRolls = frames.get(frameIndex + 1).getRolls();
        return nextRolls.isEmpty() ? 0 : nextRolls.get(0);
    }
}