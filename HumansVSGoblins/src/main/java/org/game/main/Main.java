package org.game.main;

import org.game.ascii.Ascii;
import org.game.characters.Entity;
import org.game.characters.Goblin;
import org.game.characters.Human;
import org.game.map.Map;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    private Map map;
    private ArrayList<Entity> characters;
    private Human human;
    private GameState gameState;

    public void initializeGame() {
        map = new Map(10, 10);
        gameState = GameState.PLAYING;

        characters = new ArrayList<>();
        human = new Human(0, 0);
        characters.add(human);

        int[] xy;
        for (int i = 0; i < 5; i++) {
            xy = map.getEmptyTile();
            characters.add(new Goblin(xy[0], xy[1]));
        }
    }

    private void handleUserInput() {
        String input = UserInput.getUserInput();
        switch (input) {
            case "n":
                human.move(map,0, -1);
                break;
            case "w":
                human.move(map,-1, 0);
                break;
            case "s":
                human.move(map,0, 1);
                break;
            case "e":
                human.move(map,1, 0);
                break;
            case "q":
                gameState = GameState.END;
                break;
            default:
                System.out.println("Invalid input");
        }
    }

    public void runGame() {
        initializeGame();


        while (gameState != GameState.END) {
            clearConsole();
            System.out.println(Ascii.GOBLIN);
            map.updateMap(characters);
            handleUserInput();

        }
    }

    public static void clearConsole(){
        try{
            String operatingSystem = System.getProperty("os.name"); //Check the current operating system

            if(operatingSystem.contains("Windows")){
                ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls");
                Process startProcess = pb.inheritIO().start();
                startProcess.waitFor();
            } else {
                ProcessBuilder pb = new ProcessBuilder("clear");
                Process startProcess = pb.inheritIO().start();

                startProcess.waitFor();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
            new Main().runGame();

    }
}
