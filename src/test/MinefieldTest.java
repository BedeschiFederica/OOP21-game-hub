package test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class MinefieldTest {

    private static final int MINES = 2;
    private static final int SIZE = 2;

    /**
     * test if the grid size is correct.
     */
    @Test
    void testSize() {
        final int sizeGrid = SIZE * SIZE;
        assertEquals(sizeGrid, 4);
    }

    /**
     * test the mines.
     */
    @Test
    void testMinePosition() {
        boolean mine = false;
        final List<Integer> minesContains = new ArrayList<>();
        final List<Integer> arrayCasual = new ArrayList<>();
        arrayCasual.add(1);
        arrayCasual.add(2);
        for (int i = 1; i <= MINES; i++) {
            while (!mine) {
                final int minePosition = (int) (Math.random() * SIZE * SIZE);
                if (!minesContains.contains(minePosition)) {
                    minesContains.add(minePosition);
                    mine = true;
                }
            }
            mine = false;
        }
        for (int i = 1; i <= MINES; i++) {
            System.out.print(minesContains.get(i));
            assertEquals(minesContains.get(i), arrayCasual.get(i));
        }
    }
}
