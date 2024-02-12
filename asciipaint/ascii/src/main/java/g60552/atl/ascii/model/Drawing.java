package g60552.atl.ascii.model;

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

    public void setColors(int idx, char c) {
        ColorShape s = (ColorShape) this.shapes.get(idx);
        s.setColor(c);
    }

    public List<Shape> getShapes() {
        return new LinkedList<>(shapes);
    }

    public void move(int idx, int x, int y) {
        ColorShape curr = (ColorShape) shapes.get(idx);
        curr.move(x, y);
    }
    int getHeight() {
        return height;
    }
    int getWidth() {
        return width;
    }

}
