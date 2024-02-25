package g60552.atl.ascii.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RectangleTest {

    private Rectangle rectangle;

    @BeforeEach
    void setUp() {
        rectangle = new Rectangle(new Point(25, 25), 10, 15, 'C');
    }

    @Test
    void isInside_pointInRectangle() {
        Point p = new Point(27, 27);
        assertTrue(rectangle.isInside(p));
    }

    @Test
    void isInside_pointOutCircle() {
        Point p = new Point(5, 5);
        assertFalse(rectangle.isInside(p));
    }

}