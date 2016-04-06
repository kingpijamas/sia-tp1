package ar.itba.edu.sia.tp1.eight_puzzle;

import ar.itba.edu.sia.tp1.gps.engine.GPSEngine;
import ar.itba.edu.sia.tp1.gps.engine.SearchStrategy;

/**
 * Created by scamisay on 30/03/16.
 */
public class Application {

	public static void main(String[] args) {
		System.out.print(1);
		Puzzle puzzle = new Puzzle(new StdInPuzzleReader(), problem -> 1);
		GPSEngine<PuzzleRule, PuzzleState> engine = new PuzzleEngine(puzzle,
				SearchStrategy.DFS);
		try {
			// long startTime = System.currentTimeMillis();
			// for (int i = 0; i < 1000; i++) {
			// engine.solve();
			// }
			// long millisTaken = (System.currentTimeMillis() - startTime) /
			// 1000;
			// System.out.println(String.format("Program took: %d millis",
			// millisTaken));
			engine.solve();
		} catch (StackOverflowError e) {
			System.out.println("Solution (if any) too deep for stack.");
		}
	}
}
