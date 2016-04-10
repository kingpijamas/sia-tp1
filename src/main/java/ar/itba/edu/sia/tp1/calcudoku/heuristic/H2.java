package ar.itba.edu.sia.tp1.calcudoku.heuristic;

import static java.lang.Math.ceil;
import ar.itba.edu.sia.tp1.calcudoku.CalcudokuState;
import ar.itba.edu.sia.tp1.calcudoku.domain.Board;

public class H2 extends CalcudokuHeuristic {
	@Override
	public int getValue(CalcudokuState state) {
		Board board = state.getBoard();
		int invalidGroups = board.invalidGroupsCount();
		int invalidColumns = board.invalidColumnsCount();
		return (int) ceil((invalidGroups + invalidColumns) / 4.0);
	}

	@Override
	public String toString() {
		return "H2";
	}
}
