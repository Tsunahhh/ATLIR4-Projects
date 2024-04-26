package esi.atl.g60552.othello.model;

import esi.atl.g60552.othello.util.Command;

public class PassCommand implements Command {
    private Game game;
    private Board lastSave;

    PassCommand(Game game) {
        this.game = game;
    }

    @Override
    public void execute() {
        lastSave = game.getBoard();
    }

    @Override
    public void unexecute() {
        game.setBoard(lastSave);
    }
}
