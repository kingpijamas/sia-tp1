package ar.itba.edu.sia.tp1.calcudoku.heuristics;

import ar.itba.edu.sia.tp1.calcudoku.CalcudokuState;

/**
 * Created by scamisay on 09/04/16.
 */
public class H4 extends H1 {

    @Override
    public int getValue(CalcudokuState state) {
        int h1 = getH(state.getBoard());
        int n = state.getN();

        int c = state.getBoard().invalidColumnsCount();
        if(c >= n/2){
            return n;
        }else{
            return h1;
        }
    }
}
