package bmr.view;

import bmr.model.Person;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import util.Observer;

public class BMRUI implements Observer {
    Person person = new Person();

    private BMRInput bmrInput = new BMRInput();;
    private BMROutput bmrOutput = new BMROutput();
    private Button btCalcBmr;
    private Button btClear;

    public BMRUI(Stage primaryStage) {

        person.register(this);

        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        HBox mainOne = new HBox(5);

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
        btCalcBmr.setOnAction(actionEvent -> calc());

        btClear = new Button("Clear");
        btClear.setMaxWidth(Double.MAX_VALUE);
        btClear.setOnAction(actionEvent -> clear());
    }

    private void calc() {
        try {
            person.set(bmrInput.isWoman(),
                    bmrInput.getBMRHeight(),
                    bmrInput.getBMRWeight(),
                    bmrInput.getBMRAge(),
                    bmrInput.getBMRLife());
        } catch (Exception e) {
            bmrOutput.failed();
        }
    }

    private void clear() {
        bmrInput.clear();
        bmrOutput.clear();
    }

    @Override
    public void update() {
        bmrOutput.setBMR(person.bmr());
        bmrOutput.setCal(person.calories());
    }
}
