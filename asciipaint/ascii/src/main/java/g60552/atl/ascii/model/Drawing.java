package g60552.atl.ascii.model;

import java.util.LinkedList;
import java.util.List;

/**
 * Class represent the drawing with shapes and methods on the draw
 */
public class Drawing {
    private List<Shape> shapes;
    private final int height;
    private final int width;

    /**
     * Create the table with de default size (50x50)
     */
    public Drawing() {
        this(50, 50);
    }

    /**
     * Create the table with the parameters size
     * @param width width
     * @param height width
     */
    public Drawing(int width, int height) {
        shapes = new LinkedList<>();
        this.height = height;
        this.width = width;
    }

    /**
     * Add shape to the list.
     * @param shape Shape
     */
    public void addShape(Shape shape) {
        shapes.add(0, shape);
    }

    /**
     * Get a Shape from a position on the draw
     * @param p Point
     * @return the Shape if found one or null
     */
    public Shape getShapeAt(Point p) {
        for (Shape s: shapes) {
            if(s.isInside(p)) {
                return s;
            }
        }
        return null;
    }

    /**
     * Set color of a specific shape
     * @param idx index
     * @param c color
     */
    public void setColors(int idx, char c) {
        ColorShape s = (ColorShape) this.shapes.get(idx);
        s.setColor(c);
    }

    /**
     * Get a copy of the list of shape
     * @return
     */
    public List<Shape> getShapes() {
        return new LinkedList<>(shapes);
    }

    /**
     * Move the shape on the draw
     * @param idx index of shape
     * @param x position
     * @param y position
     */
    public void move(int idx, int x, int y) {
        ColorShape curr = (ColorShape) shapes.get(idx);
        curr.move(x, y);
    }

    /**
     * Get height
     * @return height of draw
     */
    int getHeight() {
        return height;
    }

    /**
     * Get width
     * @return width of draw
     */
    int getWidth() {
        return width;
    }

}
