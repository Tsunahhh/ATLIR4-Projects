package g60552.atl.ascii.model;

import g60552.atl.ascii.util.Command;

public class UngroupCommand implements Command {
    private Drawing drawing;
    private int index;
    private int nbShapes = 0;
    private Group group = null;

    public UngroupCommand(Drawing drawing, int index) {
        this.drawing = drawing;
        this.index = index;

    }

    /**
     *
     */
    @Override
    public void execute() {
        nbShapes = drawing.ungroup(index);
        group = (Group) drawing.getShape(index);
    }

    /**
     *
     */
    @Override
    public void unexecute() {
        drawing.addShape(group, index);
        int size = drawing.getListSize();
        for (int i = size - 1; i >= size - nbShapes; i--) {
            drawing.removeShape(i);
        }
    }
}
