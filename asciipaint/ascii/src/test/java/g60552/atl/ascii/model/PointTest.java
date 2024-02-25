package g60552.atl.ascii.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class PointTest {

    private Point p1;

    @BeforeEach
    void setUp() {
        p1 = new Point(10, 10);
    }

    @Test
    void movePositiv() {
        p1.move(10, 10);
        Point p2 = new Point(20, 20);
        assertEquals(p1.getX(), p2.getX());
        assertEquals(p1.getY(), p2.getY());
    }

    @Test
    void moveNegativ() {
        p1.move(-10, -10);
        Point p2 = new Point(0, 0);
        assertEquals(p1.getX(), p2.getX());
        assertEquals(p1.getY(), p2.getY());
    }

    @Test
    void distanceTo() {
        Point p3 = new Point(30, 10);
        assertEquals(p1.distanceTo(p3), 20);
    }
}