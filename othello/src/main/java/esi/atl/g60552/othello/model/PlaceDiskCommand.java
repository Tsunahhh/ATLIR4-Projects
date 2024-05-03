package esi.atl.g60552.othello.model;

import esi.atl.g60552.othello.util.Command;

public class PlaceDiskCommand implements Command {
    private Game game;
    private Board save;
    private Player player;
    private int x;
    private int y;

    PlaceDiskCommand(Game game, int x, int y) {
        this.game = game;
        this.x = x;
        this.y = y;
    }

    @Override
    public void execute() {
        if (save == null) {
            player = game.currPlayer();
            save = game.getBoard();
            game.placeDisk(x, y);
            game.nextPlayer();
        } else {
            Board tmp = save.getCopy();
            save = game.getBoard();
            game.setBoard(tmp);
            if (game.currPlayer().getColor() == player.getColor()) {
                game.nextPlayer();
            }
            if (game.currPlayer().isBot()) {
                game.nextPlayer();
            }
        }

    }

    @Override
    public void unexecute() {
        Board tmp = save.getCopy();
        save = game.getBoard();
        game.setBoard(tmp);
        while (game.currPlayer() != player) {
            game.nextPlayer();
        }
    }
}
