package g60552.atl.ascii.model;

import g60552.atl.ascii.util.Command;

/**
 * Class represent the command to delete a shape.
 */
public class DeleteCommand implements Command {
    private Drawing drawing;
    private Shape shape;
    private int index;

    /**
     * Constructor with 2 arguments
     * @param drawing the draw
     * @param index index to delete
     */
    public DeleteCommand(Drawing drawing, int index) {
        this.drawing = drawing;
        this.index = index;
    }

    /**
     * Execute the command.
     */
    @Override
    public void execute() {
        shape = drawing.removeShape(index);
    }

    /**
     * Reverse the command.
     */
    @Override
    public void unexecute() {
        drawing.addShape(shape, index);
    }
}
