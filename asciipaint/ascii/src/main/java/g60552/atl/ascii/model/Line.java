package g60552.atl.ascii.model;

public class Line extends ColorShape {
    private Point p1;
    private Point p2;
    private double coeff;
    private double dist;

    /**
     * Create a ColorShape with a color.
     *
     * @param color color of the shape
     */
    public Line(Point p1, Point p2, char color) {
        super(color);
        this.p1 = p1;
        this.p2 = p2;
        coeff = ((p2.getX() - p1.getX()) / (p2.getY() - p1.getY()));
        dist = p2.distanceTo(p1);
    }

    /**
     * @param p a point
     * @return
     */
    @Override
    public boolean isInside(Point p) {
        double distTop1 = p.distanceTo(p1);
        double distTop2 = p.distanceTo(p2);
        double m = ((p.getX() - p1.getX()) / (p.getY() - p1.getY()));
        return coeff == m && distTop1 <= dist && distTop2 <= dist;
    }

    /**
     * @param dx coord-x to sum
     * @param dy coord-y to sum
     */
    @Override
    public void move(double dx, double dy) {
        p1.move(dx, dy);
        p2.move(dx, dy);
    }
}
