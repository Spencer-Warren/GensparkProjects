package org.game.main;

import org.game.characters.Entity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class GameStateTest {

    private static final InputStream original = System.in;
    private static final PrintStream originalOut = System.out;
    private static ByteArrayOutputStream baos;

    @BeforeEach
    void setUp() {
        baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
    }


    @AfterEach
    void tearDown() {
        // Restore System.in
        System.setIn(original);
        System.setOut(originalOut);
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
//        UserInput.setIn(new Scanner(newIn));

//        assertEquals(GamePlayState.END, gameState.getGameState(), "Game state should be END");
        assertNotNull(gameState.getMap(), "Map should not be null");
    }

    @ParameterizedTest
    @MethodSource
    void testHandleInputDirections(String input, int moves, int expectedX, int expectedY) {
        GameState gameState = new GameState();
        gameState.initializeGame();

        for (int i = 0; i < moves; i++) {
            gameState.handleUserInput(input);
        }

        int actualY = gameState.getHuman().getY();
        assertEquals(expectedY, actualY, "Y should be " + expectedY);

        int actualX = gameState.getHuman().getX();
        assertEquals(expectedX, actualX, "X should be " + expectedX);
    }

    private static Stream <Arguments> testHandleInputDirections() {
        return Stream.of(
                Arguments.of("n", 1, 0, 0),
                Arguments.of("w", 1, 0, 0),
                Arguments.of("s", 1, 0, 1),
                Arguments.of("e", 1, 1, 0)
        );
    }

    @Test
    void testInvalidInput() {
        GameState gameState = new GameState();
        gameState.initializeGame();

        gameState.handleUserInput("x");
        assertTrue(baos.toString().contains("Invalid input"), "Invalid input should be printed");
    }

    @Test
    void removeCharacterTest() {
        GameState gameState = new GameState();
        gameState.initializeGame();

        gameState.removeCharacter(gameState.getCharacters().get(0));
        assertEquals(5, gameState.getCharacters().size(), "There should be 5 goblins");
    }

    @Test
    void testForCombatTest() {
        GameState gameState = new GameState();
        gameState.initializeGame();
        assertEquals(0, gameState.testForCombat().length, "There should be no combat");
    }

    @Test
    void testForCombat2() {
        GameState gameState = new GameState();
        gameState.initializeGame();

        gameState.getHuman().setX(1);
        gameState.getHuman().setY(0);
        Entity golin = gameState.getCharacters().get(1);
        golin.setX(1);
        golin.setY(0);
        gameState.getMap().updateMap(gameState.getCharacters());
        assertNotNull(gameState.testForCombat(), "There should be combat");
    }
}