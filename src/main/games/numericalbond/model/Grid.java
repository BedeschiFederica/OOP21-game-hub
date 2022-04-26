package main.games.numericalbond.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;

import main.games.numericalbond.utility.Position;

/**
 * Class that represents a (square) grid of blocks.
 */
public class Grid {

    private static final int MAX_LINKS_PER_BLOCK = 99;

    private final int numLines;
    private final Map<Position, Block> blocks;

    /**
     * Builds a new {@link Grid}.
     * @param numLines
     *          the number of lines that the square grid has
     */
    public Grid(final int numLines) {
        this.numLines = numLines;
        this.blocks = new HashMap<>();
        IntStream.range(0, this.numLines)
                .forEach(i -> IntStream.range(0, this.numLines)
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
        this.numLines = numLines;
        this.blocks = Map.copyOf(blocks);
    }

    /**
     * Gets the number of lines of the grid.
     * @return the number of lines of the grid
     */
    public int getNumLines() {
        return this.numLines;
    }

    /**
     * Gets the map of the blocks.
     * @return the map of the blocks
     */
    public Map<Position, Block> getBlocks() {
        return Map.copyOf(this.blocks);
    }

    /**
     * Tells if the given position is legal or not.
     * To be legal it has to be in range 0 - getNumLines().
     * @param position
     *          the position to check
     * @return true if the given position is legal
     */
    public boolean isLegal(final Position position) {
        return position != null
                && position.getX() >= 0 && position.getX() < this.numLines
                && position.getY() >= 0 && position.getY() < this.numLines;
    }

    private void requireLegalPosition(final Position position) {
        if (!isLegal(position)) {
            throw new IllegalArgumentException("Illegal position");
        }
    }

    /**
     * Gets the block in the given position.
     * Note: the given position must be legal, otherwise you'll get an IllegalArgumentException.
     * @param position
     *          the position of the block
     * @return the block in the given position
     */
    public Block getBlockAt(final Position position) {
        requireLegalPosition(position);
        return this.blocks.get(position);
    }

    /**
     * Gets, if possible, the position next to the given one in the given direction.
     * Note: the given position must be legal, otherwise you'll get an IllegalArgumentException.
     * @param position
     *          the initial position
     * @param direction
     *          the direction of the nearby position
     * @return the nearby position if it's legal, Optional.empty() otherwise
     */
    public Optional<Position> getNearbyPosition(final Position position, final Direction direction) {
        requireLegalPosition(position);
        Objects.requireNonNull(direction);
        if (isLegal(direction.getPosition(position))) {
            return Optional.of(direction.getPosition(position));
        }
        return Optional.empty();
    }

    /**
     * Gets, if possible, the direction from the first given position to the second given position.
     * Note: the given positions must be legal, otherwise you'll get an IllegalArgumentException.
     * @param pos1
     *          the first position
     * @param pos2
     *          the second position
     * @return the direction from pos1 to pos2, Optional.empty() if the positions aren't adjacent
     */
    public Optional<Direction> getDirection(final Position pos1, final Position pos2) {
        requireLegalPosition(pos1);
        requireLegalPosition(pos2);
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
     * Note: the given positions must be legal, otherwise you'll get an IllegalArgumentException.
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
     * Note: you should call the method canLink() before calling this one, otherwise
     *       you'll get an IllegalStateException if the two blocks can't link.
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
     * Note: the given positions must be legal and adjacent, otherwise you'll get an IllegalArgumentException.
     * @param pos1
     *          the position of the first block
     * @param pos2
     *          the position of the second block
     * @return the number of links between the two blocks
     */
    public int getLinks(final Position pos1, final Position pos2) {
        final Optional<Direction> directionFrom1To2 = getDirection(pos1, pos2);
        if (directionFrom1To2.isEmpty()) {
            throw new IllegalArgumentException("Positions aren't adjacent");
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
        return "Grid [numLines=" + this.numLines + ", blocks=" + this.blocks + "]";
    }

}
