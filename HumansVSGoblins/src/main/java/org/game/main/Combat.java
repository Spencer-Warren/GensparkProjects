package org.game.main;

import org.game.ascii.Ascii;
import org.game.characters.Entity;

import java.util.Scanner;

public class Combat {
    private Combat()
    {
        // This class should not be instantiated
    }
    private static boolean isBlocking;
    private static Scanner scanner = new Scanner(System.in);

    public static boolean combat(Entity attacker, Entity defender) {
        Main.clearConsole();
        System.out.println("Combat has started! You are fighting a goblin!");
        System.out.println("Press enter to continue....");
        scanner.nextLine();

        while (attacker.getHealth() > 0 && defender.getHealth() > 0) {
            Main.clearConsole();
            printCombatInfo(attacker, defender);

            attack(attacker, defender);
            if (defender.getHealth() <= 0) {
                break;
            }
            scanner.nextLine();

            attack(defender, attacker);
            if (attacker.getHealth() <= 0) {
                break;
            }
            scanner.nextLine();

        }

        System.out.println();
        System.out.println("Combat has ended!");
        System.out.println();

        if (attacker.getHealth() <= 0) {
            System.out.println("You died!");
            System.out.println("Press enter to continue....");
            scanner.nextLine();
            return false;
        } else {
            System.out.println("You killed the goblin!");
            System.out.println("Press enter to continue....");
            scanner.nextLine();
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
