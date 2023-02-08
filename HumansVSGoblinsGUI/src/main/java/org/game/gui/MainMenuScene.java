package org.game.gui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainMenuScene extends DefaultScene {
	private Button gameButton;
	private Button aboutButton;

	public MainMenuScene(Stage stage) {
		super(stage, new VBox());
		initElements(); // Initialize elements, this is to be defined in subclasses
	}

	@Override
	protected void initElements() {
		gameButton = new Button("Play");
		aboutButton = new Button("About..");
		Button exit = new Button("Exit");

		exit.setOnAction((ActionEvent event) -> Platform.exit());

		createTitleBar("Humans vs Goblins");

		Text foot = new Text();
		foot.setText("By: Spencer Warren");
		foot.setFont(Font.font(20));
		foot.getStyleClass().add("footer");
		
		VBox buttons = new VBox(65);
		buttons.setAlignment(Pos.CENTER);
		buttons.getChildren().addAll(gameButton, aboutButton, exit);

		// make buttons fill empty space
		buttons.prefHeightProperty().bind(getScene().heightProperty());
				
		for (Node n : buttons.getChildren()) {
			if (n instanceof Button) {
				Button b = (Button) n;
				b.setPrefSize(450, 40);
				b.getStyleClass().add("button-menu");
			}
		}
		getRoot().getChildren().addAll(buttons, foot);
	}

	public Button getGameButton() {
		return gameButton;
	}

	public Button getAboutButton() {
		return aboutButton;
	}

}
