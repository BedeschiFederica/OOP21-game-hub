package test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

public class MinefieldTest {

    private int mines = 2;
    private int size = 2;
    /**
     * test if the grid size is correct.
     */
    void testSize() {
        final int sizeGrid = size * size;
        assertEquals(sizeGrid, 4);
    }

    /**
     * test the mines.
     */
    void testMinePosition() {
        boolean mine = false;
        final List<Integer> minesContains = new ArrayList<>();
        final List<Integer> arrayCasual = new ArrayList<>();
        arrayCasual.add(1);
        arrayCasual.add(2);
        for (int i = 1; i <= mines; i++) {
            while (!mine) {
                final int minePosition = (int) (Math.random() * size * size);
                if (!minesContains.contains(minePosition)) {
                    minesContains.add(minePosition);
                    mine = true;
                }
            }
            mine = false;
        }
        for (int i = 1; i <= mines; i++) {
            System.out.print(minesContains.get(i));
            assertEquals(minesContains.get(i), arrayCasual.get(i));
            }
    }
}
