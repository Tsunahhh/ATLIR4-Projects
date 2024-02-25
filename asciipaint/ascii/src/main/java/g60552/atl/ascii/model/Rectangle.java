package g60552.atl.ascii.model;

/**
 * Class represent a rectangle with his methods.
 */
public class Rectangle extends ColorShape {
    private Point upperLeft;
    private double width;
    private double heigth;

    /**
     * Create a rectangle with his settings
     * @param upperLeft reference Point
     * @param width width
     * @param height height
     * @param color color
     */
    public Rectangle(Point upperLeft, double width, double height, char color) {
        super(color);

        if (width < 1 || height < 1) {
            throw new IllegalArgumentException("size of shape is too low");
        }

        this.upperLeft = new Point(upperLeft);
        this.width = width;
        this.heigth = height;
    }

    /**
     * Verify if a Point is in the perimeters.
     * @param p a Point
     * @return true if in the perimeters of false.
     */
    public boolean isInside(Point p) {
        boolean res = false;
        if (p.getX() >= upperLeft.getX() && p.getX() < upperLeft.getX() + width) {
            if (p.getY() >= upperLeft.getY() && p.getY() < upperLeft.getY() + heigth) {
                res = true;
            }
        }
        return res;
    }


    /**
     * Move the reference Point
     * @param dx add on x
     * @param dy add on y
     */
    public void move(double dx, double dy) {
        upperLeft.move(dx, dy);
    }
}
