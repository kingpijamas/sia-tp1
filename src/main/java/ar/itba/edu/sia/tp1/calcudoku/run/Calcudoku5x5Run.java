package ar.itba.edu.sia.tp1.calcudoku.run;

import static ar.itba.edu.sia.tp1.gps.engine.SearchStrategy.A_STAR;
import static ar.itba.edu.sia.tp1.calcudoku.domain.Operator.PLUS;

import java.util.Arrays;
import java.util.List;

import ar.itba.edu.sia.tp1.calcudoku.domain.Board;
import ar.itba.edu.sia.tp1.calcudoku.domain.Group;
import ar.itba.edu.sia.tp1.calcudoku.domain.Position;
import ar.itba.edu.sia.tp1.calcudoku.heuristic.CalcudokuHeuristic;
import ar.itba.edu.sia.tp1.calcudoku.heuristic.H6;
import ar.itba.edu.sia.tp1.gps.engine.SearchStrategy;

public class Calcudoku5x5Run extends CalcudokuRun {
	public static void main(String[] args) throws Exception {
		Calcudoku5x5Run test = new Calcudoku5x5Run(A_STAR, new H6(), 1);
		test.run();
	}

	private static Board buildBoard() {
		int n = 5;

		List<Position> row0 = Position.allInRow(0, n, 0);
		List<Position> row1 = Position.allInRow(0, n, 1);
		List<Position> row2 = Position.allInRow(0, n, 2);
		List<Position> row3 = Position.allInRow(0, n, 3);
		List<Position> row4 = Position.allInRow(0, n, 4);

		Group sumR0 = new Group(PLUS, 1 + 2 + 3 + 4 + 5, row0);
		Group sumR1 = new Group(PLUS, 1 + 2 + 3 + 4 + 5, row1);
		Group sumSq1 = new Group(PLUS, (1 + 2 + 3 + 4 + 5) * 2,
				Position.allInArea(0, 4, 2, 4));
		Board board = new Board(n, Arrays.asList(sumR0, sumR1, sumSq1));

		board.put(row0.get(0), 1);
		board.put(row0.get(1), 3);
		board.put(row0.get(2), 2);
		board.put(row0.get(3), 4);
		board.put(row0.get(4), 5);

		board.put(row1.get(0), 2);
		board.put(row1.get(1), 4);
		board.put(row1.get(2), 1);
		board.put(row1.get(3), 5);
		board.put(row1.get(4), 3);

		board.put(row2.get(0), 2);
		board.put(row2.get(1), 3);
		board.put(row2.get(2), 4);
		board.put(row2.get(3), 5);
		board.put(row2.get(4), 1);

		board.put(row3.get(0), 4);
		board.put(row3.get(1), 1);
		board.put(row3.get(2), 3);
		board.put(row3.get(3), 5);
		board.put(row3.get(4), 2);

		board.put(row4.get(0), 5);
		board.put(row4.get(1), 3);
		board.put(row4.get(2), 1);
		board.put(row4.get(3), 2);
		board.put(row4.get(4), 4);

		// Group sumSq2 = new Group(PLUS, (1 + 2 + 3 + 4 + 5) * 1,
		// Position.allInArea(0, 4, 4, 4));

		// Group sumSq1 = new Group(PLUS, , Position.allInArea(3,
		// 4, 1, 2));
		// Group sumSq2 = new Group(PLUS, , Position.allInArea(3,
		// 4, 2, 3));
		// Group sumSq3 = new Group(PLUS, , Position.allInArea(3,
		// 4, 3, 4));
		// Group sumSq4 = new Group(PLUS, , Position.allInArea(3,
		// 4, 4, 5));

		return board;
	}

	public Calcudoku5x5Run(SearchStrategy searchStrategy, int timesToRun) {
		super(buildBoard(), searchStrategy, timesToRun);
	}

	public Calcudoku5x5Run(SearchStrategy searchStrategy,
			CalcudokuHeuristic heuristic, int timesToRun) {
		super(buildBoard(), searchStrategy, heuristic, timesToRun);
	}
}
