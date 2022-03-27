package main.numericalbond.model;

import java.util.HashMap;
import java.util.Map;

public class LevelGenerator {
	
	private static final int MAX_LINKS_PER_BLOCK = 99;
	
	private final int numLines;
	private final Map<Position, Block> blocks = new HashMap<>();

	public LevelGenerator(final int numLines) {
		this.numLines = numLines;
		final int numBlocks = this.numLines * this.numLines;
		for (int i = 0; i < numBlocks; i++) {
			this.blocks.put(new Position(i / numLines, i % numLines), new Block(MAX_LINKS_PER_BLOCK));
		}
		generate();
	}
	
	private void generate() {
		
	}

	public Map<Position, Block> getBlocks() {
		return this.blocks;
	}
	
}
