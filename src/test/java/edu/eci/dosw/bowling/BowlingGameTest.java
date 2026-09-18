package edu.eci.dosw.bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BowlingGameTest {

    @Test
    @DisplayName("roll(0) en el primer tiro no lanza excepcion y registra 0 pinos")
    void rollZeroPins_doesNotThrowAndRegistersZero() {
        // Arrange
        BowlingGame game = new BowlingGame();

        // Act
        assertDoesNotThrow(() -> game.roll(0));

        // Assert
        List<Frame> frames = game.getFrames();
        assertEquals(1, frames.size());
        assertEquals(List.of(0), frames.get(0).getRolls());
    }

    @Test
    @DisplayName("roll(-1) lanza IllegalArgumentException")
    void rollNegativePins_throwsException() {
        // Arrange
        BowlingGame game = new BowlingGame();

        // Act & Assert
        assertThrows(
                IllegalArgumentException.class,
                () -> game.roll(-1)
        );
    }

    @Test
    @DisplayName("roll(11) lanza IllegalArgumentException")
    void rollAbove10Pins_throwsException() {
        // Arrange
        BowlingGame game = new BowlingGame();

        // Act & Assert
        assertThrows(
                IllegalArgumentException.class,
                () -> game.roll(11)
        );
    }

    @Test
    @DisplayName("Dos tiros en un frame que suman mas de 10 lanza IllegalArgumentException")
    void rollsExceedingTenInFrame_throwsException() {
        // Arrange
        BowlingGame game = new BowlingGame();
        game.roll(7);

        // Act & Assert
        assertThrows(
                IllegalArgumentException.class,
                () -> game.roll(6)
        );
    }

    @Test
    @DisplayName("roll() lanza IllegalStateException cuando el juego ya esta completo")
    void rollAfterGameComplete_throwsException() {
        // Arrange
        BowlingGame game = new BowlingGame();
        for (int i = 0; i < 10; i++) {
            game.roll(0);
            game.roll(0);
        }

        // Act & Assert
        assertThrows(
                IllegalStateException.class,
                () -> game.roll(0)
        );
    }

    @Test
    @DisplayName("roll(10) marca el frame como STRIKE y avanza al siguiente frame")
    void rollTenPins_marksFrameAsStrikeAndAdvances() {
        // Arrange
        BowlingGame game = new BowlingGame();

        // Act
        game.roll(10);
        game.roll(3);

        // Assert
        List<Frame> frames = game.getFrames();
        assertEquals(2, frames.size());
        assertEquals(FrameType.STRIKE, frames.get(0).getType());
        assertEquals(List.of(10), frames.get(0).getRolls());
        assertEquals(List.of(3), frames.get(1).getRolls());
    }

    @Test
    @DisplayName("roll(5) + roll(5) marca el frame como SPARE")
    void rollsSummingTen_marksFrameAsSpare() {
        // Arrange
        BowlingGame game = new BowlingGame();

        // Act
        game.roll(5);
        game.roll(5);

        // Assert
        List<Frame> frames = game.getFrames();
        assertEquals(1, frames.size());
        assertEquals(FrameType.SPARE, frames.get(0).getType());
    }

    @Test
    @DisplayName("Frame 10 con strike acepta hasta 3 tiros sin lanzar excepcion")
    void tenthFrameWithStrike_acceptsThreeRolls() {
        // Arrange
        BowlingGame game = new BowlingGame();
        for (int i = 0; i < 9; i++) {
            game.roll(0);
            game.roll(0);
        }

        // Act & Assert
        assertDoesNotThrow(() -> {
            game.roll(10);
            game.roll(10);
            game.roll(10);
        });
    }
}