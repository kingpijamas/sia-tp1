package ar.itba.edu.sia.tp1.calcudoku;

import ar.itba.edu.sia.tp1.gps.GPSProblem;
import ar.itba.edu.sia.tp1.gps.engine.GPSEngine;
import ar.itba.edu.sia.tp1.gps.engine.SearchStrategy;

/**
 * Created by scamisay on 02/04/16.
 */
public class CalcudokuEngine extends GPSEngine<CalcudokuRule, CalcudokuState> {
	public CalcudokuEngine(GPSProblem<CalcudokuRule, CalcudokuState> problem,
			SearchStrategy searchStrategy) {
		super(problem, searchStrategy, problem.getRules().size());
	}
}
