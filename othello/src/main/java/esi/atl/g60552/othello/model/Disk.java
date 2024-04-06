package esi.atl.g60552.othello.model;

/**
 * Representation of a disk.
 */
public class Disk {
    private DiskColor diskColor;

    /**
     * Construct a disk.
     * @param diskColor the color of the disk.
     */
    Disk(DiskColor diskColor) {
        this.diskColor = diskColor;
    }

    /**
     * Get the color of the disk
     * @return the color.
     */
    DiskColor getColor() {
        return diskColor;
    }

    /**
     * Flip the disk to the other color (BLACK <=> WHITE)
     */
    void flip() {
        if (diskColor == DiskColor.BLACK) {
            diskColor = DiskColor.WHITE;
        } else {
            diskColor = DiskColor.BLACK;
        }
    }

    /**
     * Get a disk with the opposite color.
     * @return a disk
     */
     Disk getOpposite() {
        if (diskColor == DiskColor.BLACK) {
            return new Disk(DiskColor.WHITE);
        } else {
            return new Disk(DiskColor.BLACK);
        }
    }
}
