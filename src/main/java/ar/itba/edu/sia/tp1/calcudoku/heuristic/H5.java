package ar.itba.edu.sia.tp1.calcudoku.heuristic;

import static java.lang.Math.min;
import ar.itba.edu.sia.tp1.calcudoku.CalcudokuState;

/**
 * Created by scamisay on 09/04/16.
 */
public class H5 extends CalcudokuHeuristic {
	@Override
	public int getValue(CalcudokuState state) {
		int m = 0; // TODO: this is what was actually being done... wtf?
		int n = state.getN();
		return min(n, m);
	}
}
