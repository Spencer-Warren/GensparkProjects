package com.guess;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class GuessTheNumber {
    public Scanner consoleIn = new Scanner(System.in);
    public Random randomNumberGenerator = new Random();
    public int getConsoleInt() {
        System.out.println();
        int choice = 0;
        try {
            choice = Integer.parseInt(consoleIn.nextLine());
        }
        catch (NumberFormatException | InputMismatchException e) {
            System.out.println(e.getMessage());
        }
        System.out.println();
        return choice;
    }
    public String getConsoleString() {
        System.out.println();
        String choice = "";
        try {
            choice = consoleIn.nextLine();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();
        return choice;
    }
    public void guessTheNumber(String name) {

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
                System.out.printf("Good job, %s! You guessed my number in %d guesses!\r\n", name, numberOfGuesses);
            }
        } while (usersGuess != correctNumber);
    }
    public void run() {
        System.out.println("Hello! What is your name?");
        String name = getConsoleString();

        System.out.printf("Well, %s, I am thinking of a number between 1 and 20\r\n", name);
        String userChoice;
        do {
            guessTheNumber(name);
            System.out.println("Would you like to play again? (y or n)");
            userChoice = getConsoleString().trim();
        }
        while (!userChoice.equals("n"));
    }
    public static void main(String[] args) {
        new GuessTheNumber().run();
    }
}