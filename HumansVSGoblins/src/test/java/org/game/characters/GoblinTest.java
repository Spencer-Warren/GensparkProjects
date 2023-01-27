package org.game.characters;

import org.game.items.Weapon;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GoblinTest {

    @Test
    void testToString() {
        Goblin goblin = new Goblin(1, 2);
        String expected = "Goblin{x=1, y=2, health=5, defense=1, weapon=Weapon{name='Scimitar', minDamage=3, maxDamage=5}}";
        String actual = goblin.toString();
        assertEquals(expected, actual, "toString should be " + expected);
    }
    @Test
    void testToStringNewWeapon() {
        Goblin goblin = new Goblin(1, 2);
        Weapon weapon = new Weapon("Axe", 7, 10);
        goblin.giveWeapon(weapon);

        String expected = "Goblin{" +
                "x=" + goblin.getX() +
                ", y=" + goblin.getY() +
                ", health=" + goblin.getHealth() +
                ", defense=" + goblin.getDefense() +
                ", weapon=" + weapon +
                '}';
        String actual = goblin.toString();

        assertEquals(expected, actual, "toString should be " + expected);
    }

    @Test
    void testGetTile() {
        Goblin goblin = new Goblin(1, 2);
        char expected = 'G';
        char actual = goblin.getTile();
        assertEquals(expected, actual, "Tile should be " + expected);
    }
    @Test
    void testGetSimpleName() {
        Goblin goblin = new Goblin(1, 2);
        String expected = "Goblin";
        String actual = goblin.getSimpleName();
        assertEquals(expected, actual, "SimpleName should be " + expected);
    }
}