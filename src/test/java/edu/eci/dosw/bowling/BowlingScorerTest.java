package edu.eci.dosw.bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BowlingScorerTest {

    @Test
    @DisplayName("Juego con todos los tiros a 0 retorna score 0")
    void allZeroGame_scoresZero() {
        // Arrange
        BowlingGame game = new BowlingGame();
        for (int i = 0; i < 10; i++) {
            game.roll(0);
            game.roll(0);
        }

        // Act & Assert
        assertEquals(0, game.score());
    }
}