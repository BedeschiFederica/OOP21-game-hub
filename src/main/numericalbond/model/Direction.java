package main.numericalbond.model;

import java.util.Random;

public enum Direction {
	UP, RIGHT, DOWN, LEFT;
	
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
	
	public static Direction getRandomDirection() {
		return Direction.values()[new Random().nextInt(Direction.values().length)];
	}
}