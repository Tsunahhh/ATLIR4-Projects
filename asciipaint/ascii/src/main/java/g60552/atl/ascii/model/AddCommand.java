package g60552.atl.ascii.model;

import g60552.atl.ascii.util.Command;

/**
 * Class represent the add command
 */
public class AddCommand implements Command {
    private Drawing drawing;
    private Shape shape;

    /**
     * AddConstructor constructor with 2 arguments
     * @param drawing the drawing
     * @param shape the shape to add.
     */
    public AddCommand(Drawing drawing, Shape shape) {
        this.drawing = drawing;
        this.shape = shape;
    }

    /**
     * Execute the command.
     */
    @Override
    public void execute() {
        drawing.addShape(shape, 0);
    }

    /**
     * Reverse the command.
     */
    @Override
    public void unexecute() {
        drawing.removeShape(shape);
    }
}
