package org.game.items;

import java.util.Random;

public class Weapon implements Item {
    private static final Random random = new Random();
    private final String name;
    private final int minDamage;

    private final int maxDamage;

    public Weapon(String name, int minDamage, int maxDamage) {
        this.name = name;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
    }

    public int randomDamage() {
        int possibleDamage = random.nextInt(maxDamage);
        if (possibleDamage < minDamage) {
            possibleDamage = minDamage;
        }
        return possibleDamage;
    }

    public int getMinDamage() {
        return minDamage;
    }

    public int getMaxDamage() {
        return maxDamage;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Weapon{" +
                "name='" + name + '\'' +
                ", minDamage=" + minDamage +
                ", maxDamage=" + maxDamage +
                '}';
    }
}
