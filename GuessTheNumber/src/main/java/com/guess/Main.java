package com.guess;

import java.util.Random;
import java.util.Scanner;

public class Main {
    private final static Random randomNumberGenerator = new Random();
    private final static Scanner consoleIn = new Scanner(System.in);
    public static int getConsoleInt() {
        System.out.println();
        int choice = Integer.parseInt(consoleIn.nextLine());
        System.out.println();
        return choice;
    }
    public static String getConsoleString() {
        System.out.println();
        String choice = consoleIn.nextLine();
        System.out.println();
        return choice;
    }
    public static void guessTheNumber(String name) {

        int correctNumber = randomNumberGenerator.nextInt(21);
        int usersGuess;
        int numberOfGuesses = 0;
        do {
            System.out.println("Take a guess");
            usersGuess = getConsoleInt();
            numberOfGuesses++;
            if (usersGuess > correctNumber){
                System.out.println("Your guess is too high.");
            } else if (usersGuess < correctNumber) {
                System.out.println("Your guess is too low.");
            }
            else {
                System.out.printf("Good job, %s! You guessed my number in %d guesses!\n", name, numberOfGuesses);
            }
        } while (usersGuess != correctNumber);
    }
    public static void main(String[] args) {
        System.out.println("Hello! What is your name?");
        String name = getConsoleString();

        System.out.printf("Well, %s, I am thinking of a number between 1 and 20\n", name);
        String userChoice;
        do {
            guessTheNumber(name);
            System.out.println("Would you like to play again? (y or n)");
            userChoice = getConsoleString().trim();
        }
        while (!userChoice.equals("n"));
    }
}