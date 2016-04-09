import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import ar.itba.edu.sia.tp1.calcudoku.CalcudokuRule;
import ar.itba.edu.sia.tp1.calcudoku.CalcudokuState;
import ar.itba.edu.sia.tp1.calcudoku.domain.Board;
import ar.itba.edu.sia.tp1.calcudoku.domain.Position;
import ar.itba.edu.sia.tp1.calcudoku.heuristics.H1;
import ar.itba.edu.sia.tp1.calcudoku.heuristics.H2;
import ar.itba.edu.sia.tp1.calcudoku.heuristics.Heuristic;
import ar.itba.edu.sia.tp1.calcudoku.marshall.CalcudokuJsonParser;
import ar.itba.edu.sia.tp1.gps.engine.GPSNode;
import ar.itba.edu.sia.tp1.gps.engine.GPSSolution;

public class GenericTest {
	protected CalcudokuJsonParser parser;

	protected InputStream is;
	protected static final Heuristic[] heuristics = { new H1(), new H2() };

	protected Board setUp(String fileName) throws IOException {
		is = new FileInputStream(fileName);

		parser = new CalcudokuJsonParser(is);

		CalcudokuState stateRead = parser.parse();
		Board b = stateRead.getBoard();
		System.out.println("reading file:" + fileName);
		return b;
	}

	protected Position position(int row, int col) {
		return new Position(row, col);
	}

	protected Board getSolutionBoard(GPSSolution<CalcudokuRule, CalcudokuState> solution) {
		if (solution != null) {
			Iterable<GPSNode<CalcudokuRule, CalcudokuState>> path = solution.getPath();
			Board finalBoard = null;
			for (GPSNode<CalcudokuRule, CalcudokuState> node : path) {
				finalBoard = node.getState().getBoard();
			}
			return finalBoard;
		}
		return null;

	}

}
