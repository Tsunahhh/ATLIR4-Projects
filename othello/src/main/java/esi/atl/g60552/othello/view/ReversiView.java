package esi.atl.g60552.othello.view;

import esi.atl.g60552.othello.model.DiskColor;
import esi.atl.g60552.othello.model.Reversi;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class ReversiView extends GridPane {
    private int size;
    private Reversi reversi;

    public ReversiView(Reversi reversi) {
        super();
        this.reversi = reversi;
        init();
    }

    private void init() {
        // Définir le fond en vert
        BackgroundFill backgroundFill = new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, null);
        super.setBackground(new Background(backgroundFill));

        // Définir des lignes noires entre les cases
        for (int row = 0; row < reversi.getSize(); row++) {
            for (int col = 0; col < reversi.getSize(); col++) {
                Rectangle cell = new Rectangle(50, 50); // Taille des cases
                cell.setFill(Color.TRANSPARENT);
                cell.setStroke(Color.BLACK);
                super.add(cell, col, row);
            }
        }
        refresh();
    }

    private void refresh() {
        for (int i = 0; i < reversi.getSize(); i++) {
            for (int j = 0; j < reversi.getSize(); j++) {
                if (!reversi.isEmpty(j, i)) {
                    if (reversi.getColor(j, i) == DiskColor.BLACK) {
                        // TODO: Utiliser les CaseView
                    } else if (reversi.getColor(j, i) == DiskColor.WHITE) {

                    }
                }
            }
        }
    }
}
