package org.game.characters;

import org.game.items.Weapon;

public class Goblin extends Entity {
    /**
     * Creates a new Goblin with the given x and y coordinates, health, attack, and defense.
     * Goblins start with 5 health, 3 attack, and 1 defense.
     * Goblins start with a scimitar that does 1-3 damage.
     *
     * @param x the x coordinate of the Goblin
     * @param y the y coordinate of the Goblin
     */
    public Goblin(int x, int y) {
        super(x, y, 5, 1);
        giveWeapon(new Weapon("Scimitar", 1, 3));
        tile = 'g';
    }

    @Override
    public String toString() {
        return "Goblin{" +
                "x=" + getX() +
                ", y=" + getY() +
                ", health=" + getHealth() +
                ", defense=" + getDefense() +
                ", weapon=" + getWeapon() +
                '}';
    }

}

