package esi.atl.g60552.othello.model;

import esi.atl.g60552.othello.util.Observable;
import esi.atl.g60552.othello.util.Observer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class represent the game with his methods.
 */
public class Reversi implements Observable {
    private Board board;
    private int size;
    private List<Observer> observers = new ArrayList<>();
    private List<Position> validPosition;
    public Reversi(int size, Player... players) {
        if (size < 3) {
            throw new IllegalArgumentException("size is too low !");
        } else if (size > 15) {
            throw new IllegalArgumentException("size is too high !");
        } else if (size % 2 == 1) {
            throw new IllegalArgumentException("size should be even !");
        }
        this.size = size;
        ArrayList<Player> playersList = new ArrayList<>();
        for (Player player : players) {
            if (player == null) {
                throw new IllegalArgumentException("Reversi: player is null !");
            } else {
                playersList.add(player);
            }
        }
        board = new Board(size, playersList);
        notifyObservers();
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
     * @param x
     * @param y
     * @return
     */
    public void placeDisk(int x, int y) {
        if (!isValidPosition(x, y)) {
            throw new IllegalArgumentException("Reversi: you can't place the disk here !");
        }

        if (board.placeDisk(x, y)) {
            nextPlayer();
            notifyObservers();
        }
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
    public DiskColor getColor(int x, int y) {
        return board.getColorAt(x, y);
    }

    private void nextPlayer() {
        board.nextPlayer();
    }

    public Player getWinner() {
        return board.getWinner();
    }

    public Player currPlayer() {
        return board.getCurrPlayer();
    }

    public boolean isValidPosition(int x, int y) {
        List<Position> validPositions = board.getListOfValidMoves();
        int i = 0;
        boolean found = false;
        while (i < validPositions.size() && !found) {
            Position currPos = validPositions.get(i);
            if (currPos.getX() == x && currPos.getY() == y) {
                found = true;
            }
            i++;
        }
        return found;
    }

    public boolean isEmpty(int x, int y) {
        return board.isEmpty(x, y);
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void unregisterObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
