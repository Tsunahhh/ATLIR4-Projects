package esi.atl.g60552.othello.model;

/**
 * Frontend for the Reversi game.
 */
public class ReversiFront {
    private Reversi reversi;

    /**
     * Constructor.
     * @param reversi the game to represent
     */
    public ReversiFront(Reversi reversi) {
        this.reversi = reversi;
    }

    /**
     * Get the size of the board.
     * @return the size
     */

    /**
     * Get the color of the disk at the given position.
     * @param x the x position
     * @param y the y position
     * @return the color
     */
    public DiskColor getColor(int x, int y) {
        return reversi.getColorAt(x, y);
    }

    /**
     * Get the current player color.
     * @return the color
     */
    public DiskColor currPlayerColor() {
        return reversi.currPlayer().getColor();
    }

    /**
     * Get the current player name.
     * @return the name
     */
    public String currPlayerName() {
        return reversi.currPlayer().getName();
    }

    /**
     * Check if the given position is empty.
     * @param x the x position
     * @param y the y position
     * @return true if empty, false otherwise
     */


    /**
     * Check if the given position is valid.
     * @param x the x position
     * @param y the y position
     * @return true if valid, false otherwise
     */
    public boolean isValidPosition(int x, int y) {
        return reversi.isValidPosition(x, y);
    }

    /**
     * Get the winner name.
     * @return the name of the winner.
     */
    public String getWinnerName() {
        return reversi.getWinner().getName();
    }
}