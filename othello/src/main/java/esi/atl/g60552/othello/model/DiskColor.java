package esi.atl.g60552.othello.model;

/**
 * Enum for the color of the disk.
 */
public enum DiskColor {
    BLACK,
    WHITE;

    DiskColor() {}
    DiskColor getOppositeColor() {
        return (this == BLACK) ? WHITE : BLACK;
    }
}
