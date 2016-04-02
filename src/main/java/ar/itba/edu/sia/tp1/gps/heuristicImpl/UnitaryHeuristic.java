package ar.itba.edu.sia.tp1.gps.heuristicImpl;

import ar.itba.edu.sia.tp1.eight_puzzle.Puzzle;
import ar.itba.edu.sia.tp1.gps.Heuristic;

/**
 * Created by scamisay on 02/04/16.
 */
public class UnitaryHeuristic implements Heuristic<Puzzle> {

    @Override
    public Integer geH(Puzzle problem) {
        return 1;
    }
}
