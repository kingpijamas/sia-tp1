package ar.itba.edu.sia.tp1.eight_puzzle;

import ar.itba.edu.sia.tp1.gps.GPSEngine;
import ar.itba.edu.sia.tp1.gps.Heuristic;
import ar.itba.edu.sia.tp1.gps.SearchStrategy;
import ar.itba.edu.sia.tp1.gps.api.enviromentReaderImpl.StandardInputEnvironmentReader;
import ar.itba.edu.sia.tp1.gps.heuristicImpl.UnitaryHeuristic;

/**
 * Created by scamisay on 30/03/16.
 */
public class Application {

    public static void main(String[] args) {
        Puzzle puzzle = new Puzzle(new StandardInputEnvironmentReader(), problem->1);
        GPSEngine pEngine = new PuzzleEngine(puzzle, SearchStrategy.BFS);
        try {
            pEngine.solve();
        } catch (StackOverflowError e) {
            System.out.println("Solution (if any) too deep for stack.");
        }
    }
}
