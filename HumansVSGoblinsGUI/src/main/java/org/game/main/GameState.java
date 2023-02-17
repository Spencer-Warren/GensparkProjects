package org.game.main;

import org.game.characters.Entity;
import org.game.characters.Goblin;
import org.game.characters.Human;
import org.game.map.Map;

import java.util.ArrayList;
import java.util.List;

public class GameState {
    private Map map;
    private ArrayList<Entity> characters;
    private Human human;

    public void initializeGame() {
        map = new Map(10, 10);

        characters = new ArrayList<>();
        human = new Human(0, 0);
        characters.add(human);

        int[] xy;
        for (int i = 0; i < 5; i++) {
            xy = map.getEmptyTile();
            characters.add(new Goblin(xy[0], xy[1]));
        }
        map.updateMap(characters);
    }

    public void handleUserInput(String input) {
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
            default:
                System.out.println("Invalid input");
        }
    }
    public Entity[] testForCombat() {
        for (Entity character : characters) {
            if (character != human && character.getX() == human.getX() && character.getY() == human.getY() && !character.equals(human)) {
                return new Entity[] { human, character };
            }
        }
        return new Entity[] {};
    }

    public Map getMap() {
        return map;
    }

    public List<Entity> getCharacters() {
        return characters;
    }

    public Human getHuman() {
        return human;
    }

    public void removeCharacter(Entity character) {
        characters.remove(character);
    }

}
