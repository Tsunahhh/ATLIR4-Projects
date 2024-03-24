package g60552.atl.ascii.model;

import g60552.atl.ascii.util.Command;

/**
 * Class represent the command to move the shape.
 */
public class MoveCommand implements Command {
    private Drawing drawing;
    private int index;
    private int dx;
    private int dy;

    /**
     * Constructor with 4 arguments
     * @param drawing the draw
     * @param index index of the shape
     * @param dx x-coord
     * @param dy y-coord
     */
    public MoveCommand(Drawing drawing, int index, int dx, int dy) {
        this.drawing = drawing;
        this.index = index;
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Execute the command.
     */
    @Override
    public void execute() {
        drawing.move(index, dx, dy);
    }

    /**
     * Reverse the command.
     */
    @Override
    public void unexecute() {
        drawing.move(index, -dx, -dy);
    }
}
