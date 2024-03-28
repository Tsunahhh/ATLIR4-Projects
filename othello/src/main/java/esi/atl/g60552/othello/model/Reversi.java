package esi.atl.g60552.othello.model;

/**
 * Class represent the game with his methods.
 */
public class Reversi {
    private Board board;
    private int size;
    public Reversi(int size) {
        if (size < 3) {
            throw new IllegalArgumentException("size is too low !");
        } else if (size > 15) {
            throw new IllegalArgumentException("size is too high !");
        } else if (size % 2 == 1) {
            throw new IllegalArgumentException("size should be even !");
        }
        this.size = size;
        board = new Board(size);
    }

    /**
     * Verify if the game is over.
     * @return true if over or false.
     */
    public boolean isOver() {
        return board.isOver();
    }

    /**
     * Place the disk.
     * @param idx index in the listOfValidMoves
     * @return
     */
    public void placeDisk(int idx) {
        board.placeDisk(idx);
        board.nextPlayer();
    }

    /**
     * Get the size of the board
     * @return the size
     */
    public int getSize() {
        return size;
    }

    /**
     *
     * @param x
     * @param y
     * @return
     */
    public Color getColor(int x, int y) {
        return board.getColorAt(x, y);
    }

}
