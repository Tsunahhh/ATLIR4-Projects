package g60552.atl.acii.model;

import java.util.ArrayList;
import java.util.List;

public class Drawing {
    private List<Shape> shapes;
    private int height = 50;
    private int width = 50;

    public Drawing() {
        this(50, 50);
    }

    public Drawing(int width, int height) {
        shapes = new ArrayList<Shape>();
        this.height = height;
        this.width = width;
    }

    public void addShape(Shape shape) {
        shapes.add(shape);
    }

    public Shape getShapeAt(Point p) {
        Shape res = null;
        for (Shape s: shapes) {
            if(s.isInside(p)) {
                res = s;
            }
        }
        return res;
    }
    int getHeight() {
        return height;
    }
    int getWidth() {
        return width;
    }
}
