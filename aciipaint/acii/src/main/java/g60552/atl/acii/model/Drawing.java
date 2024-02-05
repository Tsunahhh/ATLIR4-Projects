package g60552.atl.acii.model;

import java.util.ArrayList;
import java.util.List;

public class Drawing {
    private List<Shape> shapes;
    private int height;
    private int width;

    public Drawing() {
        shapes = new ArrayList<Shape>();
    }
    public Drawing(int width, int height) {
        //
    }

    public void addShape(Shape shape) {

    }

    public Shape getShapeAt(Point p) {
        return null;
    }
    int getHeight() {
        return height;
    }
    int getWidth() {
        return width;
    }
}
