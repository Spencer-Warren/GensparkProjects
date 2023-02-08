package org.game.assets;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Assets {
    private Assets() {
        // Prevent instantiation
    }
    public static ImageView getTileImage() {
        return getImageView("stone.png");
    }

    public static ImageView getGoblinImage() {
        return getImageView("goblin.png");
    }

    public static ImageView getHumanImage() {
        return getImageView("human.png");
    }

    private static ImageView getImageView(String path) {
        Image image = new Image(path, 50, 50, true, true);
        return new ImageView(image);
    }

}
