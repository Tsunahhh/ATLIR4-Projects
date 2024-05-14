package esi.atl.g60552.othello.view;

import esi.atl.g60552.othello.model.Player;
import esi.atl.g60552.othello.model.Reversi;
import esi.atl.g60552.othello.util.Observer;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ShowScore extends GridPane implements Observer {

    private Reversi reversi;
    private Player player;
    private Label scoreLabel;
    private Button closeButton;

    public ShowScore(Stage stage, Reversi reversi) {
        this.reversi = reversi;
        this.player = reversi.currPlayer();

        scoreLabel = new Label("Score " + player.getName()  + "(" + player.getColor() + ")" + " :" + reversi.getScore(player));
        scoreLabel.setStyle(
                "-fx-font-weight: bold;" +
                "-fx-font-size: 16px;"
        );
        closeButton = new Button("Close");
        closeButton.setOnAction(e -> stage.close());

        this.setPadding(new Insets(10));
        this.add(scoreLabel, 0, 0);
        this.add(closeButton, 0, 1);
    }

    @Override
    public void update() {
        scoreLabel.setText("Score " + player.getName() + "(" + player.getColor() + ")" + " :" + reversi.getScore(player));
    }
}
