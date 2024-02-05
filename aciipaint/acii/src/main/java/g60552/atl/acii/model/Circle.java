package g60552.atl.acii.model;

public class Circle extends ColorShape {
    private Point center;
    private double radius;
    public Circle(Point center, double radius, char color) {
        super(color);
    }

    public boolean isInside(Point p) {
        return center.distanceTo(p) <= radius;
    }

    public void move(double dx, double dy) {
        center.move(dx, dy);
    }
}
