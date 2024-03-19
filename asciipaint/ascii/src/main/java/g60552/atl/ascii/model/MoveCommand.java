package g60552.atl.ascii.model;

import g60552.atl.ascii.util.Command;

public class MoveCommand implements Command {
    private Drawing drawing;
    private int index;
    private int dx;
    private int dy;
    public MoveCommand(Drawing drawing, int index, int dx, int dy) {
        this.drawing = drawing;
        this.index = index;
        this.dx = dx;
        this.dy = dy;
    }

    @Override
    public void execute() {
        drawing.move(index, dx, dy);
    }

    @Override
    public void unexecute() {
        drawing.move(index, -dx, -dy);
    }
}
