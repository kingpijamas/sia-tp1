package ar.itba.edu.sia.tp1.calcudoku;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ar.itba.edu.sia.tp1.calcudoku.domain.Board;
import ar.itba.edu.sia.tp1.calcudoku.domain.Group;
import ar.itba.edu.sia.tp1.calcudoku.domain.Operator;
import ar.itba.edu.sia.tp1.calcudoku.domain.Position;
import ar.itba.edu.sia.tp1.gps.GPSHeuristic;
import ar.itba.edu.sia.tp1.gps.engine.SearchStrategy;

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

		Board board = new Board(4, Arrays.asList()); //getBoard3X3();

		GPSHeuristic<CalcudokuState> heuristic = state -> 1;
		Calcudoku calcudoku = new Calcudoku(new CalcudokuState(board),
				heuristic);
		calcudoku.fillBoardWithRandomValuesInRows();

		CalcudokuState state = calcudoku.getInitialState();
		System.out.println(state.getBoard().fullToString());

		CalcudokuEngine engine = new CalcudokuEngine(calcudoku,
				SearchStrategy.BFS);

		try {
			engine.solve();
		} catch (StackOverflowError e) {
			System.out.println("Solution (if any) too deep for stack.");
		}
	}

	private static Position position(int row, int col) {
		return new Position(row, col);
	}

	//ONE swap to complete
	private static Board getBoard2X2(){
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

	//ONE swap to complete
	private static Board getBoard3X3(){
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
}
