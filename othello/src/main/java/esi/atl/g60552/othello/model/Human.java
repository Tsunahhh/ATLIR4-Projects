package esi.atl.g60552.othello.model;

/**
 * Class represent a player with his methods.
 */
public class Human implements Player{

    private String name;
    private DiskColor diskColor;

    /**
     * Construct a player with name and colors of disks.
     * @param name the name of the player
     * @param diskColor the color of disks
     */
    public Human(String name, DiskColor diskColor) {
        if (name == null) {
            throw new IllegalArgumentException("Player: name is null!");
        } else if (name.isEmpty()) {
            throw new IllegalArgumentException("Player: name is empty!");
        }

        this.name = name;
        this.diskColor = diskColor;
    }


    /**
     * Get the color of disks for the player
     * @return the color of disks
     */
    @Override
    public DiskColor getColor() {
        return diskColor;
    }

    /**
     * Get the name of the player
     * @return the name of the player
     */
    @Override
    public String getName() {
        return name;
    }

}