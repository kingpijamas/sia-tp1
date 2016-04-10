package ar.itba.edu.sia.tp1.calcudoku.heuristic;

import static ar.itba.edu.sia.tp1.utils.ObjectUtils.toStringBuilder;
import ar.itba.edu.sia.tp1.calcudoku.CalcudokuState;
import ar.itba.edu.sia.tp1.gps.GPSHeuristic;

public abstract class CalcudokuHeuristic implements
		GPSHeuristic<CalcudokuState> {
	@Override
	public String toString() {
		return toStringBuilder(this).toString();
	}
}
