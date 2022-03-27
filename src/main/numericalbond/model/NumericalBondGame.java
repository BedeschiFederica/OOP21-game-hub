package main.numericalbond.model;

import java.util.Map;

public class NumericalBondGame {
	
	private static final int NUM_LINES = 3;
	
	private final Map<Position, Block> blocks;

	public NumericalBondGame() {
		LevelGenerator levelGenerator = new LevelGenerator(NUM_LINES);
		this.blocks = levelGenerator.getBlocks();
	}
	
}
