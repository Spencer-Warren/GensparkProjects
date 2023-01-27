package hangman;


public class HangmanGameStates {

    /**
     * state 1
     * word: cat
     * c _ _
     * missed: "b"
     * Solved: false
     * @return new HangmanGame object
     */
    public static HangmanGame State1() {
        HangmanGame hangmanGame = new HangmanGame();
        hangmanGame.reset("cat");
        Hangman hangman = hangmanGame.getHangman();
        hangman.guessLetter("c");
        hangman.guessLetter("b");
        return hangmanGame;
    }

    /**
     * state 2
     * word: cat
     * c a t
     * missed: ""
     * Solved: true
     * @return new HangmanGame object
     */
    public static HangmanGame State2() {
        HangmanGame hangmanGame = new HangmanGame();
        hangmanGame.reset("cat");
        Hangman hangman = hangmanGame.getHangman();
        hangman.guessLetter("c");
        hangman.guessLetter("a");
        hangman.guessLetter("t");
        return hangmanGame;
    }

    /**
     * state 3
     * word: cat
     * _ _ _
     * missed: "xyz"
     * Solved: false
     * @return new HangmanGame object
     */
    public static HangmanGame State3() {
        HangmanGame hangmanGame = new HangmanGame();
        hangmanGame.reset("cat");
        Hangman hangman = hangmanGame.getHangman();
        hangman.guessLetter("x");
        hangman.guessLetter("y");
        hangman.guessLetter("z");
        return hangmanGame;
    }
}
