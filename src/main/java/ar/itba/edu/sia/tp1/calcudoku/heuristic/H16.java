package ar.itba.edu.sia.tp1.calcudoku.heuristic;

import static java.lang.Math.ceil;
import static java.lang.Math.max;

import java.util.ArrayList;
import java.util.List;

import ar.itba.edu.sia.tp1.calcudoku.CalcudokuState;
import ar.itba.edu.sia.tp1.calcudoku.domain.Board;
import ar.itba.edu.sia.tp1.calcudoku.domain.Group;
import ar.itba.edu.sia.tp1.calcudoku.domain.Operator;

public class H16 extends CalcudokuHeuristic {
	@Override
	public int getValue(CalcudokuState state) {
		Board board = state.getBoard();
		int invalidGroupsCount = board.invalidGroupsCount();
		int invalidColumnsCount = board.invalidColumnsCount();

		int invalidIdGroups = 0;
		for (Group group : board.getGroups()) {
			if (group.getOperator() == Operator.IDENTITY
					&& !group.isCorrect(board)) {
				invalidIdGroups++;
			}
		}
		// int invalidNonIdGroups = invalidGroupsCount - invalidIdGroups;

		int steps = (int) ceil(
				max(invalidGroupsCount, invalidColumnsCount) / 2.0);

		int aux = Math.min(invalidIdGroups, steps);
		if (invalidIdGroups == steps) {
			return invalidIdGroups;
		}

		double hValue = 0;
		hValue += aux;
		steps -= aux;

		List<Group> invalidGroups = getInvalidNonIdGroups(board);
		aux = Math.min(steps, invalidGroupsCount);
		for (int i = 0; i < aux; i++) {
			Group group = invalidGroups.get(i);
			hValue += 1.d / group.getPositions().size();
		}
		steps -= aux;

		aux = Math.min(invalidColumnsCount, steps);
		hValue += aux * 0.1;
		return (int) Math.ceil(hValue);
	}

	public List<Group> getInvalidNonIdGroups(Board board) {
		List<Group> groups = board.getGroups();
		List<Group> invalidGroups = new ArrayList<>(groups.size());

		for (Group group : groups) {
			if (group.getOperator() != Operator.IDENTITY
					&& !group.isCorrect(board)) {
				invalidGroups.add(group);
			}
		}
		return invalidGroups;
	}
}
