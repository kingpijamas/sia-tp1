package ar.itba.edu.sia.tp1.calcudoku;

import java.util.List;

import ar.itba.edu.sia.tp1.gps.GPSHeuristic;
import ar.itba.edu.sia.tp1.gps.GPSProblem;
import ar.itba.edu.sia.tp1.gps.ProblemParser;

/**
 * Created by scamisay on 02/04/16.
 */
public class Calcudoku implements GPSProblem<CalcudokuRule, CalcudokuState> {
	private final CalcudokuState initialState;
	private final GPSHeuristic<Calcudoku> heuristic;

	public Calcudoku(CalcudokuState initialState,
			GPSHeuristic<Calcudoku> heuristic) {
		this.initialState = initialState;
		this.heuristic = heuristic;
	}

	public Calcudoku(ProblemParser<CalcudokuState> problemReader,
			GPSHeuristic<Calcudoku> heuristic) {
		this(problemReader.parse(), heuristic);
	}

	@Override
	public CalcudokuState getInitialState() {
		return initialState;
	}

	@Override
	public boolean isGoal(CalcudokuState state) {
		return false; // TODO
	}

	@Override
	public List<CalcudokuRule> getRules() {
		return null; // TODO
	}

	@Override
	public int getHValue(CalcudokuState state) {
		return 0; // TODO
	}
}
