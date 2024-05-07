package esi.atl.g60552.othello.model;

import esi.atl.g60552.othello.util.Command;
import esi.atl.g60552.othello.util.CommandManager;
import esi.atl.g60552.othello.util.Observable;
import esi.atl.g60552.othello.util.Observer;
import javafx.application.Platform;

import java.util.ArrayList;
import java.util.List;

/**
 * Reversi game. It represents the front of the game with its methods.
 */
public class Reversi implements Observable {
    private Game game;
    private List<Observer> observers = new ArrayList<>();
    private CommandManager commandManager;

    /**
     * Constructor of the game.
     */
    public Reversi() {
        this.commandManager = new CommandManager();
    }

    /**
     * Get the current player.
     * @return the current player.
     */
    public Player currPlayer() {
        return game.currPlayer();
    }

    /**
     * Get the score of a player.
     * @param player the player.
     * @return the score of the player.
     */
    public int getScore(Player player) {
        return game.getScore(player);
    }

    /**
     * Verify if the position is valid for the current player.
     * @param x the x position.
     * @param y the y position.
     * @return true if the position is valid or false.
     */
    public boolean isValidPosition(int x, int y) {
        return game.isValidPosition(x, y);
    }

    /**
     * Get the winner of the game.
     * @return the winner of the game.
     */
    public Player getWinner() {
        return game.getWinner();
    }

    /**
     * Place a disk on the board.
     * @param x the x position.
     * @param y the y position.
     */
    public void placeDisk(int x, int y) {
        Command command = new PlaceDiskCommand(game, x, y);
        commandManager.add(command);
        notifyObservers();
        nextPlayer();
        notifyObservers();
    }

    /**
     * Change the player and play the strategy while the current player is a bot.
     */
    private void nextPlayer() {
        if (currPlayer().isBot()) {
            new Thread(() -> {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Platform.runLater(() -> {
                    game.playStrategy();
                    game.nextPlayer();
                    notifyObservers();
                });
            }).start();
        }
    }


    /**
     * Pass the turn to the next player.
     */
    public void pass() {
        Command command = new PassCommand(game);
        commandManager.add(command);
        nextPlayer();
        notifyObservers();
    }

    /**
     * Undo the last command.
     */
    public void undo() {
        commandManager.undo();
        notifyObservers();
    }

    /**
     * Redo the last command.
     */
    public void redo() {
        commandManager.redo();
        notifyObservers();
    }

    /**
     * Get a copy of board from the game.
     * @return copy of board.
     */
    public Board getBoard() {
        return game.getBoard();
    }


    /**
     * Initialize the game with the size, difficulty, player1, player2 and if player2 is a bot.
     * @param size the size of the board.
     * @param difficulty the difficulty of the bot.
     * @param player1 the name of the player1.
     * @param player2 the name of the player2.
     * @param isPlayer2Bot if the player2 is a bot.
     */
    public void initGame(int size, int difficulty, String player1, String player2, boolean isPlayer2Bot) {
        Player p1 = new Human(player1, DiskColor.BLACK);
        Player p2;

        if (isPlayer2Bot) {
            p2 = new Bot(player2, DiskColor.WHITE);
        } else {
            p2 = new Human(player2, DiskColor.WHITE);
        }

        game = new Game(size, difficulty, p1, p2);
    }

    /**
     * Register an observer.
     * @param observer the observer.
     */
    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    /**
     * Unregister an observer.
     * @param observer the observer.
     */
    @Override
    public void unregisterObserver(Observer observer) {
        observers.remove(observer);
    }

    /**
     * Notify the observers.
     */
    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    /**
     * Know if the game is playing.
     * @return true if the game is playing or false.
     */
    public boolean isPlaying() {
        return game.isPlaying();
    }

    /**
     * Know if the game is over.
     * @return true if the game is over or false.
     */
    public boolean isOver() {
        return game.isOver();
    }

    /**
     * Know if the undo is possible.
     * @return true if the undo is possible or false.
     */
    public boolean isRedoable() {
        return commandManager.isRedoable();
    }

    /**
     * Know if the redo is possible.
     * @return true if the redo is possible or false.
     */
    public boolean isUndoable() {
        return commandManager.isUndoable();
    }
}