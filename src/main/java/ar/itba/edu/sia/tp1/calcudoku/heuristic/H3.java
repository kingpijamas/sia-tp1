package ar.itba.edu.sia.tp1.calcudoku.heuristic;

import ar.itba.edu.sia.tp1.calcudoku.CalcudokuState;

/**
 * Created by scamisay on 09/04/16.
 */
public class H3 extends CalcudokuHeuristic {
	private static final H1 h1 = new H1();

	@Override
	public int getValue(CalcudokuState state) {
		int m = h1.getValue(state);
		return Math.max(m * (m - 1), m);
	}
}
