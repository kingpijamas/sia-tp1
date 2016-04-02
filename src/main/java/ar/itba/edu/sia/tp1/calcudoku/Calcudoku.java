package ar.itba.edu.sia.tp1.calcudoku;

import ar.itba.edu.sia.tp1.gps.Heuristic;
import ar.itba.edu.sia.tp1.gps.api.EnvironmentReader;
import ar.itba.edu.sia.tp1.gps.api.GPSProblem;
import ar.itba.edu.sia.tp1.gps.api.GPSRule;
import ar.itba.edu.sia.tp1.gps.api.GPSState;

import java.util.List;

/**
 * Created by scamisay on 02/04/16.
 */
public class Calcudoku extends GPSProblem{

    private Heuristic<Calcudoku> heuristic;

    public Calcudoku(EnvironmentReader environmentReader, Heuristic<Calcudoku> heuristic) {
        this.reader = environmentReader;
        this.heuristic = heuristic;
    }

    @Override
    public GPSState getInitState() {
        return reader.read();
    }

    @Override
    public boolean isGoal(GPSState state) {
        return false;
    }

    @Override
    public List<GPSRule> getRules() {
        return null;
    }

    @Override
    public int getHValue(GPSState state) {
        return 0;
    }
}
