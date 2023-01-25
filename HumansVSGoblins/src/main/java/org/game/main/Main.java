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

    public void handleUserInput() {
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
            map.updateMap(characters);
            map.printMap();
            handleUserInput();
            testForCombat();
            if(characters.size() == 1) {
                gameState = GameState.END;
                System.out.println("Game Over, you win!");
                System.out.println("Press enter to exit");
            }

        }
    }

    public void testForCombat() {
        for (Entity character : characters) {
            if (character != human && character.getX() == human.getX() && character.getY() == human.getY() && !character.equals(human)) {
                if (!Combat.combat(human, character)) {
                    gameState = GameState.END;
                } else {
                    characters.remove(character);
                    break;
                }
            }
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

    public Map getMap() {
        return map;
    }

    public ArrayList<Entity> getCharacters() {
        return characters;
    }

    public Human getHuman() {
        return human;
    }

    public GameState getGameState() {
        return gameState;
    }

    public static void main(String[] args) {
        new Main().runGame();
    }
}
