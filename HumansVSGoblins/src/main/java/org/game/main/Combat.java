package org.game.main;

import org.game.characters.Entity;

public class Combat {
    private Combat()
    {
        // This class should not be instantiated
    }
    public static void combat(Entity attacker, Entity defender) {
        while (attacker.getHealth() > 0 && defender.getHealth() > 0) {
            attacker.attack(defender);
            if (defender.getHealth() > 0) {
                defender.attack(attacker);
            }
        }

        int damage = attacker.attack(defender);
        System.out.println(attacker + " attacked " + defender + " for " + damage + " damage!");
    }
}
