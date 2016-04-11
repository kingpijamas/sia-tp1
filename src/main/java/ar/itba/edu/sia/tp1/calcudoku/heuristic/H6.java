package ar.itba.edu.sia.tp1.calcudoku.heuristic;

import static java.lang.Math.ceil;

import java.util.List;

import ar.itba.edu.sia.tp1.calcudoku.CalcudokuState;
import ar.itba.edu.sia.tp1.calcudoku.domain.Board;
import ar.itba.edu.sia.tp1.calcudoku.domain.Group;

/**
 * Created by scamisay on 09/04/16.
 */
public class H6 extends CalcudokuHeuristic { // *really* good
	@Override
	public int getValue(CalcudokuState state) {
		Board board = state.getBoard();
		int n = board.getN();
		List<Group> groups = board.getGroups();

		int minSwaps = 0;
		for (Group group : groups) {
			if (!group.isCorrect(board)) {
				int value = group.getValue(board);
				minSwaps += (int) ceil(
						(Math.abs(group.getResult() - value) / n) / 2);
			}
		}
		return (int) Math.max(minSwaps,
				Math.ceil(board.invalidColumnsCount() / 2));
	}
}
