package esi.atl.g60552.othello.view;

import esi.atl.g60552.othello.model.Reversi;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class ButtonsBox extends HBox {

    private Reversi reversi;
    private Button pause;
    private Button undo;
    private Button redo;
    private Button pass;
    private Button stop;
    private Button leave;

    ButtonsBox() {
        super();
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
            System.exit(0);
        });
        pause.setOnMouseClicked(e -> {

        });
        pass.setOnMouseClicked(e -> {
            reversi.pass();
        });
        stop.setOnMouseClicked(e -> {

        });
    }

    void showButtons() {

    }
    void hideButtons() {

    }

}
