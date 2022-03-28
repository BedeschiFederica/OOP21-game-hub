package main.numericalbond.model;

import java.util.HashMap;
import java.util.Map;

public class Block {
	
	private final int MAX_LINKS_PER_SIDE = 2;
	
	private final int maxLinks;
	private final Map<Direction, Integer> linksPerSide;
	
	public Block(final int maxLinks) {
		this.maxLinks = maxLinks;
		this.linksPerSide = new HashMap<>();
		for (Direction d: Direction.values()) {
			this.linksPerSide.put(d, 0);
		}
	}

	public int getMaxLinks() {
		return this.maxLinks;
	}

	public int getLinks(final Direction direction) {
		return this.linksPerSide.get(direction);
	}
	
	public void addLink(final Direction direction) {
		if (this.linksPerSide.get(direction) == MAX_LINKS_PER_SIDE) {
			throw new IllegalStateException();
		}
		this.linksPerSide.put(direction, getLinks(direction) + 1);
	}
	
	public int getCurrentLinks() {
		return this.linksPerSide.values().stream().reduce(0, (x, y) -> x + y);
	}

	@Override
	public String toString() {
		return "Block [maxLinks=" + this.maxLinks + ", linksPerSide" + this.linksPerSide + "]";
	}
	
}
