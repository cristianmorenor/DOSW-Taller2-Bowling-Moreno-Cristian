package edu.eci.dosw.bowling;

import java.util.ArrayList;
import java.util.List;

/**
 * Motor de un juego de Bowling para un jugador.
 * Un juego tiene exactamente 10 frames.
 */
public class BowlingGame {

    private final List<Frame> frames;
    private int currentFrame;

    public BowlingGame() {
        this.frames = new ArrayList<>();
        this.currentFrame = 0;
    }

    /** Registra pinos derribados. Lanza IllegalArgumentException si pines < 0 o > 10.
     *  Lanza IllegalStateException si el juego ya termino. */
    public void roll(int pins) {
        validatePinCount(pins);
        validateGameNotComplete();
        Frame frame = getCurrentFrame();
        validateFrameTotal(frame, pins);
        frame.addRoll(pins);
    }

    private void validateGameNotComplete() {
        if (isComplete()) {
            throw new IllegalStateException("El juego ya estaa completo");
        }
    }

    private Frame getCurrentFrame() {
        if (frames.isEmpty() || frames.get(frames.size() - 1).isComplete()) {
            Frame newFrame = new Frame();
            frames.add(newFrame);
            return newFrame;
        }
        return frames.get(frames.size() - 1);
    }

    private void validateFrameTotal(Frame frame, int pins) {
        if (frame.getPinsSum() + pins > 10) {
            throw new IllegalArgumentException("La suma de pines en el frame no puede superar 10");
        }
    }

    private void validatePinCount(int pins) {
        if (pins < 0 || pins > 10) {
            throw new IllegalArgumentException("El numero de pinos debe estar entre 0 y 10: " + pins);
        }
    }

    /** Puntaje total. Lanza IllegalStateException si el juego no esta completo. */
    public int score() {
        // TODO: implementar con TDD
        return 0;
    }

    /** true cuando los 10 frames han sido completados. */
    public boolean isComplete() {
        return frames.size() >= 10 && frames.get(frames.size() - 1).isComplete();
    }

    public List<Frame> getFrames() { return List.copyOf(frames); }
}