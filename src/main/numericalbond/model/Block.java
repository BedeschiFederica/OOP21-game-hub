package main.numericalbond.model;

import java.util.HashMap;
import java.util.Map;

public class Block {
	
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
		this.linksPerSide.put(direction, getLinks(direction) + 1);
	}
	
	public int getCurrentLinks() {
		return this.linksPerSide.values().stream().reduce(0, (x, y) -> x + y);
	}

	@Override
	public String toString() {
		return "Block [maxLinks=" + maxLinks + "]";
	}
	
}
