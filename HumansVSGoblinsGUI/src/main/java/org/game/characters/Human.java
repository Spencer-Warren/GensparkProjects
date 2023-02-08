package org.game.characters;

import org.game.items.Weapon;

public class Human extends Entity {
    /**
     * Creates a new Human with the given x and y coordinates, health, attack, and defense.
     * Humans start with 10 health, 5 attack, and 5 defense.
     * Humans start with a sword that does 1-5 damage.
     *
     * @param x the x coordinate of the Human
     * @param y the y coordinate of the Human
     */
    public Human(int x, int y) {
        super(x, y, 10, 2, 'H');
        giveWeapon(new Weapon("Sword", 3, 6));
    }

    @Override
    public String toString() {
        return "Human{" +
                "x=" + getX() +
                ", y=" + getY() +
                ", health=" + getHealth() +
                ", defense=" + getDefense() +
                ", weapon=" + getWeapon() +
                '}';
    }

    @Override
    public String getSimpleName() {
        return "Human";
    }
}

