package esi.atl.g60552.othello.model;

import esi.atl.g60552.othello.util.Observable;
import esi.atl.g60552.othello.util.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the board of the game with his methods.
 */
public class Board implements Observable {

    private Disk[][] board;
    private int size;
    private Player currPlayer;
    List<Player> participants; // 2 players
    private List<Position> listOfValidMoves;
    int blackDisks = 0;
    int whiteDisks = 0;
    List<Observer> observers = new ArrayList<>();

    /**
     * Construct the board with size
     * @param size the size
     */
    Board(int size, List<Player> players) {
        board = new Disk[size][size];
        this.size = size;
        participants = players;
        listOfValidMoves = new ArrayList<>();
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
        nextPlayer();
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
    private boolean isInBoard(int x, int y) {
        return x > 0 && x < size && y > 0 && y < size;
    }

    /**
     * Verify if the direction can be flipped by the player
     * @param x x-coords
     * @param y y-coords
     * @param direction the direction
     * @return true if the direction can be flipped or false
     */
    private boolean isDirectionValid(int x, int y, Direction direction) {
        boolean isPlaceable = false;

        x += direction.getXDirection();
        y += direction.getYDirection();
        if (!isInBoard(x, y) || board[y][x] == null || board[y][x].getColor() == currPlayer.getColor()) {
            return false;
        }

        while (isInBoard(x, y) && board[y][x] != null && board[y][x].getColor() != currPlayer.getColor()) {
            x += direction.getXDirection();
            y += direction.getYDirection();
        }

        if (isInBoard(x, y) && board[y][x] != null) {
            isPlaceable = true;
        }
        return isPlaceable;
    }

    /**
     * Verify if the position is a placeable case for the current player
     * @param x x-coords
     * @param y y-coords
     * @return true if placeable of false
     */
    private boolean isPlaceable(int x, int y) {
        for (Direction direction : Direction.values()) {
            if (isDirectionValid(x, y, direction)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get all the next placements for a player
     */
    private void nextValidMoves() {
        listOfValidMoves = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (isPlaceable(j, i) && board[i][j] == null) {
                    listOfValidMoves.add(new Position(j, i));
                }
            }
        }

    }

    /**
     * Verify if board is full or not
     * @return true if full or false
     */
    private boolean isFull() {
        int i = 0;
        boolean isFull = true;
        while (i < size && isFull) {
            int j = 0;
            while (j < size && isFull) {
                if (board[i][j] == null) {
                    isFull = false;
                }
                j++;
            }
            i++;
        }
        return isFull;
    }

    /**
     * Verify if the game is over.
     * @return true if over or false.
     */
    public boolean isOver() {
        return isFull() || listOfValidMoves.isEmpty();
    }

    /**
     * Get the winner of the game.
     * @return the winner
     */
    public Player getWinner() {
        Player winner;
        List<Player> listOfPlayers = participants; // 1 other (the other player)
        Player other = listOfPlayers.get(0);

        if (listOfValidMoves.isEmpty()) {
            winner = other;
        } else if (currPlayer.getColor() == DiskColor.BLACK) {
            winner = (blackDisks > whiteDisks) ? currPlayer : other;
        }  else {
            winner = (whiteDisks > blackDisks) ? currPlayer : other;
        }

        return winner;
    }

    /**
     * Change the current player to the next player.
     */
    public void nextPlayer() {
        if (currPlayer == null) {
            currPlayer = participants.remove(0);
        } else {
            Player tmp = participants.remove(0);
            participants.add(currPlayer);
            currPlayer = tmp;
        }
        nextValidMoves();
        notifyObservers();
    }

    public Player getCurrPlayer() {
        return currPlayer;
    }

    /**
     * Flip disks to a direction until the current player disk
     * @param x x-coords
     * @param y y-coords
     * @param direction the direction
     */
    private void flipDirection(int x, int y, Direction direction) {
        x += direction.getXDirection();
        y += direction.getYDirection();
        while (board[y][x].getColor() != currPlayer.getColor()) {
            board[y][x].flip();
            x += direction.getXDirection();
            y += direction.getYDirection();
        }
    }

    /**
     * Place the disk
     * @param x x-coords
     * @param y y-coords
     */
    public boolean placeDisk(int x, int y) {
        boolean res = false;
        for (Direction direction : Direction.values()) {
            if (isDirectionValid(x, y, direction)) {
                board[y][x] = new Disk(currPlayer.getColor());
                flipDirection(x, y, direction);
                res = true;
            }
        }
        notifyObservers();
        return res;
    }

    /**
     * Get a list positions of valid moves
     * @return the list
     */
    public List<Position> getListOfValidMoves() {
        return listOfValidMoves;
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
