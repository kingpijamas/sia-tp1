package ar.itba.edu.sia.tp1.calcudoku;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import ar.itba.edu.sia.tp1.calcudoku.domain.Board;
import ar.itba.edu.sia.tp1.calcudoku.heuristic.H1;
import ar.itba.edu.sia.tp1.calcudoku.heuristic.H2;
import ar.itba.edu.sia.tp1.calcudoku.heuristic.H6;
import ar.itba.edu.sia.tp1.calcudoku.run.CalcudokuRun;
import ar.itba.edu.sia.tp1.gps.GPSHeuristic;
import ar.itba.edu.sia.tp1.gps.engine.GPSSolution;
import ar.itba.edu.sia.tp1.gps.engine.SearchStrategy;
import ar.itba.edu.sia.tp1.util.timing.TimedResults;
import ar.itba.edu.sia.tp1.util.timing.Timer;

public class PerformanceTest extends GenericTest {
	private static final GPSHeuristic<CalcudokuState> dummyHeuristic = state -> {
		throw new IllegalStateException();
	};

	private static final List<GPSHeuristic<CalcudokuState>> heuristics = Arrays.asList( new H6());

	//@Test
	public void performanceTest() throws Exception {
		String outputfileName = "./src/test/resources/performanceN=3.csv";
		String[] inputFileName = { "./src/test/resources/3x3_easy1.json", "./src/test/resources/3x3_easy2.json",
				"./src/test/resources/I.json" };
		runTest(outputfileName, inputFileName, 5);

	}

	@Test
	public void performanceTest2() throws Exception {
		String outputfileName = "./src/test/resources/performanceN=4.csv";
		String[] inputFileName = { "./src/test/resources/4x4_easy.json", "./src/test/resources/4x4_medium.json",
				"./src/test/resources/4x4_hard.json" };
		runTest(outputfileName, inputFileName, 5);
	}

	//@Test
	public void performanceTest4() throws Exception {
		String outputfileName = "./src/test/resources/performanceN=5.csv";
		String[] inputFileName = {  "./src/test/resources/5x5_easy.json"
							,"./src/test/resources/5x5_medium.json",
							"./src/test/resources/5x5_hard.json"};
		runTest(outputfileName, inputFileName, 5);
	}
	
	
	public void performanceTest3() throws Exception {
		String outputfileName = "./src/test/resources/performanceN=6.csv";
		String[] inputFileName = { "src/test/resources/6x6_easy.json" };
		runTest(outputfileName, inputFileName, 1);
	}

	// answers: es un vector de matrices. Cada matriz son las respuestas de cada
	// tabler.
	// Cada matriz es de 3 columnas(i,j, valor esperado en dicha celda)
	private void runTest(String csvFileName, String[] fileNameArray, int max_repeat) throws Exception {

		BufferedWriter writer = new BufferedWriter(new FileWriter(csvFileName));
		int files_count = fileNameArray.length;

		for (SearchStrategy strategy : SearchStrategy.values()) {
			for (GPSHeuristic<CalcudokuState> heuristic : heuristics) {

				List<Long> current_times = new ArrayList<Long>();
				List<Long> current_explosions=new ArrayList<Long>();
				List<Long> current_swaps=new ArrayList<Long>();
				for (int index = 0; index < files_count; index++) {
					for (int repeat = 0; repeat < max_repeat; repeat++) {
						String fileName = fileNameArray[index];
						Board originalboard = setUp(fileName);
						Board board = originalboard.deepCopy();

						CalcudokuRun test = new CalcudokuRun(board, strategy,
								heuristic);
						TimedResults<GPSSolution<CalcudokuRule, CalcudokuState>> timedResult = Timer
								.timer().timesToRun(1).toTime(test::run).start();

						GPSSolution<CalcudokuRule, CalcudokuState> solution = timedResult
								.getLast().getValue();
						//recoleccion de datos
						long current_time = timedResult.getLast().getElapsedTime();
						current_times.add(current_time);
						
						long explosions=solution.getExplosionCount();
						current_explosions.add(explosions);
						
						long swaps=solution.getCost();
						current_swaps.add(swaps);
						//
						
						Board finalBoard = getSolutionBoard(solution);
					}
				}
				
				long time_avg = mean(current_times);
				long explosions_avg=mean(current_explosions);
				long swaps_avg=mean(current_swaps);
				
				recordData(writer, time_avg,explosions_avg,swaps_avg , strategy, heuristic);

				if (strategy == SearchStrategy.BFS || strategy == SearchStrategy.DFS
						|| strategy == SearchStrategy.IDDFS)
					break;// si es dfs o bfs se haria lo mismo, no importa la
							// heuristica ya que no la usa

			}
		}

		writer.close();
	}

	private long mean(List<Long> current_times) {
		long size = current_times.size();
		long sum = 0;

		for (long l : current_times) {
			sum += l;
		}
		return sum / size;
	}

	private void recordData(BufferedWriter writer, long time, long explosions,long swaps,
			SearchStrategy strategy, GPSHeuristic<CalcudokuState> heuristic) throws IOException {
		

		

		String strategyName = strategy.toString();
		if (strategy.isInformed()) {
			strategyName += "_" + heuristic.toString();
		}

		String str = String.format("%s, %d, %d, %d %n", strategyName, time, explosions, swaps);
		writer.append((CharSequence) str);
		writer.flush();
	}

	private List<GPSHeuristic<CalcudokuState>> getHeuristicsFor(SearchStrategy strategy) {
		if (strategy.isInformed()) {
			return heuristics;
		}
		return Arrays.asList(dummyHeuristic);
	}
}