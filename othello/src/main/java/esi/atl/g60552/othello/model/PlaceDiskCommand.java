package esi.atl.g60552.othello.model;

import esi.atl.g60552.othello.util.Command;

/**
 * Command to place a disk.
 */
public class PlaceDiskCommand implements Command {
    private Game game;
    private Board save;
    private Player player;

    /**
     * Constructor.
     * @param game the game
     * @param x the x position
     * @param y the y position
     */
    PlaceDiskCommand(Game game, int x, int y) {
        this.game = game;
        player = game.currPlayer();
        save = game.getBoard();
        game.placeDisk(x, y);
        game.nextPlayer();
    }

    /**
     * Execute the command.
     */
    @Override
    public void execute() {
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

    /**
     * Unexecute the command.
     */
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
