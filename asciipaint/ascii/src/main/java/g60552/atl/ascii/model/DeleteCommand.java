package g60552.atl.ascii.model;

import g60552.atl.ascii.util.Command;

public class DeleteCommand implements Command {
    private Drawing drawing;
    private Shape shape;
    private int index;

    public DeleteCommand(Drawing drawing, int index) {
        this.drawing = drawing;
        this.index = index;
    }
    @Override
    public void execute() {
        shape = drawing.removeShape(index);
    }

    @Override
    public void unexecute() {
        drawing.addShape(shape, index);
    }
}
