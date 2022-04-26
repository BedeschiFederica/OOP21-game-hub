package main.games.numericalbond.utility;

import java.util.Objects;

/**
 * Class that represents a position specified by its coordinates x and y.
 */
public class Position {

    private final int x;
    private final int y;

    /**
     * Builds a new {@link Position}.
     * @param x
     *          its x coordinate
     * @param y
     *          its y coordinate
     */
    public Position(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Gets the x coordinate.
     * @return the x coordinate
     */
    public int getX() {
        return this.x;
    }

    /**
     * Gets the y coordinate.
     * @return the y coordinate
     */
    public int getY() {
        return this.y;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.x, this.y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Position other = (Position) obj;
        return this.x == other.x && this.y == other.y;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Position [x=" + this.x + ", y=" + this.y + "]";
    }

}
