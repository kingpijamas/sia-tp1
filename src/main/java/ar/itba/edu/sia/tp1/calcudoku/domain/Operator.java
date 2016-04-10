package ar.itba.edu.sia.tp1.calcudoku.domain;

import java.util.Optional;

import ar.itba.edu.sia.tp1.calcudoku.heuristic.BinaryFunction;

/**
 * Created by scamisay on 02/04/16.
 */
public enum Operator {
	PLUS((x, y) -> x + y, 0, true), MINUS((x, y) -> x - y, 0, false), MULTIPLY(
			(x, y) -> x * y, 1, true), DIVIDE((x, y) -> x / y, 1, false), IDENTITY(
			-1);

	private final boolean commutative;
	private final int zero;
	private final Optional<BinaryFunction> binaryFunction;

	private Operator(int zero) {
		this(Optional.empty(), zero, false);
	}

	private Operator(BinaryFunction function, int zero, boolean commutative) {
		this(Optional.of(function), zero, commutative);
	}

	private Operator(Optional<BinaryFunction> function, int zero,
			boolean commutative) {
		this.binaryFunction = function;
		this.zero = zero;
		this.commutative = commutative;
	}

	public BinaryFunction asBinaryFunction() {
		return binaryFunction.get();
	}

	public boolean isCommutative() {
		return commutative;
	}

	public int getZero() {
		return zero;
	}

	@Override
	public String toString() {
		return name().toUpperCase();
	}
}
