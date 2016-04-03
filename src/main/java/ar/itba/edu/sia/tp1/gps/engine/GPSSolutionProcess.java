package ar.itba.edu.sia.tp1.gps.engine;

import gnu.trove.map.hash.TObjectIntHashMap;

import java.util.Optional;
import java.util.Queue;

import ar.itba.edu.sia.tp1.gps.GPSProblem;
import ar.itba.edu.sia.tp1.gps.GPSRule;
import ar.itba.edu.sia.tp1.gps.GPSState;

class GPSSolutionProcess<R extends GPSRule, S extends GPSState<R, S>> {
	private final Queue<GPSNode<R, S>> openNodes;
	private final GPSProblem<R, S> problem;

	private final TObjectIntHashMap<S> bestCosts = new TObjectIntHashMap<S>();

	private Optional<GPSNode<R, S>> solution;
	private long explosionsCount = 0;

	GPSSolutionProcess(GPSProblem<R, S> problem, Queue<GPSNode<R, S>> openNodes) {
		this.problem = problem;
		this.openNodes = openNodes;
		openNodes.add(new GPSNode<>(problem.getInitialState(), 0));
	}

	long getExplosionsCount() {
		return explosionsCount;
	}

	Optional<GPSNode<R, S>> solve() {
		while (!openNodes.isEmpty()) {
			GPSNode<R, S> currentNode = openNodes.poll();

			if (problem.isGoal(currentNode.getState())) {
				solution = Optional.of(currentNode);
				return solution;
			}
			explode(currentNode);
		}
		solution = Optional.empty();
		return solution;
	}

	private void explode(GPSNode<R, S> node) {
		explosionsCount++;
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
					GPSNode<R, S> newNode = new GPSNode<>(node, newState,
							newGValue);
					openNodes.add(newNode);
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
