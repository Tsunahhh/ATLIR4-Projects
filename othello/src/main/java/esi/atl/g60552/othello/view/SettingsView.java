package esi.atl.g60552.othello.view;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

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

    SettingsView(AppView appView) {
        super();
        this.appView = appView;
        init();
        initLabels();
        initInputs();
        initHandlers();
        initStyle();
    }

    void init() {
        this.setPadding(new Insets(10));
        this.setHgap(15);
        this.setVgap(6);
        this.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, new Insets(5))));
    }

    void initLabels() {
        Label lblSettings = new Label("Settings ");
        lblSettings.setStyle("-fx-font-family: 'Segoe UI', Helvetica, Arial, sans-serif; " +
                "-fx-font-size: 24px; " +
                "-fx-background-radius: 5px; " +
                "-fx-padding: 8px 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-underline: true;"
        );
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
        //rdbBot.setStyle("-fx-font-family: 'Segoe UI', Helvetica, Arial, sans-serif; " +
        //        "-fx-font-size: 14px; " +
        //        "-fx-text-fill: #333333;");
        rdbHuman.setToggleGroup(group);
        //rdbHuman.setStyle("-fx-font-family: 'Segoe UI', Helvetica, Arial, sans-serif; " +
        //        "-fx-font-size: 14px; " +
        //        "-fx-text-fill: #333333;");
        rdbBot.setSelected(true);
        this.add(rdbBot, 1, 4, 1, 1);
        this.add(rdbHuman, 2, 4, 1, 1);

        apply = new Button("Apply");
        this.add(apply, 1, 7, 2, 1);

        fullScreen = new Button("Full Screen");
        this.add(fullScreen, 1, 8, 2, 1);
    }

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
                        "-fx-padding: 4px 8px;"
                );
            } else {
                node.setStyle("-fx-padding: 4px 8px;");
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

    public int getSize() {
        return tfdSize.getValue();
    }

    public Color getBGColor() {
        return colorPicker.getValue();
    }

    public String getPlayer1() {
        return tfdPlayer1.getText();
    }

    public String getPlayer2() {
        return tfdPlayer2.getText();
    }

    public boolean isBot() {
        return rdbBot.isSelected();
    }

    public int getDifficulty() {
        return chbDifficulty.getSelectionModel().getSelectedIndex();
    }

    public void initHandlers() {
        apply.setOnAction(e -> {
            this.appView.apply();
        });
        fullScreen.setOnMouseClicked(e -> {
            appView.fullScreenWindow();
        });
    }
}
