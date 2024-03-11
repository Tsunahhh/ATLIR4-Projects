package bmr;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class BMROutput extends GridPane {
    private TextField tfdBmr = new TextField();
    private TextField tfdCal = new TextField();

    BMROutput() {
        super();
        this.setPadding(new Insets(10));
        this.setHgap(15);
        this.setVgap(6);
        this.genLabels();
        this.genOutputs();
    }

    private void genLabels() {
        Label lblResult = new Label("Résultats");
        lblResult.setStyle("-fx-underline: true;");
        this.add(lblResult, 0, 0, 2, 1);

        Label lblBmr = new Label("BMR");
        this.add(lblBmr, 0, 1);

        Label lblCal = new Label("Calories");
        this.add(lblCal, 0, 2);
    }

    private void genOutputs() {
        // Inputs
        tfdBmr.setPromptText("Résultat du BMR");
        tfdBmr.setEditable(false);
        this.add(tfdBmr, 1, 1, 2, 1);

        tfdCal.setPromptText("Dépense en calories");
        tfdCal.setEditable(false);
        this.add(tfdCal, 1, 2, 2, 1);
    }

    public void setBMR(double bmr) {
        tfdBmr.setStyle("-fx-text-fill: black");
        tfdBmr.setText(Double.toString(bmr));
    }

    public void setCal(double cal) {
        tfdCal.setStyle("-fx-text-fill: black");
        tfdCal.setText(Double.toString(cal));
    }

    public void failed() {
        tfdBmr.setStyle("-fx-text-fill: red");
        tfdCal.setStyle("-fx-text-fill: red");
        tfdBmr.setText("failed !");
        tfdCal.setText("failed !");
    }

    public void clear() {
        tfdBmr.setText("");
        tfdCal.setText("");
    }
}
