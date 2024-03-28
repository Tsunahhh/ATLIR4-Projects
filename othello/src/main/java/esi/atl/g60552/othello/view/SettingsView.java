package esi.atl.g60552.othello.view;

import javafx.scene.control.TextInputDialog;

import java.util.Optional;

public class SettingsView  {
    private Optional<String> textInput;

    private TextInputDialog inputSize = new TextInputDialog();
    private TextInputDialog inputName = new TextInputDialog();
    private int size = 0;
    private String name = "";
    SettingsView() {
        init();
        askSize();
        askName();
    }

    private void init() {
        inputName.setTitle("Othello");
        inputName.setHeaderText("Othello Settings");
        inputName.setResizable(false);
        inputSize.setTitle("Othello");
        inputSize.setHeaderText("Othello Settings");
        inputSize.setResizable(false);
    }

    private void askSize() {
        inputSize.setContentText("Set the size: ");
        textInput = inputSize.showAndWait();
        if (textInput.isPresent()) {
            validSize(textInput.get());
        } else {
            askSize();
        }
    }

    private void askName() {
        inputName.setContentText("Set the name: ");
        textInput = inputName.showAndWait();
        if (textInput.isPresent()) {
            validName(textInput.get());
        } else {
            askName();
        }
    }

    private void validSize(String sizeInput) {
        try {
            size = Integer.parseInt(sizeInput);
            if (!isSizeValid(size)) {
                throw new IllegalArgumentException("Settings: size is not correct !");
            }
        } catch (Exception e) {
            askSize();
        }
    }

    private void validName(String nameInput) {
        if (nameInput.contains(" ")) {
            askName();
        }
    }

    private boolean isSizeValid(int size) {
        return size > 3 && size < 15 && size % 2 == 0;
    }

    public int getSize() {
        return size;
    }

    public String getName() {
        return name;
    }

}
