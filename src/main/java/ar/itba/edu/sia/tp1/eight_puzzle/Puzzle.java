package ar.itba.edu.sia.tp1.eight_puzzle;

import java.util.LinkedList;
import java.util.List;

import ar.itba.edu.sia.tp1.gps.GPSHeuristic;
import ar.itba.edu.sia.tp1.gps.GPSProblem;
import ar.itba.edu.sia.tp1.gps.ProblemParser;

public class Puzzle implements GPSProblem<PuzzleRule, PuzzleState> {
	private static final PuzzleState FINAL_STATE = new PuzzleState(new int[][] {
			{ 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, -1 } });

	private final PuzzleState initialState;
	private final GPSHeuristic<PuzzleState> heuristic;

	public Puzzle(ProblemParser<PuzzleState> problemReader,
			GPSHeuristic<PuzzleState> heuristic) {
		this.initialState = problemReader.parse();
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
		return heuristic.getValue(state);
	}
}
