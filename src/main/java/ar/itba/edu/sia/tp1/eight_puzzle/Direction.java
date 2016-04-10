package ar.itba.edu.sia.tp1.eight_puzzle;

import java.awt.Point;

public enum Direction {
	LEFT(0, -1), DOWN(1, 0), RIGHT(0, 1), UP(-1, 0);

	private final Point delta;

	private Direction(int xDelta, int yDelta) {
		this.delta = new Point(xDelta, yDelta);
	}

	public Point getDelta() {
		return delta;
	}

	@Override
	public String toString() {
		switch (this) {
		case LEFT:
			return "Left";
		case DOWN:
			return "Down";
		case RIGHT:
			return "Right";
		case UP:
			return "Up";
		default:
			throw new IllegalStateException();
		}
	}
}
