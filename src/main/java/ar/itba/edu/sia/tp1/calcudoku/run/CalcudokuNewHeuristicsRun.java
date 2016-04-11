package ar.itba.edu.sia.tp1.calcudoku.run;

import static ar.itba.edu.sia.tp1.calcudoku.domain.Operator.IDENTITY;
import static ar.itba.edu.sia.tp1.calcudoku.domain.Operator.MINUS;
import static ar.itba.edu.sia.tp1.calcudoku.domain.Operator.MULTIPLY;
import static ar.itba.edu.sia.tp1.calcudoku.domain.Operator.PLUS;
import static ar.itba.edu.sia.tp1.calcudoku.domain.Position.position;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import ar.itba.edu.sia.tp1.calcudoku.domain.Board;
import ar.itba.edu.sia.tp1.calcudoku.domain.Group;
import ar.itba.edu.sia.tp1.calcudoku.heuristic.CalcudokuHeuristic;
import ar.itba.edu.sia.tp1.calcudoku.heuristic.H16;
import ar.itba.edu.sia.tp1.calcudoku.marshall.CalcudokuJsonParser;
import ar.itba.edu.sia.tp1.gps.engine.SearchStrategy;

public class CalcudokuNewHeuristicsRun extends CalcudokuRun {
	private static final String PATH = "./src/test/resources/b6x6.json";

	public static void main(String[] args) throws Exception {
		CalcudokuNewHeuristicsRun test = new CalcudokuNewHeuristicsRun(
				SearchStrategy.A_STAR, new H16(), 1);
		test.run();
	}

	private static Board buildBoard() {
		int n = 5;

		Group g0 = Group.of(MULTIPLY, 8, position(0, 0), position(1, 0));
		Group g1 = Group.of(MINUS, 4, position(0, 1), position(0, 2));
		Group g2 = Group.of(MINUS, 1, position(0, 3), position(0, 4));
		Group g3 = Group.of(MULTIPLY, 90, position(1, 1), position(1, 2),
				position(2, 1), position(3, 1));
		Group g4 = Group.of(PLUS, 5, position(1, 3), position(2, 3));
		Group g5 = Group.of(PLUS, 8, position(1, 4), position(2, 4));
		Group g6 = Group.of(MULTIPLY, 5, position(2, 0), position(3, 0));
		Group g7 = Group.of(MINUS, 2, position(2, 2), position(3, 2));
		Group g8 = Group.of(IDENTITY, 2, position(3, 3));
		Group g9 = Group.of(IDENTITY, 3, position(4, 0));
		Group g10 = Group.of(MULTIPLY, 40, position(4, 1), position(4, 2),
				position(4, 3), position(4, 4), position(3, 4));

		Board board = new Board(n,
				Arrays.asList(g0, g1, g2, g3, g4, g5, g6, g7, g8, g9, g10));
		return board;
	}

	@SuppressWarnings("unused")
	private static Board buildBoardFromJson() throws IOException {
		CalcudokuJsonParser parser;

		try (InputStream is = new FileInputStream(PATH)) {
			parser = new CalcudokuJsonParser(is);

			return parser.parse().getBoard();
		}
	}

	public CalcudokuNewHeuristicsRun(Board board, SearchStrategy searchStrategy,
			int timesToRun) {
		super(board, searchStrategy, timesToRun);
	}

	public CalcudokuNewHeuristicsRun(Board board, SearchStrategy searchStrategy,
			CalcudokuHeuristic heuristic, int timesToRun) {
		super(board, searchStrategy, heuristic, timesToRun);
	}

	public CalcudokuNewHeuristicsRun(SearchStrategy searchStrategy,
			int timesToRun) {
		this(buildBoard(), searchStrategy, timesToRun);
	}

	public CalcudokuNewHeuristicsRun(SearchStrategy searchStrategy,
			CalcudokuHeuristic heuristic, int timesToRun) {
		this(buildBoard(), searchStrategy, heuristic, timesToRun);
	}
}
