package org.game.characters;

import org.game.items.Weapon;
import org.game.map.Map;

public class Entity {
    private int x;
    private int y;
    private int health;
    private int defense;
    private Weapon weapon;

    protected char tile;

    public Entity(int x, int y, int health, int defense) {
        this.x = x;
        this.y = y;
        this.health = health;
        this.defense = defense;
    }

    public int attack(Entity target) {
        int damage = weapon.randomDamage();
        return target.takeDamage(damage);
    }

    public int takeDamage(int damage) {
        int damageTaken = damage - defense;
        if (damageTaken < 0) {
            damageTaken = 0;
        }
        health -= damageTaken;
        return damageTaken;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void giveWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public String toString() {
        return "Character{health=" + health + ", defense=" + defense + ", weapon=" + weapon + "}";
    }

    public void move(Map map, int dX, int dY) {
        int newX = x + dX;
        int newY = y + dY;

        if (newX < 0 || newX >= map.getWidth() || newY < 0 || newY >= map.getHeight()) {
            return;
        }

        if (map.getTile(newX, newY) == '.') {
            x = newX;
            y = newY;
            map.setTile(x, y, tile);
        }
    }

    public char getTile() {
        return tile;
    }
}
