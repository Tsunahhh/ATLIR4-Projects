package bmr;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class BMRUI extends VBox{

    private BMRInput bmrInput;
    private BMROutput bmrOutput;
    private Button btCalcBmr;
    private Button btClear;

    BMRUI(int spacing) {
        super(spacing);
        super.setPadding(new Insets(10));
        HBox mainOne = new HBox(5);

        bmrInput = new BMRInput();
        bmrOutput = new BMROutput();

        this.genButtons();

        mainOne.getChildren().addAll(bmrInput, bmrOutput);
        super.getChildren().addAll(mainOne, btCalcBmr, btClear);

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
            BMRCalc calc = new BMRCalc(bmrInput.getBMRSexe(), bmrInput.getBMRHeight(), bmrInput.getBMRWeight(), bmrInput.getBMRAge());
            double cal = -1;
            switch (bmrInput.getBMRLife()) {
                case "sedentaire" -> cal = calc.cal(Activity.NEVER);
                case "peu" -> cal = calc.cal(Activity.LOW);
                case "moyen" -> cal = calc.cal(Activity.MID);
                case "beaucoup" -> cal = calc.cal((Activity.HIGH));
                case "extreme" -> cal = calc.cal(Activity.EXTREME);
            }
            bmrOutput.setBMR(calc.bmr());
            bmrOutput.setCal(cal);
        } catch (NumberFormatException nb) {
            bmrOutput.failed();
        }
    }

    private void clear() {
        bmrInput.clear();
        bmrOutput.clear();
    }
}
