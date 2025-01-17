package esi.atl.g60552.othello.model;

import esi.atl.g60552.othello.util.CommandManager;
import esi.atl.g60552.othello.util.Observable;
import esi.atl.g60552.othello.util.Observer;
import esi.atl.g60552.othello.util.Strategy;
import javafx.application.Platform;

import java.util.*;


/**
 * Reversi game. It represents the game with its methods.
 */
public class Game {
    private static final int MIN_SIZE = 3;
    private static final int MAX_SIZE = 15;
    private Strategy strategy;
    private int size;
    private Board board;
    private Player currPlayer;
    private List<Player> participants;
    private boolean isPlaying = false;

    /**
     * Constructor.
     * @param size size of the board
     * @param players players of the game
     */
    public Game(int size, int difficulty, Player... players) {

        if (size < MIN_SIZE) {
            throw new IllegalArgumentException("size is too low !");
        } else if (size > MAX_SIZE) {
            throw new IllegalArgumentException("size is too high !");
        } else if (size % 2 == 1) {
            throw new IllegalArgumentException("size should be even !");
        }

        // Set the strategy
        switch (difficulty){
            case 0 -> strategy = new DietStrategy(this);
            case 1 -> strategy = new FirstStrategy(this);
            case 2 -> strategy = new RandomStrategy(this);
            case 3 -> strategy = new GluttonStrategy(this);
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

        for (Player player : participants) {
            if (!getListOfValidMoves(player).isEmpty()) {
                return false;
            }
        }

        return getListOfValidMoves(currPlayer).isEmpty();
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
            isPlaying = true;

            for (Direction direction : Direction.values()) {
                if (isDirectionValid(x, y, direction, currPlayer) != 0) {
                    board.flipDirection(x, y, direction, currPlayer);
                }
            }

            board.placeDisk(x, y, currPlayer.getColor());
        }
    }

    public void pass() {
        nextPlayer();
    }

    /**
     * Get the color of the disk at the given position.
     * @param x the x position
     * @param y the y position
     * @return the color
     */
    public DiskColor getColorAt(int x, int y) {
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
        Map<Position, Integer> validPositions = getListOfValidMoves(currPlayer);
        for (var position : validPositions.keySet()) {
            if (position.getX() == x && position.getY() == y) {
                return true;
            }
        }
        return false;
    }

    /**
     * Verify if the direction is valid.
     * @param x x position
     * @param y y position
     * @param direction the direction
     * @param player the player
     * @return the number of disks that can be flipped
     */
    int isDirectionValid(int x, int y, Direction direction, Player player) {
        int cptPlaceable = 0;
        if (getBoard().isEmpty(x, y)) {
            x += direction.getXDirection();
            y += direction.getYDirection();
            while (getBoard().isInBoard(x, y) && !getBoard().isEmpty(x, y) && getBoard().getColorAt(x, y) != player.getColor()) {
                cptPlaceable++;
                x += direction.getXDirection();
                y += direction.getYDirection();
            }
            if (!getBoard().isInBoard(x, y) || getBoard().isEmpty(x, y)) {
                cptPlaceable = 0;
            }
        }
        return cptPlaceable;
    }

    /**
     * Get a list positions of valid moves
     * @return the list
     */
    public Map<Position, Integer> getListOfValidMoves(Player player) {
        Map<Position, Integer> moves = new HashMap<>();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int cptPlaceable = isPlaceable(j, i, player);
                if (cptPlaceable != 0) {
                    moves.put(new Position(j, i), cptPlaceable);
                }
            }
        }

        return moves;
    }

    /**
     * Verify if the position is a placeable case for the current player
     * @param x x-coords
     * @param y y-coords
     * @return true if placeable of false
     */
    int isPlaceable(int x, int y, Player player) {
        int diskCpt = 0;
        for (Direction currDir : Direction.values()) {
            diskCpt += isDirectionValid(x, y, currDir, player);
        }
        return diskCpt;
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

        if (currPlayer.getColor() == DiskColor.BLACK) {
            winner = (blackDisks > whiteDisks) ? currPlayer : other;
        }  else {
            winner = (whiteDisks > blackDisks) ? currPlayer : other;
        }

        return winner;
    }

    /**
     * Verify if the game is a draw.
     * @return true if draw or false
     */
    public boolean isEquality() {
        return board.getBlackDisks() == board.getWhiteDisks();
    }

    void nextPlayer() {
        Player tmp = participants.remove(0);
        participants.add(currPlayer);
        currPlayer = tmp;
    }

    /**
     * Play the current strategy
     */
    void playStrategy() {
        strategy.playStrategy();
    }

    /**
     * Get the score of the player.
     * @param player the player
     * @return the score
     */
    public int getScore(Player player) {
        if (player.getColor() == DiskColor.BLACK) {
            return board.getBlackDisks();
        } else {
            return board.getWhiteDisks();
        }
    }

    /**
     * Verify if the game is playing.
     * @return true if playing or false
     */
    public boolean isPlaying() {
        return isPlaying;
    }

    /**
     * Get a copy of the board.
     * @return the copy.
     */
    public Board getBoard() {
        return board.getCopy();
    }

    /**
     * Set the board from other board.
     * @param board the board.
     */
    void setBoard(Board board) {
        this.board.setBoard(board);
    }
}
