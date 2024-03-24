package g60552.atl.ascii.model;

import java.util.List;

public class Group extends ColorShape{
    private List<ColorShape> shapes;
    Group(List<ColorShape> shapes) {
        super(shapes.get(shapes.size() - 1).getColor());
        this.shapes = shapes;
    }

    @Override
    public boolean isInside(Point p) {
        for (Shape shape: shapes) {
            if (shape.isInside(p)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void move(double dx, double dy) {
        for (Shape shape: shapes) {
            shape.move(dx, dy);
        }
    }

    @Override
    public char getColor() {
        return super.getColor();
    }

    List<ColorShape> ungroup() {
        return shapes;
    }
}
