package ar.itba.edu.sia.tp1.calcudoku.heuristic;

import static java.lang.Math.min;
import ar.itba.edu.sia.tp1.calcudoku.CalcudokuState;

/**
 * Created by scamisay on 09/04/16.
 */
public class H4 extends CalcudokuHeuristic {
	private static final H1 h1 = new H1();

	@Override
	public int getValue(CalcudokuState state) {
		int m = h1.getValue(state);
		int n = state.getN();
		return min(n, m);
	}
}
