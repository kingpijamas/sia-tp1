package ar.itba.edu.sia.tp1.calcudoku;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import ar.itba.edu.sia.tp1.calcudoku.domain.Board;
import ar.itba.edu.sia.tp1.calcudoku.domain.Position;
import ar.itba.edu.sia.tp1.gps.GPSHeuristic;
import ar.itba.edu.sia.tp1.gps.GPSProblem;
import ar.itba.edu.sia.tp1.gps.ProblemParser;

/**
 * Created by scamisay on 02/04/16.
 */
public class CalcudokuProblem implements
		GPSProblem<CalcudokuRule, CalcudokuState> {
	private final CalcudokuState initialState;
	private final List<CalcudokuRule> rules;
	private final Optional<GPSHeuristic<CalcudokuState>> heuristic;

	public CalcudokuProblem(Board board) {
		this(board, null);
	}

	public CalcudokuProblem(Board board, GPSHeuristic<CalcudokuState> heuristic) {
		this.initialState = new CalcudokuState(board);
		this.rules = CalcudokuRule.buildSwapsInColumns(initialState.getN());
		this.heuristic = Optional.ofNullable(heuristic);
	}

	public CalcudokuProblem(CalcudokuState initialState) {
		this(initialState, null);
	}

	public CalcudokuProblem(CalcudokuState initialState,
			GPSHeuristic<CalcudokuState> heuristic) {
		this.initialState = initialState;
		this.rules = CalcudokuRule.buildSwapsInColumns(initialState.getN());
		this.heuristic = Optional.ofNullable(heuristic);
	}

	public CalcudokuProblem(ProblemParser<CalcudokuState> problemReader,
			GPSHeuristic<CalcudokuState> heuristic) {
		this(problemReader.parse(), heuristic);
	}

	@Override
	public CalcudokuState getInitialState() {
		/**
		 * TODO: el randomizador deberia pertenecer a una implementacion de
		 * parser donde solo reciba el N como parametro. Porque me puede
		 * interesar probar un estado inicial predeterminado
		 */

		/* fillWithRandomValues(initialState.getBoard()); */
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
		return heuristic.get().getValue(state);
	}

	// public void fillBoardWithRandomValues() {
	// Board board = initialState.getBoard();
	// int n = board.getN();
	//
	// List<Integer> values = new ArrayList<>(n * n);
	// for (int value = 1; value <= n; value++) {
	// for (int appearances = 0; appearances < n; appearances++) {
	// values.add(value);
	// }
	// }
	// Collections.shuffle(values);
	//
	// for (int i = 0; i < values.size(); i++) {
	// board.put(new Position(i / n, i % n), values.get(i));
	// }
	// }

	public void fillBoardWithRandomValuesInRows() {
		Board board = initialState.getBoard();
		int n = board.getN();

		for (int i = 0; i < n; i++) {
			List<Integer> values = new ArrayList<>(n * n);
			for (int value = 1; value <= n; value++) {
				values.add(value);
			}
			Collections.shuffle(values);

			for (int j = 0; j < values.size(); j++) {
				board.put(new Position(i, j), values.get(j));
			}
		}
	}
}
