package ar.itba.edu.sia.tp1.util.timing;

@FunctionalInterface
public interface Timeable<V> {
	V run();
}
