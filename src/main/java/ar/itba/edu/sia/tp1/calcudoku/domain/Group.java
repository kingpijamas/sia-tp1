package ar.itba.edu.sia.tp1.calcudoku.domain;

import static ar.itba.edu.sia.tp1.util.ObjectUtils.toStringBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import ar.itba.edu.sia.tp1.util.ComparatorUtils;

/**
 * Created by scamisay on 02/04/16.
 */
public class Group {
	private final List<Position> positions;
	private final Operator operator;
	private final int expectedResult;

	public static Group of(Operator operator, int result,
			Position... positions) {
		return new Group(operator, result, Arrays.asList(positions));
	}

	public Group(Operator operator, int result, Position... positions) {
		this(operator, result, Arrays.asList(positions));
	}

	public Group(Operator operator, int result, List<Position> positions) {
		this.positions = Collections.unmodifiableList(positions);
		this.operator = operator;
		this.expectedResult = result;
	}

	public List<Position> getPositions() {
		return positions;
	}

	public Operator getOperator() {
		return operator;
	}

	public int getResult() {
		return expectedResult;
	}

	public boolean isCorrect(Board board) {
		return getValue(board) == expectedResult;
	}

	public boolean isCorrect(List<Integer> values) {
		Integer val=getValue(values);
		if(val==null){
			System.out.println();
			getValue(values);
		}
		return val == expectedResult;
	}

	public Integer getValue(Board board) {
		List<Integer> values = new ArrayList<>(positions.size());
		for (Position position : positions) {
			values.add(board.getCellValue(position));
		}
		return getValue(values);
	}

	public Integer getValue(List<Integer> values) {
		switch (operator) {
			case IDENTITY :
				return getValueIdentity(values);
			case PLUS :
				return getValuePlus(values);
			case MULTIPLY :
				return getValueMultiply(values);
			case DIVIDE :
				return getValueDivide(values);
			case MINUS :
				return getValueMinus(values);
			default :
				throw new IllegalStateException();
		}
	}

	/**
	 * As result is always positive then 'values' is sorted desc and values are
	 * substracted in that order. It is true if the substraction equals 'result'
	 * 
	 * @param values
	 * @return
	 */
	private int getValueMinus(List<Integer> values) {
		Collections.sort(values, ComparatorUtils::reverseComparison);

		int subtraction = values.isEmpty() ? 0 : values.get(0);
		for (int i = 1; i < values.size(); i++) {
			subtraction -= values.get(i);
		}

		return subtraction;
	}

	/**
	 * values = {a, b} and a % b = 0 and r = a / b
	 * 
	 * @param values
	 * @return
	 */
	private Integer getValueDivide(List<Integer> values) {
		Collections.sort(values, ComparatorUtils::reverseComparison);
		int greaterValue = values.get(0);
		int lesserValue = values.get(1);

		if (greaterValue % lesserValue != 0) {
			return 0;
		}
		return greaterValue / lesserValue;
	}

	private int getValueMultiply(List<Integer> values) {
		int prod = 1;
		for (int aValue : values) {
			prod *= aValue;
		}
		return prod;
	}

	private int getValuePlus(List<Integer> values) {
		int sum = 0;
		for (int aValue : values) {
			sum += aValue;
		}
		return sum;
	}

	private Integer getValueIdentity(List<Integer> values) {
		if (values.size() != 1) {
			return null;
		}
		return values.get(0);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((operator == null) ? 0 : operator.hashCode());
		result = prime * result
				+ ((positions == null) ? 0 : positions.hashCode());
		result = prime * result + this.expectedResult;
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
		if (expectedResult != other.expectedResult)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return toStringBuilder(this).appendToString(positions.toString())
				.append("operator", operator).append("result", expectedResult)
				.toString();
	}
}
