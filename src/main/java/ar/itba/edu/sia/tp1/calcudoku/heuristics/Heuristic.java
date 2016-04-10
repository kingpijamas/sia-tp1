package ar.itba.edu.sia.tp1.calcudoku.heuristics;

import ar.itba.edu.sia.tp1.calcudoku.CalcudokuState;
import ar.itba.edu.sia.tp1.calcudoku.domain.Board;
import ar.itba.edu.sia.tp1.gps.GPSHeuristic;

public abstract class Heuristic implements GPSHeuristic<CalcudokuState> {

	public int getValue(CalcudokuState state) {
		Board board = state.getBoard();
		return getH(board);
	}

	public int getH(CalcudokuState state) {
		Board board = state.getBoard();
		return getH(board);
	}

	public int getH(Board board) {
		int incorrect_groups = board.invalidGroupsCount();
		int incorrect_columns = board.invalidColumnsCount();
		return getH(incorrect_groups, incorrect_columns);

	}

	abstract int getH(int incorrect_groups, int incorrect_columns);

}
