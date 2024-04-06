package esi.atl.g60552.othello.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiskTest {
    Disk blackDisk;
    Disk whiteDisk;

    @BeforeEach
    void init() {
        blackDisk = new Disk(DiskColor.BLACK);
        whiteDisk = new Disk(DiskColor.WHITE);
    }

    @Test
    void testBlackGetColor() {
        assertEquals(blackDisk.getColor(), DiskColor.BLACK);
    }

    @Test
    void testWhiteGetColor() {
        assertEquals(whiteDisk.getColor(), DiskColor.WHITE);
    }


    @Test
    void testFlipBlackDiskFromBlackDisk() {
        blackDisk.flip();
        assertEquals(blackDisk.getColor(), DiskColor.WHITE);
    }

    @Test
    void testGetOppositeFromWhiteDisk() {
        Disk opposite = whiteDisk.getOpposite();
        assertEquals(opposite.getColor(), DiskColor.BLACK);
    }
}