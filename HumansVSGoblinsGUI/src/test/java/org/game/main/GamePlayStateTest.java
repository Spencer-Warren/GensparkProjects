package org.game.main;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class GamePlayStateTest {

    @Test
    void values() {
        GamePlayState[] expected = {GamePlayState.PLAYING, GamePlayState.COMBAT, GamePlayState.END};
        GamePlayState[] actual = GamePlayState.values();
        assertArrayEquals(expected, actual, "values should be " + Arrays.toString(expected));
    }

    @Test
    void valueOf() {
        GamePlayState expected = GamePlayState.PLAYING;
        GamePlayState actual = GamePlayState.valueOf("PLAYING");
        assertEquals(expected, actual, "valueOf should be " + expected);
    }
}