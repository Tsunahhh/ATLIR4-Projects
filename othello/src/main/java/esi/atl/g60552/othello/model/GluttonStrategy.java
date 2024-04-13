package esi.atl.g60552.othello.model;

import esi.atl.g60552.othello.util.Strategy;

public class GluttonStrategy implements Strategy {
    private Reversi reversi;
    GluttonStrategy(Reversi reversi) {
        this.reversi = reversi;
    }
    @Override
    public void playStrategy() {

    }
}
