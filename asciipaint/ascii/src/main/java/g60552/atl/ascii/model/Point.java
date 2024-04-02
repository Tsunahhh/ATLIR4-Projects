package g60552.atl.ascii.model;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

/**
 * Represents a point with operations
 */
public class Point {
    private double x;
    private double y;

    /**
     * Create a point from positions
     * @param x position
     * @param y position
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;

    }

    /**
     * Create a new point from another point
     * @param p a point
     */
    public Point(Point p) {
        this(p.x, p.y);
    }

    /**
     * Move the point with sum
     * @param dx position x to sum
     * @param dy position y to sum
     */
    public void move(double dx, double dy) {
        x += dx;
        y += dy;
    }

    /**
     * Get the distance from another point
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
