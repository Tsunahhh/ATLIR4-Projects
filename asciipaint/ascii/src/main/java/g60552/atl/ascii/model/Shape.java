package g60552.atl.ascii.model;

/**
 * Interface of Shape with methods
 */
public interface Shape {

    /**
     * Verify if point is inside of shape.
     * @param p a point
     * @return true if inside or false
     */
    boolean isInside(Point p);

    /**
     * Move the referece point of the shape
     * @param dx coord-x to sum
     * @param dy coord-y to sum
     */
    void move(double dx, double dy);

    /**
     * Get the color of the shape
     * @return the color
     */
    char getColor();
}
