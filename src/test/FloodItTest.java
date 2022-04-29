package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

import main.games.floodit.model.Cell;
import main.games.floodit.model.Colors;
import main.games.floodit.model.FloodItModel;
import main.games.floodit.model.MaxMovesCounter;
import main.games.floodit.model.Pair;
import main.games.floodit.model.Table;

class FloodItTest {


    /**
     * Tests if Colors enum reacts properly when recives wrong inputs.
     */
    @Test
    void testEnumColors() {
        final int largeNum = 12;
        final int littleNum = -4;
        assertThrows(IllegalArgumentException.class, () -> Colors.getRandomColors(largeNum));
        assertThrows(IllegalArgumentException.class, () -> Colors.getRandomColors(littleNum));
    }

    /**
     * Tests the cells flooding.
     */
    @Test
    void testCellFlooding() {
        final Pair<Integer, Integer> position = new Pair<>(2, 3);
        final Cell cell1 = new Cell(Colors.INDIGO, position);
        final Cell cell2 = new Cell(Colors.GREEN, position);
        assertFalse(cell1.isFlooded());
        assertFalse(cell2.isFlooded());
        cell1.flood();
        assertTrue(cell1.isFlooded());
        assertFalse(cell2.isFlooded());
    }

    /**
     * Tests the maximum moves generation.
     */
    @Test
    void testMaxMovesGeneration() {
        final FloodItModel model = new FloodItModel();
        final int size1 = 2;
        final int size2 = 10;
        final int maxMoves = 28;
        assertNull(model.getMCounter());
        model.setMCounter(new MaxMovesCounter(size1));
        assertNotNull(model.getMCounter());
        model.setMaxMoves();
        assertEquals(0, model.getMaxMoves());
        model.setMCounter(new MaxMovesCounter(size2));
        model.setMaxMoves();
        assertEquals(maxMoves, model.getMaxMoves());
    }

    /**
     * Tests Table generation. Checks if the adjacents cells of the cell at position (0, 0) are correctly linked.
     */
    @Test
    void testTable() {
        final Table table = new Table(4, 3, List.of(Colors.MAGENTA, Colors.LIME, Colors.PURPLE));
        table.generateTable();
        assertNull(table.getCell(0, 0).getAdjacentCells().get(0));                              // The cell at the top of top-left cell.
        assertEquals(table.getCell(1, 0), table.getCell(0, 0).getAdjacentCells().get(1));       // The cell at the bottom of top-left cell.
        assertNull(table.getCell(0, 0).getAdjacentCells().get(3));                              // The cell on the left of top-left cell.
        assertEquals(table.getCell(0, 1), table.getCell(0, 0).getAdjacentCells().get(2));       // The cell on the right of top-left cell.
    }

    /**
     * Tests FloodItModel resetting.
     */
    @Test
    void testModelResetting() {
        final FloodItModel model = new FloodItModel();
        final int num = 5;
        model.setTSize(num);
        model.setCurrentColor(Colors.ORANGE);
        model.setMCounter(new MaxMovesCounter(num));
        model.setMaxMoves();
        model.setNumofColors(num);
        assertNotNull(model.getRowSize()); 
        assertNotNull(model.getCurrentColor());
        assertNotNull(model.getMCounter()); 
        assertNotNull(model.getMaxMoves()); 
        assertNotNull(model.getNumOfColors()); 
        model.clear();
        assertEquals(0, model.getRowSize()); 
        assertNull(model.getCurrentColor());
        assertNull(model.getMCounter()); 
        assertEquals(0, model.getMaxMoves()); 
        assertEquals(0, model.getNumOfColors()); 
    }

}
