package main.games.numericalbond.model;

/**
 * Interface that represents a block that can be linked to another block.
 */
public interface Block {

    /**
     * Gets the total number of links that the block will have to have.
     * @return the total number of links that the block will have to have
     */
    int getLinksToHave();

    /**
     * Gets the number of links that the block has in the given direction.
     * @param direction
     *          the direction of the links
     * @return the number of links in the given direction
     */
    int getLinks(Direction direction);

    /**
     * Gets the current number of total links of the block.
     * @return the current number of total links of the block
     */
    int getCurrentLinks();

    /**
     * Tells if the block can be linked in the given direction or not.
     * It checks the number of links per side.
     * @param direction
     *          the direction of the link
     * @return true if the block can be linked in the given direction
     */
    boolean canLink(Direction direction);

    /**
     * Adds a link in the given direction.
     * Note: you should call the method canLink() before calling this one, otherwise
     *       you'll get an IllegalStateException if the two blocks can't link.
     * @param direction
     *          the direction of the link
     */
    void addLink(Direction direction);

    /**
     * Links the block in the given direction.
     * If the maximum number of links in the given direction is reached,
     * the number of links will be reset to the default.
     * @param direction
     *          the direction of the link
     */
    void link(Direction direction);

}
