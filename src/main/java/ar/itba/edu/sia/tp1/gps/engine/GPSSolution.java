package ar.itba.edu.sia.tp1.gps.engine;

import java.util.ArrayDeque;
import java.util.Deque;

import org.apache.commons.collections4.IterableUtils;

import ar.itba.edu.sia.tp1.gps.GPSRule;
import ar.itba.edu.sia.tp1.gps.GPSState;

public class GPSSolution<R extends GPSRule, S extends GPSState<R, S>> {
	private final Iterable<GPSNode<R, S>> path;
	private final long explosionCount;
	private final Integer cost;
	private final long analyzedNodes;

	static <R extends GPSRule, S extends GPSState<R, S>> GPSSolution<R, S> of(
			GPSNode<R, S> lastNode, long explosionCount, long analyzedNodes) {
		return new GPSSolution<R, S>(lastNode, explosionCount, analyzedNodes);
	}

	static <R extends GPSRule, S extends GPSState<R, S>> GPSSolution<R, S> failure(
			long explosionCount, long analyzedNodes) {
		return of(null, explosionCount, analyzedNodes);
	}

	GPSSolution(GPSNode<R, S> lastNode, long explosionCount,
			long analyzedNodes) {
		this.path = getSolutionPath(lastNode);
		this.explosionCount = explosionCount;
		this.cost = lastNode == null ? null : lastNode.getGValue();
		this.analyzedNodes = analyzedNodes;
	}

	public boolean isSuccess() {
		return path.iterator().hasNext();
	}

	public boolean isFailure() {
		return !isSuccess();
	}

	public long getExplosionCount() {
		return explosionCount;
	}

	public int getCost() {
		if (cost == null) {
			throw new IllegalStateException();
		}
		return cost;
	}

	public Iterable<GPSNode<R, S>> getPath() {
		return path;
	}

	public long getAnalyzedNodes() {
		return analyzedNodes;
	}

	private Iterable<GPSNode<R, S>> getSolutionPath(GPSNode<R, S> node) {
		Deque<GPSNode<R, S>> solution = new ArrayDeque<>();
		GPSNode<R, S> currNode = node;
		while (currNode != null) {
			solution.push(currNode);
			currNode = currNode.getParent();
		}
		return IterableUtils.unmodifiableIterable(solution);
	}
}
