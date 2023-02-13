package org.game.gui;

import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CombatScene extends SubScene{
    public CombatScene(Stage stage, MainMenuScene mainMenu) {
        super(stage, new VBox(), mainMenu);
        initElements();
    }

    @Override
    protected void initElements() {
        createTitleBar("Combat");
        HBox body = new HBox();
        body.setSpacing(15);
        body.setPadding(new Insets(20));

        getRoot().getChildren().add(body);
    }

}
