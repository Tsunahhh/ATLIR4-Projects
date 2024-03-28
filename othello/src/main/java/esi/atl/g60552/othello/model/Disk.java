package esi.atl.g60552.othello.model;

/**
 * Representation of a disk.
 */
public class Disk {
    private Color color;

    /**
     * Construct a disk.
     * @param color the color of the disk.
     */
    Disk(Color color) {
        this.color = color;
    }

    /**
     * Get the color of the disk
     * @return the color.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Flip the disk to the other color (BLACK <=> WHITE)
     */
    public void flip() {
        if (color == Color.BLACK) {
            color = Color.WHITE;
        } else {
            color = Color.BLACK;
        }
    }

    /**
     * Get a disk with the opposite color.
     * @return a disk
     */
    public Disk getOpposite() {
        if (color == Color.BLACK) {
            return new Disk(Color.WHITE);
        } else {
            return new Disk(Color.BLACK);
        }
    }
}
