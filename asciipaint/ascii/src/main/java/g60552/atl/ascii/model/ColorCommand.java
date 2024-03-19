package g60552.atl.ascii.model;

import g60552.atl.ascii.util.Command;

public class ColorCommand implements Command {
    private Drawing drawing;
    private int index;
    private char color;
    private char oldColor;
    ColorCommand(Drawing drawing, int index, char color) {
        this.index = index;
        this.color = color;
        this.drawing = drawing;
        this.oldColor = drawing.getShapes().get(index).getColor();
    }
    @Override
    public void execute() {
        drawing.setColors(index, color);
    }

    @Override
    public void unexecute() {
        drawing.setColors(index, oldColor);
    }
}
