package esi.atl.g60552.othello.view;

import esi.atl.g60552.othello.model.Player;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * Class represent the game information.

 */
public class GameInfo extends HBox {

    /**
     * Construct the game information.
     */
    GameInfo() {
        super(10);
        this.setStyle("-fx-font-family: 'Segoe UI', Helvetica, Arial, sans-serif; " +
                "-fx-font-size: 14px; " +
                "-fx-text-fill: #333333; " +
                "-fx-background-color: #ffffff; " +
                "-fx-background-radius: 5px; " +
                "-fx-border-width: 2px; " +
                "-fx-border-color: #333333; " +
                "-fx-padding: 8px 16px;");
    }

    /**
     * Update the game information.
     * @param player the player
     * @param score the score
     */
    void update(Player player, int score) {
        this.getChildren().clear();
        Label playerName = new Label("Player: " + player.getName());
        playerName.setStyle("-fx-font-family: 'Segoe UI', Helvetica, Arial, sans-serif; " +
                "-fx-font-size: 14px; " +
                "-fx-text-fill: #333333;");
        Label color = new Label("Color: " + player.getColor().name());
        color.setStyle("-fx-font-family: 'Segoe UI', Helvetica, Arial, sans-serif; " +
                "-fx-font-size: 14px; " +
                "-fx-text-fill: #333333;");
        Label scoreLabel = new Label("Score: " + String.valueOf(score));
        scoreLabel.setStyle("-fx-font-family: 'Segoe UI', Helvetica, Arial, sans-serif; " +
                "-fx-font-size: 14px; " +
                "-fx-text-fill: #333333;");
        this.getChildren().addAll(playerName, color, scoreLabel);
    }
}
