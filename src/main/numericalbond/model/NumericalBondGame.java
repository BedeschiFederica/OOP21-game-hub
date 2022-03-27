package main.numericalbond.model;

import java.util.Map;

public class NumericalBondGrid {
	
	private final Map<Position, Block> blocks;

	public NumericalBondGrid() {
		LevelGenerator levelGenerator = new LevelGenerator();
		this.blocks = levelGenerator.getBlocks();
	}
	
}
