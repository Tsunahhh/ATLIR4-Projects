package bmr.view;

import bmr.model.Person;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class BMRUI {

    private BMRInput bmrInput;
    private BMROutput bmrOutput;
    private Button btCalcBmr;
    private Button btClear;

    public BMRUI(Stage primaryStage) {
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        HBox mainOne = new HBox(5);

        bmrInput = new BMRInput();
        bmrOutput = new BMROutput();

        this.genButtons();

        mainOne.getChildren().addAll(bmrInput, bmrOutput);
        root.getChildren().addAll(mainOne, btCalcBmr, btClear);
        Scene scene = new Scene(root, Color.GRAY);
        primaryStage.setTitle("Calcule du BMR");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private void genButtons() {
        btCalcBmr = new Button("Calcul du BMR");
        btCalcBmr.setMaxWidth(Double.MAX_VALUE);
        btCalcBmr.setOnAction(actionEvent -> calcBmr());
        btClear = new Button("Clear");
        btClear.setMaxWidth(Double.MAX_VALUE);
        btClear.setOnAction(actionEvent -> clear());
    }

    private void calcBmr() {
        try {
            Person person = new Person(bmrInput.isWoman(), bmrInput.getBMRHeight(), bmrInput.getBMRWeight(), bmrInput.getBMRAge(), bmrInput.getBMRLife());
            bmrOutput.setBMR(person.bmr());
            bmrOutput.setCal(person.calories());
        } catch (NumberFormatException nb) {
            bmrOutput.failed();
        }
    }

    private void clear() {
        bmrInput.clear();
        bmrOutput.clear();
    }
}
