package ar.itba.edu.sia.tp1.calcudoku.heuristics;

import ar.itba.edu.sia.tp1.calcudoku.CalcudokuState;
import ar.itba.edu.sia.tp1.gps.GPSHeuristic;

/**
 * Created by scamisay on 09/04/16.
 */
public class H8 implements GPSHeuristic<CalcudokuState> {
    private static final H1 h1 = new H1();

    @Override
    public int getValue(CalcudokuState state) {
        int h1Value = h1.getValue(state);
        return h1Value * h1Value;
    }
}
