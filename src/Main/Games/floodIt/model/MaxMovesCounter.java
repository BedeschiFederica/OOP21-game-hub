package main.games.floodit.model;

/**
 * Counts the maximum moves.
 */
public class MaxMovesCounter implements MovesCounter {

    private static final int MAX_MOVES_EASY = 16;
    private static final int MAX_MOVES_MEDIUM = 28;
    private static final int MAX_MOVES_HARD = 40;
    private static final int SIZE_EASY = 5;
    private static final int SIZE_MEDIUM = 10;
    private static final int SIZE_HARD = 15;
    private int size;

    public MaxMovesCounter(final int size) {
        this.size = size;
    }

    /**
     * Counts the max moves.
     */
    @Override
    public int count() {
        switch (size) {
        case SIZE_EASY:
            return MAX_MOVES_EASY;
        case SIZE_MEDIUM:
            return MAX_MOVES_MEDIUM;
        case SIZE_HARD:
            return MAX_MOVES_HARD;
        default:
            return 0;
        }
    }

    /**
     * Sets the size for the maximum moves count.
     * 
     * @param newSize The size for the count.
     */
    public void setSize(final int newSize) {
        this.size = newSize;
    }

}
