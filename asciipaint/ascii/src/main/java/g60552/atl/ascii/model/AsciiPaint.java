package g60552.atl.ascii.model;

import java.util.List;

public class AsciiPaint {
    Drawing drawing;
    public AsciiPaint() {
        drawing = new Drawing();
    }
    public AsciiPaint(int width, int height) {
        drawing = new Drawing(width, height);

    }
    public void newCircle(int x, int y, double radius, char color) {
        drawing.addShape(new Circle(new Point(x, y), radius, color));
    }
    public void newRectangle(int x, int y, double width, double height, char color) {
        drawing.addShape(new Rectangle(new Point(x, y), width, height, color));
    }
    public void newSquare(int x, int y, double side, char color) {
        drawing.addShape(new Square(new Point(x, y), side, color));
    }

    public void move(int index, int x, int y) {
        this.drawing.move(index, x, y);
    }
    public void setColor(int index, char color) {
        this.drawing.setColors(index, color);
    }

    public String asAscii() {
        Shape s;
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < drawing.getHeight(); i++) {
            for (int j = 0; j < drawing.getWidth(); j++) {
                s = drawing.getShapeAt(new Point(j, i));
                res.append((s != null) ? s.getColor() : " ");
            }
            res.append("\n");
        }
        return res.toString();
    }

    public String asList() {
        StringBuilder res = new StringBuilder();
        List<Shape> l = this.drawing.getShapes();
        for (int i = 0; i < l.size(); i++) {
            ColorShape cs = (ColorShape) l.get(i);
            res.append(i).append(" - ").append(cs.getColor()).append("\n");
        }
        return res.toString();
    }
}
