package bmr;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class BMRApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        BMRUI root = new BMRUI(10);
        Scene scene = new Scene(root, Color.GRAY);
        stage.setTitle("Calcule du BMR");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }}
