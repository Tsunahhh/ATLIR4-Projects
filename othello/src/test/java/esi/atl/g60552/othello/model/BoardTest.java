package esi.atl.g60552.othello.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    private Board board;

    @BeforeEach
    void setUp() {
        Player p1 = new Human("Player1", DiskColor.BLACK);
        Player p2 = new Human("Player2", DiskColor.WHITE);
        ArrayList<Player> playersList = new ArrayList<>();
        playersList.add(p1);
        playersList.add(p2);
        board = new Board(4, playersList);
    }

    /**
     * . . . .
     * . W B .
     * . B W .
     * . . . .
     */
    @Test
    void testInit() {
        assertEquals(DiskColor.WHITE, board.getColorAt(1, 1));
        assertEquals(DiskColor.BLACK, board.getColorAt(1, 2));
        assertEquals(DiskColor.BLACK, board.getColorAt(2, 1));
        assertEquals(DiskColor.WHITE, board.getColorAt(2, 2));
    }

    /**
     * . B . .
     * . B B .
     * . B W .
     */
    void situationA() {
        board.placeDisk(1, 0); // Assume it s a good move
        board.nextPlayer();
    }

    /**
     * . B W .
     * . B W .
     * . B W .
     * . . . .
     */
    void situationB() {
        situationA();
        board.placeDisk(2, 0); // Assume it s a good move
        board.nextPlayer();
    }

    /**
     * . B B B
     * . B B .
     * . B W .
     * . . . .
     */
    void situationC() {
        situationB();
        board.placeDisk(3, 0);
        board.nextPlayer();
    }

    @Test
    void playerAtStartValid() {
        assertEquals(DiskColor.BLACK, board.getCurrPlayer().getColor());
    }

    @Test
    void isNextPlayerValid() {
        board.nextPlayer();
        assertEquals(DiskColor.WHITE, board.getCurrPlayer().getColor());
    }

    @Test
    void placeOnNoValidCase() {
        assertThrows(IllegalArgumentException.class, () -> board.placeDisk(0, 0));
    }

    @Test
    void placeOnNoValidCase2() {
        assertThrows(IllegalArgumentException.class, () -> board.placeDisk(2, 0));
    }


    @Test
    void placeableValidAtStart() {
        assertFalse(board.isPlaceable(0, 0));
        assertTrue(board.isPlaceable(0, 1));
        assertFalse(board.isPlaceable(0, 2));
        assertFalse(board.isPlaceable(0, 3));
        assertTrue(board.isPlaceable(1, 0));
        assertFalse(board.isPlaceable(1, 1));
        assertFalse(board.isPlaceable(1, 2));
        assertFalse(board.isPlaceable(1, 3));
        assertFalse(board.isPlaceable(2, 0));
        assertFalse(board.isPlaceable(2, 1));
        assertFalse(board.isPlaceable(2, 2));
        assertTrue(board.isPlaceable(2, 3));
        assertFalse(board.isPlaceable(3, 0));
        assertFalse(board.isPlaceable(3, 1));
        assertTrue(board.isPlaceable(3, 2));
        assertFalse(board.isPlaceable(3, 3));
    }

    @Test
    void isDirectionValidAtStart() {
        // (1; 0) is normally a placeable position
        assertFalse(board.isDirectionValid(1, 0, Direction.UP));
        assertTrue(board.isDirectionValid(1, 0, Direction.DOWN));
        assertFalse(board.isDirectionValid(1, 0, Direction.LEFT));
        assertFalse(board.isDirectionValid(1, 0, Direction.RIGHT));
        assertFalse(board.isDirectionValid(1, 0, Direction.UPLEFT));
        assertFalse(board.isDirectionValid(1, 0, Direction.UPRIGHT));
        assertFalse(board.isDirectionValid(1, 0, Direction.DOWNLEFT));
        assertFalse(board.isDirectionValid(1, 0, Direction.DOWNRIGHT));

        // (0; 1) is normally a placeable position
        assertFalse(board.isDirectionValid(0, 1, Direction.UP));
        assertFalse(board.isDirectionValid(0, 1, Direction.DOWN));
        assertFalse(board.isDirectionValid(0, 1, Direction.LEFT));
        assertTrue(board.isDirectionValid(0, 1, Direction.RIGHT));
        assertFalse(board.isDirectionValid(0, 1, Direction.UPLEFT));
        assertFalse(board.isDirectionValid(0, 1, Direction.UPRIGHT));
        assertFalse(board.isDirectionValid(0, 1, Direction.DOWNLEFT));
        assertFalse(board.isDirectionValid(0, 1, Direction.DOWNRIGHT));

        // (2; 3) is normally a placeable position
        assertTrue(board.isDirectionValid(2, 3, Direction.UP));
        assertFalse(board.isDirectionValid(2, 3, Direction.DOWN));
        assertFalse(board.isDirectionValid(2, 3, Direction.LEFT));
        assertFalse(board.isDirectionValid(2, 3, Direction.RIGHT));
        assertFalse(board.isDirectionValid(2, 3, Direction.UPLEFT));
        assertFalse(board.isDirectionValid(2, 3, Direction.UPRIGHT));
        assertFalse(board.isDirectionValid(2, 3, Direction.DOWNLEFT));
        assertFalse(board.isDirectionValid(2, 3, Direction.DOWNRIGHT));

        // (3; 2) is normally a placeable position
        assertFalse(board.isDirectionValid(3, 2, Direction.UP));
        assertFalse(board.isDirectionValid(3, 2, Direction.DOWN));
        assertTrue(board.isDirectionValid(3, 2, Direction.LEFT));
        assertFalse(board.isDirectionValid(3, 2, Direction.RIGHT));
        assertFalse(board.isDirectionValid(3, 2, Direction.UPLEFT));
        assertFalse(board.isDirectionValid(3, 2, Direction.UPRIGHT));
        assertFalse(board.isDirectionValid(3, 2, Direction.DOWNLEFT));
        assertFalse(board.isDirectionValid(3, 2, Direction.DOWNRIGHT));
    }

    @Test
    void placeADiskOnTopLeft() {
        board.placeDisk(0, 1);
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
        assertFalse(board.isDirectionValid(3, 0, Direction.UP));
        assertFalse(board.isDirectionValid(3, 0, Direction.DOWN));
        assertTrue(board.isDirectionValid(3, 0, Direction.LEFT));
        assertFalse(board.isDirectionValid(3, 0, Direction.RIGHT));
        assertFalse(board.isDirectionValid(3, 0, Direction.UPLEFT));
        assertFalse(board.isDirectionValid(3, 0, Direction.UPRIGHT));
        assertTrue(board.isDirectionValid(3, 0, Direction.DOWNLEFT));
        assertFalse(board.isDirectionValid(3, 0, Direction.DOWNRIGHT));

        // 3; 1
        assertFalse(board.isDirectionValid(3, 1, Direction.UP));
        assertFalse(board.isDirectionValid(3, 1, Direction.DOWN));
        assertTrue(board.isDirectionValid(3, 1, Direction.LEFT));
        assertFalse(board.isDirectionValid(3, 1, Direction.RIGHT));
        assertFalse(board.isDirectionValid(3, 1, Direction.UPLEFT));
        assertFalse(board.isDirectionValid(3, 1, Direction.UPRIGHT));
        assertFalse(board.isDirectionValid(3, 1, Direction.DOWNLEFT));
        assertFalse(board.isDirectionValid(3, 1, Direction.DOWNRIGHT));

        // 3; 2
        assertFalse(board.isDirectionValid(3, 2, Direction.UP));
        assertFalse(board.isDirectionValid(3, 2, Direction.DOWN));
        assertTrue(board.isDirectionValid(3, 2, Direction.LEFT));
        assertFalse(board.isDirectionValid(3, 2, Direction.RIGHT));
        assertTrue(board.isDirectionValid(3, 2, Direction.UPLEFT));
        assertFalse(board.isDirectionValid(3, 2, Direction.UPRIGHT));
        assertFalse(board.isDirectionValid(3, 2, Direction.DOWNLEFT));
        assertFalse(board.isDirectionValid(3, 2, Direction.DOWNRIGHT));

        // 3; 3
        assertFalse(board.isDirectionValid(3, 3, Direction.UP));
        assertFalse(board.isDirectionValid(3, 3, Direction.DOWN));
        assertFalse(board.isDirectionValid(3, 3, Direction.LEFT));
        assertFalse(board.isDirectionValid(3, 3, Direction.RIGHT));
        assertTrue(board.isDirectionValid(3, 3, Direction.UPLEFT));
        assertFalse(board.isDirectionValid(3, 3, Direction.UPRIGHT));
        assertFalse(board.isDirectionValid(3, 3, Direction.DOWNLEFT));
        assertFalse(board.isDirectionValid(3, 3, Direction.DOWNRIGHT));
    }

}