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
            game.setBoard(save);
        }
    }

    @Override
    public void unexecute() {
        save = game.getBoard();
        game.setBoard(save);
    }
}
