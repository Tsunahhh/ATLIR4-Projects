package g60552.atl.ascii.model;

import g60552.atl.ascii.util.Command;

public class AddCommand implements Command {
    private Drawing drawing;
    private Shape shape;
    public AddCommand(Drawing drawing, Shape shape) {
        this.drawing = drawing;
        this.shape = shape;
    }

    @Override
    public void execute() {
        this.drawing.addShape(shape);
    }

    @Override
    public void unexecute() {
        this.drawing.removeShape(shape);
    }
}
