package g60552.atl.ascii.model;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Point {
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;

    }
    public Point(Point p) {
        this(p.x, p.y);
    }
    public void move(double dx, double dy) {
        x += dx;
        y += dy;
    }
    double distanceTo(Point other) {
        return sqrt(pow(x-other.x, 2) + pow(y-other.y, 2));
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
