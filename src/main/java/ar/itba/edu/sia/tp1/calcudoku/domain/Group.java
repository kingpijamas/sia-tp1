package ar.itba.edu.sia.tp1.calcudoku.domain;

import static ar.itba.edu.sia.tp1.utils.ObjectUtils.toStringBuilder;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

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

	public boolean isCorrect(List<Integer> values) {
		switch (operator) {
			case IDENTITY :
				return isCorrectIdentity(values);
			case PLUS :
				return isCorrectPlus(values);
			case MULTIPLY :
				return isCorrectMultiply(values);
			case DIVIDE :
				return isCorrectDivide(values);
			case MINUS :
				return isCorrectMinus(values);
		}
		return false;
	}

	// todo: implement
	private boolean isCorrectMinus(List<Integer> values) {
		return false;
	}

	// todo: redefinir e implementar porque no siempre seria como en el ejemplo
	/**
	 * values = {result, 1, 1, ...}
	 * 
	 * @param values
	 * @return
	 */
	private boolean isCorrectDivide(List<Integer> values) {
		Iterator<Integer> it = values.iterator();
		while (it.hasNext()) {
			Integer aValue = it.next();
			if (!aValue.equals(result) && !aValue.equals(1)) {
				return false;
			}
			if (aValue.equals(result)) {
				break;
			}
		}

		while (it.hasNext()) {
			if (!it.next().equals(1)) {
				return false;
			}
		}
		return true;
	}

	private boolean isCorrectMultiply(List<Integer> values) {
		Integer prod = 0;
		for (Integer aValue : values) {
			prod *= aValue;
		}
		return prod.equals(result);
	}

	private boolean isCorrectPlus(List<Integer> values) {
		Integer sum = 0;
		for (Integer aValue : values) {
			sum += aValue;
		}
		return sum.equals(result);
	}

	private boolean isCorrectIdentity(List<Integer> values) {
		return values.size() == 1 && values.get(0) == result;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((operator == null) ? 0 : operator.hashCode());
		result = prime * result
				+ ((positions == null) ? 0 : positions.hashCode());
		result = prime * result + this.result;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Group other = (Group) obj;
		if (operator != other.operator)
			return false;
		if (positions == null) {
			if (other.positions != null)
				return false;
		} else if (!positions.equals(other.positions))
			return false;
		if (result != other.result)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return toStringBuilder(this).appendToString(positions.toString())
				.append("operator", operator).append("result", result)
				.toString();
	}
}
