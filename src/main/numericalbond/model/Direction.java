package main.numericalbond.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.function.Function;

import main.numericalbond.Position;

public enum Direction {
    UP(p -> new Position(p.getX() - 1, p.getY())),
    RIGHT(p -> new Position(p.getX(), p.getY() + 1)),
    DOWN(p -> new Position(p.getX() + 1, p.getY())),
    LEFT(p -> new Position(p.getX(), p.getY() - 1));

    private final Function<Position, Position> calculatePositionFunc;

    Direction(final Function<Position, Position> calculatePositionFunc) {
        this.calculatePositionFunc = calculatePositionFunc;
    }

    public Position getPosition(final Position pos) {
        Objects.requireNonNull(pos);
        return this.calculatePositionFunc.apply(pos);
    }

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

    public static List<Direction> getDirections() {
        return Arrays.asList(Direction.values());
    }

    public static Direction getRandomDirection() {
        return Direction.values()[new Random().nextInt(Direction.values().length)];
    }
}
