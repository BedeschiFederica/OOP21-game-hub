package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.general.GameController;
import main.numericalbond.NumericalBondController;
import main.numericalbond.model.Block;
import main.numericalbond.model.Direction;

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
