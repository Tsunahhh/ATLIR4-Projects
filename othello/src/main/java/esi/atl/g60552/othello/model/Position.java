package esi.atl.g60552.othello.model;

/**
 * Class represent a position with methods.
 */
public class Position {
    private int x;
    private int y;

    /**
     * Construct a Position without values on x=0 and y=0.
     */
    Position() {
        this(0, 0);
    }

    /**
     * Construct a Position with his coords
     * @param x x-coords
     * @param y y-coords
     */
    Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Get the x-coord
     * @return get the x coord
     */
    public int getX() {
        return x;
    }

    /**
     * Get the y-coord
     * @return get the y-coord
     */
    public int getY() {
        return y;
    }

    /**
     * Verify if the position is equals to another position
     * @param other the other position
     * @return true if equals, false otherwise
     */
    public boolean isEquals(Position other) {
        return x == other.getX() && y == other.getY();
    }
}
