package g60552.atl.ascii.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CircleTest {

    private Circle circle;

    @BeforeEach
    void setUp() {
        circle = new Circle(new Point(25, 25), 10, 'C');
    }

    @Test
    void isInside_pointInCircle() {
        Point p = new Point(24, 24);
        assertTrue(circle.isInside(p));
    }

    @Test
    void isInside_pointOutCircle() {
        Point p = new Point(5, 5);
        assertFalse(circle.isInside(p));
    }
}