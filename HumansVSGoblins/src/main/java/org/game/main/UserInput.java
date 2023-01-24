package org.game.main;

import java.util.Scanner;

public class UserInput {
    private static final String[] VALID_INPUTS = {"n", "w", "s", "e", "q"};
    private static final String[] VALID_INPUTS_DESCRIPTIONS = {"Move North", "Move West", "Move South", "Move East", "Quit Game"};
    private static final Scanner scanner = new Scanner(System.in);

    private UserInput()
    {
        // This class should not be instantiated
    }

    public static String getUserInput() {
        String input = "";
        while (!isValidInput(input)) {
            System.out.println("Please enter a valid input:");
            for (int i = 0; i < VALID_INPUTS.length; i++) {
                System.out.println(VALID_INPUTS[i] + " - " + VALID_INPUTS_DESCRIPTIONS[i]);
            }
            input = scanner.nextLine();
        }
        return input;
    }

    public static boolean isValidInput(String input) {
        for (String validInput : VALID_INPUTS) {
            if (input.equals(validInput)) {
                return true;
            }
        }
        return false;
    }
}
