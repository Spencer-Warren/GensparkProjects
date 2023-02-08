package org.game.gui;

import javafx.geometry.Insets;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.game.assets.Assets;
import org.game.main.GameState;

public class GameScene extends SubScene {
    private GridPane graphicalMap;

    private GameState gameState;


    public GameScene(Stage stage, MainMenuScene mainMenu) {
        super(stage, new VBox(), mainMenu);
        gameState = new GameState();
        gameState.initializeGame();
        initElements();
    }

    @Override
    protected void initElements() {
        createTitleBar("Game");
        VBox body = new VBox();
        body.setSpacing(15);
        body.setPadding(new Insets(20));

        graphicalMap = new GridPane();
        graphicalMap.setHgap(10);
        graphicalMap.setVgap(10);
        graphicalMap.setPadding(new Insets(0, 10, 0, 10));
        updateMap();

        body.getChildren().add(graphicalMap);
        getRoot().getChildren().add(body);
    }

    private void updateMap() {
        graphicalMap.getChildren().clear();
        char[][] map = gameState.getMap().getCharsMap();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                switch (map[i][j]) {
                    case 'H':
                        graphicalMap.add(Assets.getHumanImage(), i, j);
                        break;
                    case 'G':
                        graphicalMap.add(Assets.getGoblinImage(), i, j);
                        break;
                    case '.':
                        graphicalMap.add(Assets.getTileImage(), i, j);
                        break;
                    default:
                        break;
                }
            }
        }
    }
}
