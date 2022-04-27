package main.games.numericalbond.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import main.games.numericalbond.utility.Position;

/**
 * Class that represents a level generator of the game Numerical Bond.
 */
public class LevelGeneratorImpl implements LevelGenerator {

    private static final Random RANDOM_SEED = new Random();

    private Grid grid;

    /**
     * Builds a new {@link LevelGeneratorImpl}.
     * @param numLines
     *          the number of lines that the grid of the game will have
     */
    public LevelGeneratorImpl(final int numLines) {
        this.grid = new GridImpl(numLines);
        generate(numLines);
    }

    private void generate(final int numLines) {
        createRandomLinks(numLines);
        initialiseBlocks();
    }

    private void createRandomLinks(final int numLines) {
        final int numLinks = (int) (Math.pow(numLines, 3) / 2);
        for (int count = 0; count < numLinks;) {
            final Position firstPos = new Position(RANDOM_SEED.nextInt(numLines), RANDOM_SEED.nextInt(numLines));
            final Block firstBlock = this.grid.getBlockAt(firstPos);
            Direction direction;
            Optional<Position> secondPos;
            do {
                direction = Direction.getRandomDirection();
                secondPos = this.grid.getNearbyPosition(firstPos, direction);
            } while (secondPos.isEmpty());
            if (firstBlock.canLink(direction)) {
                firstBlock.addLink(direction);
            } else {
                continue;
            }
            this.grid.getBlockAt(secondPos.get()).addLink(direction.opposite());
            count++;
        }
    }

    private void initialiseBlocks() {
        final Map<Position, Block> initialisedBlocks = new HashMap<>();
        this.grid.getBlocks().forEach((p, b) -> initialisedBlocks.put(p, new BlockImpl(b.getCurrentLinks())));
        this.grid = new GridImpl(this.grid.getNumLines(), initialisedBlocks);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Grid getGrid() {
        return this.grid;
    }

}
