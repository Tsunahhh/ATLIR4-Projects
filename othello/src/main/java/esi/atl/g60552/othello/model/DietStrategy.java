package esi.atl.g60552.othello.model;

import esi.atl.g60552.othello.util.Strategy;

import java.util.Map;

/**
 * Diet strategy.
 */
public class DietStrategy implements Strategy {
    private Game game;

    /**
     * Constructor.
     * @param game the game
     */
    DietStrategy(Game game) {
        this.game = game;
    }

    /**
     * Play the strategy.
     */
    @Override
    public void playStrategy() {
        Position result = null;
        int min = Integer.MAX_VALUE;
        Map<Position, Integer> moves = game.getListOfValidMoves(game.currPlayer());
        for (Map.Entry<Position, Integer> move : moves.entrySet()) {
            Position position = move.getKey();
            int score = move.getValue();
            if (score < min) {
                min = score;
                result = position;
            }
        }
        if (result == null) {
            game.pass(); // No position found !
        } else {
            game.placeDisk(result.getX(), result.getY());
        }
    }
}
