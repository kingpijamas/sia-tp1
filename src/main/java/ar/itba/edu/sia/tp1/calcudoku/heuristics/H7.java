package ar.itba.edu.sia.tp1.calcudoku.heuristics;

import ar.itba.edu.sia.tp1.calcudoku.CalcudokuState;
import ar.itba.edu.sia.tp1.gps.GPSHeuristic;

/**
 * Created by scamisay on 09/04/16.
 */
public class H7 implements GPSHeuristic<CalcudokuState> {
    private static final int H3_TIME = 109;
    private static final int H5_TIME = 334;
    private static final int TOTAL_TIME = H3_TIME + H5_TIME;

    private static final H3 h3 = new H3();
    private static final H5 h5 = new H5();

    @Override
    public int getValue(CalcudokuState state) {
        int h3Value = h3.getValue(state);
        int h5Value = h5.getValue(state);
        return (h3Value * (TOTAL_TIME - H3_TIME) + h5Value * (TOTAL_TIME - H5_TIME)) / TOTAL_TIME;
    }
}
