package main.games.numericalbond.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Class that represents a block that can be linked to another block.
 */
public class BlockImpl implements Block {

    private static final int DEFAULT_LINKS_PER_SIDE = 0;
    private static final int MAX_LINKS_PER_SIDE = 2;

    private final int linksToHave;
    private final Map<Direction, Integer> linksPerSide;

    /**
     * Builds a new {@link BlockImpl}.
     * @param linksToHave
     *          the total number of links that the block will have to have
     */
    public BlockImpl(final int linksToHave) {
        this.linksToHave = linksToHave;
        this.linksPerSide = new HashMap<>();
        for (final Direction d : Direction.values()) {
            this.linksPerSide.put(d, DEFAULT_LINKS_PER_SIDE);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getLinksToHave() {
        return this.linksToHave;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getLinks(final Direction direction) {
        Objects.requireNonNull(direction);
        return this.linksPerSide.get(direction);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCurrentLinks() {
        return this.linksPerSide.values().stream().reduce(0, (x, y) -> x + y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canLink(final Direction direction) {
        Objects.requireNonNull(direction);
        return this.linksPerSide.get(direction) < MAX_LINKS_PER_SIDE;
    }

    /**
     * {@inheritDoc}
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
     * {@inheritDoc}
     */
    @Override
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
        return "BlockImpl [linksToHave=" + this.linksToHave + ", linksPerSide" + this.linksPerSide + "]";
    }

}
