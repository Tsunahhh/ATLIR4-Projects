package g60552.atl.acii.model;

public abstract class ColorShape implements Shape  {
    private char color;

    public ColorShape(char color) {
        this.color = color;
    }

    public void setColor(char color) {
        this.color = color;
    }

    @Override
    abstract public boolean isInside(Point p);

    @Override
    abstract public void move(double dx, double dy);

    @Override
    public char getColor() {
        return color;
    }
}
