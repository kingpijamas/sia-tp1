package ar.itba.edu.sia.tp1.calcudoku.run;

import java.util.Arrays;

import ar.itba.edu.sia.tp1.calcudoku.domain.Board;
import ar.itba.edu.sia.tp1.calcudoku.heuristic.CalcudokuHeuristic;
import ar.itba.edu.sia.tp1.gps.engine.SearchStrategy;

public class CalcudokuGrouplessRandomRun extends CalcudokuRun {
	public static void main(String[] args) throws Exception {
		int n = 6;
		CalcudokuGrouplessRandomRun test = new CalcudokuGrouplessRandomRun(n,
				SearchStrategy.A_STAR, 1);
		test.run();
	}

	public CalcudokuGrouplessRandomRun(int n, SearchStrategy searchStrategy,
			int timesToRun) {
		super(new Board(n, Arrays.asList()), searchStrategy, timesToRun);
	}

	public CalcudokuGrouplessRandomRun(int n, SearchStrategy searchStrategy,
			CalcudokuHeuristic heuristic, int timesToRun) {
		super(new Board(n, Arrays.asList()), searchStrategy, heuristic,
				timesToRun);
	}
}
