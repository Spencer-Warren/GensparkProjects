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

		String labelText = "This desktop app is a coding tutorial aimed towards a less technical audience.%n%n"+
				"It includes 8 topics each with its own mini activity. And saves where you were in those activities.%n"+
				"It has all the text, information and activity answers stored in a JSON file.%n%n"+
				"This application was built using Java and utilizes the JavaFX Libraries for its UI.%n%n"+
				"Created by:%n"+
				"\tSpencer Warren%n%n"+
				"Version 1.0%n"+
				"2/7/2023%n";

		labelText = String.format(labelText);
		
		Label text = new Label(labelText);
		
		text.setWrapText(true);
		text.setTextAlignment(TextAlignment.JUSTIFY);
		text.setFont(new Font(15));
		
		body.getChildren().addAll(text);
		
		getRoot().getChildren().add(body);
	}
}
