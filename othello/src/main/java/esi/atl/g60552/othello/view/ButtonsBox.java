package esi.atl.g60552.othello.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class ButtonsBox extends HBox {

    private final AppView appView;
    private Button pause;
    private Button undo;
    private Button redo;

    private Button pass;
    private Button stop;
    private Button quit;

    ButtonsBox(AppView appView) {
        super(10);
        this.appView = appView;
        this.setStyle("-fx-font-family: 'Segoe UI', Helvetica, Arial, sans-serif; " +
                "-fx-font-size: 14px; " +
                "-fx-text-fill: #333333; " +
                "-fx-background-color: #ffffff; " +
                "-fx-background-radius: 5px; " +
                "-fx-border-width: 2px; " +
                "-fx-border-color: #333333; " +
                "-fx-padding: 8px 16px;");
        this.setAlignment(Pos.CENTER);
        initButtons();
        initHandles();
        initStyles();
    }

    void initButtons() {
        pause = new Button("Pause");
        pass = new Button("Pass");
        stop = new Button("Stop");
        quit = new Button("Quit");
        redo = new Button("Redo");
        undo = new Button("Undo");
        this.getChildren().addAll(undo, redo, pause, pass, stop, quit);
    }

    void initHandles() {
        quit.setOnMouseClicked(e -> {
            appView.quit();
        });
        pause.setOnMouseClicked(e -> {
            appView.pause();
        });
        pass.setOnMouseClicked(e -> {
            appView.pass();
        });
        stop.setOnMouseClicked(e -> {
            appView.giveUp();
        });
        redo.setOnMouseClicked(e -> {
            appView.redo();
        });
        undo.setOnMouseClicked(e -> {
            appView.undo();
        });

    }

    void initStyles() {
        for (var btn : this.getChildren()) {
            btn.setStyle("-fx-font-family: 'Segoe UI', Helvetica, Arial, sans-serif; " +
                    "-fx-font-size: 14px; " +
                    "-fx-text-fill: #333333; " +
                    "-fx-background-color: #ffffff; " +
                    "-fx-background-radius: 5px; " +
                    "-fx-border-width: 2px; " +
                    "-fx-border-color: #333333; " +
                    "-fx-padding: 8px 16px;");
        }

    }
}
