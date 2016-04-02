package ar.itba.edu.sia.tp1.calcudoku;

import ar.itba.edu.sia.tp1.gps.GPSEngine;
import ar.itba.edu.sia.tp1.gps.Heuristic;
import ar.itba.edu.sia.tp1.gps.SearchStrategy;
import ar.itba.edu.sia.tp1.gps.api.EnvironmentReader;

/**
 * Created by scamisay on 02/04/16.
 */
public class CalcudokuApplication {
    public static void main(String[] args) {
        EnvironmentReader reader = () -> {
            return null;
        };
        Heuristic<Calcudoku> heuristic = problem -> 1;
        Calcudoku calcudoku = new Calcudoku(reader, heuristic);
        GPSEngine pEngine = new CalcudokuEngine(calcudoku, SearchStrategy.BFS);
        try {
            pEngine.solve();
        } catch (StackOverflowError e) {
            System.out.println("Solution (if any) too deep for stack.");
        }
    }
}
