package g60552.atl.acii.model;

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

    @Override
    public String toString() {
        Shape s;
        String res = "";
        for (int i = 0; i < drawing.getHeight(); i++) {
            for (int j = 0; j < drawing.getWidth(); j++) {
                s = drawing.getShapeAt(new Point(j, i));
                res += (s != null) ? s.getColor() : " ";
            }
            res += "\n";
        }
        return res;
    }
}
