package g60552.atl.ascii.model;

import g60552.atl.ascii.util.Command;

/**
 * Class represent the command to ungroup shapes.
 */
public class UngroupCommand implements Command {
    private Drawing drawing;
    private int index;
    private int nbShapes = 0;
    private Group group = null;

    /**
     * Constructor with 2 parameters
     * @param drawing the draw
     * @param index index of the group
     */
    public UngroupCommand(Drawing drawing, int index) {
        this.drawing = drawing;
        this.index = index;
    }

    /**
     * execute the command
     */
    @Override
    public void execute() {
        nbShapes = drawing.ungroup(index); // la logique ici pas dans drawing TODO.
        group = (Group) drawing.getShape(index);
    }

    /**
     * Reverse the command
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
