package esi.atl.g60552.othello.model;

/**
 * Class represent a player with his methods.
 */
public interface Player {

    /**
     * Get the name of the player.
     * @return the name of the player
     */
    String getName();

    /**
     * Get the color of the player.
     * @return the color of the player
     */
    DiskColor getColor();
    boolean isBot();
}