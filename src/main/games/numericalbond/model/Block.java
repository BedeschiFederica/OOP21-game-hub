package main.games.numericalbond.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Class that represents a block that can be linked to another block.
 */
public class Block {

    private static final int DEFAULT_LINKS_PER_SIDE = 0;
    private static final int MAX_LINKS_PER_SIDE = 2;

    private final int maxLinks;
    private final Map<Direction, Integer> linksPerSide;

    /**
     * Builds a new {@link Block}.
     * @param maxLinks
     *          the total maximum of links that the block can have
     */
    public Block(final int maxLinks) {
        this.maxLinks = maxLinks;
        this.linksPerSide = new HashMap<>();
        for (final Direction d : Direction.values()) {
            this.linksPerSide.put(d, DEFAULT_LINKS_PER_SIDE);
        }
    }

    /**
     * Gets the total maximum of links that the block can have.
     * @return the total maximum of links
     */
    public int getMaxLinks() {
        return this.maxLinks;
    }

    /**
     * Gets the number of links that the block has in the given direction.
     * @param direction
     *          the direction of the links
     * @return the number of links in the given direction
     */
    public int getLinks(final Direction direction) {
        Objects.requireNonNull(direction);
        return this.linksPerSide.get(direction);
    }

    /**
     * Gets the current number of total links of the block.
     * @return the current number of total links of the block
     */
    public int getCurrentLinks() {
        return this.linksPerSide.values().stream().reduce(0, (x, y) -> x + y);
    }

    /**
     * Tells if the block can be linked in the given direction or not.
     * It checks the number of links per side.
     * @param direction
     *          the direction of the link
     * @return true if the block can be linked in the given direction
     */
    public boolean canLink(final Direction direction) {
        Objects.requireNonNull(direction);
        return this.linksPerSide.get(direction) < MAX_LINKS_PER_SIDE;
    }

    /**
     * Adds a link in the given direction.
     * Note: you should call the method canLink() before calling this one, otherwise
     *       you'll get an IllegalStateException if the two blocks can't link.
     * @param direction
     *          the direction of the link
     */
    public void addLink(final Direction direction) {
        if (!canLink(direction)) {
            throw new IllegalStateException();
        }
        this.linksPerSide.put(direction, getLinks(direction) + 1);
    }

    private void resetLinks(final Direction direction) {
        this.linksPerSide.put(direction, DEFAULT_LINKS_PER_SIDE);
    }

    /**
     * Links the block in the given direction.
     * If the maximum number of links in the given direction is reached,
     * the number of links will be reset to the default.
     * @param direction
     *          the direction of the link
     */
    public void link(final Direction direction) {
        if (!canLink(direction)) {
            resetLinks(direction);
        } else {
            addLink(direction);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Block [maxLinks=" + this.maxLinks + ", linksPerSide" + this.linksPerSide + "]";
    }

}
