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
    void fullGameTest() {
        String expected = "H A N G M A N\n" +
                "Guess a letter: \n" +
                "a\n" +
                "+--+\n" +
                "   |\n" +
                "   |\n" +
                "   |\n" +
                "  ===\n" +
                "Missed Letters: \n" +
                "_ _ a _\n" +
                "Guess a letter: \n" +
                "a\n" +
                "You have already guessed that letter. Choose again.\n" +
                "+--+\n" +
                "   |\n" +
                "   |\n" +
                "   |\n" +
                "  ===\n" +
                "Missed Letters: \n" +
                "_ _ a _\n" +
                "Guess a letter: \n" +
                "h\n" +
                "+--+\n" +
                " 0 |\n" +
                "   |\n" +
                "   |\n" +
                "  ===\n" +
                "Missed Letters: h\n" +
                "_ _ a _\n" +
                "Guess a letter: \n" +
                "t\n" +
                "+--+\n" +
                " 0 |\n" +
                "   |\n" +
                "   |\n" +
                "  ===\n" +
                "Missed Letters: h\n" +
                "t _ a _\n" +
                "Guess a letter: \n" +
                "r\n" +
                "+--+\n" +
                " 0 |\n" +
                "   |\n" +
                "   |\n" +
                "  ===\n" +
                "Missed Letters: h\n" +
                "t r a _\n" +
                "Guess a letter: \n" +
                "p\n" +
                "Yes! The secret word is \"trap\"! You have won!\n" +
                "Do you want to play again? (yes or no)\n" +
                "no\n" +
                "\n";
    }
}
