package com.dragon;

import java.util.Scanner;

public class Dragon {
    private final static Scanner consoleIn = new Scanner(System.in);
    public static int getResponse() {
        int choice = 0;
        try {
            choice = Integer.parseInt(consoleIn.nextLine());
        }
        catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("\n");
        return choice;
    }
    private static String getCaveText(int choice) {
        String path1 = "You approach the cave...\n" +
                "It is dark and spooky...\n" +
                "A large dragon jumps out in front of you! He opens his jaws and...\n" +
                "Gobbles you down in one bite!\n";

        String path2 = "You approach the cave...\n" +
                "It is dark and spooky...\n" +
                "A large dragon jumps out in front of you! He opens his jaws and...\n" +
                "Offers you a handful of gold!\n";

        if (choice == 1) {
            return path1;
        }
        else {
            return path2;
        }
    }
    public static void main(String[] args) {
        String intro = "You are in a land full of dragons. In front of you,\n" +
                        "you see two caves. In one cave, the dragon is friendly\n" +
                        "and will share his treasure with you. The other dragon\n" +
                        "is greedy and hungry and will eat you on sight.\n" +
                        "Which cave will you go into? (1 or 2)\n";
        // Intro string
        System.out.println(intro);

        int choice = getResponse();
        String text = getCaveText(choice);
        System.out.println(text);
    }
}