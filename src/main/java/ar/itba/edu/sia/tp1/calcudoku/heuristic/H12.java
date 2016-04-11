package ar.itba.edu.sia.tp1.calcudoku.heuristic;

import java.util.List;

import ar.itba.edu.sia.tp1.calcudoku.CalcudokuState;
import ar.itba.edu.sia.tp1.calcudoku.domain.Board;
import ar.itba.edu.sia.tp1.calcudoku.domain.Group;
import static java.lang.Math.*;

/**
 * Created by scamisay on 09/04/16.
 */
public class H12 extends CalcudokuHeuristic {
	private static final double NON_IDENTITY_PUNISHMENT_RATIO = 0.8;

	private static final H1 h1 = new H1();

	@Override
	public int getValue(CalcudokuState state) {
		Board board = state.getBoard();
		List<Group> groups = state.getGroups(); // IMPORTANT: they are sorted by
												// size

		int nonIdentityIncorrectCount = 0;
		boolean hasFailedGroups = false;

		for (Group group : groups) {
			if (!group.isCorrect(board)) {
				switch (group.getOperator()) {
					case IDENTITY :
						return h1.getValue(state);
					default :
						nonIdentityIncorrectCount += group.getPositions().size();
				}
				hasFailedGroups = true;
			}
		}

		// if (!hasFailedGroups) {
		// return 1; // ?
		// }

		int h1Value = h1.getValue(state);
		return getValueForNonIdentityGroupsFailedCase(nonIdentityIncorrectCount,
				board.getN(), h1Value);
	}

	private int getValueForNonIdentityGroupsFailedCase(
			int nonIdentityIncorrectCount, int n, int h1Value) {
		int totalPositions = n * n;

		int value = (int) ceil(NON_IDENTITY_PUNISHMENT_RATIO
				* (((double) nonIdentityIncorrectCount) / totalPositions)
				* h1Value);
		assert value > 0;
		return value;
	}
}
