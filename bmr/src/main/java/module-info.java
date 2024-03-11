module bmr {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens bmr to javafx.fxml;
    exports bmr;
}