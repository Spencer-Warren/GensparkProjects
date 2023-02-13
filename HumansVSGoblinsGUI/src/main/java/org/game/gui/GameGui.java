package org.game.gui;

import javafx.application.Application;
import javafx.stage.Stage;

public class GameGui extends Application {

    private Stage stage;
    private MainMenuScene mainMenu;
    private AboutScene aboutScene;
    private GameScene gameScene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        stage.setTitle("Humans vs Goblins");

        initScenes();

        stage.setScene(mainMenu.getScene());
        stage.setResizable(false); // Disable resizing to reduce complexity
        stage.show();
    }

    /**
     * Initializes all scenes
     * Also binds buttons to scenes
     */
    private void initScenes() {
        mainMenu = new MainMenuScene(stage);
        aboutScene = new AboutScene(stage, mainMenu);
        gameScene = new GameScene(stage, mainMenu);

        mainMenu.bindButtonToScene(mainMenu.getAboutButton(), aboutScene);
        mainMenu.bindButtonToScene(mainMenu.getGameButton(), gameScene);
        aboutScene.bindButtons();
        gameScene.bindButtons();
    }
}


