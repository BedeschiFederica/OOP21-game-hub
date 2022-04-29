package test;

import java.util.ArrayList;

import main.games.minefield.controller.MinefieldController;

public class MinefieldTest {

    private int mines = 2;
    private int size = 2;
    private MinefieldController cont = new MinefieldController();
    /**
     * test if the grid size is correct.
     */
    void testSize() {
        int sizeGrid = size * size;
        System.out.print(sizeGrid); //4
    }

    /**
     * test the mines.
     */
    void testMinePosition() {
        boolean mine = false;
        ArrayList<Integer> minesContains = new ArrayList<Integer>();
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
            }
    }
    /**
     * test the controller.
     * the
     */
    void testView() {
        //cont.startGame(size, mines);
    }
}
