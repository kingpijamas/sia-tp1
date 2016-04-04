package ar.itba.edu.sia.tp1.calcudoku.domain;

import java.util.*;

import org.apache.commons.lang3.builder.ToStringBuilder;

import static ar.itba.edu.sia.tp1.calcudoku.domain.Operator.IDENTITY;

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

	public boolean isCorrect(List<Integer> values) {
		switch (operator){
			case IDENTITY:
				return isCorrectIdentity(values);
			case PLUS:
				return isCorrectPlus(values);
			case MULTIPLY:
				return isCorrectMultiply(values);
			case DIVIDE:
				return isCorrectDivide(values);
			case MINUS:
				return isCorrectMinus(values);
		}
		return false;
	}

	//todo: implement
	private boolean isCorrectMinus(List<Integer> values) {
		return false;
	}


    //todo: redefinir e implementar porque no siempre seria como en el ejemplo
	/**
	 * values = {result, 1, 1, ...}
	 * @param values
	 * @return
     */
	private boolean isCorrectDivide(List<Integer> values) {
        Iterator<Integer> it = values.iterator();
        while (it.hasNext()){
            Integer aValue = it.next();
            if(!aValue.equals(result) && !aValue.equals(1)){
                return false;
            }
            if(aValue.equals(result)){
                break;
            }
        }

        while (it.hasNext()){
            Integer aValue = it.next();
            if(!aValue.equals(1)){
                return false;
            }
        }
        return true;
	}

	private boolean isCorrectMultiply(List<Integer> values) {
		Integer prod = 0;
		for(Integer aValue : values){
			prod *= aValue;
		}
		return prod.equals(result);
	}

	private boolean isCorrectPlus(List<Integer> values) {
		Integer sum = 0;
		for(Integer aValue : values){
			sum += aValue;
		}
		return sum.equals(result);
	}

	private boolean isCorrectIdentity(List<Integer> values) {
		return values.size() == 1 && values.get(0) == result;
	}
}
