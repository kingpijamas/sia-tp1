package ar.itba.edu.sia.tp1.eight_puzzle;

import ar.itba.edu.sia.tp1.gps.engine.GPSEngine;
import ar.itba.edu.sia.tp1.gps.engine.SearchStrategy;

/**
 * Created by scamisay on 30/03/16.
 */
public class Application {

	public static void main(String[] args) {
		Puzzle puzzle = new Puzzle(new StdInPuzzleReader(), problem -> 1);
		GPSEngine<PuzzleRule, PuzzleState> engine = new PuzzleEngine(puzzle,
				SearchStrategy.BFS);
		try {
			engine.solve();
		} catch (StackOverflowError e) {
			System.out.println("Solution (if any) too deep for stack.");
		}
	}
}
