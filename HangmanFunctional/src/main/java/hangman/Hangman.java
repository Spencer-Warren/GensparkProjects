package hangman;

import java.util.Arrays;
import java.util.Objects;

public class Hangman {

    private String guessedLetters;
    private String missedLetters;
    private final String wordToGuess;
    private int numberWrongGuesses;

    /**
     * Initialize a hangman
     * @param wordToGuess word for user to guess
     */
    public Hangman (String wordToGuess) {
        if (Objects.isNull(wordToGuess) || wordToGuess.isBlank()) {
            throw new IllegalArgumentException("Word cannot be null or blank");
        }
        this.wordToGuess = wordToGuess;
        this.guessedLetters = "";
        this.missedLetters = "";
        this.numberWrongGuesses = 0;
    }

    /**
     * Returns a string so that correctly guess chars appear
     * and not guessed ones show an _
     * @return String sequence of guessed chars
     */
    public String getGuessedSequence(){
        return Arrays.stream(wordToGuess.split(""))
                .map(c -> !guessedLetters.contains(c) ? "_" : c)
                .reduce((a, b) -> a + " " + b).get();
    }

    /**
     * return missed letters
     * for UI
     * @return String of missed letters
     */
    public String getMissedLetters() {
        return missedLetters;
    }

    /**
     * Returns boolean to see if a certain character
     * was previously guessed
     * @param c char to check
     * @return boolean has been guessed
     */
    public Boolean isLetterGuessed(char c) {
        return charInString(guessedLetters, c);
    }

    /**
     * returns boolean if user has
     * guessed the word correctly
     * @return boolean word guessed
     */
    public Boolean isComplete(){
        return !getGuessedSequence().contains("_");
    }

    /**
     * return number of wrong guesses
     * @return number of chars guessed wrong
     */
    public int getNumberWrongGuesses() {
        return numberWrongGuesses;
    }

    /**
     * Given a char adds to the guessed letters
     * and adds incorrect
     * @param c String letter user guessed
     */
    public void guessLetter(String c) {
        // if its already guessed ignore
        if (Boolean.TRUE.equals(isLetterGuessed(c.charAt(0)))) {
            return;
        }
        // add to total list of guest letters
        guessedLetters += c;
        // If it is not in the word add to missed
        if (Boolean.FALSE.equals(charInString(wordToGuess, c.charAt(0)))) {
            missedLetters += c;
            numberWrongGuesses++;
        }
    }

    /**
     * Checks to see if a char is in a given string
     * @param s String to check
     * @param charToFind char to find in string
     * @return boolean if char is in string
     */
    private Boolean charInString(String s, char charToFind){
        String c = String.valueOf(charToFind);
        return s.contains(c);
    }
}