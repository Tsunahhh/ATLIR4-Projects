package esi.atl.g60552.othello.view;

import esi.atl.g60552.othello.model.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import esi.atl.g60552.othello.util.Observer;
import javafx.stage.StageStyle;


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
    private final double APP_HEIGHT;
    private final double APP_WIDTH;

    public AppView(Stage stage) {
        this.stage = stage;
        this.reversi = new Reversi();
        initViews();
        initGame();
        update();
        initStage();
        this.APP_HEIGHT = stage.getHeight();
        this.APP_WIDTH = stage.getWidth();
    }

    private void initViews() {
        root = new BorderPane();

        gameInfo = new GameInfo();
        BorderPane.setMargin(gameInfo, new Insets(0, 0, MARGIN_, 0));
        root.setTop(gameInfo);

        corps = new BorderPane();

        settingsView = new SettingsView(this);
        settingsView.setAlignment(Pos.CENTER);
        corps.setRight(settingsView);

        reversiView = new ReversiView();
        reversiView.setAlignment(Pos.CENTER);
        BorderPane.setMargin(reversiView, new Insets(0, MARGIN_, 0, 0));
        corps.setCenter(reversiView);
        root.setCenter(corps);


        buttonsBox = new ButtonsBox(this);
        BorderPane.setMargin(buttonsBox, new Insets(MARGIN_, 0, 0, 0));
        root.setBottom(buttonsBox);

        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    private void initStage() {
        stage.getIcons().add(new Image(getClass().getResource("/icons/icon.png").toString()));
        stage.setTitle("Reversi");
        stage.setResizable(false);
        stage.show();
    }

    private void initGame() {

        reversi.initGame(
                settingsView.getSize(),
                settingsView.getDifficulty(),
                settingsView.getPlayer1(),
                settingsView.getPlayer2(),
                settingsView.isBot()
        );

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
            giveUp.setContentText("Game is over: " + reversi.currPlayer().getName() + " give up !");
            Stage giveUpStage = (Stage) giveUp.getDialogPane().getScene().getWindow();
            giveUpStage.getIcons().add(new Image(getClass().getResource("/icons/icon.png").toString()));
            giveUp.showAndWait();
            reset();
        }
    }

    void quit() {
        System.exit(0);
    }

    void apply() {
        reversiView.setBgGameColor(settingsView.getBGColor());
        reset();
    }

    void adaptWindow() {
        stage.setWidth(APP_WIDTH);
        stage.setHeight(APP_HEIGHT);
        stage.centerOnScreen();
    }

    void fullScreenWindow() {
        Stage newStage = new Stage();
        boolean isMaximized = stage.isMaximized();
        if (isMaximized) {
            newStage.initStyle(StageStyle.DECORATED);
        } else {
            newStage.initStyle(StageStyle.UNDECORATED);
        }
        newStage.setScene(stage.getScene());
        stage.close();
        stage = newStage;

        stage.setMaximized(!isMaximized);
        initStage();
        if (isMaximized) adaptWindow();
    }

    void undo() {
        reversi.undo();
    }
    void redo() {
        reversi.redo();
    }

    void showSettings() {
        if (!corps.getChildren().contains(settingsView)) {
            corps.setRight(settingsView);
        }
    }

    void hideSettings() {
        corps.getChildren().remove(settingsView);
    }
}