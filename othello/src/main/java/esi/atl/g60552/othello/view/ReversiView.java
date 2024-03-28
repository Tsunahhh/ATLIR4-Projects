package esi.atl.g60552.othello.view;

import esi.atl.g60552.othello.model.Color;
import esi.atl.g60552.othello.model.Reversi;

public class ReversiView {
    private Reversi reversi;

    public ReversiView(Reversi reversi, String help) {

    }

    public String asReversi() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < this.reversi.getSize(); i++) {
            for (int j = 0; j < this.reversi.getSize(); j++) {
                if (reversi.getColor(j, i) == Color.WHITE) {
                    res.append(" ⬤ ");
                } else if (reversi.getColor(j, i) == Color.BLACK) {
                    res.append(" ○ ");
                } else {
                    res.append("   ");
                }

            }
        }
        return res.toString();
    }

}
