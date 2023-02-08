package org.game.main;

import org.game.ascii.Ascii;
import org.game.characters.Goblin;
import org.game.characters.Human;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class CombatTest {
    private static final PrintStream originalOut = System.out;
    private static ByteArrayOutputStream baos;

    @BeforeEach
    void setUp() {
        baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void printCombatInfo() {

        Goblin goblin = new Goblin(1, 2);
        Human human = new Human(1, 2);
        Combat.printCombatInfo(human, goblin);

        String expected = Ascii.BATTLE_SCENE + "%n" +
                "Your health: 10%n" +
                "Goblin health: 5%n" +
                "Your defense: 2%n" +
                "Goblin defense: 1%n";

        expected = String.format(expected);
        String actual = baos.toString();

        assertEquals(expected, actual, "printCombatInfo should be " + expected);
    }

    @Test
    void attack() {
        Human human = new Human(1, 2);
        Goblin goblin = new Goblin(1, 2);
        Combat.attack(human, goblin);
        Pattern pattern = Pattern.compile("Human attacked the Goblin for [0-9] damage!");
        assertTrue(pattern.matcher(baos.toString()).find(), "attack should be " + pattern + " but was " + baos.toString());
    }
//    @Test
//    void pause() {
//        String input = "test";
//        System.setIn(new ByteArrayInputStream(input.getBytes()));
////        Combat.pause();
//        assertEquals("Press enter to continue...." + System.lineSeparator(), baos.toString(), "pause should be Press enter to continue.... but was " + baos.toString());
//
//    }
}