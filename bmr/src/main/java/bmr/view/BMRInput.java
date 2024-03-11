package bmr.view;

import bmr.model.Activity;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class BMRInput extends GridPane {

    private TextField tfdHeight = new TextField();
    private TextField tfdWeight = new TextField();
    private TextField tfdAge = new TextField();
    private RadioButton rdbFemme = new RadioButton("Femme");
    private RadioButton rdbHomme = new RadioButton("Homme");
    private ToggleGroup tgrSex = new ToggleGroup();
    private ChoiceBox<String> cbxStyleLife = new ChoiceBox<>();

    BMRInput() {
        super();
        this.setPadding(new Insets(10));
        this.setHgap(15);
        this.setVgap(6);
        this.genLabels();
        this.genInputs();
    }

    private void genLabels() {

        Label lblData = new Label("Données");
        lblData.setStyle("-fx-underline: true;");
        this.add(lblData, 0, 0, 2, 1);

        Label lblSize = new Label("Taille (cm)");
        this.add(lblSize, 0, 1);

        Label lblWeight = new Label("Poids (kg)");
        this.add(lblWeight, 0, 2);

        Label lblYear = new Label("Age (années)");
        this.add(lblYear, 0, 3);

        Label lblSex = new Label("Sexe");
        this.add(lblSex, 0, 4);

        Label lblStyleLife = new Label("Style de vie");
        this.add(lblStyleLife, 0, 5);
    }

    private void genInputs() {
        this.tfdHeight.setPromptText("Taille en cm");
        this.add(tfdHeight, 1, 1, 2, 1);

        tfdWeight.setPromptText("Poids en kg");
        this.add(tfdWeight, 1, 2, 2, 1);

        tfdAge.setPromptText("Age en années");
        this.add(tfdAge, 1, 3, 2, 1);

        // Radio, with toggle group
        rdbFemme.setToggleGroup(tgrSex);
        rdbHomme.setToggleGroup(tgrSex);
        tgrSex.selectToggle(rdbFemme);
        this.add(rdbFemme, 1, 4);
        this.add(rdbHomme, 2, 4);

        // ChoiceBox
        cbxStyleLife.getItems().addAll("sedentaire", "peu", "moyen", "beaucoup", "extreme");
        cbxStyleLife.setValue("sedentaire");
        this.add(cbxStyleLife, 1, 5, 2, 1);
    }

    public double getBMRHeight() {
        return Double.parseDouble(tfdHeight.getText());
    }

    public double getBMRWeight() {
        return Double.parseDouble(tfdWeight.getText());
    }

    public int getBMRAge() {
        return Integer.parseInt(tfdAge.getText());
    }

    public boolean isWoman() {
        RadioButton tmp = (RadioButton) tgrSex.getSelectedToggle();
        return tmp.getText().equals("Femme");
    }

    public Activity getBMRLife() {
        switch (cbxStyleLife.getValue()) {
            case "sedentaire" -> {
                return Activity.NEVER;
            }
            case "peu" -> {
                return Activity.LOW;
            }
            case "moyen" -> {
                return Activity.MID;
            }
            case "beaucoup" -> {
                return Activity.HIGH;
            }
            case "extreme" -> {
                return Activity.EXTREME;
            }
            default -> {
                throw new IllegalArgumentException("Activity not in the combobox");
            }
        }
    }

    public void clear() {
        tfdAge.setText("");
        tfdHeight.setText("");
        tfdWeight.setText("");
        cbxStyleLife.setValue("sedentaire");
        tgrSex.selectToggle(rdbFemme);
    }

}
