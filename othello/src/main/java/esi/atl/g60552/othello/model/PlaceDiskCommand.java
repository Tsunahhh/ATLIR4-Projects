package esi.atl.g60552.othello.model;

import esi.atl.g60552.othello.util.Command;

public class PlaceDiskCommand implements Command {
    private Game game;
    private Board save;
    private int x;
    private int y;

    PlaceDiskCommand(Game game, int x, int y) {
        this.game = game;
        this.x = x;
        this.y = y;
    }

    @Override
    public void execute() {
        save = game.getBoard();
        game.placeDisk(x, y);
    }

    @Override
    public void unexecute() {
        game.setBoard(save);
    }
}
