package g60552.atl.ascii.model;

import java.util.Iterator;
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
     * @param height height
     */
    public Drawing(int width, int height) {
        if (width < 1 || height < 1) {
            throw new IllegalArgumentException("width or height can't be null or negative !");
        }

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
     * @param p a point
     * @return the shape if found one or null
     */
    public Shape getShapeAt(Point p) {
        Iterator<Shape> it = shapes.iterator();
        boolean found = false;
        Shape s = null;

        while (it.hasNext() && !found) {
            s = it.next();
            if(s.isInside(p)) {
                found = true;
            }
        }
        return (found) ? s : null;
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
     * @return copy of list
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
