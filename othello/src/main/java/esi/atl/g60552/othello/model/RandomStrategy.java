package esi.atl.g60552.othello.model;

import esi.atl.g60552.othello.util.Strategy;

import java.util.List;
import java.util.Random;

public class RandomStrategy implements Strategy {
    private Reversi reversi;
    public RandomStrategy(Reversi reversi) {
        this.reversi = reversi;
    }

    private Position randomPos() {
        List<Position> positions = reversi.getListOfValidMoves(reversi.currPlayer());
        Random random = new Random();
        Position result = null;
        if (!positions.isEmpty()) {
            result = positions.get(random.nextInt(positions.size()));
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
