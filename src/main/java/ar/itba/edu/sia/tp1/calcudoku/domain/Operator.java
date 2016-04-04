package ar.itba.edu.sia.tp1.calcudoku.domain;

/**
 * Created by scamisay on 02/04/16.
 */
public enum Operator {
	MINUS("+"), PLUS("-"), MULTIPLY("*"), DIVIDE("/"), IDENTITY("id");

	private final String representation;

	private Operator(String representation) {
		this.representation = representation;
	}

	@Override
	public String toString() {
		return "'" + representation + "'";
	}
}
