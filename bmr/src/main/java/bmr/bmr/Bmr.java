package bmr.bmr;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Bmr extends Application {

    private double calcBmr(String genre, double height, double weight, int age) {
        double result;
        if (genre.equals("Homme")) {
            result = 13.7 * weight + 5 * height - 6.8 * age + 66;
        } else {
            result = 9.6 * weight + 1.8 * height - 4.7 * age + 655;
        }
        return result;
    }

    private double freqToValue(String freq) {
        double res = 0;
        switch (freq) {
            case "sedentaire" -> res = 1.2;
            case "peu" -> res = 1.375;
            case "moyen" -> res = 1.55;
            case "beaucoup" -> res = 1.725;
            case "extreme" -> res = 1.9;
        }
        return res;
    }


    @Override
    public void start(Stage stage) throws Exception {

        VBox root = new VBox(10);
        root.setPadding(new Insets(10));

        HBox mainOne = new HBox(5);

        // Grid 1
        GridPane gridData = new GridPane();
        gridData.setPadding(new Insets(10));
        gridData.setHgap(15);
        gridData.setVgap(6);

        //Labels
        Label lblData = new Label("Données");
        lblData.setStyle("-fx-underline: true;");
        gridData.add(lblData, 0, 0, 2, 1);

        Label lblSize = new Label("Taille (cm)");
        gridData.add(lblSize, 0, 1);

        Label lblWeight = new Label("Poids (kg)");
        gridData.add(lblWeight, 0, 2);

        Label lblYear = new Label("Age (années)");
        gridData.add(lblYear, 0, 3);

        Label lblSex = new Label("Sexe");
        gridData.add(lblSex, 0, 4);

        Label lblStyleLife = new Label("Style de vie");
        gridData.add(lblStyleLife, 0, 5);

        // Inputs
        TextField tfdHeight = new TextField();
        tfdHeight.setPromptText("Taille en cm");
        gridData.add(tfdHeight, 1, 1, 2, 1);

        TextField tfdWeight = new TextField();
        tfdWeight.setPromptText("Poids en kg");
        gridData.add(tfdWeight, 1, 2, 2, 1);

        TextField tfdAge = new TextField();
        tfdAge.setPromptText("Age en années");
        gridData.add(tfdAge, 1, 3, 2, 1);

        // Radio, with toggle group
        RadioButton rdbFemme = new RadioButton("Femme");
        RadioButton rdbHomme = new RadioButton("Homme");
        ToggleGroup tgrGenre = new ToggleGroup();
        rdbFemme.setToggleGroup(tgrGenre);
        rdbHomme.setToggleGroup(tgrGenre);
        tgrGenre.selectToggle(rdbFemme);
        gridData.add(rdbFemme, 1, 4);
        gridData.add(rdbHomme, 2, 4);

        // ChoiceBox
        ChoiceBox<String> cbxStyleLife = new ChoiceBox<>();
        cbxStyleLife.getItems().addAll("sedentaire", "peu", "moyen", "beaucoup", "extreme");
        cbxStyleLife.setValue("sedentaire");
        gridData.add(cbxStyleLife, 1, 5, 2, 1);

        // Grid 2 : result
        GridPane gridResult = new GridPane();
        gridResult.setPadding(new Insets(10));
        gridResult.setHgap(15);
        gridResult.setVgap(6);

        // Labels
        Label lblResult = new Label("Résultats");
        lblResult.setStyle("-fx-underline: true;");
        gridResult.add(lblResult, 0, 0, 2, 1);

        Label lblBmr = new Label("BMR");
        gridResult.add(lblBmr, 0, 1);

        Label lblCal = new Label("Calories");
        gridResult.add(lblCal, 0, 2);

        // Inputs
        TextField tfdBmr = new TextField();
        tfdBmr.setPromptText("Résultat du BMR");
        tfdBmr.setEditable(false);
        gridResult.add(tfdBmr, 1, 1, 2, 1);

        TextField tfdCal = new TextField();
        tfdCal.setPromptText("Dépense en calories");
        tfdCal.setEditable(false);
        gridResult.add(tfdCal, 1, 2, 2, 1);

        //Calc Bmr
        Button btCalcBmr = new Button("Calcul du BMR");
        btCalcBmr.setMaxWidth(Double.MAX_VALUE);

        btCalcBmr.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String result;

                RadioButton selGenre = (RadioButton) tgrGenre.getSelectedToggle();
                String sex = selGenre.getText();

                try {
                    double weight = Double.parseDouble(tfdWeight.getText());
                    double height = Double.parseDouble(tfdHeight.getText());
                    int age = Integer.parseInt(tfdAge.getText());
                    String freq = cbxStyleLife.getValue();

                    double res = calcBmr(sex, height, weight, age);

                    result = String.valueOf(res);
                    tfdBmr.setStyle("-fx-text-fill: black");
                    tfdBmr.setText(result);

                    result = String.valueOf(res * freqToValue(freq));
                    tfdCal.setStyle("-fx-text-fill: black");
                    tfdCal.setText(result);

                } catch (NumberFormatException nb) {
                    result = "Failed!";
                    tfdBmr.setStyle("-fx-text-fill: red");
                    tfdCal.setStyle("-fx-text-fill: red");
                    tfdBmr.setText(result);
                    tfdCal.setText(result);

                }

            }
        });

        //Clear
        Button btClear = new Button("Clear");
        btClear.setMaxWidth(Double.MAX_VALUE);
        btClear.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                tfdBmr.setText("");
                tfdCal.setText("");
                tfdAge.setText("");
                tfdHeight.setText("");
                tfdWeight.setText("");
                cbxStyleLife.setValue("sedentaire");
                tgrGenre.selectToggle(rdbFemme);

            }
        });

        mainOne.getChildren().addAll(gridData, gridResult);
        root.getChildren().addAll(mainOne, btCalcBmr, btClear);
        Scene scene = new Scene(root, Color.GRAY);
        stage.setTitle("Calcule du MBR");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}