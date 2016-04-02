package ar.itba.edu.sia.tp1.eight_puzzle;

import ar.itba.edu.sia.tp1.gps.Heuristic;
import ar.itba.edu.sia.tp1.gps.api.EnvironmentReader;
import ar.itba.edu.sia.tp1.gps.api.GPSProblem;
import ar.itba.edu.sia.tp1.gps.api.GPSRule;
import ar.itba.edu.sia.tp1.gps.api.GPSState;

import java.util.LinkedList;
import java.util.List;

public class Puzzle extends GPSProblem {

    private Heuristic<Puzzle> heuristic;

    public Puzzle(EnvironmentReader environmentReader, Heuristic<Puzzle> heuristic) {
        this.reader = environmentReader;
        this.heuristic = heuristic;
    }

    @Override
    public GPSState getInitState() {
        return reader.read();
    }

    @Override
    public boolean isGoal(GPSState state) {
        return state.equals(PuzzleState.finalState());
    }

    @Override
    public List<GPSRule> getRules() {
        List<GPSRule> rules = new LinkedList<GPSRule>();
        for (Direction d : Direction.values()) {
            rules.add(new PuzzleRule(d));
        }
        return rules;
    }

    // Valor Heurística para A*
    @Override
    public int getHValue(GPSState state) {
        return heuristic.geH(this);
    }

}
