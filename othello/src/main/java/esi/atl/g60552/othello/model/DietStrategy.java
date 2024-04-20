package esi.atl.g60552.othello.model;

import esi.atl.g60552.othello.util.Strategy;

import java.util.Map;

public class DietStrategy implements Strategy {
    private Reversi reversi;
    DietStrategy(Reversi reversi) {
        this.reversi = reversi;
    }
    @Override
    public void playStrategy() {
        Position result = null;
        int min = Integer.MAX_VALUE;
        Map<Position, Integer> moves = reversi.getListOfValidMoves(reversi.currPlayer());
        for (Map.Entry<Position, Integer> move : moves.entrySet()) {
            Position position = move.getKey();
            int score = move.getValue();
            if (score < min) {
                min = score;
                result = position;
            }
        }
        if (result == null) {
            reversi.pass(); // No position found !
        } else {
            reversi.placeDisk(result.getX(), result.getY());
        }
    }
}
