package g60552.atl.ascii.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class AsciiPaintTest {

    private AsciiPaint ap;

    @BeforeEach
    public void setUp() {
        ap = new AsciiPaint(50, 50);
        ap.newSquare(10, 10, 10, 'I');
        ap.newRectangle(20, 20, 16, 10, 'R');
        ap.newCircle(5, 5, 10, 'C');
    }

    @Test
    void asciiPaintNegativeWidth() {
        assertThrows(IllegalArgumentException.class, () -> {
            ap = new AsciiPaint(-9, 7);
        });
    }

    @Test
    void asciiPaintNegativeHeight() {
        assertThrows(IllegalArgumentException.class, () -> {
            ap = new AsciiPaint(9, -7);
        });
    }

    @Test
    void asciiPaintNullDim() {
        assertThrows(IllegalArgumentException.class, () -> {
            ap = new AsciiPaint(0, 0);
        });
    }

    @Test
    void circleBasic() {
        ap.newCircle(10, 10, 7, 'C');
        ap.newCircle(17, 10, 10, 'R');
    }

    @Test
    void cicleNotInDrawing() {
        ap.newCircle(99, 99, 20, 'T');
    }

    @Test
    void cicleNegativeRadius() {
        assertThrows(IllegalArgumentException.class, () -> {ap.newCircle(9, 9, -20, 'P');});
    }

    @Test
    void cicleBigRadius() {
        ap.newCircle(25, 25, 100, 'E');
    }

    @Test
    void rectangleBasic() {
        ap.newRectangle(20, 20, 10, 15, 'R');
    }

    @Test
    void rectangleNotInDraw() {
        ap.newRectangle(99, 99, 15, 19, 'Y');
    }

    @Test
    void rectangleBigSize() {
        ap.newRectangle(0, 0, 99, 200, 'X');
    }

    @Test
    void rectangleNegCoord() {
        ap.newRectangle(-2, -2, 20, 12, 'V');
    }

    @Test
    void rectangleWithLowWidth() {
        assertThrows(IllegalArgumentException.class, () -> {ap.newRectangle(25, 25, 0, 24, 'M');});
    }

    @Test
    void rectangleNegativeHeight() {
        assertThrows(IllegalArgumentException.class, () -> {ap.newRectangle(25, 25, 3, -24, 'M');});
    }

    @Test
    void rectangleNegativeWidth() {
        assertThrows(IllegalArgumentException.class, () -> {ap.newRectangle(25, 25, -33, 24, 'M');});
    }

    @Test
    void squareBasic() {
        ap.newSquare(10, 10, 10, 'S');
    }
    @Test
    void squareOut() {
        ap.newSquare(99, 99, 10, 'S');
    }
    @Test
    void squareNegativePosition() {
        ap.newSquare(-10, -10, 10, 'S');
    }

    @Test
    void squareNegativeSize() {
        assertThrows(IllegalArgumentException.class,
                () -> ap.newSquare(10, 10, -10, 'S'));
    }

    @Test
    void squareBigSize() {
        ap.newSquare(10, 10, 500, 'R');
    }

    @Test
    void moveOutOfDraw() {
        ap.move(0, 99, 99);
    }

    @Test
    void moveBasic() {
        ap.move(0, 20, 20);
    }

    @Test
    void moveOnWall() {
        ap.move(1, 0, 0);
    }

    @Test
    void setColor() {
        ap.setColor(1, 'D');
        char result = 'D';
        assertEquals(result, ap.getShapesList().get(1).getColor());
    }
}