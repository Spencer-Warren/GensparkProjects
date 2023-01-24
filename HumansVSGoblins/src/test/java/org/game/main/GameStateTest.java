package org.game.main;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class GameStateTest {

    @Test
    void values() {
        GameState[] expected = {GameState.PLAYING, GameState.COMBAT, GameState.END};
        GameState[] actual = GameState.values();
        assertArrayEquals(expected, actual, "values should be " + Arrays.toString(expected));
    }

    @Test
    void valueOf() {
        GameState expected = GameState.PLAYING;
        GameState actual = GameState.valueOf("PLAYING");
        assertEquals(expected, actual, "valueOf should be " + expected);
    }
}