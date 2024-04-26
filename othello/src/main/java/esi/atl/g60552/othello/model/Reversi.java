package esi.atl.g60552.othello.model;

import esi.atl.g60552.othello.util.Command;
import esi.atl.g60552.othello.util.CommandManager;
import esi.atl.g60552.othello.util.Observable;
import esi.atl.g60552.othello.util.Observer;
import javafx.application.Platform;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Reversi implements Observable {
    private Game game;
    private List<Observer> observers = new ArrayList<>();
    private CommandManager commandManager;

    public Reversi() {
        this.commandManager = new CommandManager();
    }

    public Player currPlayer() {
        return game.currPlayer();
    }

    public int getScore(Player player) {
        return game.getScore(player);
    }

    public boolean isValidPosition(int x, int y) {
        return game.isValidPosition(x, y);
    }

    public Player getWinner() {
        return game.getWinner();
    }


    public void placeDisk(int x, int y) {
        Command command = new PlaceDiskCommand(game, x, y);
        commandManager.add(command);
        game.nextPlayer();
        notifyObservers();
        nextPlayer();
        notifyObservers();
    }

    public void nextPlayer() {
        if (currPlayer().isBot()) {
            new Thread(() -> {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Platform.runLater(() -> {
                    System.out.println(currPlayer() + "played strat !");
                    game.playStrategy();
                    System.out.println(currPlayer() + "next player");
                    game.nextPlayer();
                    notifyObservers();
                    System.out.println(currPlayer() + "turn !");
                });
            }).start();
        }
    }


    public void pass() {
        Command command = new PassCommand(game);
        commandManager.add(command);
        notifyObservers();
    }

    public void undo() {
        commandManager.undo();
        notifyObservers();
    }

    public void redo() {
        commandManager.redo();
        notifyObservers();
    }

    public Board getBoard() {
        return game.getBoard();
    }


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

    public boolean isPlaying() {
        return game.isPlaying();
    }

    public boolean isOver() {
        return game.isOver();
    }
}