package esi.atl.g60552.othello.model;

import esi.atl.g60552.othello.util.Strategy;

import java.util.List;
import java.util.Map;

public class FirstStrategy implements Strategy {
    private Reversi reversi;

    public FirstStrategy(Reversi reversi) {
        this.reversi = reversi;
    }

    @Override
    public void playStrategy() {
        Map<Position, Integer> positions = reversi.getListOfValidMoves(reversi.currPlayer());
        if (positions.isEmpty()) {
            reversi.pass();
        } else {
            Position selected = positions.keySet().iterator().next();
            reversi.placeDisk(selected.getX(), selected.getY());
        }
    }
}
