package ar.itba.edu.sia.tp1.calcudoku.run;

import static ar.itba.edu.sia.tp1.calcudoku.domain.Position.position;

import java.util.Arrays;

import ar.itba.edu.sia.tp1.calcudoku.domain.Board;
import ar.itba.edu.sia.tp1.calcudoku.heuristic.CalcudokuHeuristic;
import ar.itba.edu.sia.tp1.calcudoku.heuristic.H1;
import ar.itba.edu.sia.tp1.gps.engine.SearchStrategy;

public class Calcudoku5x5Run extends CalcudokuRun {
	public static void main(String[] args) throws Exception {
		Calcudoku5x5Run test = new Calcudoku5x5Run(SearchStrategy.A_STAR,
				new H1(), 1);
		test.run();
	}

	private static Board buildBoard() {
		int n = 5;

		Board board = new Board(n, Arrays.asList());

		board.put(position(0, 0), 1);
		board.put(position(0, 1), 3);
		board.put(position(0, 2), 2);
		board.put(position(0, 3), 4);
		board.put(position(0, 4), 5);

		board.put(position(1, 0), 2);
		board.put(position(1, 1), 4);
		board.put(position(1, 2), 1);
		board.put(position(1, 3), 5);
		board.put(position(1, 4), 3);

		board.put(position(2, 0), 2);
		board.put(position(2, 1), 3);
		board.put(position(2, 2), 4);
		board.put(position(2, 3), 5);
		board.put(position(2, 4), 1);

		board.put(position(3, 0), 4);
		board.put(position(3, 1), 1);
		board.put(position(3, 2), 3);
		board.put(position(3, 3), 5);
		board.put(position(3, 4), 2);

		board.put(position(4, 0), 5);
		board.put(position(4, 1), 3);
		board.put(position(4, 2), 1);
		board.put(position(4, 3), 2);
		board.put(position(4, 4), 4);

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
