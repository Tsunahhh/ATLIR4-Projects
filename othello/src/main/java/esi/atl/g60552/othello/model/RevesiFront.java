package esi.atl.g60552.othello.model;

public class RevesiFront {
    private Reversi reversi;

    public RevesiFront(Reversi reversi) {
        this.reversi = reversi;

    }

    public int getSize() {
        return reversi.getSize();
    }

    public DiskColor getColor(int x, int y) {
        return reversi.getColor(x, y);
    }

    public DiskColor currPlayerColor() {
        return reversi.currPlayer().getColor();
    }

    public String currPlayerName() {
        return reversi.currPlayer().getName();
    }

    public boolean isEmpty(int x, int y) {
        return reversi.isEmpty(x, y);
    }

    public boolean isValidPosition(int x, int y) {
        return reversi.isValidPosition(x, y);
    }

    public String getWinnerName() {
        return reversi.getWinner().getName();
    }
}