package esi.atl.g60552.othello.view;

import esi.atl.g60552.othello.model.*;
import javafx.geometry.Pos;
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
    private Stage stage;

    public AppView(Stage stage) {
        this.stage = stage;
        initViews();
        initGame();
        update();
        showSettings();
        stage.setScene(scene);
        stage.show();
    }

    private void initViews() {
        root = new VBox(10);
        root.setAlignment(Pos.CENTER);

        gameInfo = new GameInfo();

        corps = new HBox(10);
        corps.setAlignment(Pos.CENTER);
        settingsView = new SettingsView(this);
        settingsView.setAlignment(Pos.CENTER);
        reversiView = new ReversiView();
        corps.getChildren().addAll(reversiView, settingsView);

        buttonsBox = new ButtonsBox(this);

        root.getChildren().addAll(gameInfo, corps, buttonsBox);
        scene = new Scene(root);
    }

    private void initGame() {
        Player p1 = new Human(settingsView.getPlayer1(), DiskColor.BLACK);
        Player p2;
        if (settingsView.isBot()) {
            p2 = new Bot(settingsView.getPlayer2(), DiskColor.WHITE);
        } else {
            p2 = new Human(settingsView.getPlayer2(), DiskColor.WHITE);
        }

        reversi = new Reversi(settingsView.getSize(), settingsView.getDifficulty(), p1, p2);
        reversi.registerObserver(this);
    }


    @Override
    public void update() {
        gameInfo.update(reversi.currPlayer(), 0);
        if (reversi.isOver()) {
            reversiView.update(reversi);
            gameOverPopup(reversi.getWinner());
            showSettings();
            reset();
        } else {
            hideSettings();
        }
        reversiView.update(reversi);
    }
    
    void reset() {
        initGame();
        gameInfo.update(reversi.currPlayer(), 0);
        reversiView.update(reversi);
        if (!corps.getChildren().contains(settingsView)) {
            showSettings();
        }
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

    void giveUp() {
        Alert giveUp = new Alert(Alert.AlertType.CONFIRMATION);
        giveUp.setTitle("Give Up");
        giveUp.setHeaderText("Give Up");
        giveUp.setContentText("Game is over: " + reversi.getCurrPlayer().getName() + " give up !");
        giveUp.showAndWait();
        reset();
    }

    void leave() {
        System.exit(0);
    }

    void apply() {
        reversiView.setBgGameColor(settingsView.getBGColor());
        reset();
    }

    void showSettings() {
        corps.getChildren().add(settingsView);
    }

    void hideSettings() {
        corps.getChildren().remove(settingsView);
    }

    void resize() {
        stage.sizeToScene();
    }
}