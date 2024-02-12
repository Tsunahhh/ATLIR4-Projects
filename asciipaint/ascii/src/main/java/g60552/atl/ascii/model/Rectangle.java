package g60552.atl.ascii.model;

public class Rectangle extends ColorShape {
    private Point upperLeft;
    private double width;
    private double heigth;
    public Rectangle(Point upperLeft, double width, double height, char color) {
        super(color);
        this.upperLeft = new Point(upperLeft);
        this.width = width;
        this.heigth = height;
    }

    public boolean isInside(Point p) {
        boolean res = false;
        if (p.getX() >= upperLeft.getX() && p.getX() < upperLeft.getX() + width) {
            if (p.getY() >= upperLeft.getY() && p.getY() < upperLeft.getY() + heigth) {
                res = true;
            }
        }
        return res;
    }

    public void move(double dx, double dy) {
        upperLeft.move(dx, dy);
    }
}
