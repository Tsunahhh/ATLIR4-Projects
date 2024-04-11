package esi.atl.g60552.othello.view;

import esi.atl.g60552.othello.model.Reversi;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class ButtonsBox extends HBox {

    private AppView appView;
    private Button pause;
    private Button undo;
    private Button redo;
    private Button pass;
    private Button stop;
    private Button leave;

    ButtonsBox(AppView appView) {
        super();
        this.appView = appView;
        HBox.setMargin(this, new Insets(10));
        initButtons();
        initHandles();
    }

    void initButtons() {
        pause = new Button("Pause");
        pass = new Button("Pass");
        stop = new Button("Stop");
        leave = new Button("Leave");
        redo = new Button("Redo");
        undo = new Button("Undo");
        this.getChildren().addAll(undo, redo, pause, pass, stop, leave);
    }

    void initHandles() {
        leave.setOnMouseClicked(e -> {
            appView.leave();
        });
        pause.setOnMouseClicked(e -> {
            appView.pause();
        });
        pass.setOnMouseClicked(e -> {
            appView.pass();
        });
        stop.setOnMouseClicked(e -> {
            appView.stop();
        });
    }

    void showButtons() {

    }

    void hideButtons() {

    }

}
