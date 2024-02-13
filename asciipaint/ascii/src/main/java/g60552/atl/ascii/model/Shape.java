package g60552.atl.ascii.model;

/**
 * Interface of Shape with methods
 */
public interface Shape {
    boolean isInside(Point p);
    void move(double dx, double dy);
    char getColor();
}
