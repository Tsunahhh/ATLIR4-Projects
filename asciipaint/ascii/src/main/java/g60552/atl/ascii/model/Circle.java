package g60552.atl.ascii.model;

/**
 * Class represent a Circle with color and methods.
 */
public class Circle extends ColorShape {
    private Point center;
    private double radius;

    /**
     * Create a circle
     * @param center center Point
     * @param radius radius
     * @param color color
     */
    public Circle(Point center, double radius, char color) {
        super(color);
        this.center = new Point(center);
        this.radius = radius;
    }

    /**
     * Verify if a Point is in the radius of the circle.
     * @param p the Point
     * @return true if in the radius or false
     */
    public boolean isInside(Point p) {
        return center.distanceTo(p) <= radius;
    }

    /**
     * Move the center of circle
     * @param dx add x position
     * @param dy add y position
     */
    public void move(double dx, double dy) {
        center.move(dx, dy);
    }
}
