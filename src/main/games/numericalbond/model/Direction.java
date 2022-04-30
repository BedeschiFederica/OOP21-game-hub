package main.games.numericalbond.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.function.Function;

import main.games.numericalbond.utility.Position;
import main.games.numericalbond.utility.PositionImpl;

/**
 * Enum that represents a direction.
 * Each value is able to calculate the position next to another one in that direction.
 */
public enum Direction {

    /**
     * UP. X coordinate lowered by one.
     */
    UP(p -> new PositionImpl(p.getX() - 1, p.getY())),

    /**
     * RIGHT. Y coordinate increased by one.
     */
    RIGHT(p -> new PositionImpl(p.getX(), p.getY() + 1)),

    /**
     * DOWN. X coordinate increased by one.
     */
    DOWN(p -> new PositionImpl(p.getX() + 1, p.getY())),

    /**
     * LEFT. Y coordinate lowered by one.
     */
    LEFT(p -> new PositionImpl(p.getX(), p.getY() - 1));

    private static final Random RANDOM_SEED = new Random();

    private final Function<Position, Position> calculatePositionFunc;

    Direction(final Function<Position, Position> calculatePositionFunc) {
        this.calculatePositionFunc = calculatePositionFunc;
    }

    /**
     * Gets the position next to the given one, in that direction.
     * @param pos
     *          the initial position
     * @return the position next to pos in that direction
     */
    public Position getPosition(final Position pos) {
        Objects.requireNonNull(pos);
        return this.calculatePositionFunc.apply(pos);
    }

    /**
     * Gets its opposite direction.
     * @return its opposite direction
     */
    public Direction opposite() {
        switch (this) {
        case UP:
            return DOWN;
        case DOWN:
            return UP;
        case RIGHT:
            return LEFT;
        case LEFT:
            return RIGHT;
        default:
            throw new IllegalStateException();
        }
    }

    /**
     * Gets all directions.
     * @return all directions
     */
    public static List<Direction> getDirections() {
        return Arrays.asList(Direction.values());
    }

    /**
     * Gets a random direction.
     * @return a random direction
     */
    public static Direction getRandomDirection() {
        return Direction.values()[RANDOM_SEED.nextInt(Direction.values().length)];
    }

}
