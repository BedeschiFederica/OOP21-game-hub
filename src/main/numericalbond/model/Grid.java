package main.numericalbond.model;

import java.util.HashMap;
import java.util.Map;
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
		return this.blocks.get(position);
	}
	
	public Block getNearbyBlock(final Position position, final Direction direction) {
		if (!check(position)) {
			throw new IllegalArgumentException("Illegal position");
		}
		Position nearbyBlockPosition;
		switch (direction) {
		case UP:
			nearbyBlockPosition = new Position(position.getX() - 1, position.getY());
			break;
		case RIGHT:
			nearbyBlockPosition = new Position(position.getX(), position.getY() + 1);
			break;
		case DOWN:
			nearbyBlockPosition = new Position(position.getX() + 1, position.getY());
			break;
		case LEFT:
			nearbyBlockPosition = new Position(position.getX(), position.getY() - 1);
			break;
		default:
			throw new NullPointerException("Null direction");
		}
		if (!check(nearbyBlockPosition)) {
			throw new IllegalStateException("Illegal position");
		}
		return this.blocks.get(nearbyBlockPosition);
	}

	private boolean check(final Position position) {
		return position != null
				&& position.getX() >= FIRST_LINE && position.getX() <= this.lastLine
				&& position.getY() >= FIRST_LINE && position.getY() <= this.lastLine;
	}

	@Override
	public String toString() {
		return "Grid [numLines=" + (this.lastLine - FIRST_LINE + 1) + ", blocks=" + this.blocks + "]";
	}
	
}
