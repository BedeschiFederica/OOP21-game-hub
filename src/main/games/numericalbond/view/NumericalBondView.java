package main.games.numericalbond.view;

import main.gamehub.model.GameView;
import main.games.numericalbond.utility.Position;

/**
 * Interface that represents a view of the game Numerical bond.
 */
public interface NumericalBondView extends GameView {

    /**
     * Sets the number of the block specified by the given position to the given block number.
     * @param position
     *          the position of the block
     * @param blockNumber
     *          the block number that has to be set
     */
    void setBlockNumber(Position position, int blockNumber);

    /**
     * Creates a link between the two blocks specified by the given positions.
     * @param pos1
     *          the position of the first block
     * @param pos2
     *          the position of the second block
     * @param links
     *          the number of existing links between the two blocks
     */
    void createLink(Position pos1, Position pos2, int links);

    /**
     * Deselects the selected block.
     */
    void deselect();

    /**
     * Closes the view.
     */
    void dispose();

}
