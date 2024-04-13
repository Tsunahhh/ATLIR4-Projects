package esi.atl.g60552.othello.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ReversiTest {
    private Reversi reversi;

    @BeforeEach
    void setUp() {
        Player p1 = new Human("Player1", DiskColor.BLACK);
        Player p2 = new Human("Player2", DiskColor.WHITE);
        ArrayList<Player> playersList = new ArrayList<>();
        playersList.add(p1);
        playersList.add(p2);
        reversi = new Reversi(4,0,  p1, p2);
    }

    /**
     * . . . .
     * . W B .     * . B W .
     * . . . .
     */
    @Test
    void testInit() {
        assertEquals(DiskColor.WHITE, reversi.getColorAt(1, 1));
        assertEquals(DiskColor.BLACK, reversi.getColorAt(1, 2));
        assertEquals(DiskColor.BLACK, reversi.getColorAt(2, 1));
        assertEquals(DiskColor.WHITE, reversi.getColorAt(2, 2));
    }

    /**
     * . B . .
     * . B B .
     * . B W .
     */
    void situationA() {
        reversi.placeDisk(1, 0); // Assume it s a good move
        reversi.nextPlayer();
    }

    /**
     * . B W .
     * . B W .
     * . B W .
     * . . . .
     */
    @Test
    void situationB() {
        situationA();
        reversi.placeDisk(2, 0); // Assume it s a good move
        reversi.nextPlayer();
    }

    /**
     * . B B B
     * . B B .
     * . B W .
     * . . . .
     */
    void situationC() {
        situationB();
        reversi.placeDisk(3, 0);
        reversi.nextPlayer();
    }

    @Test
    void playerAtStartValid() {
        assertEquals(DiskColor.BLACK, reversi.getCurrPlayer().getColor());
    }

    @Test
    void isNextPlayerValid() {
        reversi.nextPlayer();
        assertEquals(DiskColor.WHITE, reversi.getCurrPlayer().getColor());
    }

    @Test
    void placeOnNoValidCase() {
        assertThrows(IllegalArgumentException.class, () -> reversi.placeDisk(0, 0));
    }

    @Test
    void placeOnNoValidCase2() {
        assertThrows(IllegalArgumentException.class, () -> reversi.placeDisk(2, 0));
    }


    @Test
    void placeableValidAtStart() {
        assertFalse(reversi.isPlaceable(0, 0, reversi.currPlayer()));
        assertTrue(reversi.isPlaceable(0, 1, reversi.currPlayer()));
        assertFalse(reversi.isPlaceable(0, 2, reversi.currPlayer()));
        assertFalse(reversi.isPlaceable(0, 3, reversi.currPlayer()));
        assertTrue(reversi.isPlaceable(1, 0, reversi.currPlayer()));
        assertFalse(reversi.isPlaceable(1, 1, reversi.currPlayer()));
        assertFalse(reversi.isPlaceable(1, 2, reversi.currPlayer()));
        assertFalse(reversi.isPlaceable(1, 3, reversi.currPlayer()));
        assertFalse(reversi.isPlaceable(2, 0, reversi.currPlayer()));
        assertFalse(reversi.isPlaceable(2, 1, reversi.currPlayer()));
        assertFalse(reversi.isPlaceable(2, 2, reversi.currPlayer()));
        assertTrue(reversi.isPlaceable(2, 3, reversi.currPlayer()));
        assertFalse(reversi.isPlaceable(3, 0, reversi.currPlayer()));
        assertFalse(reversi.isPlaceable(3, 1, reversi.currPlayer()));
        assertTrue(reversi.isPlaceable(3, 2, reversi.currPlayer()));
        assertFalse(reversi.isPlaceable(3, 3, reversi.currPlayer()));
    }

    @Test
    void isDirectionValidAtStart() {
        // (1; 0) is normally a placeable position
        assertFalse(reversi.isDirectionValid(1, 0, Direction.UP, reversi.getCurrPlayer()));
        assertTrue(reversi.isDirectionValid(1, 0, Direction.DOWN, reversi.getCurrPlayer()));
        assertFalse(reversi.isDirectionValid(1, 0, Direction.LEFT, reversi.getCurrPlayer()));
        assertFalse(reversi.isDirectionValid(1, 0, Direction.RIGHT, reversi.getCurrPlayer()));
        assertFalse(reversi.isDirectionValid(1, 0, Direction.UPLEFT, reversi.getCurrPlayer()));
        assertFalse(reversi.isDirectionValid(1, 0, Direction.UPRIGHT, reversi.getCurrPlayer()));
        assertFalse(reversi.isDirectionValid(1, 0, Direction.DOWNLEFT, reversi.getCurrPlayer()));
        assertFalse(reversi.isDirectionValid(1, 0, Direction.DOWNRIGHT, reversi.getCurrPlayer()));

        // (0; 1) is normally a placeable position
        assertFalse(reversi.isDirectionValid(0, 1, Direction.UP, reversi.getCurrPlayer()));
        assertFalse(reversi.isDirectionValid(0, 1, Direction.DOWN, reversi.getCurrPlayer()));
        assertFalse(reversi.isDirectionValid(0, 1, Direction.LEFT, reversi.getCurrPlayer()));
        assertTrue(reversi.isDirectionValid(0, 1, Direction.RIGHT, reversi.getCurrPlayer()));
        assertFalse(reversi.isDirectionValid(0, 1, Direction.UPLEFT, reversi.getCurrPlayer()));
        assertFalse(reversi.isDirectionValid(0, 1, Direction.UPRIGHT, reversi.getCurrPlayer()));
        assertFalse(reversi.isDirectionValid(0, 1, Direction.DOWNLEFT, reversi.getCurrPlayer()));
        assertFalse(reversi.isDirectionValid(0, 1, Direction.DOWNRIGHT, reversi.getCurrPlayer()));

        // (2; 3) is normally a placeable position
        assertTrue(reversi.isDirectionValid(2, 3, Direction.UP, reversi.getCurrPlayer()));
        assertFalse(reversi.isDirectionValid(2, 3, Direction.DOWN, reversi.getCurrPlayer()));
        assertFalse(reversi.isDirectionValid(2, 3, Direction.LEFT, reversi.getCurrPlayer()));
        assertFalse(reversi.isDirectionValid(2, 3, Direction.RIGHT, reversi.getCurrPlayer()));
        assertFalse(reversi.isDirectionValid(2, 3, Direction.UPLEFT, reversi.getCurrPlayer()));
        assertFalse(reversi.isDirectionValid(2, 3, Direction.UPRIGHT, reversi.getCurrPlayer()));
        assertFalse(reversi.isDirectionValid(2, 3, Direction.DOWNLEFT, reversi.getCurrPlayer()));
        assertFalse(reversi.isDirectionValid(2, 3, Direction.DOWNRIGHT, reversi.getCurrPlayer()));

        // (3; 2) is normally a placeable position
        assertFalse(reversi.isDirectionValid(3, 2, Direction.UP, reversi.getCurrPlayer()));
        assertFalse(reversi.isDirectionValid(3, 2, Direction.DOWN, reversi.getCurrPlayer()));
        assertTrue(reversi.isDirectionValid(3, 2, Direction.LEFT, reversi.getCurrPlayer()));
        assertFalse(reversi.isDirectionValid(3, 2, Direction.RIGHT, reversi.getCurrPlayer()));
        assertFalse(reversi.isDirectionValid(3, 2, Direction.UPLEFT, reversi.getCurrPlayer()));
        assertFalse(reversi.isDirectionValid(3, 2, Direction.UPRIGHT, reversi.getCurrPlayer()));
        assertFalse(reversi.isDirectionValid(3, 2, Direction.DOWNLEFT, reversi.getCurrPlayer()));
        assertFalse(reversi.isDirectionValid(3, 2, Direction.DOWNRIGHT, reversi.getCurrPlayer()));
    }

    @Test
    void placeADiskOnTopLeft() {
        reversi.placeDisk(0, 1);
    }

    @Test
    void testSituationA() {
        situationA();
    }

    /**
     * . B W +
     * . B W +
     * . B W +
     * . . . +
     */
    @Test
    void checkDirectionsSituationB() {
        situationB();

        // 3; 0
        assertFalse(reversi.isDirectionValid(3, 0, Direction.UP, reversi.getCurrPlayer()));
        assertFalse(reversi.isDirectionValid(3, 0, Direction.DOWN, reversi.getCurrPlayer()));
        assertTrue(reversi.isDirectionValid(3, 0, Direction.LEFT, reversi.getCurrPlayer()));
        assertFalse(reversi.isDirectionValid(3, 0, Direction.RIGHT, reversi.getCurrPlayer()));
        assertFalse(reversi.isDirectionValid(3, 0, Direction.UPLEFT, reversi.getCurrPlayer()));
        assertFalse(reversi.isDirectionValid(3, 0, Direction.UPRIGHT, reversi.getCurrPlayer()));
        assertTrue(reversi.isDirectionValid(3, 0, Direction.DOWNLEFT, reversi.getCurrPlayer()));
        assertFalse(reversi.isDirectionValid(3, 0, Direction.DOWNRIGHT, reversi.getCurrPlayer()));

        // 3; 1
        assertFalse(reversi.isDirectionValid(3, 1, Direction.UP, reversi.getCurrPlayer()));
        assertFalse(reversi.isDirectionValid(3, 1, Direction.DOWN, reversi.getCurrPlayer()));
        assertTrue(reversi.isDirectionValid(3, 1, Direction.LEFT, reversi.getCurrPlayer()));
        assertFalse(reversi.isDirectionValid(3, 1, Direction.RIGHT, reversi.getCurrPlayer()));
        assertFalse(reversi.isDirectionValid(3, 1, Direction.UPLEFT, reversi.getCurrPlayer()));
        assertFalse(reversi.isDirectionValid(3, 1, Direction.UPRIGHT, reversi.getCurrPlayer()));
        assertFalse(reversi.isDirectionValid(3, 1, Direction.DOWNLEFT, reversi.getCurrPlayer()));
        assertFalse(reversi.isDirectionValid(3, 1, Direction.DOWNRIGHT, reversi.getCurrPlayer()));

        // 3; 2
        assertFalse(reversi.isDirectionValid(3, 2, Direction.UP, reversi.getCurrPlayer()));
        assertFalse(reversi.isDirectionValid(3, 2, Direction.DOWN, reversi.getCurrPlayer()));
        assertTrue(reversi.isDirectionValid(3, 2, Direction.LEFT, reversi.getCurrPlayer()));
        assertFalse(reversi.isDirectionValid(3, 2, Direction.RIGHT, reversi.getCurrPlayer()));
        assertTrue(reversi.isDirectionValid(3, 2, Direction.UPLEFT, reversi.getCurrPlayer()));
        assertFalse(reversi.isDirectionValid(3, 2, Direction.UPRIGHT, reversi.getCurrPlayer()));
        assertFalse(reversi.isDirectionValid(3, 2, Direction.DOWNLEFT, reversi.getCurrPlayer()));
        assertFalse(reversi.isDirectionValid(3, 2, Direction.DOWNRIGHT, reversi.getCurrPlayer()));

        // 3; 3
        assertFalse(reversi.isDirectionValid(3, 3, Direction.UP, reversi.getCurrPlayer()));
        assertFalse(reversi.isDirectionValid(3, 3, Direction.DOWN, reversi.getCurrPlayer()));
        assertFalse(reversi.isDirectionValid(3, 3, Direction.LEFT, reversi.getCurrPlayer()));
        assertFalse(reversi.isDirectionValid(3, 3, Direction.RIGHT, reversi.getCurrPlayer()));
        assertTrue(reversi.isDirectionValid(3, 3, Direction.UPLEFT, reversi.getCurrPlayer()));
        assertFalse(reversi.isDirectionValid(3, 3, Direction.UPRIGHT, reversi.getCurrPlayer()));
        assertFalse(reversi.isDirectionValid(3, 3, Direction.DOWNLEFT, reversi.getCurrPlayer()));
        assertFalse(reversi.isDirectionValid(3, 3, Direction.DOWNRIGHT, reversi.getCurrPlayer()));
    }

}