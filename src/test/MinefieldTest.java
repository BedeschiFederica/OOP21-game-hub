package test;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import main.games.minefield.controller.Cell;
import main.games.minefield.view.ViewField;

class MinefieldTest {

    private static final int MINES = 2;
    private static final int SIZE = 2;
    private static final int TYPE = 0;
    private static final int POSITION = 10;

    /**
     * test if the grid size is correct.
     */
    @Test
    void testSize() {
        new ViewField(SIZE, MINES, "", null, null);
        assertEquals(ViewField.getGridSize(), SIZE);
    }

    /**
     * test cell.
     */
    @Test
    void testCell() {
        final Cell cell = new Cell(TYPE, POSITION, false, false, null);
        assertEquals(cell.getType(), TYPE);
        assertEquals(cell.getPosition(), POSITION);
        cell.setDiscovered(true);
        assertEquals(cell.isDiscovered(), true);
        cell.setFlagged(true);
        assertEquals(cell.isFlagged(), true);
    }
}
