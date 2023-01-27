package hangman;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HangmanUIStatesTest {
    private static final String lineSeparator = System.lineSeparator();

    @org.junit.jupiter.api.Test
    void createHangmanBlankTest() {

        String expected =   "+--+"  + lineSeparator +
                            "   |"  + lineSeparator +
                            "   |"  + lineSeparator +
                            "   |"  + lineSeparator +
                            "  ===" + lineSeparator;
        String actual = HangmanUIStates.hangmanString(0);
        assertEquals(expected,actual);
    }
    @org.junit.jupiter.api.Test
    void createHangmanTwoIncorrectTest() {

        String expected =   "+--+"  + lineSeparator +
                " 0 |"  + lineSeparator +
                " | |"  + lineSeparator +
                "   |"  + lineSeparator +
                "  ===" + lineSeparator;
        String actual = HangmanUIStates.hangmanString(2);
        assertEquals(expected,actual);
    }
    @org.junit.jupiter.api.Test
    void createHangmanFullTest() {
        String lineSeprator = System.lineSeparator();

        String expected =   "+--+"  + lineSeprator +
                " 0 |"  + lineSeprator +
                "-|-|"  + lineSeprator +
                "/ \\|"  + lineSeprator +
                "  ===" + lineSeprator;
        String actual = HangmanUIStates.hangmanString(6);
        assertEquals(expected,actual);
    }

    @Test
    void createHangmanState4Test() {
        String lineSeprator = System.lineSeparator();

        String expected =   "+--+"  + lineSeprator +
                " 0 |"  + lineSeprator +
                "-|-|"  + lineSeprator +
                "   |"  + lineSeprator +
                "  ===" + lineSeprator;
        String actual = HangmanUIStates.hangmanString(4);
        assertEquals(expected,actual);
    }
    @Test
    void createHangmanState5Test() {
        String lineSeprator = System.lineSeparator();

        String expected =   "+--+"  + lineSeprator +
                " 0 |"  + lineSeprator +
                "-|-|"  + lineSeprator +
                "/  |"  + lineSeprator +
                "  ===" + lineSeprator;
        String actual = HangmanUIStates.hangmanString(5);
        assertEquals(expected,actual);
    }
}