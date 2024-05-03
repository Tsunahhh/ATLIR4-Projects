package esi.atl.g60552.othello.model;

import esi.atl.g60552.othello.util.Command;

public class PassCommand implements Command {
    private Game game;
    private Board save;

    PassCommand(Game game) {
        this.game = game;
    }

    @Override
    public void execute() {
        if (save == null) {
            save = game.getBoard();
            game.pass();
        } else {
            Board tmp = save.getCopy();
            save = game.getBoard();
            game.setBoard(tmp);
        }
    }

    @Override
    public void unexecute() {
        Board tmp = save.getCopy();
        save = game.getBoard();
        game.setBoard(tmp);
    }
}
