package ar.itba.edu.sia.tp1.calcudoku.heuristic;

@FunctionalInterface
public interface BinaryFunction {
	int apply(int x, int y);
}
