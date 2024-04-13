package esi.atl.g60552.othello.model;

import esi.atl.g60552.othello.util.Strategy;

import java.util.List;

public class FirstStrategy implements Strategy {
    private Reversi reversi;

    public FirstStrategy(Reversi reversi) {
        this.reversi = reversi;
    }

    @Override
    public void playStrategy() {
        List<Position> positions = reversi.getListOfValidMoves();
        Position selected = positions.get(0);
        reversi.placeDisk(selected.getX(), selected.getY());
    }
}
