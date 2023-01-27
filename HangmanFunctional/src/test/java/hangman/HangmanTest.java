package hangman;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HangmanTest {
    @Test
    void getGuessedSequenceTest() {
        Hangman hangman = HangmanStates.State1();

        String expected = "c _ _";
        String actual = hangman.getGuessedSequence();
        assertEquals(expected, actual, "Must have c _ _");
    }
    @Test
    void getGuessedSequenceFullTest() {
        Hangman hangman = HangmanStates.State2();

        String expected = "c a t";
        String actual = hangman.getGuessedSequence();
        assertEquals(expected, actual, "Guessed Sequence should be the full word");
        assertTrue(hangman.isComplete(), "Should be complete");
    }

    @Test
    void getMissedLettersTestOne() {
        Hangman hangman = HangmanStates.State1();

        String expected = "b";
        String actual = hangman.getMissedLetters();
        assertEquals(expected, actual, "Should only contain 'b'");
    }
    @Test
    void getMissedLettersTestMany() {
        Hangman hangman = HangmanStates.State3();

        String expected = "xyz";
        String actual = hangman.getMissedLetters();
        assertEquals(expected, actual, "Should only contain 'xyz'");
    }

    @Test
    void isLetterGuessedTrueTest() {
        Hangman hangman = HangmanStates.State1();
        boolean result = hangman.isLetterGuessed('c');
        assertTrue(result, "Should be true");
    }

    @Test
    void isLetterGuessedFalseTest() {
        Hangman hangman = HangmanStates.State1();
        boolean result = hangman.isLetterGuessed('z');
        assertFalse(result, "Should be false");
    }

    @Test
    void guessLetterWrongTest() {
        Hangman hangman = HangmanStates.State1();

        int expected = 1;
        int actual = hangman.getNumberWrongGuesses();
        assertEquals(expected,actual, "One wrong guess");

        String expected2 = "b";
        String actual2 = hangman.getMissedLetters();
        assertEquals(expected2, actual2, "Only 'b' guessed wrong");
    }

    @Test
    void wordExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> new Hangman(null));
        assertThrows(IllegalArgumentException.class, () -> new Hangman(""));
        assertThrows(IllegalArgumentException.class, () -> new Hangman(" "));
    }
}