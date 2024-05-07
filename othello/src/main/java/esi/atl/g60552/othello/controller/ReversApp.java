package esi.atl.g60552.othello.controller;

import esi.atl.g60552.othello.view.AppView;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main class of the application.
 */
public class ReversApp extends Application {

    /**
     * Start the application.
     * @param stage the stage of the application
     * @throws Exception if an error occurs
     */
    @Override
    public void start(Stage stage) throws Exception {
        AppView av = new AppView(stage);
    }

    /**
     * Main method of the application.
     * @param args the arguments of the application
     */
    public static void main(String[] args) {
        launch(args);
    }
}
