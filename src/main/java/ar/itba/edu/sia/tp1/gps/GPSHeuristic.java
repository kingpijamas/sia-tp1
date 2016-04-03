package ar.itba.edu.sia.tp1.gps;

/**
 * Created by scamisay on 02/04/16.
 */
@FunctionalInterface
public interface GPSHeuristic<P extends GPSProblem<?, ?>> {
	int getValue(P problem);
}
