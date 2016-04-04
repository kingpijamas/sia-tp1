package ar.itba.edu.sia.tp1.calcudoku;

import static ar.itba.edu.sia.tp1.utils.ObjectUtils.toStringBuilder;
import ar.itba.edu.sia.tp1.calcudoku.domain.Position;
import ar.itba.edu.sia.tp1.gps.GPSRule;

/**
 * Created by scamisay on 02/04/16.
 */
public class CalcudokuRule implements GPSRule {
	private final Position position;
	private final int value;

	public CalcudokuRule(Position position, int value) {
		this.position = position;
		this.value = value;
	}

	public Position getPosition() {
		return position;
	}

	public int getValue() {
		return value;
	}

	@Override
	public Integer getCost() {
		return null; // TODO
	}

	@Override
	public String getName() {
		return null; // TODO
	}

	@Override
	public String toString() {
		return toStringBuilder(this).append("position", position)
				.append("value", value).toString();
	}
}
