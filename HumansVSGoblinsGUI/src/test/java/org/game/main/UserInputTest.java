package org.game.main;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class UserInputTest {


    @Test
    void getUserInput() {
        // Create a new input stream with the provided inputt
        InputStream original = System.in;
        System.setIn(new ByteArrayInputStream("w".getBytes()));

        String expected = "w";
        String actual = String.valueOf(UserInput.getUserInput());
        assertEquals(expected, actual, "getUserInput should be " + expected);

        // Restore System.in
        System.setIn(original);
    }

    @Test
    void isValidInput() {
        boolean expected = true;
        boolean actual = UserInput.isValidInput("w");
        assertEquals(expected, actual, "isValidInput should be " + expected);
    }
}