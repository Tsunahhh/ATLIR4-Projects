package esi.atl.g60552.othello.view;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.ColorInput;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.util.Optional;

public class SettingsView extends GridPane {

    private AppView appView;
    private ChoiceBox<Integer> tfdSize = new ChoiceBox();
    private ColorPicker colorPicker = new ColorPicker();
    private TextField tfdPlayer1 = new TextField();
    private TextField tfdPlayer2 = new TextField();
    private RadioButton rdbBot = new RadioButton("Bot");
    private RadioButton rdbHuman = new RadioButton("Human");
    private ChoiceBox<String> tfdDifficulty = new ChoiceBox<>();
    private Button apply = new Button("Apply");

    SettingsView(AppView appView) {
        super();
        this.appView = appView;
        init();
        initLabels();
        initInputs();
        initHandlers();
    }

    void init() {
        this.setPadding(new Insets(10));
        this.setHgap(15);
        this.setVgap(6);
    }

    void initLabels() {
        Label lblSettings = new Label("Settings: ");
        this.add(lblSettings, 0, 0, 3, 1);
        Label lblSize = new Label("Size: ");
        this.add(lblSize, 0, 1);
        Label lblColor = new Label("BackGround: ");
        this.add(lblColor, 0, 2);
        Label lblPlayer1 = new Label("Player1: ");
        this.add(lblPlayer1, 0, 3);
        Label lblIsBot = new Label("Is Player 2: ");
        this.add(lblIsBot, 0, 4);
        Label lblPlayer2 = new Label("Player2: ");
        this.add(lblPlayer2, 0, 5);
        Label lblDifficulty = new Label("Difficulty: ");
        this.add(lblDifficulty, 0, 6);
    }

    void initInputs() {
        for (int i = 4; i <= 15; i += 2) {
            tfdSize.getItems().add(i);
        }
        tfdSize.setValue(8);
        tfdSize.setStyle("-fx-font-family: 'Segoe UI', Helvetica, Arial, sans-serif; " +
                "-fx-font-size: 14px; " +
                "-fx-text-fill: #333333; " +
                "-fx-background-color: #ffffff; " +
                "-fx-background-radius: 5px; " +
                "-fx-border-width: 2px; " +
                "-fx-border-color: #333333; " +
                "-fx-padding: 8px 16px;");
        this.add(tfdSize, 1, 1);

        colorPicker.setStyle("-fx-font-family: 'Segoe UI', Helvetica, Arial, sans-serif; " +
                "-fx-font-size: 14px; " +
                "-fx-text-fill: #333333; " +
                "-fx-background-color: #ffffff; " +
                "-fx-background-radius: 5px; " +
                "-fx-border-width: 2px; " +
                "-fx-border-color: #333333; " +
                "-fx-padding: 8px 16px;");
        this.add(colorPicker, 1, 2);

        tfdPlayer1.setText("Player1");
        tfdPlayer1.setStyle("-fx-font-family: 'Segoe UI', Helvetica, Arial, sans-serif; " +
                "-fx-font-size: 14px; " +
                "-fx-text-fill: #333333; " +
                "-fx-background-color: #ffffff; " +
                "-fx-background-radius: 5px; " +
                "-fx-border-width: 2px; " +
                "-fx-border-color: #333333; " +
                "-fx-padding: 8px 16px;");
        this.add(tfdPlayer1, 1, 3);

        tfdPlayer2.setText("Player2");
        tfdPlayer2.setStyle("-fx-font-family: 'Segoe UI', Helvetica, Arial, sans-serif; " +
                "-fx-font-size: 14px; " +
                "-fx-text-fill: #333333; " +
                "-fx-background-color: #ffffff; " +
                "-fx-background-radius: 5px; " +
                "-fx-border-width: 2px; " +
                "-fx-border-color: #333333; " +
                "-fx-padding: 8px 16px;");
        this.add(tfdPlayer2, 1, 5);

        tfdDifficulty.getItems().addAll("Easy", "Medium", "Hard");
        tfdDifficulty.setValue("Medium");
        tfdDifficulty.setStyle("-fx-font-family: 'Segoe UI', Helvetica, Arial, sans-serif; " +
                "-fx-font-size: 14px; " +
                "-fx-text-fill: #333333; " +
                "-fx-background-color: #ffffff; " +
                "-fx-background-radius: 5px; " +
                "-fx-border-width: 2px; " +
                "-fx-border-color: #333333; " +
                "-fx-padding: 8px 16px;");
        this.add(tfdDifficulty, 1, 6);

        ToggleGroup group = new ToggleGroup();
        rdbBot.setToggleGroup(group);
        rdbBot.setStyle("-fx-font-family: 'Segoe UI', Helvetica, Arial, sans-serif; " +
                "-fx-font-size: 14px; " +
                "-fx-text-fill: #333333;");
        rdbHuman.setToggleGroup(group);
        rdbHuman.setStyle("-fx-font-family: 'Segoe UI', Helvetica, Arial, sans-serif; " +
                "-fx-font-size: 14px; " +
                "-fx-text-fill: #333333;");
        rdbBot.setSelected(true);
        this.add(rdbBot, 1, 4);
        this.add(rdbHuman, 2, 4);

        apply = new Button("Apply");
        apply.setStyle("-fx-font-family: 'Segoe UI', Helvetica, Arial, sans-serif; " +
                "-fx-font-size: 14px; " +
                "-fx-text-fill: #333333; " +
                "-fx-background-color: #ffffff; " +
                "-fx-background-radius: 5px; " +
                "-fx-border-width: 2px; " +
                "-fx-border-color: #333333; " +
                "-fx-padding: 8px 16px;");
        this.add(apply, 1, 7);
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

    public String getDifficulty() {
        return tfdDifficulty.getValue();
    }

    public void hide() {
        this.setVisible(false);
    }

    public void show() {
        this.setVisible(true);
    }

    public void initHandlers() {
        apply.setOnAction(e -> {
            this.appView.apply();
        });
    }
}
