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

public class Calcudoku3x3Run extends CalcudokuRun {
	public static void main(String[] args) throws Exception {
		Calcudoku3x3Run test = new Calcudoku3x3Run(SearchStrategy.A_STAR,
				new H1(), 1);
		test.run();
	}

	private static Board buildBoard() {
		// ONE swap to complete
		int n = 3;

		List<Group> groups = new ArrayList<>();
		// Group gSum = new Group(
		// Arrays.asList(new Position(0, 0), new Position(0, 1),
		// new Position(1, 0), new Position(2, 0)),
		// Operator.PLUS, 7);
		// groups.add(gSum);

		Group gdiv = new Group(Arrays.asList(position(2, 1), position(2, 2)),
				Operator.PLUS, 3);
		groups.add(gdiv);

		Board board = new Board(n, groups);

		board.put(position(0, 0), 1);
		board.put(position(0, 1), 2);
		board.put(position(0, 2), 3);

		board.put(position(1, 0), 1);
		board.put(position(1, 1), 3);
		board.put(position(1, 2), 2);

		board.put(position(2, 0), 3);
		board.put(position(2, 1), 2);
		board.put(position(2, 2), 1);
		return board;
	}

	public Calcudoku3x3Run(SearchStrategy searchStrategy, int timesToRun) {
		super(buildBoard(), searchStrategy, timesToRun);
	}

	public Calcudoku3x3Run(SearchStrategy searchStrategy,
			CalcudokuHeuristic heuristic, int timesToRun) {
		super(buildBoard(), searchStrategy, heuristic, timesToRun);
	}
}
