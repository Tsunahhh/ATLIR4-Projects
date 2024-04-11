package esi.atl.g60552.othello.view;

import esi.atl.g60552.othello.model.Player;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;


public class GameInfo extends HBox {
    GameInfo() {
        super();
    }

    void update(Player player, int score) {
        this.getChildren().clear();
        Label playerName = new Label("Player: " + player.getName());
        Label color = new Label("Color: " + player.getColor().name());
        Label scoreLabel = new Label("Score: " + String.valueOf(score));
        this.getChildren().addAll(playerName, color, scoreLabel);
    }
}
