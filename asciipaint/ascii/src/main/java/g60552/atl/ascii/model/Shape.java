package g60552.atl.ascii.model;

public interface Shape {
    boolean isInside(Point p);
    void move(double dx, double dy);
    char getColor();
}