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
}