package org.game.items;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class WeaponTest {
    private static final Weapon sword = new Weapon("Sword", 1, 10);

    @Test
    void randomDamageTest() {
        for (int i = 0; i < 100; i++) {
            int damage = sword.randomDamage();
            assertTrue(damage >= 1 && damage <= 10, "Damage should be between 1 and 10");
        }
    }

    @Test
    void getNameTest() {
        assertEquals("Sword", sword.getName(), "Name should be Sword");
    }

    @Test
    void toStringTest() {
        assertEquals("Weapon{name='Sword', minDamage=1, maxDamage=10}", sword.toString(), "toString should be Weapon{name='Sword', minDamage=1, maxDamage=10}");
    }

    @Test
    void getMinDamageTest() {
        assertEquals(1, sword.getMinDamage(), "Min damage should be 1");
    }

    @Test
    void getMaxDamageTest() {
        assertEquals(10, sword.getMaxDamage(), "Max damage should be 10");
    }
}