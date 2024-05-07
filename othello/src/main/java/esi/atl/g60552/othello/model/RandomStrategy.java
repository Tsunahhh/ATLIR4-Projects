package esi.atl.g60552.othello.model;

import esi.atl.g60552.othello.util.Strategy;

import java.util.Map;
import java.util.Random;

/**
 * Random strategy for the game.
 */
public class RandomStrategy implements Strategy {
    private static final int PERCENT_OF_PASS = 5;
    private Game reversi;

    /**
     * Constructor of the random strategy.
     * @param reversi the game.
     */
    public RandomStrategy(Game reversi) {
        this.reversi = reversi;
    }

    /**
     * Get a random position from the list of valid moves.
     * @return a random position.
     */
    private Position randomPos() {
        Map<Position, Integer> positions = reversi.getListOfValidMoves(reversi.currPlayer());
        Random random = new Random();
        Position result = null;
        if (!positions.isEmpty()) {
            int cpt = 0;
            int randInt = random.nextInt(0, positions.size());
            for (Map.Entry<Position, Integer> entry : positions.entrySet()) {
                if (randInt == cpt) {
                    result = entry.getKey();
                }
                cpt++;
            }
        }
        return result;
    }


    /**
     * Play the random strategy.
     */
    @Override
    public void playStrategy() {
        Position position = randomPos();
        Random random = new Random();
        // Chance to pass if there is a pos valid
        if (position != null && random.nextInt(1, 101) > PERCENT_OF_PASS) {
            reversi.placeDisk(position.getX(), position.getY());
        }
    }
}
