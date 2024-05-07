package esi.atl.g60552.othello.view;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.Objects;

/**
 * Represents a case of the board.
 */
public class CaseView extends StackPane {
    private final int size;
    private Circle circle;
    private Rectangle rectangle;
    private final Color backgroundColor;

    /**
     * Constructor.
     * @param backgroundColor the background color
     * @param size the size
     */
    public CaseView(Color backgroundColor, int size) {
        super();
        this.backgroundColor = backgroundColor;
        this.size = size;
        init();
    }

    /**
     * Initialize the case.
     */
    void init() {
        rectangle = new Rectangle(size, size, backgroundColor);
        if (backgroundColor.equals(Color.BLACK)) {
            rectangle.setStroke(Color.WHITE);
        } else {
            rectangle.setStroke(Color.BLACK);
        }
        circle = new Circle((int)(size / 2) - 4 , Color.TRANSPARENT);
        this.getChildren().addAll(rectangle, circle);
    }

    /**
     * Set the color of the rectangle.
     * @param color the color
     */
    public void setColorRectangle(Color color) {
        rectangle.setFill(color);
    }

    /**
     * Set the color of the disk.
     * @param color the color
     */
    public void setColorDisk(Color color) {
        circle.setFill(color);
        if (Objects.equals(color, Color.BLACK)) {
            circle.setStroke(Color.WHITE);
        } else {
            circle.setStroke(Color.BLACK);
        }
    }

    /**
     * Erase the disk.
     */
    public void eraseDisk() {
        circle.setFill(Color.TRANSPARENT);
        circle.setStroke(Color.TRANSPARENT);
    }

    /**
     * Get the size.
     * @return the size
     */
    public int getSize() {
        return size;
    }
}
