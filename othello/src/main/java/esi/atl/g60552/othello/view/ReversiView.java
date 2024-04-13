package esi.atl.g60552.othello.view;

import esi.atl.g60552.othello.model.Board;
import esi.atl.g60552.othello.model.DiskColor;
import esi.atl.g60552.othello.model.Position;
import esi.atl.g60552.othello.model.Reversi;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.util.List;

public class ReversiView extends GridPane {
    private static final int REVERSI_SIZE = 700;
    private Color bgGameColor;

    ReversiView() {
        super();
        this.bgGameColor = Color.GREEN;
    }

    private void actions(Reversi reversi, int col, int row, final CaseView caseView) {
        Board board = reversi.getBoard();
        caseView.setOnMouseEntered(e -> {
            if (board.isEmpty(col, row)) {
                if (reversi.isValidPosition(col, row)) {
                    caseView.setColorRectangle(Color.LIME);
                } else {
                    caseView.setColorRectangle(Color.RED);
                }

                if (reversi.currPlayer().getColor() == DiskColor.BLACK) {
                    caseView.setColorDisk(Color.rgb(0, 0, 0, 0.5));
                } else {
                    caseView.setColorDisk(Color.rgb(255, 255, 255, 0.5));
                }
            }
        });

        caseView.onMouseClickedProperty().set(e -> {
            if (reversi.isValidPosition(col, row)) {
                reversi.placeDisk(col, row);
            }
        });

        caseView.setOnMouseExited(e -> {
            caseView.setColorRectangle(bgGameColor);
            if (board.isEmpty(col, row)) {
                caseView.eraseDisk();
            }
        });
    }

    public void update(Reversi reversi) {
        this.getChildren().clear();
        Board board = reversi.getBoard();
        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                CaseView caseView = new CaseView(bgGameColor, REVERSI_SIZE / reversi.getBoard().getSize());
                if (!board.isEmpty(col, row)) {
                    if (board.getColorAt(col, row) == DiskColor.BLACK) {
                        caseView.setColorDisk(Color.BLACK);
                    } else if (board.getColorAt(col, row) == DiskColor.WHITE) {
                        caseView.setColorDisk(Color.WHITE);
                    }
                }
                actions(reversi, col, row, caseView);
                this.add(caseView, col, row);
            }
        }
    }

    void setBgGameColor(Color bgGameColor) {
        this.bgGameColor = bgGameColor;
    }
}
