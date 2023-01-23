package com.hangman;

public class HangmanUIStates {
    public enum headState {
        ONE (String.format("   |%n")),
        TWO (String.format(" 0 |%n"));
        private final String display;
        headState(String display) {
            this.display = display;
        }
    }

    public enum bodyState {
        ONE (String.format("   |%n")),
        TWO (String.format(" | |%n")),
        THREE (String.format("-| |%n")),
        FOUR (String.format("-|-|%n")),
        FIVE (String.format("/  |%n")),
        SIX (String.format("/ \\|%n"));
        private final String display;
        bodyState(String display) {
            this.display = display;
        }
    }

    public static String hangmanString(int numberWrongGuesses){
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("+--+%n"));

        if(numberWrongGuesses > 0) {
            sb.append(headState.TWO.display);
        }
        else {
            sb.append(headState.ONE.display);
        }

        switch (numberWrongGuesses) {
            case 2:
                sb.append(bodyState.TWO.display);
                sb.append(bodyState.ONE.display);
                break;
            case 3:
                sb.append(bodyState.THREE.display);
                sb.append(bodyState.ONE.display);
                break;
            case 4:
                sb.append(bodyState.FOUR.display);
                sb.append(bodyState.ONE.display);
                break;
            case 5:
                sb.append(bodyState.FOUR.display);
                sb.append(bodyState.FIVE.display);
                break;
            case 6:
                sb.append(bodyState.FOUR.display);
                sb.append(bodyState.SIX.display);
                break;
            default:
                sb.append(bodyState.ONE.display);
                sb.append(bodyState.ONE.display);
                break;
        }
        sb.append(String.format("  ===%n"));
        return sb.toString();
    }
}
