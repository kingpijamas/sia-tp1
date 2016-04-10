package ar.itba.edu.sia.tp1.calcudoku.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by scamisay on 02/04/16.
 */
public class Position {
	private final int row;
	private final int col;

	public static List<Position> allInArea(int minRow, int maxRow, int minCol,
			int maxCol) {
		List<Position> positions = new ArrayList<>();
		for (int i = minRow; i <= maxRow; i++) {
			for (int j = minCol; j <= maxCol; j++) {
				positions.add(position(i, j));
			}
		}
		return positions;
	}

	public static List<Position> allInRow(int minRow, int maxRow, int col) {
		return allInArea(minRow, maxRow, col, col);
	}

	public static List<Position> allInCol(int row, int minCol, int maxCol) {
		return allInArea(row, row, minCol, maxCol + 1);
	}

	public static Position position(int row, int col) {
		return new Position(row, col);
	}

	public Position(int row, int col) {
		this.row = row;
		this.col = col;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Position position = (Position) o;

		if (row != position.row)
			return false;
		return col == position.col;
	}

	@Override
	public int hashCode() {
		int result = row;
		result = 31 * result + col;
		return result;
	}

	@Override
	public String toString() {
		return String.format("(%d, %d)", row, col);
	}
}
