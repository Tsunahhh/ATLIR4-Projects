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
        List<Position> positions = reversi.getListOfValidMoves();
        Random random = new Random();
        return positions.get(random.nextInt(positions.size()));
    }


    @Override
    public void playStrategy() {
        Position position = randomPos();
        reversi.placeDisk(position.getX(), position.getY());
    }
}
