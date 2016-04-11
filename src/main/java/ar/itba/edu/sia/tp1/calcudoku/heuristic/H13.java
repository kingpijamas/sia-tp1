package ar.itba.edu.sia.tp1.calcudoku.heuristic;

import java.util.List;

import ar.itba.edu.sia.tp1.calcudoku.CalcudokuState;
import ar.itba.edu.sia.tp1.calcudoku.domain.Board;
import ar.itba.edu.sia.tp1.calcudoku.domain.Group;
import ar.itba.edu.sia.tp1.calcudoku.domain.Operator;

/**
 * Created by scamisay on 09/04/16.
 */
public class H13 extends CalcudokuHeuristic {
	private static final double NON_IDENTITY_PUNISHMENT_RATIO = 0.8;

	private static final H1 h1 = new H1();

	@Override
	public int getValue(CalcudokuState state) {
		Board board = state.getBoard();
		List<Group> groups = state.getGroups(); // IMPORTANT: they are sorted by
												// size
		int n = board.getN();

		int idGroups = 0;
		int idGroupsOk = 0;
		int i;
		for (i = 0; i < groups.size(); i++) {
			Group group = groups.get(i);
			if (group.getOperator() == Operator.IDENTITY) {
				idGroups++;

				if (group.isCorrect(board)) {
					idGroupsOk++;
				}
			}
		}
		if (idGroupsOk != idGroups) {
			return h1.getValue(state) - idGroupsOk;
		}

		double hValue = 0;
		for (; i < groups.size(); i++) {
			Group group = groups.get(i);
			if (!group.isCorrect(board)) {
				int groupSize = group.getPositions().size();
				hValue += (1 - ((double) groupSize) / 10) * groupSize;
			}
		}
		hValue += board.invalidColumnsCount() * 0.1;
		return (int) Math.ceil(hValue);
	}
}
