package ar.itba.edu.sia.tp1.calcudoku;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ar.itba.edu.sia.tp1.calcudoku.domain.Board;
import ar.itba.edu.sia.tp1.calcudoku.domain.Group;
import ar.itba.edu.sia.tp1.calcudoku.domain.Operator;
import ar.itba.edu.sia.tp1.calcudoku.domain.Position;
import ar.itba.edu.sia.tp1.calcudoku.heuristics.H1;
import ar.itba.edu.sia.tp1.calcudoku.marshall.CalcudokuJsonParser;
import ar.itba.edu.sia.tp1.gps.GPSHeuristic;
import ar.itba.edu.sia.tp1.gps.engine.GPSSolution;
import ar.itba.edu.sia.tp1.gps.engine.SearchStrategy;
import ar.itba.edu.sia.tp1.utils.CalcudokuJSPrinter;

/**
 * Created by scamisay on 02/04/16.
 */
public class CalcudokuApplication {
	public static void main(String[] args) throws IOException {
		// List<Position> positions = asList(position(0, 0), position(0, 1),
		// position(0, 2));
		// List<Group> groups = asList(new Group(positions, Operator.PLUS, 5));
		// try (FileOutputStream fis = new FileOutputStream(new
		// File("pepe.json"))) {
		// CalcudokuJsonSerializer writer = new CalcudokuJsonSerializer(fis);
		// writer.serialize(calcudoku);
		// }

		int n = 5;
		Board board = new Board(n, Arrays.asList()); // getBoard3X3();
		// Board board = getBoard6X6FromJson();

		GPSHeuristic<CalcudokuState> heuristic = new H1();
		Calcudoku calcudoku = new Calcudoku(new CalcudokuState(board),
				heuristic);
		calcudoku.fillBoardWithRandomValuesInRows();

		CalcudokuState state = calcudoku.getInitialState();
		System.out.println(state.getBoard().fullToString());

		CalcudokuEngine engine = new CalcudokuEngine(calcudoku,
				SearchStrategy.A_STAR);

		GPSSolution<CalcudokuRule, CalcudokuState> solution = null;
		try {
			solution = engine.solve();

			String finalSolution = new CalcudokuJSPrinter()
					.printJS(solution, n);

			System.out.print(1);
		} catch (StackOverflowError e) {
			System.out.println("Solution (if any) too deep for stack.");
		}

	}

	private static Position position(int row, int col) {
		return new Position(row, col);
	}

	// ONE swap to complete
	private static Board getBoard2X2() {
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

	// ONE swap to complete
	private static Board getBoard3X3() {
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

	private static Board getBoard5X5() {
		int n = 5;

		List<Group> groups = new ArrayList<>();

		Board board = new Board(n, groups);

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

	private static Board getBoard6X6FromJson() {
		String fileName = "./src/test/resources/b6x6.json";

		CalcudokuJsonParser parser;

		InputStream is = null;
		try {
			is = new FileInputStream(fileName);
			parser = new CalcudokuJsonParser(is);

			CalcudokuState stateRead = parser.parse();
			Board b = stateRead.getBoard();

			return b;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;

	}

	private static Board getBoard6X6() {
		int n = 6;

		List<Group> groups = new ArrayList<>();
		Group gs1 = new Group(Arrays.asList(position(0, 0), position(0, 1),
				position(0, 2), position(0, 3), position(0, 4), position(0, 5),
				position(1, 0), position(1, 1), position(1, 2), position(1, 3),
				position(1, 4), position(2, 0), position(2, 1), position(2, 2),
				position(2, 3), position(3, 0), position(3, 1), position(3, 2),
				position(2, 0), position(2, 1)), Operator.PLUS, 91);
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
}
