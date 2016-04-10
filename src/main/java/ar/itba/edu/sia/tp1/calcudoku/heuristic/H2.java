package ar.itba.edu.sia.tp1.calcudoku.heuristic;

import static java.lang.Math.ceil;
import ar.itba.edu.sia.tp1.calcudoku.CalcudokuState;
import ar.itba.edu.sia.tp1.calcudoku.domain.Board;

public class H2 extends CalcudokuHeuristic {
	@Override
	public int getValue(CalcudokuState state) {
		Board board = state.getBoard();
		int incorrectGroups = board.invalidGroupsCount();
		int incorrectColumns = board.invalidColumnsCount();
		return (int) ceil((incorrectGroups + incorrectColumns) / 4.0);
	}
}