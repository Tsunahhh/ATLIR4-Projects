package g60552.atl.acii.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Drawing {
    private List<Shape> shapes;
    private final int height;
    private final int width;

    public Drawing() {
        this(50, 50);
    }

    public Drawing(int width, int height) {
        shapes = new LinkedList<>();
        this.height = height;
        this.width = width;
    }

    public void addShape(Shape shape) {
        shapes.add(0, shape);
    }

    public Shape getShapeAt(Point p) {
        for (Shape s: shapes) {
            if(s.isInside(p)) {
                return s;
            }
        }
        return null;
    }
    int getHeight() {
        return height;
    }
    int getWidth() {
        return width;
    }
}
