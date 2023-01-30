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

class FullGameTest {
    private static final PrintStream originalOut = System.out;
    private static final InputStream originalIn = System.in;
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

    @AfterAll
    static void afterAll() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    @Test
    void fullGameTest() {
        String expected = "H A N G M A N%n" +
                "Guess a letter: %n" +
                "+--+%n" +
                "   |%n" +
                "   |%n" +
                "   |%n" +
                "  ===%n" +
                "Missed Letters: %n" +
                "_ _ a _%n" +
                "Guess a letter: %n" +
                "You have already guessed that letter. Choose again.%n" +
                "+--+%n" +
                "   |%n" +
                "   |%n" +
                "   |%n" +
                "  ===%n" +
                "Missed Letters: %n" +
                "_ _ a _%n" +
                "Guess a letter: %n" +
                "+--+%n" +
                " 0 |%n" +
                "   |%n" +
                "   |%n" +
                "  ===%n" +
                "Missed Letters: h%n" +
                "_ _ a _%n" +
                "Guess a letter: %n" +
                "+--+%n" +
                " 0 |%n" +
                "   |%n" +
                "   |%n" +
                "  ===%n" +
                "Missed Letters: h%n" +
                "t _ a _%n" +
                "Guess a letter: %n" +
                "+--+%n" +
                " 0 |%n" +
                "   |%n" +
                "   |%n" +
                "  ===%n" +
                "Missed Letters: h%n" +
                "t r a _%n" +
                "Guess a letter: %n" +
                "Yes! The secret word is \"trap\"! You have won!%n" +
                "Do you want to play again? (yes or no)%n";
        expected = String.format(expected);

        String input = "a%na%nh%nt%nr%np%nnno%n";
        input = String.format(input);
        bais = new ByteArrayInputStream(input.getBytes());
        System.setIn(bais);

        new HangmanGame().reset("trap").run();
        String actual = baos.toString();
        assertEquals(expected, actual, "The output is not correct.");
    }

    @Test
    void fullGameTestRestart() {
        String input = "a%na%nh%nt%nr%np%nyes%na%nb%nc%nd%ne%nf%ng%nh%ni%nl%nl%n";
        input = String.format(input);
        bais = new ByteArrayInputStream(input.getBytes());
        System.setIn(bais);

        HangmanGame hmg = new HangmanGame().reset("trap");
        hmg.run();
        assertNotEquals("trap", hmg.getWordToGuess(), "The word to guess is not reset.");
    }

    @Test
    void mainTest() {
        String input = "a%nb%nc%nd%ne%nf%ng%nh%ni%nl%nl%n";
        input = String.format(input);
        bais = new ByteArrayInputStream(input.getBytes());
        System.setIn(bais);

        HangmanGame.main(new String[0]);
        String actual = baos.toString();
        assertTrue(actual.contains("Do you want to play again? (yes or no)"), "The output is not correct.");
    }
}
