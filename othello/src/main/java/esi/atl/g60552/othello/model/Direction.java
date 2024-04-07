package esi.atl.g60552.othello.model;

/**
 * Enum for the direction of the disk.
 */
public enum Direction {
    UPLEFT(-1, -1),
    UPRIGHT(1, -1),
    DOWNLEFT(-1, 1),
    DOWNRIGHT(1, 1),
    UP(0, -1),
    DOWN(0, 1),
    LEFT(-1, 0),
    RIGHT(1, 0);

    private int x;
    private int y;

    /**
     * Construct a direction.
     * @param x the x direction
     * @param y the y direction
     */
    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Get the x direction.
     * @return the x direction
     */
    public int getXDirection() {
        return x;
    }

    /**
     * Get the y direction.
     * @return the y direction
     */
    public int getYDirection() {
        return y;
    }
}
