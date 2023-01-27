package hangman;

public class HangmanStates {
    /**
     * state 1
     * word: cat
     * c _ _
     * missed: "b"
     * Solved: false
     * @return new Hangman object
     */
    public static Hangman State1() {
        Hangman hangman = new Hangman("cat");
        hangman.guessLetter("c");
        hangman.guessLetter("b");
        return hangman;
    }

    /**
     * state 2
     * word: cat
     * c a t
     * missed: ""
     * Solved: true
     * @return new Hangman object
     */
    public static Hangman State2() {
        Hangman hangman = new Hangman("cat");
        hangman.guessLetter("c");
        hangman.guessLetter("a");
        hangman.guessLetter("t");
        return hangman;
    }

    /**
     * state 3
     * word: cat
     * _ _ _
     * missed: "xyz"
     * Solved: false
     * @return new Hangman object
     */
    public static Hangman State3() {
        Hangman hangman = new Hangman("cat");
        hangman.guessLetter("x");
        hangman.guessLetter("y");
        hangman.guessLetter("z");
        return hangman;
    }
}
