package ar.itba.edu.sia.tp1.gps;

import ar.itba.edu.sia.tp1.gps.api.GPSProblem;

/**
 * Created by scamisay on 02/04/16.
 */
@FunctionalInterface
public interface Heuristic<T extends GPSProblem> {

    public Integer geH(T problem);
}
