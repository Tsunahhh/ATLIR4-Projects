package esi.atl.g60552.othello.model;

import esi.atl.g60552.othello.util.Command;
import esi.atl.g60552.othello.util.CommandManager;

/**
 * Frontend for the Reversi game.
 */
public class ReversiFront {
    private Reversi reversi;
    private CommandManager commandManager;

    public ReversiFront(Reversi reversi) {
        this.reversi = reversi;
    }
    public DiskColor getColor(int x, int y) {
        return reversi.getColorAt(x, y);
    }
    public Player currPlayer() {
        return reversi.currPlayer();
    }
    public int getScore(Player player) {
        return reversi.getScore(player);
    }
    public boolean isValidPosition(int x, int y) {
        return reversi.isValidPosition(x, y);
    }
    public String getWinnerName() {
        return reversi.getWinner().getName();
    }
    public void placeDisk(int x, int y) {
        Command command = new PlaceDiskCommand(reversi, x, y);
        commandManager.add(command);

    }
    public void pass() {
        Command command = new PassCommand(reversi);
        commandManager.add(command);
    }
    public void undo() {
        commandManager.undo();
    }
    public void redo() {
        commandManager.redo();
    }
}