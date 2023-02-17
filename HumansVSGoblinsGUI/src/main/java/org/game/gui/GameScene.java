package org.game.gui;

import javafx.geometry.Insets;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.game.assets.Assets;
import org.game.characters.Entity;
import org.game.main.GameState;

public class GameScene extends SubScene {
    private GridPane graphicalMap;
    private final GameState gameState;
    private Pane combatPane;
    private Button[] buttons;
    private Label status;

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

        GridPane buttonPane = createButtons();
        status = new Label();
        updateStatus();
        HBox statusBar = new HBox();
        statusBar.setSpacing(15);
        statusBar.getChildren().addAll(buttonPane, status);

        combatPane = new Pane();

        VBox rightSide = new VBox();
        rightSide.setSpacing(15);
        rightSide.getChildren().addAll(statusBar, combatPane);

        body.getChildren().addAll(graphicalMap, rightSide);
        getRoot().getChildren().add(body);
    }

    private void updateMap() {
        graphicalMap.getChildren().clear();
        gameState.getMap().updateMap(gameState.getCharacters());
        combatCheck();

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

    public void updateStatus() {
        status.setText("Your Health: " + gameState.getHuman().getHealth() + "/"
                + gameState.getHuman().getMaxHealth() + "\n"
        + " Your Attack: " + gameState.getHuman().getWeapon().getMinDamage()
                + "-" + gameState.getHuman().getWeapon().getMaxDamage() + "\n"
        + " Your Defense: " + gameState.getHuman().getDefense());
    }

    private GridPane createButtons() {
        Button northButton = new Button("North");
        Button southButton = new Button("South");
        Button eastButton = new Button("East");
        Button westButton = new Button("West");

        buttons = new Button[]{northButton, southButton, eastButton, westButton};

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

        GridPane buttonPane = new GridPane();
        buttonPane.setHgap(5);
        buttonPane.setVgap(5);
        buttonPane.setPadding(new Insets(0, 10, 0, 10));
        buttonPane.add(northButton, 1, 0);
        buttonPane.add(southButton, 1, 2);
        buttonPane.add(eastButton, 2, 1);
        buttonPane.add(westButton, 0, 1);
        return buttonPane;
    }

    private void combatCheck() {
        Entity[] combatants = gameState.testForCombat();
        if (combatants.length == 2) {
            CombatScene combatScene = new CombatScene(this, combatants[0], combatants[1]);
            combatPane.getChildren().clear();
            combatPane.getChildren().add(combatScene.getPane());
            toggleButtons(false);
        }
    }

    private void toggleButtons(boolean b) {
        for (Button button : buttons) {
            button.setDisable(!b);
        }
    }

    private void resetGame() {
        gameState.initializeGame();
        updateMap();
        updateStatus();
        toggleButtons(true);
    }

    public void endCombat(boolean won) {
        Entity[] combatants = gameState.testForCombat();
        if (won) {
            gameState.removeCharacter(combatants[1]);
            if (gameState.getCharacters().size() == 1) {
                end(true);
                return;
            }
        } else {
            end(false);
            return;
        }
        toggleButtons(true);
        updateMap();
    }

    private void end(boolean won) {
        toggleButtons(false);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText(won ? "You won!" : "You lost!");
        alert.setContentText("What would you like to do?");

        ButtonType playAgainButton = new ButtonType("Play Again");
        ButtonType exitButton = new ButtonType("Exit");

        alert.getButtonTypes().setAll(playAgainButton, exitButton);
        alert.showAndWait();
        switch (alert.getResult().getText()) {
            case "Play Again":
                resetGame();
                break;
            case "Exit":
                resetGame();
                getStage().setScene(getParentScene().getScene());
                break;
            default:
                break;
        }
    }
}
