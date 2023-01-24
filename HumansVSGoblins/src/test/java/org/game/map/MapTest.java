package org.game.map;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class MapTest {

    /**
     * Tests that the map is initialized with all '.' tiles.
     */
    @Test
    void setTile() {
        Map map = new Map(10, 10);
        map.setTile(0, 0, 'X');
        assertEquals('X', map.getTile(0, 0), "Tile should be 'X'");
    }

    /**
     * Tests the toString output of the map.
     */
    @Test
    void testToString() {
        Map map = new Map(10, 10);
        map.setTile(0, 0, 'X');

        String expected = String.format(
                "X . . . . . . . . . %n" +
                ". . . . . . . . . . %n" +
                ". . . . . . . . . . %n" +
                ". . . . . . . . . . %n" +
                ". . . . . . . . . . %n" +
                ". . . . . . . . . . %n" +
                ". . . . . . . . . . %n" +
                ". . . . . . . . . . %n" +
                ". . . . . . . . . . %n" +
                ". . . . . . . . . . %n");

        String actual = map.toString();
        assertEquals(expected, actual, "toString should be " + expected);
    }

    /**
     * Tests that the getEmptyTile method returns a tile that is empty.
     */
    @Test
    void testGetEmptyTile() {
        Map map = new Map(10, 10);
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            map.setTile(random.nextInt(10), random.nextInt(10), 'X');
        }
        int[] tile = map.getEmptyTile();
        assertEquals('.', map.getTile(tile[0], tile[1]), "Tile should be '.'");
    }
    @Test
    void testGetEmptyTile2() {
        Map map = new Map(10, 10);
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            map.setTile(random.nextInt(10), random.nextInt(10), 'X');
        }
        for (int i = 0; i < 20; i++) {
            int[] tile = map.getEmptyTile();
            assertEquals('.', map.getTile(tile[0], tile[1]), "Tile should be '.'");
        }
    }

    @Test
    void width() {
        Map map = new Map(10, 10);
        assertEquals(10, map.getWidth(), "Width should be 10");
    }

    @Test
    void height() {
        Map map = new Map(10, 10);
        assertEquals(10, map.getHeight(), "Height should be 10");
    }

}