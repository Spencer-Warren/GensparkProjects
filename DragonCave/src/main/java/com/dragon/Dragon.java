package com.dragon;

import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Dragon {
    private Scanner consoleIn;
    public Dragon() {
       consoleIn = new Scanner(System.in);
    }

    public Dragon(InputStream in){
        consoleIn = new Scanner(in);
    }

    public int getResponse() {
        int choice = 0;
        try {
            while (choice == 0) {
                choice = Integer.parseInt(consoleIn.nextLine());
            }
        }
        catch (NumberFormatException e) {
            System.out.println("Please enter 1 or 2");
        }
        catch (NoSuchElementException e) {
            choice = 1;
        }
        return choice;
    }
    public String getCaveText(int choice) {
        String path1 = "You approach the cave...%n" +
                "It is dark and spooky...%n" +
                "A large dragon jumps out in front of you! He opens his jaws and...%n" +
                "Gobbles you down in one bite!%n";
        path1 = String.format(path1);

        String path2 = "You approach the cave...%n" +
                "It is dark and spooky...%n" +
                "A large dragon jumps out in front of you! He opens his jaws and...%n" +
                "Offers you a handful of gold!%n";
        path2 = String.format(path2);

        if (choice == 1) {
            return path1;
        }
        else {
            return path2;
        }
    }

    public void run(){
        String intro = "You are in a land full of dragons. In front of you,%n" +
                "you see two caves. In one cave, the dragon is friendly%n" +
                "and will share his treasure with you. The other dragon%n" +
                "is greedy and hungry and will eat you on sight.%n" +
                "Which cave will you go into? (1 or 2)%n";
        intro = String.format(intro);

        // Intro string
        System.out.println(intro);

        int choice = getResponse();
        String text = getCaveText(choice);
        System.out.println(text);
    }

    public static void main(String[] args) {
        new Dragon().run();
    }
}