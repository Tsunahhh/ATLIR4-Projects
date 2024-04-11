package esi.atl.g60552.othello.view;

import esi.atl.g60552.othello.model.*;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
        register();
        stage.setScene(scene);
        stage.show();
        update();
    }

    private void initViews() {
        root = new VBox();

        gameInfo = new GameInfo();

        corps = new HBox();
        settingsView = new SettingsView();
        reversiView = new ReversiView();
        corps.getChildren().addAll(reversiView, settingsView);

        buttonsBox = new ButtonsBox();

        root.getChildren().addAll(gameInfo, corps, buttonsBox);
        scene = new Scene(root);
    }

    private void initGame() {
        Player p1 = new Human("Human", DiskColor.BLACK);
        Player p2 = new Human("Strategy", DiskColor.WHITE);
        reversi = new Reversi(4, p1, p2);
        reversiView.setGame(reversi);
    }

    private void register() {
        reversi.registerObserver(this);
    }


    @Override
    public void update() {
        gameInfo.update(reversi.currPlayer(), 0, 0);
        if (reversi.isOver()) {
            Alert gameOver = new Alert(Alert.AlertType.INFORMATION);
            gameOver.setTitle("Game Over");
            gameOver.setHeaderText("Game Over");
            gameOver.setContentText("Congratulations!");
            gameOver.showAndWait();
            settingsView.showSettings();
            initGame();
        } else {
            settingsView.hideSettings();
        }
        reversiView.update();
    }
}