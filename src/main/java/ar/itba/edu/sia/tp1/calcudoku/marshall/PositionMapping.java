package ar.itba.edu.sia.tp1.calcudoku.marshall;

import org.boon.json.annotations.JsonProperty;

import ar.itba.edu.sia.tp1.calcudoku.domain.Position;

class PositionMapping {
	@JsonProperty("row")
	private int row;

	@JsonProperty("col")
	private int col;

	public PositionMapping() {
	}

	public PositionMapping(int row, int col) {
		this.row = row;
		this.col = col;
	}

	public PositionMapping(Position position) {
		this(position.getRow(), position.getCol());
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public Position unmarshall() {
		return new Position(row, col);
	}
}