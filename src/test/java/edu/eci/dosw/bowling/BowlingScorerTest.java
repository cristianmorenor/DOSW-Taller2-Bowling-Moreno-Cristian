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


    @Test
    @DisplayName("Juego sin strikes ni spares retorna la suma directa de los tiros")
    void gameWithoutBonuses_scoresSumOfRolls() {
        // Arrange
        BowlingGame game = new BowlingGame();
        for (int i = 0; i < 10; i++) {
            game.roll(3);
            game.roll(4);
        }

        // Act & Assert
        assertEquals(70, game.score());
    }

    @Test
    @DisplayName("Spare en frame 1 suma 10 mas el primer tiro del frame 2")
    void spareInFirstFrame_addsBonusFromNextRoll() {
        // Arrange
        BowlingGame game = new BowlingGame();
        game.roll(5);
        game.roll(5); // spare frame 1
        game.roll(3);
        game.roll(0); // frame 2
        for (int i = 0; i < 8; i++) {
            game.roll(0);
            game.roll(0);
        }

        // Act & Assert
        assertEquals(16, game.score());
    }


    @Test
    @DisplayName("Strike en frame 1 suma 10 mas los dos tiros siguientes")
    void strikeInFirstFrame_addsBonusFromNextTwoRolls() {
        // Arrange
        BowlingGame game = new BowlingGame();
        game.roll(10); // strike frame 1
        game.roll(4);
        game.roll(3); // frame 2
        for (int i = 0; i < 8; i++) {
            game.roll(0);
            game.roll(0);
        }

        // Act & Assert
        assertEquals(24, game.score());
    }

    @Test
    @DisplayName("Dos strikes consecutivos suman correctamente el bono del primero")
    void twoConsecutiveStrikes_addsBonusCorrectly() {
        // Arrange
        BowlingGame game = new BowlingGame();
        game.roll(10); // strike frame 1
        game.roll(10); // strike frame 2
        game.roll(5);
        game.roll(2); // frame 3
        for (int i = 0; i < 7; i++) {
            game.roll(0);
            game.roll(0);
        }

        // Act & Assert
        assertEquals(49, game.score());
    }
}