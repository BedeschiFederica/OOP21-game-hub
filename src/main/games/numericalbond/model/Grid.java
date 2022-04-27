package main.games.numericalbond.model;

import java.util.Map;
import java.util.Optional;

import main.games.numericalbond.utility.Position;

/**
 * Interface that represents a (square) grid of blocks.
 */
public interface Grid {

    /**
     * Gets the number of lines of the grid.
     * @return the number of lines of the grid
     */
    int getNumLines();

    /**
     * Gets the map of the blocks.
     * @return the map of the blocks
     */
    Map<Position, Block> getBlocks();

    /**
     * Tells if the given position is legal or not.
     * To be legal it has to be in range 0 - getNumLines().
     * @param position
     *          the position to check
     * @return true if the given position is legal
     */
    boolean isLegal(Position position);

    /**
     * Gets the block in the given position.
     * Note: the given position must be legal, otherwise you'll get an IllegalArgumentException.
     * @param position
     *          the position of the block
     * @return the block in the given position
     */
    Block getBlockAt(Position position);

    /**
     * Gets, if possible, the position next to the given one in the given direction.
     * Note: the given position must be legal, otherwise you'll get an IllegalArgumentException.
     * @param position
     *          the initial position
     * @param direction
     *          the direction of the nearby position
     * @return the nearby position if it's legal, Optional.empty() otherwise
     */
    Optional<Position> getNearbyPosition(Position position, Direction direction);

    /**
     * Gets, if possible, the direction from the first given position to the second given position.
     * Note: the given positions must be legal, otherwise you'll get an IllegalArgumentException.
     * @param pos1
     *          the first position
     * @param pos2
     *          the second position
     * @return the direction from pos1 to pos2, Optional.empty() if the positions aren't adjacent
     */
    Optional<Direction> getDirection(Position pos1, Position pos2);

    /**
     * Tells if the two blocks in the given positions can link or not.
     * It checks adjacency.
     * Note: the given positions must be legal, otherwise you'll get an IllegalArgumentException.
     * @param pos1
     *          the position of the first block
     * @param pos2
     *          the position of the second block
     * @return true if the two blocks specified by pos1 and pos2 can link
     */
    boolean canLink(Position pos1, Position pos2);

    /**
     * Links the two blocks specified by the given positions.
     * Note: you should call the method canLink() before calling this one, otherwise
     *       you'll get an IllegalStateException if the two blocks can't link.
     * @param pos1
     *          the position of the first block
     * @param pos2
     *          the position of the second block
     */
    void link(Position pos1, Position pos2);

    /**
     * Gets the number of links between the two blocks specified by the given positions.
     * Note: the given positions must be legal and adjacent, otherwise you'll get an IllegalArgumentException.
     * @param pos1
     *          the position of the first block
     * @param pos2
     *          the position of the second block
     * @return the number of links between the two blocks
     */
    int getLinks(Position pos1, Position pos2);

    /**
     * Tells if the grid is complete.
     * Every block needs to have the number of currents links equal to their maximum one.
     * @return true if the grid is complete.
     */
    boolean isComplete();

}
