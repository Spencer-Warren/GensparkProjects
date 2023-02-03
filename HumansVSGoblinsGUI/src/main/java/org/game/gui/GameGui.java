package org.game.gui;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GameGui extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Parent root = new VBox(100);
        primaryStage.setTitle("Humans vs Goblins");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }
}


