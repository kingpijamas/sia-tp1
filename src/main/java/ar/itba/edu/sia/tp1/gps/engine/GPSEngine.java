package ar.itba.edu.sia.tp1.gps.engine;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Collectors;

import org.apache.commons.collections4.IterableUtils;

import ar.itba.edu.sia.tp1.gps.GPSProblem;
import ar.itba.edu.sia.tp1.gps.GPSRule;
import ar.itba.edu.sia.tp1.gps.GPSState;

public abstract class GPSEngine<R extends GPSRule, S extends GPSState<R, S>> {
	private final SearchStrategy searchStrategy;
	private final GPSProblem<R, S> problem;
	private final int maxSolutionDepth;

	public GPSEngine(GPSProblem<R, S> problem, SearchStrategy searchStrategy, int maxSolutionDepth) {
		this.problem = problem;
		this.searchStrategy = searchStrategy;
		this.maxSolutionDepth = maxSolutionDepth;
	}

	public GPSSolution<R, S> solve() {
		GPSSolutionProcess<R, S> solutionProcess = buildSolutionProcess();
		GPSSolution<R, S> solution = solutionProcess.solve();

		if (solution.isSuccess()) {
			System.out.println("OK! solution found!");
			System.out.println("Expanded nodes: " + solution.getExplosionCount());
			System.out.println("Solution cost: " + solution.getCost());
			System.out.println("\nSolution path (chronological order):\n" + IterableUtils.toList(solution.getPath())
					.stream().map(GPSNode::toString).collect(Collectors.joining("\n")));
		} else {
			System.err.println("FAILED! solution not found!");
			System.out.println("Expanded nodes: " + solution.getExplosionCount());
		}
		return solution;
	}

	private GPSSolutionProcess<R, S> buildSolutionProcess() {
		return new GPSSolutionProcess<>(problem, () -> buildOpenNodes(), searchStrategy, maxSolutionDepth);
	}

	protected Queue<GPSNode<R, S>> buildOpenNodes() {
		return new PriorityQueue<>(getNodeComparator(searchStrategy));
	}

	private Comparator<GPSNode<R, S>> getNodeComparator(SearchStrategy strategy) {
		switch (strategy) {
		case BFS:
			return (n1, n2) -> Integer.compare(n1.getGValue(), n2.getGValue());
		case DFS:
			// break through
		case IDDFS:
			return (n1, n2) -> Integer.compare(n2.getGValue(), n1.getGValue());
		case GREEDY:
			return (n1, n2) -> Integer.compare(n1.getHValue(), n2.getHValue());
		case A_STAR:
			return (n1, n2) -> Integer.compare(n1.getFValue(), n2.getFValue());
		default:
			throw new IllegalStateException("Unknown search strategy");
		}
	}
}
