package g60552.atl.ascii.model;

import g60552.atl.ascii.util.Command;

import java.util.List;
import java.util.Stack;

/**
 * Class represent AsciiPaint with commands methods.
 */
public class AsciiPaint {
    Drawing drawing;

    private CommandManager commandManager = new CommandManager();

    /**
     * Constructor without argument
     */
    public AsciiPaint() {
        drawing = new Drawing();
    }

    /**
     * Constructor with the size of paint table
     * @param width width
     * @param height height
     */
    public AsciiPaint(int width, int height) {
        // todo: verif
        drawing = new Drawing(width, height);
    }

    /**
     * Add a new circle on the paint
     * @param x position
     * @param y position
     * @param radius radius
     * @param color color
     */
    public void newCircle(int x, int y, double radius, char color) {
        // todo: verif
        Command command = new AddCommand(drawing, new Circle(new Point(x, y), radius, color));
        commandManager.add(command);
    }

    /**
     * Add a new rectangle on the paint
     * @param x position
     * @param y position
     * @param width width
     * @param height height
     * @param color color
     */
    public void newRectangle(int x, int y, double width, double height, char color) {
        // todo: verif

        Command command = new AddCommand(drawing, new Rectangle(new Point(x, y), width, height, color));
        commandManager.add(command);
    }

    /**
     * Add a new square on the paint table
     * @param x position
     * @param y position
     * @param side side
     * @param color color
     */
    public void newSquare(int x, int y, double side, char color) {
        // todo: verif
        Command command = new AddCommand(drawing, new Square(new Point(x, y), side, color));
        commandManager.add(command);
    }

    /**
     * Move the shape to another position.
     * @param index index of shape
     * @param x new position x
     * @param y new position y
     */
    public void move(int index, int x, int y) {
        // todo: verif
        Command command = new MoveCommand(drawing, index, x, y);
        commandManager.add(command);
    }

    /**
     * Change the color of a shape.
     * @param index index of shape
     * @param color new color
     */
    public void setColor(int index, char color) {
        // todo: verif
        Command command = new ColorCommand(drawing, index, color);
        commandManager.add(command);
    }


    /**
     * Get the height of the paint drawing.
     * @return height
     */
    public int getHeight() {
        return this.drawing.getHeight();
    }

    /**
     * Get the width of the paint drawing.
     * @return
     */
    public int getWidth() {
        return this.drawing.getWidth();
    }

    /**
     * Get the color from a position on the table drawing.
     * @param x x-position
     * @param y y-position
     * @return the color
     */
    public char getColorPos(int x, int y) {
        // todo: verif
        char c = ' ';
        Shape s = this.drawing.getShapeAt(new Point(x, y));
        if (s != null) {
            c = s.getColor();
        }
        return c;
    }

    /**
     * Get the list of shapes.
     * @return list of shapes
     */
    public List<Shape> getShapesList() {
        return this.drawing.getShapes();
    }

    public void removeShape(int idx) {
        drawing.removeShape(idx);
    }

    public void group(List<Integer> idx) {
        drawing.group(idx);
    }
    public void ungroup(int idx) {
        drawing.ungroup(idx);
    }
    public void redo() {
        commandManager.redo();
    }
    public void undo() {
        commandManager.undo();
    }
}
