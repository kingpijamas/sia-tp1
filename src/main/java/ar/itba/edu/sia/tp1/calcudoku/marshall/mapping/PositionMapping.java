package ar.itba.edu.sia.tp1.calcudoku.marshall.mapping;

import static ar.itba.edu.sia.tp1.util.ObjectUtils.toStringBuilder;

import org.boon.json.annotations.JsonInclude;
import org.boon.json.annotations.JsonProperty;

import ar.itba.edu.sia.tp1.calcudoku.domain.Position;

public class PositionMapping {
	@JsonInclude
	@JsonProperty("row")
	private int row;

	@JsonInclude
	@JsonProperty("col")
	private int col;

	PositionMapping() {
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

	@Override
	public String toString() {
		return toStringBuilder(this).append("row", row).append("col", col)
				.toString();
	}
}