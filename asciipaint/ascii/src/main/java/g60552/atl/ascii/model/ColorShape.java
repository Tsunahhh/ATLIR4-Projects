package g60552.atl.ascii.model;

public abstract class ColorShape implements Shape  {
    private char color;

    public ColorShape(char color) {
        this.color = color;
    }

    public void setColor(char color) {
        this.color = color;
    }

    @Override
    public char getColor() {
        return color;
    }
}
