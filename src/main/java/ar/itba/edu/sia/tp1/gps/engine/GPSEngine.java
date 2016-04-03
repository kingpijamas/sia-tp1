package ar.itba.edu.sia.tp1.gps.engine;

import java.util.Optional;
import java.util.Queue;

import ar.itba.edu.sia.tp1.gps.GPSProblem;
import ar.itba.edu.sia.tp1.gps.GPSRule;
import ar.itba.edu.sia.tp1.gps.GPSState;

public abstract class GPSEngine<R extends GPSRule, S extends GPSState<R, S>> {
	private final GPSProblem<R, S> problem;

	public GPSEngine(GPSProblem<R, S> problem) {
		this.problem = problem;
	}

	public void solve() {
		GPSSolutionProcess<R, S> solutionProcess = buildSolutionProcess();
		Optional<GPSNode<R, S>> solution = solutionProcess.solve();

		if (!solution.isPresent()) {
			System.err.println("FAILED! solution not found!");
			System.out.println("Expanded nodes: "
					+ solutionProcess.getExplosionsCount());
		} else {
			GPSNode<R, S> currentNode = solution.get();
			System.out.println("OK! solution found!");
			System.out.println(currentNode.getSolution());
			System.out.println("Expanded nodes: "
					+ solutionProcess.getExplosionsCount());
			System.out.println("Solution cost: " + currentNode.getGValue());
		}
	}

	private GPSSolutionProcess<R, S> buildSolutionProcess() {
		Queue<GPSNode<R, S>> openNodes = buildOpenNodes();
		openNodes.add(new GPSNode<>(problem.getInitialState(), 0));

		return new GPSSolutionProcess<>(problem, openNodes);
	}

	protected abstract Queue<GPSNode<R, S>> buildOpenNodes();
}
