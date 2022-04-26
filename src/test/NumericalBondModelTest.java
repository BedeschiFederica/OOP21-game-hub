package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.games.numericalbond.controller.NumericalBondControllerImpl;
import main.games.numericalbond.model.Block;
import main.games.numericalbond.model.Direction;
import main.general.GameController;

class NumericalBondModelTest {

    @Test
    void test() {
        // fail("Not yet implemented");
    }

    @Test
    void blockTest() {
        final Block block = new Block(5);
        assertEquals(block.getMaxLinks(), 5);
        block.addLink(Direction.UP);
        assertEquals(block.getCurrentLinks(), 1);
        block.addLink(Direction.DOWN);
        block.addLink(Direction.UP);
        assertEquals(block.getCurrentLinks(), 3);
    }

}
