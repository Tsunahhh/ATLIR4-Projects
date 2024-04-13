package esi.atl.g60552.othello.model;

/**
 * Class represent a bot player.
 */
public class Bot implements Player {

    private String name;
    private DiskColor diskColor;

    public Bot(String name, DiskColor diskColor) {
        this.name = name;
        this.diskColor = diskColor;
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public DiskColor getColor() {
        return diskColor;
    }
}
