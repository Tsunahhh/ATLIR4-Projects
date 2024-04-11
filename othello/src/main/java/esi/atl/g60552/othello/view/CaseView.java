package esi.atl.g60552.othello.view;

import javafx.scene.control.Cell;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.Stack;

public class CaseView extends StackPane {
    Circle circle;
    Rectangle rectangle;
    Color backgroundColor;
    public CaseView(Color backgroundColor) {
        super();
        this.backgroundColor = backgroundColor;
        init();
    }

    void init() {
        rectangle = new Rectangle(50, 50, backgroundColor);
        rectangle.setStroke(Color.BLACK);
        circle = new Circle(20, Color.TRANSPARENT);
        this.getChildren().addAll(rectangle, circle);
    }

    public void setColorRectangle(Color color) {
        rectangle.setFill(color);
    }

    public void setColorDisk(Color color) {
        circle.setFill(color);
    }

    public void eraseDisk() {
        circle.setFill(Color.TRANSPARENT);
    }
}
