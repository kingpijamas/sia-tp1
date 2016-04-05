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

	static <R extends GPSRule, S extends GPSState<R, S>> GPSSolution<R, S> of(
			GPSNode<R, S> lastNode, long explosionCount) {
		return new GPSSolution<R, S>(lastNode, explosionCount);
	}

	static <R extends GPSRule, S extends GPSState<R, S>> GPSSolution<R, S> failure(
			long explosionCount) {
		return of(null, explosionCount);
	}

	GPSSolution(GPSNode<R, S> lastNode, long explosionCount) {
		this.path = getSolutionPath(lastNode);
		this.explosionCount = explosionCount;
		this.cost = lastNode == null ? null : lastNode.getGValue();
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
