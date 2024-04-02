package g60552.atl.ascii.model;

import g60552.atl.ascii.util.Command;
import g60552.atl.ascii.util.CommandManager;

import java.util.List;

/**
 * Class represent AsciiPaint with commands methods.
 */
public class AsciiPaint {
    private Drawing drawing; // TODO: PRIVATE !!

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
        if (width < 1 || height < 1) {
            throw new IllegalArgumentException("settings: the size can't be null or negative");
        }
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
        if (radius < 1) {
            throw new IllegalArgumentException("add: the radius of circle can't be null or negative");
        }
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
        if (width < 1 || height < 1) {
            throw new IllegalArgumentException("add: the size of rectangle can't be null or negative");
        }

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
        if (side < 1) {
            throw new IllegalArgumentException("add: the size of square can't be null or negative");
        }
        Command command = new AddCommand(drawing, new Square(new Point(x, y), side, color));
        commandManager.add(command);
    }

    public void newLine(int x1, int y1, int x2, int y2, char color) {
        System.out.println(x1 + " " + y1 + " " + x2 + " " + y2);
        Command command = new AddCommand(drawing, new Line(new Point(x1, y1), new Point(x2, y2), color));
        commandManager.add(command);
    }

    /**
     * Move the shape to another position.
     * @param index index of shape
     * @param x new position x
     * @param y new position y
     */
    public void move(int index, int x, int y) {

        if (drawing.getListSize() <= index) {
            throw new IllegalArgumentException("move: index doesnt exist");
        }

        Command command = new MoveCommand(drawing, index, x, y);
        commandManager.add(command);
    }

    /**
     * Change the color of a shape.
     * @param index index of shape
     * @param color new color
     */
    public void setColor(int index, char color) {
        if (drawing.getListSize() <= index) {
            throw new IllegalArgumentException("color: index doesnt exist");
        }
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
     * Get the color from a position on the draw.
     * @param x x-position
     * @param y y-position
     * @return the color
     */
    public char getColorPos(int x, int y) {
        if (x >= drawing.getWidth() || x < 0) {
            throw new ArrayIndexOutOfBoundsException("getColorPos: x coord out of range");
        } else if (y >= drawing.getHeight() || y < 0) {
            throw new ArrayIndexOutOfBoundsException("getColorPos: y coord ouf of range");
        }
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

    /**
     * Delete a shape from index
     * @param idx index
     */
    public void delShape(int idx) {
        if (drawing.getListSize() <= idx) {
            throw new IllegalArgumentException("delete: index doesnt exist");
        }
        Command command = new DeleteCommand(drawing, idx);
        commandManager.add(command);
    }

    /**
     * Group shapes from indexes.
     * @param idx list of indexes
     */
    public void group(List<Integer> idx) {
        for (int i : idx) {
            if (drawing.getListSize() <= i) {
                throw new IllegalArgumentException("group: index " + i + "doesnt exist");
            }
        }
        Command command = new GroupCommand(drawing, idx);
        commandManager.add(command);
    }

    /**
     * Ungroup shapes.
     * @param idx index of group
     */
    public void ungroup(int idx) {
        if (drawing.getListSize() <= idx) {
            throw new IllegalArgumentException("ungroup: index doesnt exist");
        }
        Command command = new UngroupCommand(drawing, idx);
        commandManager.add(command);
    }

    /**
     * Redo the command.
     */
    public void redo() {
        commandManager.redo();
    }

    /**
     * Undo the command.
     */
    public void undo() {
        commandManager.undo();
    }
}
