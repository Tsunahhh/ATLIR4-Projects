package esi.atl.g60552.othello.model;

import esi.atl.g60552.othello.util.Command;

/**
 * Command to pass the turn.
 */
public class PassCommand implements Command {
    private Game game;
    private Board save;
    private Player player;

    /**
     * Constructor.
     * @param game the game
     */
    PassCommand(Game game) {
        this.game = game;
        player = game.currPlayer();
        save = game.getBoard();
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

        // If the current player is the same as the player who passed, we change the current player
        if (game.currPlayer().getColor() == player.getColor()) {
            game.nextPlayer();
        }

        // If the current player is a bot, we change the current human player.
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
