package ar.itba.edu.sia.tp1.eight_puzzle;

import ar.itba.edu.sia.tp1.gps.SearchStrategy;
import ar.itba.edu.sia.tp1.gps2.GPSEngine;
import ar.itba.edu.sia.tp1.gps2.api.enviromentReaderImpl.StandardInputEnvironmentReader;

/**
 * Created by scamisay on 30/03/16.
 */
public class Application {

    public static void main(String[] args) {
        Puzzle puzzle = new Puzzle(new StandardInputEnvironmentReader());
        GPSEngine pEngine = new PuzzleEngine(puzzle, SearchStrategy.ASTAR);
        try {
            pEngine.solve();
        } catch (StackOverflowError e) {
            System.out.println("Solution (if any) too deep for stack.");
        }
    }
}
