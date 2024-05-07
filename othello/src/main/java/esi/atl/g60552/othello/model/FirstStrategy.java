package esi.atl.g60552.othello.model;

import esi.atl.g60552.othello.util.Strategy;

import java.util.Map;

/**
 * First strategy.
 */
public class FirstStrategy implements Strategy {
    private Game game;

    /**
     * Constructor.
     * @param game the game
     */
    public FirstStrategy(Game game) {
        this.game = game;
    }

    /**
     * Play the strategy.
     */
    @Override
    public void playStrategy() {
        Map<Position, Integer> positions = game.getListOfValidMoves(game.currPlayer());
        if (positions.isEmpty()) {
            game.pass();
        } else {
            Position selected = positions.keySet().iterator().next();
            game.placeDisk(selected.getX(), selected.getY());
        }
    }
}
