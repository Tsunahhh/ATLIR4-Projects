package esi.atl.g60552.othello.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DirectionTest {

        @Test
        void getXDirection() {
            assertEquals(Direction.UPLEFT.getXDirection(), -1);
            assertEquals(Direction.UPRIGHT.getXDirection(), 1);
            assertEquals(Direction.DOWNLEFT.getXDirection(), -1);
            assertEquals(Direction.DOWNRIGHT.getXDirection(), 1);
            assertEquals(Direction.UP.getXDirection(), 0);
            assertEquals(Direction.DOWN.getXDirection(), 0);
            assertEquals(Direction.LEFT.getXDirection(), -1);
            assertEquals(Direction.RIGHT.getXDirection(), 1);
        }

        @Test
        void getYDirection() {
            assertEquals(Direction.UPLEFT.getYDirection(), -1);
            assertEquals(Direction.UPRIGHT.getYDirection(), -1);
            assertEquals(Direction.DOWNLEFT.getYDirection(), 1);
            assertEquals(Direction.DOWNRIGHT.getYDirection(), 1);
            assertEquals(Direction.UP.getYDirection(), -1);
            assertEquals(Direction.DOWN.getYDirection(), 1);
            assertEquals(Direction.LEFT.getYDirection(), 0);
            assertEquals(Direction.RIGHT.getYDirection(), 0);
        }
}