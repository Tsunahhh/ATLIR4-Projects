package g60552.atl.ascii.model;

import g60552.atl.ascii.util.Command;

/**
 * Class represent the command to change the color of a shape.
 */
public class ColorCommand implements Command {
    private Drawing drawing;
    // TODO: Shape shape
    private int index;
    private char color;
    private char oldColor;

    /**
     * Constructor with 3 arguments
     * @param drawing the draw
     * @param index the index
     * @param color the new color.
     */
    ColorCommand(Drawing drawing, int index, char color) {
        this.index = index;
        this.color = color;
        this.drawing = drawing;
        this.oldColor = drawing.getShape(index).getColor();
    }

    /**
     * Execute the command.
     */
    @Override
    public void execute() {
        // TODO: shape.setColor(color)
        drawing.setColors(index, color);
    }

    /**
     * Reverse the command
     */
    @Override
    public void unexecute() {
        drawing.setColors(index, oldColor);
    }
}
