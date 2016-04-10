package ar.itba.edu.sia.tp1.calcudoku.heuristics;

import ar.itba.edu.sia.tp1.calcudoku.CalcudokuState;
import ar.itba.edu.sia.tp1.calcudoku.domain.Position;
import ar.itba.edu.sia.tp1.gps.GPSHeuristic;

/**
 * Created by scamisay on 09/04/16.
 */
public class H5 implements GPSHeuristic<CalcudokuState> {

    @Override
    public int getValue(CalcudokuState state) {
        int n = state.getN();
        int expectedValueForColumn = n*(n+1)/2;
        int swapsNeeded = 0;

        for(int j=0 ; j< n; j++){
            int sumCol = 0;
            for(int i=0 ; i< n; i++){
                sumCol += state.getBoard().getCellValue(new Position(i,j));
            }
            float delta = Math.abs(sumCol - expectedValueForColumn);
            if(delta > 0){
                swapsNeeded += Math.ceil(delta/n);
            }
        }
        return swapsNeeded;
    }

}
