package ar.itba.edu.sia.tp1.gps;

/**
 * Created by scamisay on 29/03/16.
 */
@FunctionalInterface
public interface ProblemReader<S extends GPSState<?, S>> {
	S readInitialState();
}
