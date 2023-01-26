package org.game.main;

import org.game.ascii.Ascii;
import org.game.characters.Entity;

import java.util.Scanner;

public class Combat {
    private Combat()
    {
        // This class should not be instantiated
    }
    private static final Scanner scanner = new Scanner(System.in);

    private static void pause() {
        System.out.println("Press enter to continue....");
        scanner.nextLine();
    }

    public static boolean combat(Entity human, Entity goblin) {
        Main.clearConsole();
        System.out.println("Combat has started! You are fighting a goblin!");
        pause();

        while (human.getHealth() > 0 && goblin.getHealth() > 0) {
            Main.clearConsole();
            printCombatInfo(human, goblin);

            attack(human, goblin);
            scanner.nextLine();

            attack(goblin, human);
            scanner.nextLine();

            if (human.getHealth() <= 0 || goblin.getHealth() <= 0) {
                break;
            }

        }

        System.out.println();
        System.out.println("Combat has ended!");
        System.out.println();

        if (human.getHealth() <= 0) {
            System.out.println("You died!");
            pause();
            return false;
        } else {
            System.out.println("You killed the goblin!");
            pause();
            return true;
        }
    }

    public static void printCombatInfo(Entity attacker, Entity defender) {
        System.out.println(Ascii.BATTLE_SCENE);

        System.out.println("Your health: " + attacker.getHealth());
        System.out.println("Goblin health: " + defender.getHealth());
        System.out.println("Your defense: " + attacker.getDefense());
        System.out.println("Goblin defense: " + defender.getDefense());
    }

    public static void attack(Entity attacker, Entity defender) {
        int damage = attacker.attack(defender);
        System.out.println(attacker.getSimpleName() + " attacked " + defender.getSimpleName() + " for " + damage + " damage!");
    }

}
