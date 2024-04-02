package g60552.atl.ascii.model;

import java.util.*;

/**
 * Class represent the drawing with shapes and methods on the draw
 */
public class
Drawing {
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
            throw new IllegalArgumentException("width or height can't be null or negative +++");
        }

        shapes = new LinkedList<>();
        this.height = height;
        this.width = width;
    }

    /**
     * Add shape to the list.
     * @param shape Shape
     */
    protected void addShape(Shape shape, int index) {
        shapes.add(index, shape);
    }

    public Shape getShape(int index) {
        return shapes.get(index);
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
    protected void setColors(int idx, char c) {
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
    protected void move(int idx, int x, int y) { //TODO  pas dans drawing.
        ColorShape curr = (ColorShape) shapes.get(idx);
        curr.move(x, y);
    }

    /**
     * Get height
     * @return height of draw
     */
    public int getHeight() {
        return height;
    }

    /**
     * Get width
     * @return width of draw
     */
    public int getWidth() {
        return width;
    }

    int getListSize() {
        return this.shapes.size();
    }

    /**
     * Remove a shape
     * @param idx index of shape
     * @return shape
     */
    Shape removeShape(int idx) {
        return shapes.remove(idx);
    }

    /**
     * Remove a shape
     * @param s shape to remove
     */
    void removeShape(Shape s) {
        shapes.remove(s);
    }

    /**
     * Group shapes from indexes
     * @param idx list of indexes
     */
    void group(List<Integer> idx, int newIndex) { // 2 4 6 // TODO pas ici.
        List<ColorShape> cs = new ArrayList<>();
        idx.sort(Comparator.reverseOrder()); // 6 4 2

        for (int i : idx) {
            cs.add((ColorShape) shapes.remove(i)); // 6 4 2
        }

        Group group = new Group(cs);
        if (newIndex == -1) {
            shapes.add(group);
        } else {
            shapes.add(newIndex, group);
        }

    }

    /**
     * Ungroup from index
     * @param idx index
     */
    int ungroup(int idx) {
        int cpt = 0;
        try {
            Group group = (Group) shapes.get(idx);
            List<ColorShape> ungrouped = group.ungroup();
            cpt = ungrouped.size();
            shapes.remove(idx);
            shapes.addAll(ungrouped);
        } catch (Exception e) {
            System.out.println("the shape is not a group");
        }
        return cpt;
    }
}
