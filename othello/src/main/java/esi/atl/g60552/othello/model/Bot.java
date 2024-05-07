package esi.atl.g60552.othello.model;

/**
 * Class represent a bot player.
 */
public class Bot implements Player {

    private String name;
    private DiskColor diskColor;

    /**
     * Construct a bot player with name and color of disks.
     * @param name the name of the bot
     * @param diskColor the color of disks
     */
    public Bot(String name, DiskColor diskColor) {
        this.name = name;
        this.diskColor = diskColor;
    }

    /**
     * Get the name of the bot.
     * @return the name of the bot
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Get the color of disks for the bot.
     * @return the color of disks
     */
    @Override
    public DiskColor getColor() {
        return diskColor;
    }

    /**
     * Verify if the player is a bot.
     * @return true
     */
    @Override
    public boolean isBot() {
        return true;
    }
}
