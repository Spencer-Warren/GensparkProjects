package hangman;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class HangmanUIStatesTest {
    private static final String lineSeparator = System.lineSeparator();

    @ParameterizedTest
    @MethodSource("hangmanUIStatesTestProvider")
    void createHangmanStateTest(int state, String expected) {
        String actual = HangmanUIStates.hangmanString(state);
        assertEquals(expected,actual);
    }

    static Stream<Arguments> hangmanUIStatesTestProvider() {
        return Stream.of(
                Arguments.of(0, "+--+"  + lineSeparator +
                        "   |"  + lineSeparator +
                        "   |"  + lineSeparator +
                        "   |"  + lineSeparator +
                        "  ===" + lineSeparator),
                Arguments.of(1, "+--+"  + lineSeparator +
                        " 0 |"  + lineSeparator +
                        "   |"  + lineSeparator +
                        "   |"  + lineSeparator +
                        "  ===" + lineSeparator),
                Arguments.of(2, "+--+"  + lineSeparator +
                        " 0 |"  + lineSeparator +
                        " | |"  + lineSeparator +
                        "   |"  + lineSeparator +
                        "  ===" + lineSeparator),
                Arguments.of(3, "+--+"  + lineSeparator +
                        " 0 |"  + lineSeparator +
                        "-| |"  + lineSeparator +
                        "   |"  + lineSeparator +
                        "  ===" + lineSeparator),
                Arguments.of(4, "+--+"  + lineSeparator +
                        " 0 |"  + lineSeparator +
                        "-|-|"  + lineSeparator +
                        "   |"  + lineSeparator +
                        "  ===" + lineSeparator),
                Arguments.of(5, "+--+"  + lineSeparator +
                        " 0 |"  + lineSeparator +
                        "-|-|"  + lineSeparator +
                        "/  |"  + lineSeparator +
                        "  ===" + lineSeparator),
                Arguments.of(6, "+--+"  + lineSeparator +
                        " 0 |"  + lineSeparator +
                        "-|-|"  + lineSeparator +
                        "/ \\|"  + lineSeparator +
                        "  ===" + lineSeparator)
        );
    }
}