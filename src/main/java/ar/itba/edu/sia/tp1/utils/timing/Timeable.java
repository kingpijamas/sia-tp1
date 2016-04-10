package ar.itba.edu.sia.tp1.utils.timing;

@FunctionalInterface
public interface Timeable<V> {
	V run();
}
