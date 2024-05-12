package esi.atl.g60552.othello.view;

import esi.atl.g60552.othello.model.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import esi.atl.g60552.othello.util.Observer;
import javafx.stage.StageStyle;

/**
 * Main view of the application.
 */
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
    private final double APP_HEIGHT;
    private final double APP_WIDTH;

    /**
     * Constructor.
     * @param stage the stage
     */
    public AppView(Stage stage) {
        this.stage = stage;
        this.reversi = new Reversi();
        reversi.registerObserver(this);
        initViews();
        initGame();
        update();
        initStage();
        this.APP_HEIGHT = stage.getHeight();
        this.APP_WIDTH = stage.getWidth();
    }

    /**
     * Initialize the views.
     */
    private void initViews() {
        root = new BorderPane();
        initBackground();

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

    /**
     * Initialize the background.
     */
    private void initBackground() {
        Image image = new Image(getClass().getResource("/background/background.jpg").toString());
        BackgroundSize backgroundSize = new BackgroundSize(1920, 1080, false, false, false, false);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);
        root.setBackground(background);
    }

    /**
     * Initialize the stage.
     */
    private void initStage() {
        stage.getIcons().add(new Image(getClass().getResource("/icons/icon.png").toString()));
        stage.setTitle("Reversi");
        stage.setResizable(false);
        stage.show();
    }


    /**
     * Initialize the game.
     */
    private void initGame() {
        reversi.initGame(
                settingsView.getSize(),
                settingsView.getDifficulty(),
                settingsView.getPlayer1(),
                settingsView.getPlayer2(),
                settingsView.isBot()
        );
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

    /**
     * Reset the game.
     */
    void reset() {
        initGame();
        update();
    }

    /**
     * Start the game.
     */
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

    /**
     * Pause the game (just a popup).
     */
    void pause() {
        if (reversi.isPlaying()) {
            Alert pause = new Alert(Alert.AlertType.INFORMATION);
            pause.setTitle("Pause");
            pause.setHeaderText("Game is paused");
            pause.setContentText("Click OK to resume the game");
            pause.showAndWait();
        }
    }

    /**
     * Pass the turn.
     */
    void pass() {
        reversi.pass();
    }

    /**
     * The player give up the game.
     */
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

    /**
     * Quit the game.
     */
    void quit() {
        System.exit(0);
    }

    /**
     * Apply the settings.
     */
    void apply() {
        reversiView.setBgGameColor(settingsView.getBGColor());
        reset();
    }

    /**
     * Adapt the window.
     */
    void adaptWindow() {
        stage.setWidth(APP_WIDTH);
        stage.setHeight(APP_HEIGHT);
        stage.centerOnScreen();
    }

    /**
     * Full screen window.
     */
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

    /**
     * Undo the last move.
     */
    void undo() {
        reversi.undo();
    }

    /**
     * Redo the last move.
     */
    void redo() {
        reversi.redo();
    }

    /**
     * Show the settings menu.
     */
    void showSettings() {
        // Show only if not already shown to avoid bugs.
        if (!corps.getChildren().contains(settingsView)) {
            corps.setRight(settingsView);
        }
    }

    /**
     * Hide the settings menu.
     */
    void hideSettings() {
        corps.getChildren().remove(settingsView);
    }
}