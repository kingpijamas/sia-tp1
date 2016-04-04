package ar.itba.edu.sia.tp1.gps.engine;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

import ar.itba.edu.sia.tp1.gps.GPSProblem;
import ar.itba.edu.sia.tp1.gps.GPSRule;
import ar.itba.edu.sia.tp1.gps.GPSState;

public abstract class GPSEngine<R extends GPSRule, S extends GPSState<R, S>> {
	private final SearchStrategy searchStrategy;
	private final GPSProblem<R, S> problem;

	public GPSEngine(GPSProblem<R, S> problem, SearchStrategy searchStrategy) {
		this.problem = problem;
		this.searchStrategy = searchStrategy;
	}

	public void solve() {
		GPSSolutionProcess<R, S> solutionProcess = buildSolutionProcess();
		GPSSolution<R, S> solution = solutionProcess.solve();

		if (solution.isSuccess()) {
			System.out.println("OK! solution found!");
			System.out.println(
					"Expanded nodes: " + solutionProcess.getExplosionsCount());
			System.out.println("Solution cost: " + solution.getCost());
			System.out.println(solution.getPath());
		} else {
			System.err.println("FAILED! solution not found!");
			System.out.println(
					"Expanded nodes: " + solutionProcess.getExplosionsCount());
		}
	}

	private GPSSolutionProcess<R, S> buildSolutionProcess() {
		return new GPSSolutionProcess<>(problem, buildOpenNodes());
	}

	protected Queue<GPSNode<R, S>> buildOpenNodes() {
		return new PriorityQueue<>(getStateComparator(searchStrategy));
	}

	private Comparator<GPSNode<R, S>> getStateComparator(
			SearchStrategy strategy) {
		// TODO: concat the (informed) comparators to heuristic comparators!
		switch (strategy) {
			case BFS :
				return (s1, s2) -> Integer.compare(s1.getGValue(),
						s2.getGValue());
			case DFS :
				return (s1, s2) -> Integer.compare(s2.getGValue(),
						s1.getGValue());
			case IDDFS :
				// TODO IDDFS Condition
				return (s1, s2) -> 0;
			case GREEDY :
				// TODO Greedy Condition
				return (s1, s2) -> 0;
			case A_STAR :
				// TODO AStar Condition
				return (s1, s2) -> 0;
			default :
				throw new IllegalStateException("Unknown search strategy");
		}
	}
}
