package esi.atl.g60552.othello.model;

import esi.atl.g60552.othello.util.Observable;
import esi.atl.g60552.othello.util.Observer;
import esi.atl.g60552.othello.util.Strategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Reversi game. It represents the game with its methods.
 */
public class Reversi implements Observable {
    private static final int MIN_SIZE = 3;
    private static final int MAX_SIZE = 15;
    private Strategy strategy;
    private int size;
    private Board board;
    private Player currPlayer;
    private List<Player> participants;
    private List<Observer> observers = new ArrayList<>();

    /**
     * Constructor.
     * @param size size of the board
     * @param players players of the game
     */
    public Reversi(int size, int difficulty, Player... players) {
        if (size < MIN_SIZE) {
            throw new IllegalArgumentException("size is too low !");
        } else if (size > MAX_SIZE) {
            throw new IllegalArgumentException("size is too high !");
        } else if (size % 2 == 1) {
            throw new IllegalArgumentException("size should be even !");
        }

        switch (difficulty){
            case 0 -> strategy = new FirstStrategy(this);
            case 1 -> strategy = new RandomStrategy(this);
            case 2 -> strategy = new GluttonStrategy(this);
        }

        this.size = size;
        this.participants = new ArrayList<>(Arrays.asList(players));
        currPlayer = participants.remove(0);
        board = new Board(size);
    }

    /**
     * Verify if the game is over.
     * @return true if over or false.
     */
    public boolean isOver() {
        return getListOfValidMoves().isEmpty();
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

        if (isValidPosition(x, y)) { // notify only if the disk is placed

            for (Direction direction : Direction.values()) {
                if (isDirectionValid(x, y, direction)) {
                    board.flipDirection(x, y, direction, currPlayer);
                }
            }

            board.placeDisk(x, y, currPlayer.getColor());
            nextPlayer();

            notifyObservers();
        }
    }

    public void pass() {
        nextPlayer();
        notifyObservers();
    }


    /**
     * Get the color of the disk at the given position.
     * @param x the x position
     * @param y the y position
     * @return the color
     */
    public DiskColor getColorAt(int x, int y) { // todo getBoard().getColor()
        return board.getColorAt(x, y);
    }

    /**
     * Get the current player.
     * @return the current player
     */
    public Player currPlayer() {
        return currPlayer;
    }

    /**
     * Verify if the given position is valid.
     * @param x x position
     * @param y y position
     * @return true if valid, false otherwise
     */
    public boolean isValidPosition(int x, int y) {
        List<Position> validPositions = getListOfValidMoves();
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

    boolean isDirectionValid(int x, int y, Direction direction) {
        boolean isPlaceable = false;
        if (board.isInBoard(x, y) && board.isEmpty(x, y)) {
            x += direction.getXDirection();
            y += direction.getYDirection();
            // The next one is other color than player
            if (board.isInBoard(x, y) && !board.isEmpty(x, y) && board.getColorAt(x, y) != currPlayer.getColor()) {
                while (board.isInBoard(x, y) && !board.isEmpty(x, y) && !isPlaceable) {
                    if (board.getColorAt(x, y) == currPlayer.getColor()) {
                        isPlaceable = true;
                    }
                    x += direction.getXDirection();
                    y += direction.getYDirection();
                }
            }

        }

        return isPlaceable;
    }

    /**
     * Get a list positions of valid moves
     * @return the list
     */
    public List<Position> getListOfValidMoves() {
        List<Position> listOfValidMoves = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (isPlaceable(j, i) && board.isEmpty(j, i)) {
                    listOfValidMoves.add(new Position(j, i));
                }
            }
        }

        return listOfValidMoves;
    }

    /**
     * Verify if the position is a placeable case for the current player
     * @param x x-coords
     * @param y y-coords
     * @return true if placeable of false
     */
    boolean isPlaceable(int x, int y) {
        return Arrays.stream(Direction.values()).anyMatch(direction -> isDirectionValid(x, y, direction));
    }

    /**
     * Get the winner of the game.
     * @return the winner
     */
    public Player getWinner() {
        Player winner;
        List<Player> listOfPlayers = participants; // 1 other (the other player)
        Player other = listOfPlayers.get(0);

        int whiteDisks = board.getWhiteDisks();
        int blackDisks = board.getBlackDisks();

        if (getListOfValidMoves().isEmpty()) {
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
    void nextPlayer() {
        Player tmp = participants.remove(0);
        participants.add(currPlayer);
        currPlayer = tmp;
        notifyObservers();
        if (currPlayer instanceof Bot) {
            try  {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            strategy.playStrategy();
        }
    }


    public Player getCurrPlayer() {
        return currPlayer;
    }

    public Board getBoard() {
        return board;
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void unregisterObserver(Observer observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
