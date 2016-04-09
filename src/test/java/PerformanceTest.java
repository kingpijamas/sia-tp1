import static org.junit.Assert.assertEquals;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import ar.itba.edu.sia.tp1.calcudoku.CalcudokuApplication;
import ar.itba.edu.sia.tp1.calcudoku.CalcudokuRule;
import ar.itba.edu.sia.tp1.calcudoku.CalcudokuState;
import ar.itba.edu.sia.tp1.calcudoku.domain.Board;
import ar.itba.edu.sia.tp1.calcudoku.heuristics.Heuristic;
import ar.itba.edu.sia.tp1.gps.engine.GPSSolution;
import ar.itba.edu.sia.tp1.gps.engine.SearchStrategy;

import org.junit.Test;

public class PerformanceTest extends GenericTest {

	@Test
	public void performanceTest() throws IOException {
		String outputfileName = "./src/test/resources/performanceN=3.csv";
		String[] inputFileName = { "./src/test/resources/3x3_easy1.json",
				"./src/test/resources/3x3_easy2.json",
				"./src/test/resources/I.json" };
		runTest(outputfileName, inputFileName);

	}

	@Test
	public void performanceTest2() throws IOException {
		String outputfileName = "./src/test/resources/performanceN=4.csv";
		String[] inputFileName = { "src/test/resources/4x4_easy.json",
				"./src/test/resources/4x4_medium.json",
				"./src/test/resources/4x4_hard.json" };
		runTest(outputfileName, inputFileName);
	}

	// answers: es un vector de matrices. Cada matriz son las respuestas de cada
	// tabler.
	// Cada matriz es de 3 columnas(i,j, valor esperado en dicha celda)
	private void runTest(String csvFileName, String[] fileNameArray)
			throws IOException {

		BufferedWriter writer = new BufferedWriter(new FileWriter(csvFileName));
		int files_count = fileNameArray.length;

		Chronometer chronometer = new Chronometer();

		for (SearchStrategy strategy : SearchStrategy.values()) {

			for (Heuristic h : heuristics) {
				chronometer.reset();

				for (int index = 0; index < files_count; index++) {
					long currentTime = 0;

					String fileName = fileNameArray[index];
					Board originalboard = setUp(fileName);
					Board board = originalboard.deepCopy();

					GPSSolution<CalcudokuRule, CalcudokuState> solution = CalcudokuApplication
							.getSolution(board, strategy, h);

					Board finalBoard = getSolutionBoard(solution);
					currentTime = chronometer.stop();

					recordData(writer, currentTime, solution, strategy);
					// // en el caso de dfs y bfs no se utiliza heuristicas
					if (strategy == SearchStrategy.DFS
							|| strategy == SearchStrategy.BFS)
						break;

				}

			}
		}

		writer.close();
	}

	private void recordData(BufferedWriter writer, long time,
			GPSSolution<CalcudokuRule, CalcudokuState> solution,
			SearchStrategy strategy) throws IOException {
		if (solution != null) {
			long exposions = solution.getExplosionCount();
			long swaps = solution.getCost();
			String str = String.format("%s, %d, %d, %d \n", strategy.toString(),
					time, exposions, swaps);
			writer.append((CharSequence) str);

		}

	}

}
