package ar.itba.edu.sia.tp1.calcudoku;

import ar.itba.edu.sia.tp1.gps.GPSEngine;
import ar.itba.edu.sia.tp1.gps.SearchStrategy;
import ar.itba.edu.sia.tp1.gps.api.GPSProblem;

/**
 * Created by scamisay on 02/04/16.
 */
public class CalcudokuEngine extends GPSEngine{

    public CalcudokuEngine(GPSProblem problem, SearchStrategy strategy) {
        super(problem, strategy);
    }
}
