package test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import main.games.minefield.controller.MinefieldController;
import main.games.minefield.model.Handler;
import main.games.minefield.view.ViewField;

public class MinefieldTest {

    private int mines = 2;
    private int size = 2;
    private MinefieldController cont = new MinefieldController();
    private Handler handler = new Handler(cont);
    /**
     * test if the grid size is correct.
     */
    void testSize() {
        int sizeGrid = size * size;
        assertEquals(sizeGrid, 4);
        ViewField viewField = new ViewField(size, mines, "Minefield - ", cont, handler);
    }

    /**
     * test the mines.
     */
    void testMinePosition() {
        boolean mine = false;
        ArrayList<Integer> minesContains = new ArrayList<Integer>();
        ArrayList<Integer> arrayCasual = new ArrayList<Integer>();
        arrayCasual.add(1);
        arrayCasual.add(2);
        for (int i = 1; i <= mines; i++) {
            while (!mine) {
                int minePosition = (int) (Math.random() * size * size);
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
