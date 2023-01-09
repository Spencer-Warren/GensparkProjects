package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String intro = "You are in a land full of dragons. In front of you,\n" +
                        "you see two caves. In one cave, the dragon is friendly\n" +
                        "and will share his treasure with you. The other dragon\n" +
                        "is greedy and hungry and will eat you on sight.\n" +
                        "Which cave will you go into? (1 or 2)\n";

        String path1 = "You approach the cave...\n" +
                        "It is dark and spooky...\n" +
                        "A large dragon jumps out in front of you! He opens his jaws and...\n" +
                        "Gobbles you down in one bite!\n";

        String path2 = "You approach the cave...\n" +
                        "It is dark and spooky...\n" +
                        "A large dragon jumps out in front of you! He opens his jaws and...\n" +
                        "Offers you a handful of gold!\n";

        Scanner consoleIn = new Scanner(System.in);
        // Intro string
        System.out.println(intro);
        // Read user choice
        int choice = Integer.parseInt(consoleIn.nextLine());

        System.out.println("\n");

        if (choice == 1) {
            System.out.println(path1);
        }
        else {
            System.out.println(path2);
        }
    }
}