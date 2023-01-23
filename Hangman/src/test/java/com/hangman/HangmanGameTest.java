package com.hangman;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class HangmanGameTest {
    private static final String lineSeparator = System.lineSeparator();
    private static final PrintStream original = System.out;
    private static final ByteArrayOutputStream baos = new ByteArrayOutputStream();

    @BeforeAll
    static void beforeAll() {
        System.setOut(new PrintStream(baos));
    }

    @AfterEach
    void afterEach() {
        baos.reset();
    }

    @AfterAll static void afterAll() {
        System.setOut(original);
    }

    @Test
    void displayHangmanTest1() {
        String expected =
                "+--+"  + lineSeparator +
                " 0 |"  + lineSeparator +
                "   |"  + lineSeparator +
                "   |"  + lineSeparator +
                "  ===" + lineSeparator +
                "Missed Letters: b" + lineSeparator +
                "c _ _ ";
        String actual = HangmanGameStates.State1().displayHangman();

        assertEquals(expected, actual);
    }

    @Test
    void displayHangmanTest2() {
        String expected =
                "+--+"  + lineSeparator +
                        "   |"  + lineSeparator +
                        "   |"  + lineSeparator +
                        "   |"  + lineSeparator +
                        "  ===" + lineSeparator +
                        "Missed Letters: " + lineSeparator +
                        "c a t ";
        String actual = HangmanGameStates.State2().displayHangman();

        assertEquals(expected, actual);
    }

    @Test
    void displayHangmanTest3() {
        String expected =
                        "+--+"  + lineSeparator +
                        " 0 |"  + lineSeparator +
                        "-| |"  + lineSeparator +
                        "   |"  + lineSeparator +
                        "  ===" + lineSeparator +
                        "Missed Letters: xyz" + lineSeparator +
                        "_ _ _ ";
        String actual = HangmanGameStates.State3().displayHangman();

        assertEquals(expected, actual);
    }
}