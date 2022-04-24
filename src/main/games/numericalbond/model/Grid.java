package main.games.numericalbond.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;

import main.games.numericalbond.controller.Position;

/**
 * Class that represents a (square) grid of blocks.
 */
public class Grid {

    private static final int MAX_LINKS_PER_BLOCK = 99;
    private static final int FIRST_LINE = 0;

    private final int lastLine;
    private final Map<Position, Block> blocks;

    /**
     * Builds a new {@link Grid}.
     * @param numLines
     *          the number of lines that the square grid has
     */
    public Grid(final int numLines) {
        this.lastLine = FIRST_LINE + numLines - 1;
        this.blocks = new HashMap<>();
        IntStream.range(FIRST_LINE, this.lastLine + 1)
                .forEach(i -> IntStream.range(FIRST_LINE, this.lastLine + 1)
                        .forEach(j -> this.blocks.put(new Position(i, j), new Block(MAX_LINKS_PER_BLOCK))));
    }

    /**
     * Builds a new {@link Grid}.
     * @param numLines
     *          the number of lines that the square grid has
     * @param blocks
     *          the map of the blocks that the grid will have
     */
    public Grid(final int numLines, final Map<Position, Block> blocks) {
        this.lastLine = FIRST_LINE + numLines - 1;
        this.blocks = Map.copyOf(blocks);
    }

    /**
     * Gets the number of lines of the grid.
     * @return the number of lines of the grid
     */
    public int getNumLines() {
        return this.lastLine - FIRST_LINE + 1;
    }

    /**
     * Gets the map of the blocks.
     * @return the map of the blocks
     */
    public Map<Position, Block> getBlocks() {
        return Map.copyOf(this.blocks);
    }

    /**
     * Gets the block in the given position.
     * @param position
     *          the position of the block
     * @return the block in the given position
     */
    public Block getBlockAt(final Position position) {
        if (!check(position)) {
            throw new IllegalArgumentException("Illegal position");
        }
        return this.blocks.get(position);
    }

    private boolean check(final Position position) {
        return position != null
                && position.getX() >= FIRST_LINE && position.getX() <= this.lastLine
                && position.getY() >= FIRST_LINE && position.getY() <= this.lastLine;
    }

    /**
     * Gets the nearby block of the block in the given position in the given direction.
     * @param position
     *          the position of the initial block
     * @param direction
     *          the direction of nearby block
     * @return the nearby block
     */
    public Block getNearbyBlock(final Position position, final Direction direction) {
        Objects.requireNonNull(direction);
        if (!check(position)) {
            throw new IllegalArgumentException("Illegal position");
        }
        final Position nearbyBlockPosition = direction.getPosition(position);
        if (!check(nearbyBlockPosition)) {
            throw new IllegalStateException("Illegal position");
        }
        return this.blocks.get(nearbyBlockPosition);
    }

    /**
     * Gets, if possible, the direction from the first given position to the second given position.
     * @param pos1
     *          the first position
     * @param pos2
     *          the second position
     * @return the direction from pos1 to pos2, Optional.empty() if the positions aren't adjacent
     */
    public Optional<Direction> getDirection(final Position pos1, final Position pos2) {
        if (!check(pos1) || !check(pos2)) {
            throw new IllegalArgumentException("Illegal position");
        }
        for (final Direction d : Direction.getDirections()) {
            if (d.getPosition(pos1).equals(pos2)) {
                return Optional.of(d);
            }
        }
        return Optional.empty();
    }

    /**
     * Tells if the two blocks in the given positions can link or not.
     * It checks adjacency.
     * @param pos1
     *          the position of the first block
     * @param pos2
     *          the position of the second block
     * @return true if the two blocks specified by pos1 and pos2 can link
     */
    public boolean canLink(final Position pos1, final Position pos2) {
        return !getDirection(pos1, pos2).isEmpty();
    }

    /**
     * Links the two blocks specified by the given positions.
     * @param pos1
     *          the position of the first block
     * @param pos2
     *          the position of the second block
     */
    public void link(final Position pos1, final Position pos2) {
        if (!canLink(pos1, pos2)) {
            throw new IllegalStateException("Can't link");
        }
        final Optional<Direction> directionFrom1To2 = getDirection(pos1, pos2);
        this.blocks.get(pos1).link(directionFrom1To2.get());
        this.blocks.get(pos2).link(directionFrom1To2.get().opposite());
    }

    /**
     * Gets the number of links between the two blocks specified by the given positions.
     * @param pos1
     *          the position of the first block
     * @param pos2
     *          the position of the second block
     * @return the number of links between the two blocks
     */
    public int getLinks(final Position pos1, final Position pos2) {
        final Optional<Direction> directionFrom1To2 = getDirection(pos1, pos2);
        if (directionFrom1To2.isEmpty()) {
            throw new IllegalStateException("Positions aren't adjacent");
        }
        return this.blocks.get(pos1).getLinks(directionFrom1To2.get());
    }

    /**
     * Tells if the grid is complete.
     * Every block needs to have the number of currents links equal to their maximum one.
     * @return true if the grid is complete.
     */
    public boolean isComplete() {
        for (final Block b : this.blocks.values()) {
            if (b.getCurrentLinks() != b.getMaxLinks()) {
                return false;
            }
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Grid [numLines=" + (this.lastLine - FIRST_LINE + 1) + ", blocks=" + this.blocks + "]";
    }

}
