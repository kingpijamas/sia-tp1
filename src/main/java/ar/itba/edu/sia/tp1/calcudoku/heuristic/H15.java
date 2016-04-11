package ar.itba.edu.sia.tp1.calcudoku.heuristic;

import static java.lang.Math.ceil;
import static java.lang.Math.max;

import ar.itba.edu.sia.tp1.calcudoku.CalcudokuState;
import ar.itba.edu.sia.tp1.calcudoku.domain.Board;
import ar.itba.edu.sia.tp1.calcudoku.domain.Group;

public class H15 extends CalcudokuHeuristic {
	@Override
	public int getValue(CalcudokuState state) {
		Board board = state.getBoard();

		int hValue = 0;
		for (Group group : board.getGroups()) {
			group.getResult();
		}
		
		int invalidGroups = board.invalidGroupsCount();
		int invalidColumns = board.invalidColumnsCount();
		return (int) ceil(max(invalidGroups, invalidColumns) / 2.0);
	}

	@Override
	public String toString() {
		return "H1";
	}
}
