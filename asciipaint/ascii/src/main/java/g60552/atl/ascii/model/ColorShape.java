package g60552.atl.ascii.model;

/**
 * Represent a shape with a color
 */
public abstract class ColorShape implements Shape  {
    private char color;

    /**
     * Create a ColorShape with a color.
     * @param color color of the shape
     */
    public ColorShape(char color) {
        this.color = color;
    }

    /**
     * Set the color
     * @param color color
     */
    public void setColor(char color) {
        this.color = color;
    }

    /**
     * Get the color
     * @return the color
     */
    @Override
    public char getColor() {
        return color;
    }
}
