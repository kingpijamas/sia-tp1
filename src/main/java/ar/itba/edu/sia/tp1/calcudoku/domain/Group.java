package ar.itba.edu.sia.tp1.calcudoku.domain;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by scamisay on 02/04/16.
 */
public class Group {
	private final List<Position> positions;
	private final Operator operator;
	private final int result;

	public Group(List<Position> positions, Operator operator, int result) {
		this.positions = Collections.unmodifiableList(positions);
		this.operator = operator;
		this.result = result;
	}

	public List<Position> getPositions() {
		return positions;
	}

	public Operator getOperator() {
		return operator;
	}

	public int getResult() {
		return result;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("positions", positions)
				.append("operator", operator).append("result", result)
				.toString();
	}
}
