package ar.itba.edu.sia.tp1.eight_puzzle;

import ar.itba.edu.sia.tp1.gps.GPSProblem;
import ar.itba.edu.sia.tp1.gps.engine.GPSEngine;
import ar.itba.edu.sia.tp1.gps.engine.SearchStrategy;

class PuzzleEngine extends GPSEngine<PuzzleRule, PuzzleState> {
	public PuzzleEngine(GPSProblem<PuzzleRule, PuzzleState> problem,
			SearchStrategy searchStrategy) {
		super(problem, searchStrategy, fact(8));
	}

	private static int fact(int n) {
		int fact = 1;
		while (n > 1) {
			fact *= n;
			n--;
		}
		return fact;
	}
}
