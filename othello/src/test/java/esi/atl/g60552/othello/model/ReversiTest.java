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
        reversi = new Reversi(4, p1, p2);
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
        assertFalse(reversi.isPlaceable(0, 0));
        assertTrue(reversi.isPlaceable(0, 1));
        assertFalse(reversi.isPlaceable(0, 2));
        assertFalse(reversi.isPlaceable(0, 3));
        assertTrue(reversi.isPlaceable(1, 0));
        assertFalse(reversi.isPlaceable(1, 1));
        assertFalse(reversi.isPlaceable(1, 2));
        assertFalse(reversi.isPlaceable(1, 3));
        assertFalse(reversi.isPlaceable(2, 0));
        assertFalse(reversi.isPlaceable(2, 1));
        assertFalse(reversi.isPlaceable(2, 2));
        assertTrue(reversi.isPlaceable(2, 3));
        assertFalse(reversi.isPlaceable(3, 0));
        assertFalse(reversi.isPlaceable(3, 1));
        assertTrue(reversi.isPlaceable(3, 2));
        assertFalse(reversi.isPlaceable(3, 3));
    }

    @Test
    void isDirectionValidAtStart() {
        // (1; 0) is normally a placeable position
        assertFalse(reversi.isDirectionValid(1, 0, Direction.UP));
        assertTrue(reversi.isDirectionValid(1, 0, Direction.DOWN));
        assertFalse(reversi.isDirectionValid(1, 0, Direction.LEFT));
        assertFalse(reversi.isDirectionValid(1, 0, Direction.RIGHT));
        assertFalse(reversi.isDirectionValid(1, 0, Direction.UPLEFT));
        assertFalse(reversi.isDirectionValid(1, 0, Direction.UPRIGHT));
        assertFalse(reversi.isDirectionValid(1, 0, Direction.DOWNLEFT));
        assertFalse(reversi.isDirectionValid(1, 0, Direction.DOWNRIGHT));

        // (0; 1) is normally a placeable position
        assertFalse(reversi.isDirectionValid(0, 1, Direction.UP));
        assertFalse(reversi.isDirectionValid(0, 1, Direction.DOWN));
        assertFalse(reversi.isDirectionValid(0, 1, Direction.LEFT));
        assertTrue(reversi.isDirectionValid(0, 1, Direction.RIGHT));
        assertFalse(reversi.isDirectionValid(0, 1, Direction.UPLEFT));
        assertFalse(reversi.isDirectionValid(0, 1, Direction.UPRIGHT));
        assertFalse(reversi.isDirectionValid(0, 1, Direction.DOWNLEFT));
        assertFalse(reversi.isDirectionValid(0, 1, Direction.DOWNRIGHT));

        // (2; 3) is normally a placeable position
        assertTrue(reversi.isDirectionValid(2, 3, Direction.UP));
        assertFalse(reversi.isDirectionValid(2, 3, Direction.DOWN));
        assertFalse(reversi.isDirectionValid(2, 3, Direction.LEFT));
        assertFalse(reversi.isDirectionValid(2, 3, Direction.RIGHT));
        assertFalse(reversi.isDirectionValid(2, 3, Direction.UPLEFT));
        assertFalse(reversi.isDirectionValid(2, 3, Direction.UPRIGHT));
        assertFalse(reversi.isDirectionValid(2, 3, Direction.DOWNLEFT));
        assertFalse(reversi.isDirectionValid(2, 3, Direction.DOWNRIGHT));

        // (3; 2) is normally a placeable position
        assertFalse(reversi.isDirectionValid(3, 2, Direction.UP));
        assertFalse(reversi.isDirectionValid(3, 2, Direction.DOWN));
        assertTrue(reversi.isDirectionValid(3, 2, Direction.LEFT));
        assertFalse(reversi.isDirectionValid(3, 2, Direction.RIGHT));
        assertFalse(reversi.isDirectionValid(3, 2, Direction.UPLEFT));
        assertFalse(reversi.isDirectionValid(3, 2, Direction.UPRIGHT));
        assertFalse(reversi.isDirectionValid(3, 2, Direction.DOWNLEFT));
        assertFalse(reversi.isDirectionValid(3, 2, Direction.DOWNRIGHT));
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
        assertFalse(reversi.isDirectionValid(3, 0, Direction.UP));
        assertFalse(reversi.isDirectionValid(3, 0, Direction.DOWN));
        assertTrue(reversi.isDirectionValid(3, 0, Direction.LEFT));
        assertFalse(reversi.isDirectionValid(3, 0, Direction.RIGHT));
        assertFalse(reversi.isDirectionValid(3, 0, Direction.UPLEFT));
        assertFalse(reversi.isDirectionValid(3, 0, Direction.UPRIGHT));
        assertTrue(reversi.isDirectionValid(3, 0, Direction.DOWNLEFT));
        assertFalse(reversi.isDirectionValid(3, 0, Direction.DOWNRIGHT));

        // 3; 1
        assertFalse(reversi.isDirectionValid(3, 1, Direction.UP));
        assertFalse(reversi.isDirectionValid(3, 1, Direction.DOWN));
        assertTrue(reversi.isDirectionValid(3, 1, Direction.LEFT));
        assertFalse(reversi.isDirectionValid(3, 1, Direction.RIGHT));
        assertFalse(reversi.isDirectionValid(3, 1, Direction.UPLEFT));
        assertFalse(reversi.isDirectionValid(3, 1, Direction.UPRIGHT));
        assertFalse(reversi.isDirectionValid(3, 1, Direction.DOWNLEFT));
        assertFalse(reversi.isDirectionValid(3, 1, Direction.DOWNRIGHT));

        // 3; 2
        assertFalse(reversi.isDirectionValid(3, 2, Direction.UP));
        assertFalse(reversi.isDirectionValid(3, 2, Direction.DOWN));
        assertTrue(reversi.isDirectionValid(3, 2, Direction.LEFT));
        assertFalse(reversi.isDirectionValid(3, 2, Direction.RIGHT));
        assertTrue(reversi.isDirectionValid(3, 2, Direction.UPLEFT));
        assertFalse(reversi.isDirectionValid(3, 2, Direction.UPRIGHT));
        assertFalse(reversi.isDirectionValid(3, 2, Direction.DOWNLEFT));
        assertFalse(reversi.isDirectionValid(3, 2, Direction.DOWNRIGHT));

        // 3; 3
        assertFalse(reversi.isDirectionValid(3, 3, Direction.UP));
        assertFalse(reversi.isDirectionValid(3, 3, Direction.DOWN));
        assertFalse(reversi.isDirectionValid(3, 3, Direction.LEFT));
        assertFalse(reversi.isDirectionValid(3, 3, Direction.RIGHT));
        assertTrue(reversi.isDirectionValid(3, 3, Direction.UPLEFT));
        assertFalse(reversi.isDirectionValid(3, 3, Direction.UPRIGHT));
        assertFalse(reversi.isDirectionValid(3, 3, Direction.DOWNLEFT));
        assertFalse(reversi.isDirectionValid(3, 3, Direction.DOWNRIGHT));
    }

}