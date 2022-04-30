package main.games.numericalbond.utility;

/**
 * Interface that represents a position specified by its coordinates x and y.
 * It's like a data type, it's made to be used everywhere you need it.
 */
public interface Position {

    /**
     * Gets the x coordinate.
     * @return the x coordinate
     */
    int getX();

    /**
     * Gets the y coordinate.
     * @return the y coordinate
     */
    int getY();

    /**
     * {@inheritDoc}
     */
    int hashCode();

    /**
     * {@inheritDoc}
     */
    boolean equals(Object obj);

}
