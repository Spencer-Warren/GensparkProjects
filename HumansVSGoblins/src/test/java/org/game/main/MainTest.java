package org.game.main;

import org.game.characters.Human;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    private static final InputStream original = System.in;

    @AfterEach
    void tearDown() {
        // Restore System.in
        System.setIn(original);
    }

    @Test
    void initializeGame() {
        Main main = new Main();
        main.initializeGame();
        assertNotNull(main.getMap(), "Map should not be null");
        assertNotNull(main.getCharacters(), "Goblins should not be null");
        assertNotNull(main.getHuman(), "Humans should not be null");
    }

    @Test
    void runGame() {
        Main main = new Main();
        main.initializeGame();

        String input = String.format("q%n");
        InputStream newIn = new ByteArrayInputStream(input.getBytes());

        UserInput.setIn(new Scanner(newIn));

        main.runGame();
        assertEquals(GameState.END, main.getGameState(), "Game state should be END");
    }

    @ParameterizedTest
    @MethodSource
    void testHandleInputDirections(String input, int moves, int expectedX, int expectedY) {
        Main main = new Main();
        main.initializeGame();

        String inputString = String.format(input);

        InputStream newIn = new ByteArrayInputStream(inputString.getBytes());
        UserInput.setIn(new Scanner(newIn));

        for (int i = 0; i < moves; i++) {
            main.handleUserInput();
        }

        int actualY = main.getHuman().getY();
        assertEquals(expectedY, actualY, "Y should be " + expectedY);

        int actualX = main.getHuman().getX();
        assertEquals(expectedX, actualX, "X should be " + expectedX);
    }

    private static Stream <Arguments> testHandleInputDirections() {
        return Stream.of(
                Arguments.of("n%n", 1, 0, 0),
                Arguments.of("w%n", 1, 0, 0),
                Arguments.of("s%n", 1, 0, 1),
                Arguments.of("e%n", 1, 1, 0)
        );
    }

    @Test
    void handleInputInvalid() {
        Main main = new Main();
        main.initializeGame();
        Human human = main.getHuman();

        String input = String.format("i%nq%n");
        InputStream newIn = new ByteArrayInputStream(input.getBytes());

        UserInput.setIn(new Scanner(newIn));

        main.handleUserInput();
        assertEquals(main.getHuman().toString(), human.toString(), "Human should not have moved");
    }

}