package esi.atl.g60552.othello.view;

import esi.atl.g60552.othello.model.DiskColor;
import esi.atl.g60552.othello.model.Human;
import esi.atl.g60552.othello.model.Player;
import esi.atl.g60552.othello.model.Reversi;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AppView {
    private int size;
    private String name;
    private MenuBar menuBar;
    private Reversi reversi;

    public AppView(Stage stage) {
        settings();
        initMenuBar();
        initGame();
        //initStopButton();
        ReversiView rv = new ReversiView(reversi);
        reversi.registerObserver(rv);
        Scene sc = new Scene(rv);
        stage.setScene(sc);
        stage.show();
    }

    private void settings() {
        SettingsView settingsView = new SettingsView();
        this.size = settingsView.getSize();
        this.name = settingsView.getName();
    }

    private void initMenuBar() {
        menuBar = new MenuBar();
    }

    private void initGame() {
        Player p1 = new Human("Guest123", DiskColor.BLACK);
        Player p2 = new Human("Guest456", DiskColor.WHITE);
        reversi = new Reversi(size, p1, p2);
    }
}
