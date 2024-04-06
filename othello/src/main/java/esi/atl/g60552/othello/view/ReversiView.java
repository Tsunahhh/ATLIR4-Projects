package esi.atl.g60552.othello.view;

import esi.atl.g60552.othello.model.DiskColor;
import esi.atl.g60552.othello.model.Reversi;
import esi.atl.g60552.othello.util.Observer;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class ReversiView extends GridPane implements Observer {
    private int size;
    private Reversi reversi;

    public ReversiView(Reversi reversi) {
        super();
        this.reversi = reversi;
        init();
        //update();
    }

    private void init() {
        // Définir le fond en vert
        BackgroundFill backgroundFill = new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, null);
        super.setBackground(new Background(backgroundFill));
        update();
    }

    private void actions(int col, int row, final CaseView caseView) {
        caseView.setOnMouseEntered(e -> {
            if (reversi.isEmpty(col, row)) {
                if (reversi.isValidPosition(col, row)) {
                    caseView.setColorRectangle(Color.LIME);
                } else {
                    caseView.setColorRectangle(Color.RED);
                }

                if (reversi.getCurrPlayerColor() == DiskColor.BLACK) {
                    caseView.setColorDisk(Color.rgb(0, 0, 0, 0.5));
                } else {
                    caseView.setColorDisk(Color.rgb(255, 255, 255, 0.5));
                }
            }
        });

        caseView.onMouseClickedProperty().set(e -> {
            if (reversi.isValidPosition(col, row)) {
                reversi.placeDisk(col, row);
                reversi.nextPlayer();
            }
        });

        caseView.setOnMouseExited(e -> {
            caseView.setColorRectangle(Color.GREEN);
            if (reversi.isEmpty(col, row)) {
                caseView.setColorDisk(Color.TRANSPARENT);
            }
        });
    }

    @Override
    public void update() {
        this.getChildren().clear();
        for (int row = 0; row < reversi.getSize(); row++) {
            for (int col = 0; col < reversi.getSize(); col++) {
                CaseView caseView = new CaseView();
                if (!reversi.isEmpty(col, row)) {
                    if (reversi.getColor(col, row) == DiskColor.BLACK) {
                        caseView.setColorDisk(Color.BLACK);
                    } else if (reversi.getColor(col, row) == DiskColor.WHITE) {
                        caseView.setColorDisk(Color.WHITE);
                    }
                }
                actions(col, row, caseView);
                this.add(caseView, col, row);
            }
        }
    }
}
