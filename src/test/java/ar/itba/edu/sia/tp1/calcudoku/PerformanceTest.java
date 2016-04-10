package ar.itba.edu.sia.tp1.calcudoku;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import ar.itba.edu.sia.tp1.calcudoku.domain.Board;
import ar.itba.edu.sia.tp1.calcudoku.heuristic.H1;
import ar.itba.edu.sia.tp1.calcudoku.heuristic.H2;
import ar.itba.edu.sia.tp1.gps.GPSHeuristic;
import ar.itba.edu.sia.tp1.gps.engine.GPSSolution;
import ar.itba.edu.sia.tp1.gps.engine.SearchStrategy;
import ar.itba.edu.sia.tp1.utils.timing.TimedResults;
import ar.itba.edu.sia.tp1.utils.timing.Timer;

public class PerformanceTest extends GenericTest {
	private static final GPSHeuristic<CalcudokuState> dummyHeuristic = state -> 1;

	private static final List<GPSHeuristic<CalcudokuState>> heuristics = Arrays
			.asList(new H1(), new H2());

	public void performanceTest() throws Exception {
		String outputfileName = "./src/test/resources/performanceN=3.csv";
		String[] inputFileName = { "./src/test/resources/3x3_easy1.json",
				"./src/test/resources/3x3_easy2.json",
				"./src/test/resources/I.json" };
		runTest(outputfileName, inputFileName);

	}

	public void performanceTest2() throws Exception {
		String outputfileName = "./src/test/resources/performanceN=4.csv";
		String[] inputFileName = { "./src/test/resources/4x4_easy.json",
				"./src/test/resources/4x4_medium.json",
				"./src/test/resources/4x4_hard.json" };
		runTest(outputfileName, inputFileName);
	}

	public void performanceTest3() throws Exception {
		String outputfileName = "./src/test/resources/performanceN=6.csv";
		String[] inputFileName = { "src/test/resources/6x6_easy.json" };
		runTest(outputfileName, inputFileName);
	}

	// answers: es un vector de matrices. Cada matriz son las respuestas de cada
	// tabler.
	// Cada matriz es de 3 columnas(i,j, valor esperado en dicha celda)
	private void runTest(String csvFileName, String[] fileNameArray)
			throws Exception {

		BufferedWriter writer = new BufferedWriter(new FileWriter(csvFileName));
		int files_count = fileNameArray.length;

		for (SearchStrategy strategy : SearchStrategy.values()) {
			for (GPSHeuristic<CalcudokuState> heuristic : getHeuristicsFor(strategy)) {
				for (int index = 0; index < files_count; index++) {
					String fileName = fileNameArray[index];
					Board originalboard = setUp(fileName);
					Board board = originalboard.deepCopy();

					TimedResults<GPSSolution<CalcudokuRule, CalcudokuState>> timedResult = Timer
							.time(() -> CalcudokuApplication.getSolution(board,
									strategy, heuristic));

					GPSSolution<CalcudokuRule, CalcudokuState> solution = timedResult
							.getLast().getValue();
					long time = timedResult.getLast().getElapsedTime();

					Board finalBoard = getSolutionBoard(solution);
					recordData(writer, time, solution, strategy, heuristic);
				}
			}
		}

		writer.close();
	}

	private void recordData(BufferedWriter writer, long time,
			GPSSolution<CalcudokuRule, CalcudokuState> solution,
			SearchStrategy strategy, GPSHeuristic<CalcudokuState> heuristic)
			throws IOException {
		if (solution.isFailure()) {
			return;
		}

		long explosions = solution.getExplosionCount();
		long swaps = solution.getCost();

		String strategyName = strategy.toString();
		if (strategy.isInformed()) {
			strategyName += "_" + heuristic.toString();
		}

		String str = String.format("%s, %d, %d, %d %n", strategyName, time,
				explosions, swaps);
		writer.append((CharSequence) str);
	}

	private List<GPSHeuristic<CalcudokuState>> getHeuristicsFor(
			SearchStrategy strategy) {
		if (strategy.isInformed()) {
			return heuristics;
		}
		return Arrays.asList(dummyHeuristic);
	}
}
