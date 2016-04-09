package ar.itba.edu.sia.tp1.calcudoku.heuristics;

import ar.itba.edu.sia.tp1.calcudoku.CalcudokuState;

/**
 * Created by scamisay on 09/04/16.
 */
public class H4 extends H1 {

    @Override
    public int getValue(CalcudokuState state) {
        int m = getH(state.getBoard());
        int n = state.getN();
        return Math.min(n,m);
    }
}
