package ar.itba.edu.sia.tp1.utils;

import ar.itba.edu.sia.tp1.calcudoku.CalcudokuRule;
import ar.itba.edu.sia.tp1.calcudoku.CalcudokuState;
import ar.itba.edu.sia.tp1.gps.engine.GPSSolution;

/**
 * Created by scamisay on 09/04/16.
 */
public class CalcudokuJSPrinter {

    public String printJS(GPSSolution<CalcudokuRule, CalcudokuState> solution, int n) {
        StringBuffer aSolution = new StringBuffer();
        aSolution.append("var ");
        aSolution.append(structureForDraw(solution, n));
        aSolution.append("\n\nvar solutionPath = ");
        aSolution.append(drawCalcudokuPath(solution));

        return aSolution.toString().replaceAll("n-","nL");
    }



}
