package ar.itba.edu.sia.tp1.calcudoku;

import java.util.Queue;

import ar.itba.edu.sia.tp1.gps.GPSProblem;
import ar.itba.edu.sia.tp1.gps.engine.GPSEngine;
import ar.itba.edu.sia.tp1.gps.engine.GPSNode;
import ar.itba.edu.sia.tp1.gps.engine.SearchStrategy;

/**
 * Created by scamisay on 02/04/16.
 */
public class CalcudokuEngine extends GPSEngine<CalcudokuRule, CalcudokuState> {
	private final SearchStrategy searchStrategy;

	public CalcudokuEngine(GPSProblem<CalcudokuRule, CalcudokuState> problem,
			SearchStrategy searchStrategy) {
		super(problem);
		this.searchStrategy = searchStrategy;
	}

	@Override
	protected Queue<GPSNode<CalcudokuRule, CalcudokuState>> buildOpenNodes() {
		return null;
	}
}
