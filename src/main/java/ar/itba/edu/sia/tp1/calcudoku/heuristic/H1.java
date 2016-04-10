package ar.itba.edu.sia.tp1.calcudoku.heuristic;

import static java.lang.Math.ceil;
import static java.lang.Math.max;
import ar.itba.edu.sia.tp1.calcudoku.CalcudokuState;
import ar.itba.edu.sia.tp1.calcudoku.domain.Board;

public class H1 extends CalcudokuHeuristic {
	@Override
	public int getValue(CalcudokuState state) {
		Board board = state.getBoard();
		int incorrectGroups = board.invalidGroupsCount();
		int incorrectColumns = board.invalidColumnsCount();
		return (int) ceil(max(incorrectGroups, incorrectColumns) / 2.0);
	}
}
