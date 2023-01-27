package org.game.characters;

import org.game.items.Weapon;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HumanTest {

    @Test
    void testToString() {
        Human human = new Human(1, 2);
        String expected = "Human{x=1, y=2, health=10, defense=2, weapon=Weapon{name='Sword', minDamage=3, maxDamage=6}}";
        String actual = human.toString();
        assertEquals(expected, actual, "toString should be " + expected);
    }

    @Test
    void testToStringNewWeapon() {
        Human human = new Human(1, 2);
        Weapon weapon = new Weapon("Axe", 7, 10);
        human.giveWeapon(weapon);
        String expected = "Human{" +
                "x=" + human.getX() +
                ", y=" + human.getY() +
                ", health=" + human.getHealth() +
                ", defense=" + human.getDefense() +
                ", weapon=" + weapon +
                '}';
        String actual = human.toString();
        assertEquals(expected, actual, "toString should be " + expected);
    }

    @Test
    void testGetTile() {
        Human human = new Human(1, 2);
        char expected = 'H';
        char actual = human.getTile();
        assertEquals(expected, actual, "getTile should be " + expected);
    }

    @Test
    void getSimpleNameTest(){
        Human human = new Human(1, 2);
        String expected = "Human";
        String actual = human.getSimpleName();
        assertEquals(expected, actual, "getSimpleName should be " + expected);
    }
}