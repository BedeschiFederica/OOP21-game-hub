package main.numericalbond.model;

import java.util.Random;

public class LevelGenerator {
	
	private final Grid grid;

	public LevelGenerator(final int numLines) {
		this.grid = new Grid(numLines);
		generate(numLines);
	}
	
	private void generate(final int numLines) {
		createRandomLinks();
		
	}

	private void createRandomLinks() {
		final Random rand = new Random();
		for (int count = 0; count < 20;) {
			Position randomPos = new Position(rand.nextInt(3), rand.nextInt(3));
			Block firstBlock = this.grid.getBlockAt(randomPos);
			Direction randomDir;
			Block secondBlock;
			do {
				randomDir = Direction.getRandomDirection();
				try {
					secondBlock = this.grid.getNearbyBlock(randomPos, randomDir);
					break;
				} catch (IllegalStateException e) {
					continue;
				}
			} while (true);
			try {
				firstBlock.addLink(randomDir);
			} catch (IllegalStateException e) {
				continue;
			}
			secondBlock.addLink(randomDir.opposite());
			count++;
			//System.out.println(firstBlock);
			//System.out.println(secondBlock);
		}
		System.out.println(this.grid);
		this.grid.getBlocks().forEach((p,b) -> System.out.println(p + ", CurrentLinks=" + b.getCurrentLinks()));
	}

	public Grid getGrid() {
		return this.grid;
	}
	
}
