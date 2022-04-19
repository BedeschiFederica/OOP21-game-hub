package main.numericalbond.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import main.numericalbond.Position;

public class LevelGenerator {

    private Grid grid;

    public LevelGenerator(final int numLines) {
        this.grid = new Grid(numLines);
        generate(numLines);
    }

    private void generate(final int numLines) {
        createRandomLinks(numLines);
        initialiseBlocks();
    }

    private void createRandomLinks(final int numLines) {
        final int numLinks = (int) (Math.pow(numLines, 3) / 2);
        final Random rand = new Random();
        for (int count = 0; count < numLinks;) {
            final Position randomPos = new Position(rand.nextInt(numLines), rand.nextInt(numLines));
            final Block firstBlock = this.grid.getBlockAt(randomPos);
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
            // System.out.println(firstBlock);
            // System.out.println(secondBlock);
        }
        // System.out.println(this.grid);
        // this.grid.getBlocks().forEach((p, b) -> System.out.println(p + ", CurrentLinks=" + b.getCurrentLinks()));
    }

    private void initialiseBlocks() {
        final Map<Position, Block> initialisedBlocks = new HashMap<>();
        this.grid.getBlocks().forEach((p, b) -> initialisedBlocks.put(p, new Block(b.getCurrentLinks())));
        this.grid = new Grid(this.grid.getNumLines(), initialisedBlocks);
    }

    public Grid getGrid() {
        return this.grid;
    }

}
