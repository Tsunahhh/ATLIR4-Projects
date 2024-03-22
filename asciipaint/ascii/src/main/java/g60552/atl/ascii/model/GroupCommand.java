package g60552.atl.ascii.model;

import g60552.atl.ascii.util.Command;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GroupCommand implements Command {
    private Drawing drawing;
    private List<Integer> indexes;

    public GroupCommand(Drawing drawing, List<Integer> indexes) {
        this.drawing = drawing;
        this.indexes = indexes;
        this.indexes.sort(Comparator.naturalOrder());
    }
    /**
     * Execute the group command
     */
    @Override
    public void execute() {
        drawing.group(new ArrayList<>(indexes), -1);
    }

    /**
     * Unexecute the group command
     */
    @Override
    public void unexecute() {
        Group group = (Group) drawing.removeShape(drawing.getListSize() - 1);
        List<ColorShape> shapes = group.ungroup();
        for (int i : indexes) {
            this.drawing.addShape(shapes.remove(shapes.size() - 1), i);
        }
    }
}
