package g60552.atl.acii.model;

public interface Shape {
    boolean isInside(Point p);
    void move(double dx, double dy);
    char getColor();
}
