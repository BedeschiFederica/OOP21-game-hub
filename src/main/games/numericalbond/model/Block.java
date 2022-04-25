package main.games.numericalbond.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Block {

    private static final int DEFAULT_LINKS_PER_SIDE = 0;
    private static final int MAX_LINKS_PER_SIDE = 2;

    private final int maxLinks;
    private final Map<Direction, Integer> linksPerSide;

    public Block(final int maxLinks) {
        this.maxLinks = maxLinks;
        this.linksPerSide = new HashMap<>();
        for (final Direction d : Direction.values()) {
            this.linksPerSide.put(d, DEFAULT_LINKS_PER_SIDE);
        }
    }

    public int getMaxLinks() {
        return this.maxLinks;
    }

    public int getLinks(final Direction direction) {
        Objects.requireNonNull(direction);
        return this.linksPerSide.get(direction);
    }

    public boolean canLink(final Direction direction) {
        Objects.requireNonNull(direction);
        return this.linksPerSide.get(direction) < MAX_LINKS_PER_SIDE;
    }

    public void addLink(final Direction direction) {
        if (!canLink(direction)) {
            throw new IllegalStateException();
        }
        this.linksPerSide.put(direction, getLinks(direction) + 1);
    }

    private void resetLinks(final Direction direction) {
        this.linksPerSide.put(direction, DEFAULT_LINKS_PER_SIDE);
    }

    public void link(final Direction direction) {
        if (!canLink(direction)) {
            resetLinks(direction);
        } else {
            addLink(direction);
        }
    }

    public int getCurrentLinks() {
        return this.linksPerSide.values().stream().reduce(0, (x, y) -> x + y);
    }

    @Override
    public String toString() {
        return "Block [maxLinks=" + this.maxLinks + ", linksPerSide" + this.linksPerSide + "]";
    }

}
