package ar.itba.edu.sia.tp1.gps.engine;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum SearchStrategy {
	GREEDY(true), A_STAR(true), IDDFS, BFS, DFS;

	private final boolean informed;

	private SearchStrategy(boolean informed) {
		this.informed = informed;
	}

	private SearchStrategy() {
		this(false);
	}

	public boolean isInformed() {
		return informed;
	}

	public static Iterable<SearchStrategy> informed() {
		return Stream.of(values()).filter(SearchStrategy::isInformed)
				.collect(Collectors.toList());
	}

	public static Iterable<SearchStrategy> unInformed() {
		return Stream.of(values()).filter(strategy -> !strategy.isInformed())
				.collect(Collectors.toList());
	}
}
