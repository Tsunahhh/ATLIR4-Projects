package esi.atl.g60552.othello.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class represent a player with his methods.
 */
public class Player {
    public static int nbOfPlayers = 0;
    private static List<Player> listOfPlayers = new ArrayList<>();
    private String name;
    private Color color;

    /**
     * Construct a player with name and colors of disks.
     * @param name the name of the player
     * @param color the color of disks
     */
    Player(String name, Color color) {
        nbOfPlayers++;

        // Conditions to construct a player.
        if (listOfPlayers.size() >= 2) {
            throw new IllegalArgumentException("Player: max two players in this game!");
        } else if (existAPlayerWithColor(color)) {
            throw new IllegalArgumentException("Player: the color is already taken!");
        } else if (name == null) {
            throw new IllegalArgumentException("Player: name is null!");
        } else if (name.isEmpty()) {
            throw new IllegalArgumentException("Player: name is empty!");
        }

        this.name = name;
        this.color = color;
        listOfPlayers.add(this);
    }

    /**
     * Verify if a player with a color already exist.
     * @param color the color of disks.
     * @return true if exist or false.
     */
    private boolean existAPlayerWithColor(Color color) {
        int i = 0;
        boolean found = false;
        while (i < listOfPlayers.size() && !found) {
            if (listOfPlayers.get(i).getColor() == color) {
                found = true;
            }
            i++;
        }
        return found;
    }

    /**
     * Get the color of disks for the player
     * @return the color of disks
     */
    public Color getColor() {
        return color;
    }

    /**
     * Get the name of the player
     * @return the name of the player
     */
    public String getName() {
        return name;
    }

    public static List<Player> getListOfPlayers() {
        return new ArrayList<>(listOfPlayers);
    }

}
