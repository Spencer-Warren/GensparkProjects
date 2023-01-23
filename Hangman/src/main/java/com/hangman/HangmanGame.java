package com.hangman;

import java.util.Random;
import java.util.Scanner;
import java.util.List;

public class HangmanGame {
    private static final List<String> words = List.of("cat", "dog", "fern", "hedge", "java" , "python", "google");
    private static final Random random = new Random();
    private final Scanner in;
    // Protected so that the tests can access it
    private final Hangman hangman;
    private final String wordToGuess;

    /**
     * default constructor
     * @param wordToGuess Given word to guess
     */
    public HangmanGame(String wordToGuess) {
        this.in = new Scanner(System.in);
        this.wordToGuess = wordToGuess;
        this.hangman = new Hangman(wordToGuess);
    }

    public Hangman getHangman() {
        return hangman;
    }

    /**
     * calls hangman ui to build the hangman
     * and returns hangman
     * and missed letters
     * and the guessed sequence
     * @return String of hangman
     */
    public String displayHangman() {
        String hangmanDisplay = HangmanUIStates.hangmanString(hangman.getNumberWrongGuesses());
        return String.format(

                        "%sMissed Letters: %s%n" +
                        "%s",
                hangmanDisplay,
                hangman.getMissedLetters(),
                hangman.getGuessedSequence()
        );
    }

    public boolean mainLoop() {
        boolean exit = true;
        System.out.println("H A N G M A N");

        do {
            System.out.println(displayHangman());
            guessLetter();
            if (Boolean.TRUE.equals(hangman.isComplete())) {
                System.out.printf("Yes! The secret word is \"%s\"! You have won!%n", wordToGuess);
                exit = exitHelp();
            }
        } while (Boolean.FALSE.equals(hangman.isComplete()));
        return exit;
    }
    private void guessLetter() {
        boolean guessed = false;
        while (!guessed) {
            System.out.println("Guess a letter.");
            String choice = in.nextLine().trim();
            if (Character.isDigit( choice.charAt(0) )) {
                System.out.println("Please enter a letter.");
                continue;
            }
            if (Boolean.TRUE.equals(hangman.isLetterGuessed(choice.charAt(0)))) {
                System.out.println("You have already guessed that letter. Choose again.");
            }
            else {
                guessed = true;
                hangman.guessLetter(choice);
            }
        }
    }

    private boolean exitHelp() {
        String response = "";
        while (response.equals("")) {
            System.out.println("Do you want to play again? (yes or no)");
            response = in.nextLine().strip().trim();
            System.out.println("\"" + response + "\"");
        }
        return !response.equals("yes");
    }

    public static void run() {
        boolean exit;
        do {
            int randomIndex = random.nextInt( HangmanGame.words.size() - 1 ) ;
            String secretWord = HangmanGame.words.get(randomIndex);
            HangmanGame hmg = new HangmanGame(secretWord);
            exit = hmg.mainLoop();
        } while (!exit);
    }
    public static void main(String[] args) {
        HangmanGame.run();
    }
}