package ar.itba.edu.sia.tp1.gps.engine;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;

import ar.itba.edu.sia.tp1.gps.GPSProblem;
import ar.itba.edu.sia.tp1.gps.GPSRule;
import ar.itba.edu.sia.tp1.gps.GPSState;

public class GPSSolutionProcess<R extends GPSRule, S extends GPSState<R, S>> {
	private final Queue<GPSNode<R, S>> openNodes;
	private final GPSProblem<R, S> problem;

	// TODO: trovify maybe?
	private final Map<S, Integer> bestCosts = new HashMap<S, Integer>();

	private Optional<GPSNode<R, S>> solution;
	private long explosionsCount = 0;

	public GPSSolutionProcess(GPSProblem<R, S> problem,
			Queue<GPSNode<R, S>> openNodes) {
		this.problem = problem;
		this.openNodes = openNodes;
		openNodes.add(new GPSNode<>(problem.getInitialState(), 0));
	}

	public long getExplosionsCount() {
		return explosionsCount;
	}

	public Optional<GPSNode<R, S>> solve() {
		while (!openNodes.isEmpty()) {
			GPSNode<R, S> currentNode = openNodes.poll();

			if (problem.isGoal(currentNode.getState())) {
				solution = Optional.of(currentNode);
				return solution;
			}
			explosionsCount++;
			explode(currentNode);
		}
		solution = Optional.empty();
		return solution;
	}

	private void explode(GPSNode<R, S> node) {
		if (!isBetterThanCurrentBest(node)) {
			return;
		}
		updateBest(node);
		if (problem.getRules() == null) { // XXX
			System.err.println("No rules!");
			return;
		}
		for (R rule : problem.getRules()) {
			Optional<S> newStateOpt = node.getState().apply(rule);

			if (newStateOpt.isPresent()) {
				S newState = newStateOpt.get();
				int newGValue = node.getGValue() + rule.getCost();
				if (isBetterThanCurrentBest(newState, newGValue)) {
					openNodes.add(new GPSNode<>(node, newState, newGValue));
				}
			}
		}
		return;
	}

	private boolean isBetterThanCurrentBest(GPSNode<R, S> node) {
		return isBetterThanCurrentBest(node.getState(), node.getGValue());
	}

	private boolean isBetterThanCurrentBest(S state, Integer cost) {
		return !bestCosts.containsKey(state) || cost < bestCosts.get(state);
	}

	private void updateBest(GPSNode<R, S> node) {
		bestCosts.put(node.getState(), node.getGValue());
	}
}
