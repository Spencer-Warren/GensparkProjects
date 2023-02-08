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

class GameStateTest {

    private static final InputStream original = System.in;

    @AfterEach
    void tearDown() {
        // Restore System.in
        System.setIn(original);
    }

    @Test
    void initializeGame() {
        GameState gameState = new GameState();
        gameState.initializeGame();
        assertNotNull(gameState.getMap(), "Map should not be null");
        assertNotNull(gameState.getCharacters(), "Goblins should not be null");
        assertNotNull(gameState.getHuman(), "Humans should not be null");
    }

    @Test
    void runGame() {
        GameState gameState = new GameState();
        gameState.initializeGame();

        String input = String.format("q%n");
        InputStream newIn = new ByteArrayInputStream(input.getBytes());

        UserInput.setIn(new Scanner(newIn));

        assertEquals(GamePlayState.END, gameState.getGameState(), "Game state should be END");
    }

    @ParameterizedTest
    @MethodSource
    void testHandleInputDirections(String input, int moves, int expectedX, int expectedY) {
        GameState gameState = new GameState();
        gameState.initializeGame();

        String inputString = String.format(input);

        InputStream newIn = new ByteArrayInputStream(inputString.getBytes());
        UserInput.setIn(new Scanner(newIn));

        for (int i = 0; i < moves; i++) {
            gameState.handleUserInput();
        }

        int actualY = gameState.getHuman().getY();
        assertEquals(expectedY, actualY, "Y should be " + expectedY);

        int actualX = gameState.getHuman().getX();
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
        GameState gameState = new GameState();
        gameState.initializeGame();
        Human human = gameState.getHuman();

        String input = String.format("i%nq%n");
        InputStream newIn = new ByteArrayInputStream(input.getBytes());

        UserInput.setIn(new Scanner(newIn));

        gameState.handleUserInput();
        assertEquals(gameState.getHuman().toString(), human.toString(), "Human should not have moved");
    }

}