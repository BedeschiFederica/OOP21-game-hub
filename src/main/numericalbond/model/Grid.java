package main.numericalbond.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;

import main.numericalbond.Position;

public class Grid {

    private static final int MAX_LINKS_PER_BLOCK = 99;
    private static final int FIRST_LINE = 0;

    private final int lastLine;
    private final Map<Position, Block> blocks;

    public Grid(final int numLines) {
        this.lastLine = FIRST_LINE + numLines - 1;
        this.blocks = new HashMap<>();
        IntStream.range(FIRST_LINE, this.lastLine + 1)
                .forEach(i -> IntStream.range(FIRST_LINE, this.lastLine + 1)
                        .forEach(j -> this.blocks.put(new Position(i, j), new Block(MAX_LINKS_PER_BLOCK))));
    }

    public Grid(final int numLines, final Map<Position, Block> blocks) {
        this.lastLine = FIRST_LINE + numLines - 1;
        this.blocks = Map.copyOf(blocks);
    }

    public int getNumLines() {
        return this.lastLine - FIRST_LINE + 1;
    }

    public Map<Position, Block> getBlocks() {
        return Map.copyOf(this.blocks);
    }

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

    // direction from pos1 to pos2
    // empty if not adjacents
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

    public boolean canLink(final Position pos1, final Position pos2) {
        return !getDirection(pos1, pos2).isEmpty();
    }

    public void link(final Position pos1, final Position pos2) {
        if (!canLink(pos1, pos2)) {
            throw new IllegalStateException("Can't link");
        }
        final Optional<Direction> directionFrom1To2 = getDirection(pos1, pos2);
        this.blocks.get(pos1).link(directionFrom1To2.get());
        this.blocks.get(pos2).link(directionFrom1To2.get().opposite());
    }

    public int getLinks(final Position pos1, final Position pos2) {
        final Optional<Direction> directionFrom1To2 = getDirection(pos1, pos2);
        if (directionFrom1To2.isEmpty()) {
            throw new IllegalStateException("Positions aren't adjacent");
        }
        return this.blocks.get(pos1).getLinks(directionFrom1To2.get());
    }

    public boolean isFinished() {
        for (final Block b : this.blocks.values()) {
            if (b.getCurrentLinks() != b.getMaxLinks()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "Grid [numLines=" + (this.lastLine - FIRST_LINE + 1) + ", blocks=" + this.blocks + "]";
    }

}
