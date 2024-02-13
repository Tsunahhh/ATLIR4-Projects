package g60552.atl.ascii.model;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

/**
 * Class represent a Point with operations
 */
public class Point {
    private double x;
    private double y;

    /**
     * Create a Point from positions
     * @param x position
     * @param y position
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;

    }

    /**
     * Create a new Point from another Point
     * @param p a Point
     */
    public Point(Point p) {
        this(p.x, p.y);
    }

    /**
     * Move the Point with sum
     * @param dx position x to sum
     * @param dy position y to sum
     */
    public void move(double dx, double dy) {
        x += dx;
        y += dy;
    }

    /**
     * Get the distance from another Point
     * @param other other Point
     * @return the distance
     */
    double distanceTo(Point other) {
        return sqrt(pow(x-other.x, 2) + pow(y-other.y, 2));
    }

    /**
     * Getter x
     * @return position x
     */
    public double getX() {
        return x;
    }

    /**
     * Getter y
     * @return position y
     */
    public double getY() {
        return y;
    }
}
