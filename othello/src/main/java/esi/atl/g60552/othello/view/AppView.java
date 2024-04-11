package esi.atl.g60552.othello.view;

import esi.atl.g60552.othello.model.*;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import esi.atl.g60552.othello.util.Observer;


public class AppView implements Observer {

    private Reversi reversi;
    private VBox root;
    private HBox corps;
    private GameInfo gameInfo;
    private ReversiView reversiView;
    private SettingsView settingsView;
    private ButtonsBox buttonsBox;
    private Scene scene;

    public AppView(Stage stage) {
        initViews();
        initGame();
        update();
        settingsView.show();
        stage.setScene(scene);
        stage.show();
    }

    private void initViews() {
        root = new VBox();

        gameInfo = new GameInfo();

        corps = new HBox();
        settingsView = new SettingsView(this);
        reversiView = new ReversiView();
        corps.getChildren().addAll(reversiView, settingsView);

        buttonsBox = new ButtonsBox(this);

        root.getChildren().addAll(gameInfo, corps, buttonsBox);
        scene = new Scene(root);
    }

    private void initGame() {
        Player p1 = new Human(settingsView.getPlayer1(), DiskColor.BLACK);
        Player p2 = new Human(settingsView.getPlayer2(), DiskColor.WHITE);
        reversi = new Reversi(settingsView.getSize(), p1, p2);
        reversi.registerObserver(this);
    }


    @Override
    public void update() {
        gameInfo.update(reversi.currPlayer(), 0);
        if (reversi.isOver()) {
            reversiView.update(reversi);
            gameOverPopup(reversi.getWinner());
            settingsView.show();
            initGame();
        } else {
            settingsView.hide();
        }
        reversiView.update(reversi);
    }

    void gameOverPopup(Player winner) {
        Alert gameOver = new Alert(Alert.AlertType.WARNING);
        gameOver.setTitle("Game Over");

        if (winner.getColor() == DiskColor.BLACK) {
            gameOver.setHeaderText("Winner is Black");
        } else {
            gameOver.setHeaderText("Winner is White");
        }

        gameOver.setContentText("Congratulations " + reversi.getWinner().getName() + " you won the game!");
        gameOver.showAndWait();
    }

    void pause() {
        Alert pause = new Alert(Alert.AlertType.INFORMATION);
        pause.setTitle("Pause");
        pause.setHeaderText("Game is paused");
        pause.setContentText("Click OK to resume the game");
        pause.showAndWait();
    }

    void pass() {
        reversi.pass();
    }

    void stop() {
        initGame();
        update();
        settingsView.show();
    }

    void leave() {
        System.exit(0);
    }

    void apply() {
        initGame();
        reversiView.setBgGameColor(settingsView.getBGColor());
        update();
    }
}