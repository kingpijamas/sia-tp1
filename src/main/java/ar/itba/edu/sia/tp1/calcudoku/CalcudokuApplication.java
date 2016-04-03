package ar.itba.edu.sia.tp1.calcudoku;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

import ar.itba.edu.sia.tp1.calcudoku.marshall.CalcudokuJsonWriter;
import ar.itba.edu.sia.tp1.gps.GPSHeuristic;
import ar.itba.edu.sia.tp1.gps.ProblemReader;
import ar.itba.edu.sia.tp1.gps.engine.GPSEngine;
import ar.itba.edu.sia.tp1.gps.engine.SearchStrategy;

/**
 * Created by scamisay on 02/04/16.
 */
public class CalcudokuApplication {
	public static void main(String[] args) throws FileNotFoundException {
		CalcudokuJsonWriter writer = new CalcudokuJsonWriter(
				new FileOutputStream(new File("pepe.json")));
		writer.writeInitialState(new Calcudoku(new CalcudokuState(3,
				new ArrayList<>()), problem -> 1));

		ProblemReader<CalcudokuState> reader = () -> {
			return null;
		};
		GPSHeuristic<Calcudoku> heuristic = problem -> 1;
		Calcudoku calcudoku = new Calcudoku(reader, heuristic);
		GPSEngine<CalcudokuRule, CalcudokuState> pEngine = new CalcudokuEngine(
				calcudoku, SearchStrategy.BFS);
		try {
			pEngine.solve();
		} catch (StackOverflowError e) {
			System.out.println("Solution (if any) too deep for stack.");
		}
	}
}
