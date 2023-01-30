package hangman;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class HangmanGameTest {
    private static final PrintStream originalOut = System.out;
    private static final InputStream originalIn = System.in;

    private static final String lineSeparator = System.lineSeparator();
    private static final ByteArrayOutputStream baos = new ByteArrayOutputStream();
    private static ByteArrayInputStream bais;

    @BeforeAll
    static void beforeAll() {
        System.setOut(new PrintStream(baos));
    }

    @AfterEach
    void afterEach() {
        baos.reset();
    }

    @AfterAll static void afterAll() {
        System.setOut(originalOut);
        System.setIn(originalIn);
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
                "c _ _";
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
                        "c a t";
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
                        "_ _ _";
        String actual = HangmanGameStates.State3().displayHangman();

        assertEquals(expected, actual);
    }

    @Test
    void handleGuessTest() {
        String input = "a";
        bais = new ByteArrayInputStream(input.getBytes());
        System.setIn(bais);

        HangmanGame hmg = HangmanGameStates.State1();
        hmg.handleGuess();

        String actualPrompt = baos.toString();
        String expectedPrompt = "Guess a letter: " + lineSeparator;
        assertEquals(expectedPrompt, actualPrompt, "Prompt should be displayed");

        assertTrue(hmg.getHangman().isLetterGuessed('a'));
    }

    @Test
    void handleGuessNumberTest() {
        String input = "1";
        bais = new ByteArrayInputStream(input.getBytes());
        System.setIn(bais);

        HangmanGame hmg = HangmanGameStates.State1();
        hmg.handleGuess();

       String actualPrompt = baos.toString();
       String expectedPrompt = "Guess a letter: " + lineSeparator
                        + "Please enter a letter: " + lineSeparator;
       assertEquals(expectedPrompt, actualPrompt, "Prompt should be displayed");
    }

    @Test
    void alreadyGuessedTest() {
        String input = "c";
        bais = new ByteArrayInputStream(input.getBytes());
        System.setIn(bais);

        HangmanGame hmg = HangmanGameStates.State1();
        hmg.handleGuess();

        String actualPrompt = baos.toString();
        String expectedPrompt = "Guess a letter: " + lineSeparator
                + "You have already guessed that letter. Choose again."+ lineSeparator;
        assertEquals(expectedPrompt, actualPrompt, "Prompt should be displayed");
    }

    /**
     * This is purposefully using == instead of .equals() because we want to make sure
     * that the Hangman object is reset, and the reference is different.
     */
    @Test
    void resetTest() {
        HangmanGame hmg = HangmanGameStates.State1();
        Hangman originalHangman = hmg.getHangman();
        String originalWord = hmg.getWordToGuess();
        hmg.reset();
        Hangman newHangman = hmg.getHangman();
        String newWord = hmg.getWordToGuess();

        assertNotSame(originalHangman, newHangman, "Hangman should be reset");
        assertNotSame(originalWord, newWord, "Word should be reset");
    }
}