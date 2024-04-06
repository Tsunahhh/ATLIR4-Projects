package esi.atl.g60552.othello.controller;

import esi.atl.g60552.othello.view.AppView;
import javafx.application.Application;
import javafx.stage.Stage;


public class ReversApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        AppView av = new AppView(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
