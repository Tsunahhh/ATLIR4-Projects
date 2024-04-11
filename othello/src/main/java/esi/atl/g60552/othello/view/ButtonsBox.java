package esi.atl.g60552.othello.view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class ButtonsBox extends HBox {
    Button pause;
    Button undo;
    Button redo;
    Button pass;
    Button stop;
    Button leave;
    ButtonsBox() {
        super();
        HBox.setMargin(this, new Insets(10));
        initButtons();
    }

    void initButtons() {
        pause = new Button("Pause");
        pass = new Button("Pass");
        stop = new Button("Stop");
        leave = new Button("Leave");
        redo = new Button("Redo");
        undo = new Button("Undo");
        this.getChildren().addAll(pause, pass, stop, leave);
    }

    void initHandles() {

    }

    void hidePause() {
        this.getChildren().remove(pause);
    }
    void showPause() {
        this.getChildren().add(pause);
    }
    void hidePass() {
        this.getChildren().remove(pass);
    }
    void showPass() {
        this.getChildren().add(pass);
    }
    void hideStop() {
        this.getChildren().remove(stop);
    }
    void showStop() {
        this.getChildren().add(stop);
    }

}
