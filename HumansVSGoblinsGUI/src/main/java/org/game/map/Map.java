package org.game.map;

import org.game.characters.Entity;
import java.util.List;
import java.util.Random;

public class Map {
    private final Random random = new Random();
    private final char[][] charsMap;

    public Map(int width, int height) {
        charsMap = new char[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                charsMap[x][y] = '.';
            }
        }
    }

    public char getTile(int x, int y) {
        return charsMap[x][y];
    }

    public void setTile(int x, int y, char tile) {
        charsMap[x][y] = tile;
    }

    public int[] getEmptyTile() {
        int x = random.nextInt(charsMap.length);
        int y = random.nextInt(charsMap[0].length);
        while (charsMap[x][y] != '.') {
            x = random.nextInt(charsMap.length);
            y = random.nextInt(charsMap[0].length);
        }
        return new int[]{x, y};
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < charsMap[0].length; y++) {
            for (char[] chars : charsMap) {
                sb.append(chars[y]).append(" ");
            }
            sb.append(String.format("%n"));
        }
        return sb.toString();
    }

    public int getWidth() {
        return charsMap.length;
    }

    public int getHeight() {
        return charsMap[0].length;
    }

    public void updateMap(List<Entity> entities) {
        for (int x = 0; x < charsMap.length; x++) {
            for (int y = 0; y < charsMap[0].length; y++) {
                charsMap[x][y] = '.';
            }
        }
        for (Entity entity : entities) {
            charsMap[entity.getX()][entity.getY()] = entity.getTile();
        }
    }

    public char[][] getCharsMap() {
        return charsMap;
    }
}
