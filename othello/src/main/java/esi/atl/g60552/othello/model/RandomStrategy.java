package esi.atl.g60552.othello.model;

import esi.atl.g60552.othello.util.Strategy;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class RandomStrategy implements Strategy {
    private Reversi reversi;
    public RandomStrategy(Reversi reversi) {
        this.reversi = reversi;
    }

    private Position randomPos() {
        Map<Position, Integer> positions = reversi.getListOfValidMoves(reversi.currPlayer());
        Random random = new Random();
        Position result = null;
        if (!positions.isEmpty()) {
            int cpt = random.nextInt(positions.size());
            for (Map.Entry<Position, Integer> entry : positions.entrySet()) {
                if (random.nextInt(0, positions.size()) == cpt) {
                    result = entry.getKey();
                }
            }
        }
        return result;
    }


    @Override
    public void playStrategy() {
        Position position = randomPos();
        if (position != null) {
            reversi.placeDisk(position.getX(), position.getY());
        } else {
            reversi.pass();
        }
    }
}
