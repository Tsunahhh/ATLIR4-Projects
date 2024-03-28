package esi.atl.g60552.othello.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class represent the board of the game with his methods.
 */
public class Board {

    private Disk[][] board;
    private int size;
    private Player currPlayer;
    List<Player> participants; // 2 players
    private List<Position> listOfValidMoves;
    int blackDisks = 0;
    int whiteDisks = 0;

    /**
     * Construct the board with size
     * @param size the size
     */
    Board(int size) {
        board = new Disk[size][size];
        this.size = size;
        participants = Player.getListOfPlayers();
        init();
    }

    /**
     * Initialize the board with disks in the middle.
     */
    private void init() {
        board[size / 2][size / 2] = new Disk(Color.BLACK);
        board[size / 2 + 1][size / 2] = new Disk(Color.WHITE);
        board[size / 2 + 1][size / 2 + 1] = new Disk(Color.BLACK);
        board[size / 2][size / 2 + 1] = new Disk(Color.WHITE);
    }

    /**
     * Get the color of the disk at position
     * @param x x-coords
     * @param y y-coords
     * @return the color
     */
    public Color getColorAt(int x, int y) {
        return board[y][x].getColor();
    }

    /**
     * Verify if the position is in the Board
     * @param x
     * @param y
     * @return
     */
    private boolean isInBoard(int x, int y) {
        return x > 0 && x < size && y > 0 && y < size;
    }

    /**
     * Verify if the position is a placeable case for the current player
     * @param x x-coords
     * @param y y-coords
     * @return true if placeable of false
     */
    private boolean isPlaceable(int x, int y) {

        boolean isPlaceable = false;
        for (Direction direction : Direction.values()) {
            x += direction.getXDirection();
            y += direction.getYDirection();
            while (isInBoard(x, y) && board[y][x] != null && board[y][x].getColor() != currPlayer.getColor()) {
                x += direction.getXDirection();
                y += direction.getYDirection();
            }
            if (isInBoard(x, y) && board[y][x] != null) {
                isPlaceable = true;
            }
        }
        return isPlaceable;
    }

    /**
     * Get all the next placements for a player
     * @return list of valid positions
     */
    List<Position> getNextValidMoves() {
        listOfValidMoves = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (isPlaceable(j, i)) {
                    listOfValidMoves.add(new Position(j, i));
                }
            }
        }

        return new ArrayList<>(listOfValidMoves);
    }

    /**
     * Verify if board is full or not
     * @return true if full or false
     */
    private boolean isFull() {
        int i = 0;
        boolean found = false;
        while (i < size && !found) {
            int j = 0;
            while (j < size && !found) {
                if (board[i][j] == null) {
                    found = true;
                }
                j++;
            }
            i++;
        }
        return found;
    }

    /**
     * Verify if the game is over
     * @return
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
        } else if (currPlayer.getColor() == Color.BLACK) {
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
            participants.add(currPlayer);
            participants.remove(0);
        }
    }

    public void placeDisk(int idx) {
        if (idx >= listOfValidMoves.size() || idx < 0) {
            throw new IllegalArgumentException("Board: index out of range for valid moves !");
        }

        Position position = listOfValidMoves.get(idx);
    }


    public Player getCurrPlayer() {
        return currPlayer;
    }
}
