package esi.atl.g60552.othello.model;

/**
 * Enum for the color of the disk.
 */
public enum DiskColor {
    BLACK,
    WHITE;

    /**
     * Get the opposite color of the disk.
     * @return the opposite color
     */
    DiskColor getOppositeColor() {
        return (this == BLACK) ? WHITE : BLACK;
    }
}
