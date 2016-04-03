package ar.itba.edu.sia.tp1.eight_puzzle;

import java.util.LinkedList;
import java.util.List;

import ar.itba.edu.sia.tp1.gps.ProblemReader;
import ar.itba.edu.sia.tp1.gps.GPSHeuristic;
import ar.itba.edu.sia.tp1.gps.GPSProblem;

public class Puzzle implements GPSProblem<PuzzleRule, PuzzleState> {
	private static final PuzzleState FINAL_STATE = new PuzzleState(new int[][] {
			{ 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, -1 } });

	private final PuzzleState initialState;
	private final GPSHeuristic<Puzzle> heuristic;

	public Puzzle(ProblemReader<PuzzleState> problemReader,
			GPSHeuristic<Puzzle> heuristic) {
		this.initialState = problemReader.readInitialState();
		this.heuristic = heuristic;
	}

	@Override
	public PuzzleState getInitialState() {
		return initialState;
	}

	@Override
	public boolean isGoal(PuzzleState state) {
		return state.equals(FINAL_STATE);
	}

	@Override
	public List<PuzzleRule> getRules() {
		List<PuzzleRule> rules = new LinkedList<PuzzleRule>();
		for (Direction d : Direction.values()) {
			rules.add(new PuzzleRule(d));
		}
		return rules;
	}

	// Valor Heurística para A*
	@Override
	public int getHValue(PuzzleState state) {
		return heuristic.getValue(this);
	}
}
