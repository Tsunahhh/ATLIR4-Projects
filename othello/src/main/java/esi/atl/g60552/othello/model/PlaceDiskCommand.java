package esi.atl.g60552.othello.model;

import esi.atl.g60552.othello.util.Command;

public class PlaceDiskCommand implements Command {
    private Reversi reversi;
    private Board lastSave;
    private int x;
    private int y;

    PlaceDiskCommand(Reversi reversi, int x, int y) {
        this.reversi = reversi;
        this.x = x;
        this.y = y;
    }


    @Override
    public void execute() {
        lastSave = reversi.getBoard();
        reversi.placeDisk(x, y);
    }

    @Override
    public void unexecute() {
        reversi.setBoard(lastSave);
    }
}
