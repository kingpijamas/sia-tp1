package ar.itba.edu.sia.tp1.calcudoku.run;

import static ar.itba.edu.sia.tp1.calcudoku.domain.Position.position;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import ar.itba.edu.sia.tp1.calcudoku.domain.Board;
import ar.itba.edu.sia.tp1.calcudoku.domain.Group;
import ar.itba.edu.sia.tp1.calcudoku.domain.Operator;
import ar.itba.edu.sia.tp1.calcudoku.heuristic.CalcudokuHeuristic;
import ar.itba.edu.sia.tp1.calcudoku.heuristic.H1;
import ar.itba.edu.sia.tp1.calcudoku.marshall.CalcudokuJsonParser;
import ar.itba.edu.sia.tp1.gps.engine.SearchStrategy;

public class Calcudoku6x6Run extends CalcudokuRun {
	private static final String PATH = "./src/test/resources/b6x6.json";

	public static void main(String[] args) throws Exception {
		Calcudoku6x6Run test = new Calcudoku6x6Run(SearchStrategy.A_STAR,
				new H1(), 1);
		test.run();
	}

	private static Board buildBoard() {
		int n = 6;

		List<Group> groups = new ArrayList<>();
		Group gs1 = Group.of(Operator.PLUS, 91, position(0, 0), position(0, 1),
				position(0, 2), position(0, 3), position(0, 4), position(0, 5),
				position(1, 0), position(1, 1), position(1, 2), position(1, 3),
				position(1, 4), position(2, 0), position(2, 1), position(2, 2),
				position(2, 3), position(3, 0), position(3, 1), position(3, 2),
				position(2, 0), position(2, 1));
		groups.add(gs1);

		Board board = new Board(n, groups);

		board.put(position(0, 0), 1);
		board.put(position(0, 1), 3);
		board.put(position(0, 2), 2);
		board.put(position(0, 3), 4);
		board.put(position(0, 4), 5);
		board.put(position(0, 5), 6);

		board.put(position(1, 0), 2);
		board.put(position(1, 1), 4);
		board.put(position(1, 2), 1);
		board.put(position(1, 3), 5);
		board.put(position(1, 4), 3);
		board.put(position(1, 5), 6);

		board.put(position(2, 0), 2);
		board.put(position(2, 1), 3);
		board.put(position(2, 2), 4);
		board.put(position(2, 3), 5);
		board.put(position(2, 4), 1);
		board.put(position(2, 5), 6);

		board.put(position(3, 0), 4);
		board.put(position(3, 1), 1);
		board.put(position(3, 2), 3);
		board.put(position(3, 3), 5);
		board.put(position(3, 4), 2);
		board.put(position(3, 5), 6);

		board.put(position(4, 0), 5);
		board.put(position(4, 1), 3);
		board.put(position(4, 2), 1);
		board.put(position(4, 3), 2);
		board.put(position(4, 4), 4);
		board.put(position(4, 5), 6);

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

	public Calcudoku6x6Run(Board board, SearchStrategy searchStrategy,
			int timesToRun) {
		super(board, searchStrategy, timesToRun);
	}

	public Calcudoku6x6Run(Board board, SearchStrategy searchStrategy,
			CalcudokuHeuristic heuristic, int timesToRun) {
		super(board, searchStrategy, heuristic, timesToRun);
	}

	public Calcudoku6x6Run(SearchStrategy searchStrategy, int timesToRun) {
		this(buildBoard(), searchStrategy, timesToRun);
	}

	public Calcudoku6x6Run(SearchStrategy searchStrategy,
			CalcudokuHeuristic heuristic, int timesToRun) {
		this(buildBoard(), searchStrategy, heuristic, timesToRun);
	}
}
