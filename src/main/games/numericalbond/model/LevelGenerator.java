package main.games.numericalbond.model;

/**
 * Interface that represents a level generator of the game Numerical Bond.
 */
public interface LevelGenerator {

    /**
     * Gets the generated grid for the game.
     * @return the grid
     */
    Grid getGrid();

}
