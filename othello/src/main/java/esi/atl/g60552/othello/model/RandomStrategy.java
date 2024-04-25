package esi.atl.g60552.othello.model;

import esi.atl.g60552.othello.util.Strategy;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class RandomStrategy implements Strategy {
    private static final int PERCENT_OF_PASS = 5;
    private Reversi reversi;
    public RandomStrategy(Reversi reversi) {
        this.reversi = reversi;
    }

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
                    System.out.println("oui");
                }
                cpt++;
            }
        }
        return result;
    }


    @Override
    public void playStrategy() {
        Position position = randomPos();
        Random random = new Random();
        // Chance to pass if there is a pos valid
        if (position != null && random.nextInt(1, 101) > PERCENT_OF_PASS) {
            reversi.placeDisk(position.getX(), position.getY());
        } else {
            reversi.pass();
        }
    }
}
