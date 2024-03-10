module bmr.bmr {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens bmr.bmr to javafx.fxml;
    exports bmr.bmr;
}