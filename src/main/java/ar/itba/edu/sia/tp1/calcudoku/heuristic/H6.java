package ar.itba.edu.sia.tp1.calcudoku.heuristic;

import static java.lang.Math.ceil;

import java.util.List;

import ar.itba.edu.sia.tp1.calcudoku.CalcudokuState;
import ar.itba.edu.sia.tp1.calcudoku.domain.Board;
import ar.itba.edu.sia.tp1.calcudoku.domain.Group;
import ar.itba.edu.sia.tp1.calcudoku.domain.Operator;
import ar.itba.edu.sia.tp1.calcudoku.domain.Position;

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
				Operator operator = group.getOperator();
				if (operator.isCommutative()) {
					BinaryFunction f = operator.asBinaryFunction();

					double accum = operator.getZero();
					for (Position position : group.getPositions()) {
						int value = board.getCellValue(position);
						accum = f.apply(group.getResult(), value);
					}
					minSwaps += (int) ceil((accum / n) / 2);
				}
			}
		}
		return (int) Math.max(minSwaps,
				Math.ceil(board.invalidColumnsCount() / 2));
	}
}
