package esi.atl.g60552.othello.model;

import esi.atl.g60552.othello.util.Observable;
import esi.atl.g60552.othello.util.Observer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents the board of the game with his methods.
 */
public class Board {

    private Disk[][] board;
    private int size;

    /**
     * Construct the board with size
     * @param size the size
     */
    Board(int size) {
        board = new Disk[size][size];
        this.size = size;
        init();
    }

    /**
     * Initialize the board with disks in the middle.
     */
    private void init() {
        board[size / 2][size / 2] = new Disk(DiskColor.WHITE);
        board[size / 2 - 1][size / 2] = new Disk(DiskColor.BLACK);
        board[size / 2 - 1][size / 2 - 1] = new Disk(DiskColor.WHITE);
        board[size / 2][size / 2 - 1] = new Disk(DiskColor.BLACK);
    }

    /**
     * Get the color of the disk at position
     * @param x x-coords
     * @param y y-coords
     * @return the color
     */
    public DiskColor getColorAt(int x, int y) {
        if (isEmpty(x, y)) {
            throw new IllegalArgumentException("Board: this case is Empty");
        }
        return board[y][x].getColor();
    }

    /**
     * Verify if the position is in the Board
     * @param x x-coords
     * @param y y-coords
     * @return true if in the board or false
     */
    public boolean isInBoard(int x, int y) {
        return x >= 0 && x < size && y >= 0 && y < size;
    }

    /**
     * Flip disks to a direction until the current player disk
     * @param x x-coords
     * @param y y-coords
     * @param direction the direction
     */
    int flipDirection(int x, int y, Direction direction, Player player) {
        x += direction.getXDirection();
        y += direction.getYDirection();
        int cpt = 0;
        while (board[y][x].getColor() != player.getColor()) {
            board[y][x].flip();
            cpt++;
            x += direction.getXDirection();
            y += direction.getYDirection();
        }
        return cpt;
    }

    /**
     * Place the disk
     * @param x x-coords
     * @param y y-coords
     */
    void placeDisk(int x, int y, DiskColor color) {
        board[y][x] = new Disk(color);
    }


    /**
     * Verify if the position is empty
     * @param x x-coords
     * @param y y-coords
     * @return true if empty or false
     */
    public boolean isEmpty(int x, int y) {
        return board[y][x] == null;
    }

    public int getBlackDisks() {
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].getColor() == DiskColor.BLACK) {
                    count++;
                }
            }
        }
        return count;
    }

    public int getWhiteDisks() {
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].getColor() == DiskColor.WHITE) {
                    count++;
                }
            }
        }
        return count;
    }

    public int getSize() {
        return size;
    }
}
