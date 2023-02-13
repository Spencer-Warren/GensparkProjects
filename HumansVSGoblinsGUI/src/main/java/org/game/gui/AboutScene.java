package org.game.gui;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class AboutScene extends SubScene {

	public AboutScene(Stage stage, DefaultScene subScene) {
		super(stage, new VBox(), subScene);
		initElements();
	}

	@Override
	protected void initElements() {
		createTitleBar("About");
		VBox body = new VBox();
		body.setSpacing(15);
		body.setPadding(new Insets(20));
		body.getStyleClass().add("level-body");

		String labelText = "This is a GUI adaptation of a grid based game.%n%n"+
				"Your a human faced with a grid of goblins to battle.%n"+
				"Defeat each goblin to win!.%n%n"+
				"This application was built using Java and utilizes the JavaFX Libraries for its GUI.%n%n"+
				"Created by:%n"+
				"\tSpencer Warren%n%n"+
				"Version 0.0%n"+
				"2/13/2023%n";

		labelText = String.format(labelText);
		
		Label text = new Label(labelText);
		
		text.setWrapText(true);
		text.setTextAlignment(TextAlignment.JUSTIFY);
		text.setFont(new Font(15));
		
		body.getChildren().addAll(text);
		
		getRoot().getChildren().add(body);
	}
}
