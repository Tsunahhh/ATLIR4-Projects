package esi.atl.g60552.othello.view;

import esi.atl.g60552.othello.model.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import esi.atl.g60552.othello.util.Observer;


public class AppView implements Observer {
    private static final int MARGIN_ = 20;
    private Reversi reversi;
    private BorderPane root;
    private BorderPane corps;
    private GameInfo gameInfo;
    private ReversiView reversiView;
    private SettingsView settingsView;
    private ButtonsBox buttonsBox;
    private Stage stage;
    private Thread timer;

    public AppView(Stage stage) {
        this.stage = stage;
        initViews();
        initGame();
        update();
        stage.getIcons().add(new Image(getClass().getResource("/icons/icon.png").toString()));
        stage.setTitle("Reversi");
        stage.setResizable(false);
        stage.show();
    }

    private void initViews() {
        root = new BorderPane();

        gameInfo = new GameInfo();
        //Pane topPane = new Pane(gameInfo);
        BorderPane.setMargin(gameInfo, new javafx.geometry.Insets(0, 0, MARGIN_, 0));
        root.setTop(gameInfo);

        corps = new BorderPane();

        settingsView = new SettingsView(this);
        settingsView.setAlignment(Pos.CENTER);
        corps.setRight(settingsView);

        reversiView = new ReversiView();
        reversiView.setAlignment(Pos.CENTER);
        corps.setCenter(reversiView);
        root.setCenter(corps);


        buttonsBox = new ButtonsBox(this);
        BorderPane.setMargin(buttonsBox, new javafx.geometry.Insets(MARGIN_, 0, 0, 0));
        root.setBottom(buttonsBox);


        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(true);
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
        gameInfo.update(reversi.currPlayer(), reversi.getScore(reversi.currPlayer()));
        if (reversi.isOver()) {
            reversiView.update(reversi);
            gameOverPopup(reversi.getWinner());
            reset();
        }

        // Settings showed only when game is not playing
        if (reversi.isPlaying()) {
            hideSettings();
        } else {
            showSettings();
        }

        reversiView.update(reversi);
    }
    
    void reset() {
        initGame();
        gameInfo.update(reversi.currPlayer(), 0);
        reversiView.update(reversi);
        update();
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
        Stage gameOverStage = (Stage) gameOver.getDialogPane().getScene().getWindow();
        gameOverStage.getIcons().add(new Image(getClass().getResource("/icons/icon.png").toString()));
        gameOverStage.showAndWait();
    }

    void pause() {
        if (reversi.isPlaying()) {
            Alert pause = new Alert(Alert.AlertType.INFORMATION);
            pause.setTitle("Pause");
            pause.setHeaderText("Game is paused");
            pause.setContentText("Click OK to resume the game");
            pause.showAndWait();
        }
    }

    void pass() {
        reversi.pass();
    }

    void giveUp() {
        if (reversi.isPlaying()) {
            Alert giveUp = new Alert(Alert.AlertType.CONFIRMATION);
            giveUp.setTitle("Give Up");
            giveUp.setHeaderText("Give Up");
            giveUp.setContentText("Game is over: " + reversi.getCurrPlayer().getName() + " give up !");
            Stage giveUpStage = (Stage) giveUp.getDialogPane().getScene().getWindow();
            giveUpStage.getIcons().add(new Image(getClass().getResource("/icons/icon.png").toString()));
            giveUp.showAndWait();
            reset();
        }
    }

    void leave() {
        System.exit(0);
    }

    void apply() {
        reversiView.setBgGameColor(settingsView.getBGColor());
        reset();
        //adaptSize();
    }

    void fullScreen() {
        if (stage.isMaximized()) {
            stage.setMaximized(false);

        } else {
            stage.setMaximized(true);
            stage.setAlwaysOnTop(true);
        }
    }

    void undo() {
        //reversi.undo();
    }
    void redo() {
        //reversi.redo();
    }

    void showSettings() {
        if (!corps.getChildren().contains(settingsView)) {
            corps.getChildren().add(settingsView);
        }
    }

    void hideSettings() {
        corps.getChildren().remove(settingsView);
    }
}