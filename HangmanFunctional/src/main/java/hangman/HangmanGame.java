package hangman;

import java.util.*;

public class HangmanGame {
    private HangmanGameState state = HangmanGameState.GUESSING;
    private static final ArrayList<String> words = new ArrayList<>(Arrays.asList("cat", "dog", "heat", "trap", "knock", "treat", "class", "reach", "wrong", "flood", "look", "give"));
    private static final Random random = new Random();
    private final Scanner in;
    // Protected so that the tests can access it
    private Hangman hangman;
    private String wordToGuess;

    /**
     * default constructor
     */
    public HangmanGame() {
        this.in = new Scanner(System.in);
        reset();

    }

    public Hangman getHangman() {
        return hangman;
    }

    /**
     * calls hangman ui to build the hangman
     * and returns hangman
     * and missed letters
     * and the guessed sequence
     *
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

    public void run() {
        boolean exit = false;
        System.out.println("H A N G M A N");

        do {
            switch (state) {
                case GUESSING:
                    handleGuess();
                    break;

                case EXIT:
                    String response = getUserInput("Do you want to play again? (yes or no)");
                    if (response.equalsIgnoreCase("yes")) {
                        reset();
                        state = HangmanGameState.GUESSING;
                    } else {
                        exit = true;
                    }
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + state);

            }
            if (Boolean.TRUE.equals(hangman.isComplete()) && state == HangmanGameState.GUESSING) {
                System.out.printf("Yes! The secret word is \"%s\"! You have won!%n", wordToGuess);
                state = HangmanGameState.EXIT;
            }
            if (state == HangmanGameState.GUESSING) {
                System.out.println(displayHangman());
            }
            if (hangman.getNumberWrongGuesses() == 6 && state == HangmanGameState.GUESSING) {
                System.out.printf("Sorry, you are hung! The word was \"%s\".%n", wordToGuess);
                state = HangmanGameState.EXIT;
            }
        } while (!exit);
    }

    public void handleGuess() {
        String guess = getUserInput("Guess a letter: ");

        if (Character.isDigit(guess.charAt(0))) {
            System.out.println("Please enter a letter: ");
        }
        if (Boolean.TRUE.equals(hangman.isLetterGuessed(guess.charAt(0)))) {
            System.out.println("You have already guessed that letter. Choose again.");
        } else {
            hangman.guessLetter(guess);
        }
    }

    public void reset() {
        words.remove(wordToGuess);
        int randomIndex = random.nextInt(HangmanGame.words.size() - 1);
        wordToGuess = HangmanGame.words.get(randomIndex);
        hangman = new Hangman(wordToGuess);
    }

    public HangmanGame reset(String word) {
        wordToGuess = word;
        hangman = new Hangman(wordToGuess);
        return this;
    }

    public String getUserInput(String prompt) {
        System.out.println(prompt);
        return in.nextLine();
    }

    public String getWordToGuess() {
        return wordToGuess;
    }

    public static void main(String[] args) {
        new HangmanGame().run();
    }
}