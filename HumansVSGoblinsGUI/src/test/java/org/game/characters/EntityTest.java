package org.game.characters;

import org.game.items.Weapon;
import org.game.map.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EntityTest {
    @BeforeEach
    void setUp() {
        entity = new Entity(0, 0, 10, 2);
        entity.giveWeapon(new Weapon("Sword", 1, 10));
    }

    private Entity entity;

    /**
     * Tests that attack and takeDamage work together.
     */
    @Test
    void attackTest() {
        Entity entity1 = new Entity(0, 0, 10, 2);
        Entity entity2 = new Entity(1, 1, 10, 2);

        Weapon weapon = new Weapon("Sword", 1, 10);
        entity1.giveWeapon(weapon);

        int originalHealth = entity2.getHealth();
        entity1.attack(entity2);

        int maxWeaponDamage = entity1.getWeapon().getMaxDamage();
        int minWeaponDamage = entity1.getWeapon().getMinDamage();

        int maxExpectedHealth = originalHealth - minWeaponDamage + entity1.getDefense();
        int minExpectedHealth = originalHealth - maxWeaponDamage + entity1.getDefense();

        assertTrue(entity2.getHealth() <= maxExpectedHealth && entity1.getHealth() >= minExpectedHealth, "Health should be between " + minExpectedHealth + " and " + maxExpectedHealth);
    }

    @Test
    void takeDamageTest() {
        int originalHealth = entity.getHealth();

        entity.takeDamage(5);
        int actualHealth = entity.getHealth();
        int expectedHealth = originalHealth - 5 + entity.getDefense();

        assertEquals(expectedHealth, actualHealth, "Health should be " + expectedHealth);
    }

    /**
     * Tests that the character cant take a negative amount of damage.
     */
    @Test
    void takeZeroDamageTest() {
        int originalHealth = entity.getHealth();

        entity.takeDamage(0);
        int actualHealth = entity.getHealth();

        assertEquals(originalHealth, actualHealth, "Health should be " + originalHealth);
    }

    @Test
    void testX() {
        entity.setX(5);
        assertEquals(5, entity.getX(), "X should be 5");
    }

    @Test
    void testY() {
        entity.setY(5);
        assertEquals(5, entity.getY(), "Y should be 5");
    }

    @Test
    void testHealth() {
        entity.setHealth(5);
        assertEquals(5, entity.getHealth(), "Health should be 5");
    }

    @Test
    void testDefense() {
        entity.setDefense(5);
        assertEquals(5, entity.getDefense(), "Defense should be 5");
    }

    @Test
    void getTileTest() {
        assertEquals(' ', entity.getTile(), "Tile should be ' '");
    }

    @Test
    void getSimpleNameTest() {
        assertEquals("Entity", entity.getSimpleName(), "SimpleName should be Entity");
    }

    @Test
    void getMaxHealthTest() {
        Entity toughEntity = new Entity(0, 0, 20, 2);
        assertEquals(20, toughEntity.getMaxHealth(), "MaxHealth should be 10");
    }



    @Test
    void giveWeapon() {
        Weapon sword = new Weapon("Sword", 1, 10);
        entity.giveWeapon(sword);
        assertEquals(sword, entity.getWeapon(), "Weapon should be Sword");
    }

    @Test
    void moveTest() {
        Map map = new Map(10, 10);
        entity.move(map,1, 1);
        assertEquals(1, entity.getX(), "X should be 1");
        assertEquals(1, entity.getY(), "Y should be 1");
    }

    @Test
    void toStringTest() {
        assertEquals("Character{health=10, defense=2, weapon=Weapon{name='Sword', minDamage=1, maxDamage=10}}", entity.toString(), "toString should be Character{health=10, defense=2, weapon=Weapon{name='Sword', minDamage=1, maxDamage=10}}");
    }

    @Test
    void equalsTest() {
        Entity entity1 = new Entity(0, 0, 10, 2);
        Entity entity2 = new Entity(0, 0, 10, 2);
        assertEquals(entity1, entity2, "Entities should be equal");
    }
    @Test
    void equalsTest2() {
        Entity entity1 = new Entity(0, 0, 10, 2);
        Entity entity2 = new Entity(1, 1, 10, 2);
        assertNotEquals(entity1, entity2, "Entities should not be equal");
    }
    @Test
    void equalsTest3() {
        Entity entity1 = new Entity(0, 0, 10, 2);
        Weapon entity2 = new Weapon("Sword", 1, 10);
        assertNotEquals(entity1, entity2, "Entities should be equal");
    }

    @Test
    void equalsTest4() {
        Entity entity1 = new Entity(0, 0, 10, 2);

        assertEquals(entity1, entity1, "Entities should be equal");
    }

    @Test
    void hashCodeTest() {
        Entity entity1 = new Entity(0, 0, 10, 2);
        Entity entity2 = new Entity(0, 0, 10, 2);
        assertEquals(entity1.hashCode(), entity2.hashCode(), "Hashcodes should be equal");
    }
}