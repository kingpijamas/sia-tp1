package ar.itba.edu.sia.tp1.calcudoku;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ar.itba.edu.sia.tp1.calcudoku.domain.Board;
import ar.itba.edu.sia.tp1.calcudoku.domain.Position;
import ar.itba.edu.sia.tp1.gps.GPSHeuristic;
import ar.itba.edu.sia.tp1.gps.GPSProblem;
import ar.itba.edu.sia.tp1.gps.ProblemParser;

/**
 * Created by scamisay on 02/04/16.
 */
public class Calcudoku implements GPSProblem<CalcudokuRule, CalcudokuState> {
	private final CalcudokuState initialState;
	private final List<CalcudokuRule> rules;
	private final GPSHeuristic<Calcudoku> heuristic;

	public Calcudoku(Board board, GPSHeuristic<Calcudoku> heuristic) {
		this.initialState = new CalcudokuState(board);
		this.rules = CalcudokuRule.buildRules(initialState.getN());
		this.heuristic = heuristic;
	}

	public Calcudoku(CalcudokuState initialState,
			GPSHeuristic<Calcudoku> heuristic) {
		this.initialState = initialState;
		this.rules = CalcudokuRule.buildRules(initialState.getN());
		this.heuristic = heuristic;
	}

	public Calcudoku(ProblemParser<CalcudokuState> problemReader,
			GPSHeuristic<Calcudoku> heuristic) {
		this(problemReader.parse(), heuristic);
	}

	@Override
	public CalcudokuState getInitialState() {
		fillWithRandomValues(initialState.getBoard());
		return initialState;
	}

	@Override
	public boolean isGoal(CalcudokuState state) {
		return state.isValid();
	}

	@Override
	public List<CalcudokuRule> getRules() {
		return rules;
	}

	@Override
	public int getHValue(CalcudokuState state) {
		return 0; // TODO
	}

	private void fillWithRandomValues(Board board) {
		int n = board.getN();

		List<Integer> values = new ArrayList<>(n * n);
		for (int value = 1; value <= n; value++) {
			for (int appearances = 0; appearances < n; appearances++) {
				values.add(value);
			}
		}
		Collections.shuffle(values);

		for (int i = 0; i < values.size(); i++) {
			board.put(new Position(i / n, i % n), values.get(i));
		}
	}
}
