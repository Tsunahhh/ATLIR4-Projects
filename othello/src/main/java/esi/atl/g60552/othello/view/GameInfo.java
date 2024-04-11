package esi.atl.g60552.othello.view;

import esi.atl.g60552.othello.model.Player;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;


public class GameInfo extends HBox {
    GameInfo() {
        super();
    }

    void update(Player player, int score, int time) {
        Label playerName = new Label(player.getName());
        Label scoreLabel = new Label(String.valueOf(score));
        Label timeLabel = new Label(String.valueOf(time));
    }
}
