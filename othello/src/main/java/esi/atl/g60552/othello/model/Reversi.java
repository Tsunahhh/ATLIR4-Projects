package esi.atl.g60552.othello.model;

import esi.atl.g60552.othello.util.Observable;
import esi.atl.g60552.othello.util.Observer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Reversi game. It represents the game with its methods.
 */
public class Reversi implements Observable {
    private Board board;
    private int size;
    private List<Observer> observers = new ArrayList<>();

    /**
     * Constructor.
     * @param size size of the board
     * @param players players of the game
     */
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
     * @param x x position
     * @param y y position
     */
    public void placeDisk(int x, int y) {
        if (!isValidPosition(x, y)) {
            throw new IllegalArgumentException("Reversi: you can't place the disk here !");
        }

        if (board.placeDisk(x, y)) { // notify only if the disk is placed
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
     * Get the color of the disk at the given position.
     * @param x the x position
     * @param y the y position
     * @return the color
     */
    public DiskColor getColor(int x, int y) {
        return board.getColorAt(x, y);
    }

    /**
     * Switch to the next player.
     */
    private void nextPlayer() {
        board.nextPlayer();
    }

    /**
     * Get the winner of the game.
     * @return the winner
     */
    public Player getWinner() {
        return board.getWinner();
    }

    /**
     * Get the current player.
     * @return the current player
     */
    public Player currPlayer() {
        return board.getCurrPlayer();
    }

    /**
     * Verify if the given position is valid.
     * @param x x position
     * @param y y position
     * @return true if valid, false otherwise
     */
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

    /**
     * Check if the given position is empty.
     * @param x the x position
     * @param y the y position
     * @return true if empty, false otherwise
     */
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
