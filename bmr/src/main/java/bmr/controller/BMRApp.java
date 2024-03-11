package bmr.controller;

import bmr.view.BMRUI;
import javafx.application.Application;
import javafx.stage.Stage;

public class BMRApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        BMRUI gui = new BMRUI(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }}
