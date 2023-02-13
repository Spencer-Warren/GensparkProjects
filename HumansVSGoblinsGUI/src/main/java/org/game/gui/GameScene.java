package org.game.gui;

import javafx.geometry.Insets;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.game.assets.Assets;
import org.game.main.GameState;

public class GameScene extends SubScene {
    private GridPane graphicalMap;

    private GameState gameState;

    /**
     * Constructor for GameScene
     * @param stage The stage to display the scene on
     * @param mainMenu The main menu scene
     */
    public GameScene(Stage stage, MainMenuScene mainMenu) {
        super(stage, new VBox(), mainMenu);
        gameState = new GameState();
        gameState.initializeGame();
        initElements();
    }

    @Override
    protected void initElements() {
        createTitleBar("Game");
        HBox body = new HBox();
        body.setSpacing(15);
        body.setPadding(new Insets(20));
        body.getStyleClass().add("level-body");

        graphicalMap = new GridPane();
        graphicalMap.setHgap(10);
        graphicalMap.setVgap(10);
        graphicalMap.setPadding(new Insets(0, 10, 0, 10));
        updateMap();
        GridPane buttons = createButtons();
        body.getChildren().addAll(graphicalMap, buttons);
        getRoot().getChildren().add(body);
    }

    private void updateMap() {
        graphicalMap.getChildren().clear();
        gameState.getMap().updateMap(gameState.getCharacters());
        gameState.testForCombat();

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


    private GridPane createButtons() {
        Button northButton = new Button("North");
        Button southButton = new Button("South");
        Button eastButton = new Button("East");
        Button westButton = new Button("West");

        northButton.setOnAction(e -> {
            gameState.handleUserInput("n");
            updateMap();
        });
        southButton.setOnAction(e -> {
            gameState.handleUserInput("s");
            updateMap();
        });
        eastButton.setOnAction(e -> {
            gameState.handleUserInput("e");
            updateMap();
        });
        westButton.setOnAction(e -> {
            gameState.handleUserInput("w");
            updateMap();
        });

        GridPane buttons = new GridPane();
        buttons.setHgap(10);
        buttons.setVgap(10);
        buttons.setPadding(new Insets(0, 10, 0, 10));
        buttons.add(northButton, 1, 0);
        buttons.add(southButton, 1, 2);
        buttons.add(eastButton, 2, 1);
        buttons.add(westButton, 0, 1);
        return buttons;
    }
//    private void switchToCombatScene() {
//        CombatScene combatScene = new CombatScene(getStage(), this);
//        getStage().setScene(combatScene);
//    }
}
