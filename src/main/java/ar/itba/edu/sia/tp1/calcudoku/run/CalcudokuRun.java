package ar.itba.edu.sia.tp1.calcudoku.run;

import ar.itba.edu.sia.tp1.calcudoku.CalcudokuEngine;
import ar.itba.edu.sia.tp1.calcudoku.CalcudokuProblem;
import ar.itba.edu.sia.tp1.calcudoku.CalcudokuRule;
import ar.itba.edu.sia.tp1.calcudoku.CalcudokuState;
import ar.itba.edu.sia.tp1.calcudoku.domain.Board;
import ar.itba.edu.sia.tp1.calcudoku.view.CalcudokuJsPrinter;
import ar.itba.edu.sia.tp1.gps.GPSHeuristic;
import ar.itba.edu.sia.tp1.gps.engine.GPSSolution;
import ar.itba.edu.sia.tp1.gps.engine.SearchStrategy;
import ar.itba.edu.sia.tp1.util.timing.TimedResults;
import ar.itba.edu.sia.tp1.util.timing.Timer;

public class CalcudokuRun {
	private final int timesToRun;
	private final SearchStrategy searchStrategy;
	private final GPSHeuristic<CalcudokuState> heuristic;
	private final Board board;

	public CalcudokuRun(Board board, SearchStrategy searchStrategy,
			int timesToRun) {
		this(board, searchStrategy, null, timesToRun);
	}

	public CalcudokuRun(Board board, SearchStrategy searchStrategy,
			GPSHeuristic<CalcudokuState> heuristic) {
		this(board, searchStrategy, heuristic, 1);
	}

	public CalcudokuRun(Board board, SearchStrategy searchStrategy,
			GPSHeuristic<CalcudokuState> heuristic, int timesToRun) {
		this.board = board;
		this.timesToRun = timesToRun;
		this.searchStrategy = searchStrategy;
		this.heuristic = heuristic;
	}

	public GPSSolution<CalcudokuRule, CalcudokuState> run() throws Exception {
		CalcudokuProblem calcudoku = new CalcudokuProblem(board, heuristic);
		CalcudokuEngine engine = new CalcudokuEngine(calcudoku, searchStrategy);

		Board board = calcudoku.getInitialState().getBoard();
		System.out.println(board.fullToString());

		try {
			TimedResults<GPSSolution<CalcudokuRule, CalcudokuState>> timedResults = Timer
					.timer().timesToRun(timesToRun).keepOnlyLastResult()
					.setUpWith(calcudoku::fillBoardWithRandomValuesInRows)
					.toTime(engine::solve).start();
			GPSSolution<CalcudokuRule, CalcudokuState> solution = timedResults
					.getLast().getValue();

			System.out.println(timedResults.getAvg() + " ms");
			System.out.println(timedResults.getStdDev() + " ms^2");
			System.out.println("\n--JSON--");
			System.out.println(new CalcudokuJsPrinter().print(solution,
					board.getN()));
			return solution;
		} catch (StackOverflowError e) {
			System.out.println("Solution (if any) too deep for stack.");
		}
		return null;
	}
}
