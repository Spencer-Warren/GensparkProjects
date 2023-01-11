package com.guess;


import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class GuessTheNumberTest {
    @Test
    void getConsoleInt() {
        String input = "1\n";

        GuessTheNumber numberGuess = new GuessTheNumber();
        numberGuess.consoleIn = new Scanner(input);

        int expected = 1;
        int actual = numberGuess.getConsoleInt();
        assertEquals(expected, actual);
    }

    @Test
    void getConsoleString() {
        String input = "y\n";

        GuessTheNumber numberGuess = new GuessTheNumber();
        numberGuess.consoleIn = new Scanner(input);

        String expected = "y";
        String actual = numberGuess.getConsoleString();
        assertEquals(expected, actual);
    }

    @Test
    void guessTheNumber() {
        String input = "Spencer\n18\nn\n";

        GuessTheNumber numberGuess = new GuessTheNumber();
        numberGuess.consoleIn = new Scanner(input);
        numberGuess.randomNumberGenerator.setSeed(1);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream original = System.out;
        System.setOut(new PrintStream(baos));

        numberGuess.run();

        System.setOut(original);
        String nl = System.lineSeparator();
        String expected = "Hello! What is your name?" +
                nl +
                nl +
                nl +
                "Well, Spencer, I am thinking of a number between 1 and 20" +
                nl +
                "Take a guess" +
                nl +
                nl +
                nl +
                "Good job, Spencer! You guessed my number in 1 guesses!" +
                nl +
                "Would you like to play again? (y or n)" +
                nl +
                nl +
                nl;

        String actual = baos.toString();

        assertEquals(expected, actual);
    }
}