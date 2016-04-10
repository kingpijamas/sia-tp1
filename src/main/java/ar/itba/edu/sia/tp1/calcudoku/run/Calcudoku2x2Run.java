package ar.itba.edu.sia.tp1.calcudoku.run;

import static ar.itba.edu.sia.tp1.calcudoku.domain.Position.position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ar.itba.edu.sia.tp1.calcudoku.domain.Board;
import ar.itba.edu.sia.tp1.calcudoku.domain.Group;
import ar.itba.edu.sia.tp1.calcudoku.domain.Operator;
import ar.itba.edu.sia.tp1.calcudoku.heuristic.CalcudokuHeuristic;
import ar.itba.edu.sia.tp1.calcudoku.heuristic.H1;
import ar.itba.edu.sia.tp1.gps.engine.SearchStrategy;

public class Calcudoku2x2Run extends CalcudokuRun {
	public static void main(String[] args) throws Exception {
		Calcudoku2x2Run test = new Calcudoku2x2Run(SearchStrategy.A_STAR,
				new H1(), 1);
		test.run();
	}

	private static Board buildBoard() {
		// ONE swap to complete
		int n = 2;

		List<Group> groups = new ArrayList<>();
		Group gsum = new Group(Arrays.asList(position(0, 0), position(0, 1)),
				Operator.PLUS, 3);
		groups.add(gsum);

		Board board = new Board(n, groups);

		board.put(position(0, 0), 1);
		board.put(position(0, 1), 2);

		board.put(position(1, 0), 1);
		board.put(position(1, 1), 2);

		return board;
	}

	public Calcudoku2x2Run(SearchStrategy searchStrategy, int timesToRun) {
		super(buildBoard(), searchStrategy, timesToRun);
	}

	public Calcudoku2x2Run(SearchStrategy searchStrategy,
			CalcudokuHeuristic heuristic, int timesToRun) {
		super(buildBoard(), searchStrategy, heuristic, timesToRun);
	}
}
