package ar.itba.edu.sia.tp1.calcudoku.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by scamisay on 02/04/16.
 */
public class Position {
	private final int row;
	private final int col;

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
		return new ToStringBuilder(this).append("row", row).append("col", col)
				.toString();
	}
}
