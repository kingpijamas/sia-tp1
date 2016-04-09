package ar.itba.edu.sia.tp1.calcudoku.heuristics;

import ar.itba.edu.sia.tp1.calcudoku.CalcudokuState;

/**
 * Created by scamisay on 09/04/16.
 */
public class H5 extends Heuristic{

    @Override
    public int getValue(CalcudokuState state) {
        int m = getH(state.getBoard());
        int n = state.getN();
        return Math.min(n,m);
    }

    @Override
    int getH(int incorrect_groups, int incorrect_columns) {
        return 0;
    }
}
