package edu.eci.dosw.bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
}