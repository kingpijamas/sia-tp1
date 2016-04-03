package ar.itba.edu.sia.tp1.eight_puzzle;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

import ar.itba.edu.sia.tp1.gps.GPSProblem;
import ar.itba.edu.sia.tp1.gps.engine.GPSEngine;
import ar.itba.edu.sia.tp1.gps.engine.GPSNode;
import ar.itba.edu.sia.tp1.gps.engine.SearchStrategy;

class PuzzleEngine extends GPSEngine<PuzzleRule, PuzzleState> {
	private final SearchStrategy searchStrategy;

	public PuzzleEngine(GPSProblem<PuzzleRule, PuzzleState> problem,
			SearchStrategy searchStrategy) {
		super(problem);
		this.searchStrategy = searchStrategy;
	}

	@Override
	protected Queue<GPSNode<PuzzleRule, PuzzleState>> buildOpenNodes() {
		return new PriorityQueue<GPSNode<PuzzleRule, PuzzleState>>(
				getStateComparator(searchStrategy));
	}

	private Comparator<GPSNode<PuzzleRule, PuzzleState>> getStateComparator(
			SearchStrategy strategy) {
		return (s1, s2) -> {
			switch (strategy) {
			case BFS:
				return Integer.compare(s1.getGValue(), s2.getGValue());
			case DFS:
				return Integer.compare(s2.getGValue(), s1.getGValue());
			case IDDFS:
				// TODO IDDFS Condition
				return 0;
			case GREEDY:
				// TODO Greedy Condition
				return 0;
			case A_STAR:
				// TODO AStar Condition
				return 0;
			default:
				return 0;
			}
		};
	}

}
