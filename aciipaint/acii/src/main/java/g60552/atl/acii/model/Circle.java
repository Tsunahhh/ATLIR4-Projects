package g60552.atl.acii.model;

public class Circle extends ColorShape {
    private Point center;
    private double radius;
    public Circle(Point center, double radius, char color) {
        super(color);
        this.center = new Point(center);
        this.radius = radius;
    }

    public boolean isInside(Point p) {
        return center.distanceTo(p) <= radius;
    }

    public void move(double dx, double dy) {
        center.move(dx, dy);
    }
}
