package esi.atl.g60552.othello.view;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

/**
 * Represents the settings view.
 */
public class SettingsView extends GridPane {

    private AppView appView;
    private ChoiceBox<Integer> tfdSize = new ChoiceBox<>();
    private ColorPicker colorPicker = new ColorPicker();
    private TextField tfdPlayer1 = new TextField();
    private TextField tfdPlayer2 = new TextField();
    private RadioButton rdbBot = new RadioButton("Bot");
    private RadioButton rdbHuman = new RadioButton("Human");
    private ChoiceBox<String> chbDifficulty = new ChoiceBox<>();
    private Button apply = new Button("Apply");
    private Button fullScreen;

    /**
     * Constructor.
     * @param appView the app view
     */
    SettingsView(AppView appView) {
        super();
        this.appView = appView;
        init();
        initLabels();
        initInputs();
        initHandlers();
        initStyle();
    }

    /**
     * Initialize the settings view.
     */
    void init() {
        this.setPadding(new Insets(10));
        this.setHgap(15);
        this.setVgap(6);
        this.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, new Insets(5))));
    }

    /**
     * Initialize the labels.
     */
    void initLabels() {
        Label lblSettings = new Label("Settings ");

        this.add(lblSettings, 0, 0, 3, 1);
        Label lblSize = new Label("Size: ");
        this.add(lblSize, 0, 1, 1, 1);
        Label lblColor = new Label("BackGround: ");
        this.add(lblColor, 0, 2, 1, 1);
        Label lblPlayer1 = new Label("Player1: ");
        this.add(lblPlayer1, 0, 3, 1, 1);
        Label lblIsBot = new Label("Is Player 2: ");
        this.add(lblIsBot, 0, 4, 1, 1);
        Label lblPlayer2 = new Label("Player2: ");
        this.add(lblPlayer2, 0, 5, 1, 1);
        Label lblDifficulty = new Label("Difficulty: ");
        this.add(lblDifficulty, 0, 6, 1, 1);
    }

    /**
     * Initialize the inputs.
     */
    void initInputs() {
        for (int i = 4; i <= 15; i += 2) {
            tfdSize.getItems().add(i);
        }
        tfdSize.setValue(8);
        this.add(tfdSize, 1, 1, 2, 1);
        this.add(colorPicker, 1, 2, 2, 1);

        tfdPlayer1.setText("Player1");
        this.add(tfdPlayer1, 1, 3, 2, 1);

        tfdPlayer2.setText("Player2");
        this.add(tfdPlayer2, 1, 5, 2, 1);

        chbDifficulty.getItems().addAll("Diet", "First",  "Random", "Glutton");
        chbDifficulty.setValue("Random");
        this.add(chbDifficulty, 1, 6, 2, 1);

        ToggleGroup group = new ToggleGroup();
        rdbBot.setToggleGroup(group);
        rdbHuman.setToggleGroup(group);
        rdbBot.setSelected(true);
        this.add(rdbBot, 1, 4, 1, 1);
        this.add(rdbHuman, 2, 4, 1, 1);

        apply = new Button("Apply");
        this.add(apply, 1, 7, 2, 1);

        fullScreen = new Button("Full Screen");
        this.add(fullScreen, 1, 8, 2, 1);
    }

    /**
     * Initialize the style.
     */
    private void initStyle() {
        for (var node : this.getChildren()) {
            if (! (node instanceof Label)) {
                node.setStyle("-fx-font-family: 'Segoe UI', Helvetica, Arial, sans-serif; " +
                        "-fx-font-size: 14px; " +
                        "-fx-text-fill: #333333; " +
                        "-fx-background-color: #ffffff; " +
                        "-fx-background-radius: 5px; " +
                        "-fx-border-width: 2px; " +
                        "-fx-border-color: #333333; " +
                        "-fx-padding: 4px 8px;" +
                        "-fx-border-radius: 5px;"
                );
            } else {
                Label lbl = (Label) node;
                if (lbl.getText() == "Settings ") {
                    lbl.setStyle("-fx-font-family: 'Segoe UI', Helvetica, Arial, sans-serif; " +
                            "-fx-font-size: 30px; " +
                            "-fx-background-radius: 5px; " +
                            "-fx-padding: 8px 8px;" +
                            "-fx-font-weight: bold;" +
                            "-fx-underline: true;"
                    );
                } else {
                    node.setStyle("-fx-padding: 4px 8px;");
                }
            }
        }
        this.setStyle("-fx-font-family: 'Segoe UI', Helvetica, Arial, sans-serif; " +
                "-fx-font-size: 14px; " +
                "-fx-text-fill: #333333; " +
                "-fx-background-color: #ffffff; " +
                "-fx-background-radius: 5px; " +
                "-fx-border-width: 2px; " +
                "-fx-border-color: #333333; " +
                "-fx-padding: 8px 8px;");
    }

    /**
     * Get the size.
     * @return the size
     */
    public int getSize() {
        return tfdSize.getValue();
    }

    /**
     * Get the background color.
     * @return the background color
     */
    public Color getBGColor() {
        return colorPicker.getValue();
    }

    /**
     * Get the player 1.
     * @return the player 1
     */
    public String getPlayer1() {
        return tfdPlayer1.getText();
    }

    /**
     * Get the player 2.
     * @return the player 2
     */
    public String getPlayer2() {
        return tfdPlayer2.getText();
    }

    /**
     * Check if the player is a bot.
     * @return true if the player is a bot, false otherwise
     */
    public boolean isBot() {
        return rdbBot.isSelected();
    }

    /**
     * Get the difficulty.
     * @return the difficulty
     */
    public int getDifficulty() {
        return chbDifficulty.getSelectionModel().getSelectedIndex();
    }

    /**
     * Initialize the handlers.
     */
    public void initHandlers() {
        apply.setOnAction(e -> {
            this.appView.apply();
        });
        fullScreen.setOnMouseClicked(e -> {
            appView.fullScreenWindow();
        });
    }
}
