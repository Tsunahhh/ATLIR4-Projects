package esi.atl.g60552.othello.model;

import esi.atl.g60552.othello.util.Command;

public class PassCommand implements Command {
    private Reversi reversi;
    private Board lastSave;

    PassCommand(Reversi reversi) {
        this.reversi = reversi;
    }

    @Override
    public void execute() {
        lastSave = reversi.getBoard();
        reversi.pass();
    }

    @Override
    public void unexecute() {
        reversi.setBoard(lastSave);
    }
}
