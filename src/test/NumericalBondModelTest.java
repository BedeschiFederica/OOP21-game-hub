package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import main.games.numericalbond.model.Block;
import main.games.numericalbond.model.BlockImpl;
import main.games.numericalbond.model.Direction;
import main.games.numericalbond.model.Grid;
import main.games.numericalbond.model.GridImpl;
import main.games.numericalbond.utility.PositionImpl;

/**
 * Class that represents a JUnit test for the Numerical bond game's model.
 */
class NumericalBondModelTest {

    private static final int LINKS_PER_BLOCK = 5;
    private static final int GRID_SIZE = 3;

    /**
     * Test if the block returns the correct number of current links.
     */
    @Test
    void testBlockCurrentLinks() {
        final Block block = new BlockImpl(LINKS_PER_BLOCK);
        block.link(Direction.UP);
        assertEquals(block.getCurrentLinks(), 1);
        block.link(Direction.DOWN);
        assertEquals(block.getCurrentLinks(), 2);
        block.link(Direction.UP);
        assertEquals(block.getCurrentLinks(), 3);
    }

    /**
     * Test if the block works correctly when the number of links per side exceeds the maximum value.
     * It should reset the number of links of that side.
     * Note: I'm assuming that the maximum value is 2 because this is a characteristic of the game that
     *       shouldn't be changed.
     */
    @Test
    void testBlockTooMuchLinksPerSide() {
        final Block block = new BlockImpl(LINKS_PER_BLOCK);
        block.link(Direction.UP);
        block.link(Direction.UP);
        assertEquals(block.getCurrentLinks(), 2);
        block.link(Direction.UP);
        assertEquals(block.getCurrentLinks(), 0);
    }

    /**
     * Test if the block works correctly if it receives a null direction.
     */
    @Test
    void testBlockNullDirections() {
        final Block block = new BlockImpl(LINKS_PER_BLOCK);
        assertThrows(NullPointerException.class, () -> block.getLinks(null));
        assertThrows(NullPointerException.class, () -> block.canLink(null));
        assertThrows(NullPointerException.class, () -> block.addLink(null));
        assertThrows(NullPointerException.class, () -> block.link(null));
    }

    /**
     * Test if the grid recognizes legal or illegal positions.
     */
    @Test
    void testGridPositions() {
        final Grid grid = new GridImpl(GRID_SIZE);
        assertTrue(grid.isLegal(new PositionImpl(0, 0)));
        assertTrue(grid.isLegal(new PositionImpl(2, 2)));
        assertTrue(grid.isLegal(new PositionImpl(1, 0)));
        assertTrue(grid.isLegal(new PositionImpl(2, 1)));
        assertFalse(grid.isLegal(new PositionImpl(-1, 0)));
        assertFalse(grid.isLegal(new PositionImpl(1, -1)));
        assertFalse(grid.isLegal(new PositionImpl(2, 3)));
        assertFalse(grid.isLegal(new PositionImpl(4, 2)));
        assertFalse(grid.isLegal(null));
    }

    /**
     * Test if the grid returns the correct nearby positions.
     */
    @Test
    void testGridNearbyPositions() {
        final Grid grid = new GridImpl(GRID_SIZE);
        assertEquals(grid.getNearbyPosition(new PositionImpl(0, 0), Direction.RIGHT).get(), new PositionImpl(0, 1));
        assertEquals(grid.getNearbyPosition(new PositionImpl(1, 2), Direction.LEFT).get(), new PositionImpl(1, 1));
        assertEquals(grid.getNearbyPosition(new PositionImpl(2, 2), Direction.UP).get(), new PositionImpl(1, 2));
        assertEquals(grid.getNearbyPosition(new PositionImpl(0, 0), Direction.UP), Optional.empty());
        assertEquals(grid.getNearbyPosition(new PositionImpl(1, 0), Direction.LEFT), Optional.empty());
        assertEquals(grid.getNearbyPosition(new PositionImpl(2, 1), Direction.DOWN), Optional.empty());
    }

    /**
     * Test if the grid returns the correct direction from one position to another.
     */
    @Test
    void testGridDirections() {
        final Grid grid = new GridImpl(GRID_SIZE);
        assertEquals(grid.getDirection(new PositionImpl(0, 0), new PositionImpl(0, 1)).get(), Direction.RIGHT);
        assertEquals(grid.getDirection(new PositionImpl(0, 1), new PositionImpl(0, 0)).get(), Direction.LEFT);
        assertEquals(grid.getDirection(new PositionImpl(1, 2), new PositionImpl(2, 2)).get(), Direction.DOWN);
        assertEquals(grid.getDirection(new PositionImpl(0, 0), new PositionImpl(1, 1)), Optional.empty());
        assertEquals(grid.getDirection(new PositionImpl(2, 0), new PositionImpl(0, 2)), Optional.empty());
        assertEquals(grid.getDirection(new PositionImpl(1, 0), new PositionImpl(1, 2)), Optional.empty());
    }

    /**
     * Test if the grid returns the correct number of links between two blocks.
     */
    @Test
    void testGridLinks() {
        final Grid grid = new GridImpl(3);
        grid.link(new PositionImpl(0, 0), new PositionImpl(0, 1));
        assertEquals(grid.getLinks(new PositionImpl(0, 0), new PositionImpl(0, 1)), 1);
        grid.link(new PositionImpl(0, 0), new PositionImpl(0, 1));
        assertEquals(grid.getLinks(new PositionImpl(0, 0), new PositionImpl(0, 1)), 2);
        grid.link(new PositionImpl(0, 0), new PositionImpl(0, 1));
        assertEquals(grid.getLinks(new PositionImpl(0, 0), new PositionImpl(0, 1)), 0);
        grid.link(new PositionImpl(1, 1), new PositionImpl(2, 1));
        grid.link(new PositionImpl(1, 1), new PositionImpl(2, 1));
        assertEquals(grid.getLinks(new PositionImpl(1, 1), new PositionImpl(2, 1)), 2);
    }

    /**
     * Test if the grid works correctly if it receives illegal inputs (null objects or illegal positions).
     */
    @Test
    void testGridIllegalInputs() {
        assertThrows(NullPointerException.class, () -> new GridImpl(3, null));
        final Grid grid = new GridImpl(3);
        assertThrows(IllegalArgumentException.class, () -> grid.getBlockAt(null));
        assertThrows(IllegalArgumentException.class, () -> grid.getBlockAt(new PositionImpl(3, 0)));
        assertThrows(IllegalArgumentException.class,
                () -> grid.getNearbyPosition(new PositionImpl(0, -1), Direction.UP));
        assertThrows(NullPointerException.class,
                () -> grid.getNearbyPosition(new PositionImpl(1, 1), null));
        assertThrows(IllegalArgumentException.class,
                () -> grid.getDirection(new PositionImpl(1, 4), new PositionImpl(0, 0)));
        assertThrows(IllegalArgumentException.class,
                () -> grid.getDirection(new PositionImpl(0, 0), null));
        assertThrows(IllegalArgumentException.class,
                () -> grid.canLink(null, new PositionImpl(0, 0)));
        assertThrows(IllegalArgumentException.class,
                () -> grid.canLink(new PositionImpl(0, 0), new PositionImpl(-1, 1)));
        assertThrows(IllegalArgumentException.class,
                () -> grid.link(new PositionImpl(0, 0), null));
        assertThrows(IllegalArgumentException.class,
                () -> grid.link(new PositionImpl(2, -1), new PositionImpl(0, 0)));
        assertThrows(IllegalArgumentException.class,
                () -> grid.getLinks(null, new PositionImpl(0, 0)));
        assertThrows(IllegalArgumentException.class,
                () -> grid.getLinks(new PositionImpl(0, 0), new PositionImpl(3, 1)));
    }

}
