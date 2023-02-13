package hangman;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class HangmanUIStates {
    private static List<String> headstates;
    private static List<String> bodystates;

    private  HangmanUIStates() {
        // private constructor
    }
    static {
        loadStates();
    }
    private static void loadStates() {
        try {
            headstates = Files.readAllLines(Paths.get("resources\\headStates.txt"));
            headstates = headstates.stream().map(String::format).collect(Collectors.toList());

            bodystates = Files.readAllLines(Paths.get("resources\\bodyStates.txt"));
            bodystates = bodystates.stream().map(String::format).collect(Collectors.toList());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("HangmanUIStates class loaded");
    }

    public static String hangmanString(int numberWrongGuesses){
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("+--+%n"));

        if(numberWrongGuesses > 0) {
            sb.append(headstates.get(1));
        }
        else {
            sb.append(headstates.get(0));
        }

        switch (numberWrongGuesses) {
            case 2:
                sb.append(bodystates.get(1));
                sb.append(bodystates.get(0));
                break;
            case 3:
                sb.append(bodystates.get(2));
                sb.append(bodystates.get(0));
                break;
            case 4:
                sb.append(bodystates.get(3));
                sb.append(bodystates.get(0));
                break;
            case 5:
                sb.append(bodystates.get(3));
                sb.append(bodystates.get(4));
                break;
            case 6:
                sb.append(bodystates.get(3));
                sb.append(bodystates.get(5));
                break;
            default:
                sb.append(bodystates.get(0));
                sb.append(bodystates.get(0));
                break;
        }
        sb.append(String.format("  ===%n"));
        return sb.toString();
    }
}
