package ar.itba.edu.sia.tp1.calcudoku;

import static java.util.Arrays.asList;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import ar.itba.edu.sia.tp1.calcudoku.domain.Group;
import ar.itba.edu.sia.tp1.calcudoku.domain.Operator;
import ar.itba.edu.sia.tp1.calcudoku.domain.Position;
import ar.itba.edu.sia.tp1.calcudoku.marshall.CalcudokuJsonSerializer;
import ar.itba.edu.sia.tp1.gps.GPSHeuristic;
import ar.itba.edu.sia.tp1.gps.engine.SearchStrategy;

/**
 * Created by scamisay on 02/04/16.
 */
public class CalcudokuApplication {
	public static void main(String[] args) throws IOException {
		List<Position> positions = asList(position(0, 0), position(0, 1),
				position(0, 2));
		List<Group> groups = asList(new Group(positions, Operator.PLUS, 5));
		CalcudokuState initialState = new CalcudokuState(3, groups);

		GPSHeuristic<Calcudoku> heuristic = problem -> 1;

		Calcudoku calcudoku = new Calcudoku(initialState, heuristic);

		try (FileOutputStream fis = new FileOutputStream(new File("pepe.json"))) {
			CalcudokuJsonSerializer writer = new CalcudokuJsonSerializer(fis);
			writer.serialize(calcudoku);
		}

		CalcudokuEngine engine = new CalcudokuEngine(calcudoku,
				SearchStrategy.BFS);

		try {
			// engine.solve();
		} catch (StackOverflowError e) {
			System.out.println("Solution (if any) too deep for stack.");
		}
	}

	private static Position position(int row, int col) {
		return new Position(row, col);
	}
}
