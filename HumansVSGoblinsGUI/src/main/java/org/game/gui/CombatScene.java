package org.game.gui;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.game.ascii.Ascii;
import org.game.characters.Entity;

public class CombatScene  {
    private final GameScene gameScene;
    private final Entity human;
    private final Entity goblin;
    private VBox root;
    private StringBuilder combatInfo;
    private Label combatInfoLabel;
    private Button endCombatButton;
    private Button attackButton;
    private boolean won;

    public CombatScene(GameScene gameScene, Entity human, Entity goblin) {
        this.human = human;
        this.goblin = goblin;
        this.gameScene = gameScene;
        init();
    }

    private void init() {
        // root
        root = new VBox();
        root.setSpacing(15);
        // combat scene
        Label battleScene = new Label(Ascii.BATTLE_SCENE);
        combatInfoLabel = new Label();
        combatInfoLabel.setText("Combat has started! You are fighting a goblin!");
        attackButton = new Button("Attack");
        attackButton.setOnAction(e -> frame());

        // end combat button
        endCombatButton = new Button("End combat");
        endCombatButton.setDisable(true);

        endCombatButton.setOnAction(e -> {
            endCombatButton.setDisable(true);
            gameScene.endCombat(won);
        });

        HBox buttonBox = new HBox();
        buttonBox.setSpacing(15);
        buttonBox.getChildren().addAll(attackButton, endCombatButton);
        root.getChildren().addAll(battleScene, combatInfoLabel, buttonBox);
    }

    public Pane getPane() {
        return root;
    }

    private void frame() {
        combatInfo = new StringBuilder();
        attack(human, goblin);
        if (isCombatOver()) {
            endCombat();
            won = true;
            return;
        }
        attack(goblin, human);
        gameScene.updateStatus();
        if (isCombatOver()) {
            endCombat();
            won = false;
            return;
        }
        combatInfo.append("\n");
        printCombatInfo();
        combatInfoLabel.setText(combatInfo.toString());
    }

    private boolean isCombatOver() {
        return human.getHealth() <= 0 || goblin.getHealth() <= 0;
    }

    private void endCombat() {
        combatInfo.append("Combat has ended!");
        if (human.getHealth() <= 0) {
            combatInfo.append("You died!");
        } else {
            combatInfo.append("You killed the goblin!");
        }
        combatInfoLabel.setText(combatInfo.toString());
        endCombatButton.setDisable(false);
        attackButton.setDisable(true);
    }

    public void printCombatInfo() {
        String format = "%s health: %d / %d\n";
        String humanInfo = String.format(format, "Your", human.getHealth(), human.getMaxHealth());
        String goblinInfo = String.format(format, "Goblin", goblin.getHealth(), goblin.getMaxHealth());
        combatInfo.append(humanInfo);
        combatInfo.append(goblinInfo);
    }

    public void attack(Entity attacker, Entity defender) {
        int damage = attacker.attack(defender);
        String format = "%s attacked the %s for %d damage!\n";
        String info = String.format(format, attacker.getSimpleName(), defender.getSimpleName(), damage);
        combatInfo.append(info);
    }
}
