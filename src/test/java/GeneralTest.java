import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import ar.itba.edu.sia.tp1.calcudoku.CalcudokuApplication;
import ar.itba.edu.sia.tp1.calcudoku.CalcudokuRule;
import ar.itba.edu.sia.tp1.calcudoku.CalcudokuState;
import ar.itba.edu.sia.tp1.calcudoku.domain.Board;
import ar.itba.edu.sia.tp1.gps.GPSHeuristic;
import ar.itba.edu.sia.tp1.gps.engine.GPSSolution;
import ar.itba.edu.sia.tp1.gps.engine.SearchStrategy;

public class GeneralTest extends GenericTest {
	// answers: es una matriz de 3 columnas(i,j, valor esperado en dicha celda)
	private void runTest(String fileName, int[][] answers) throws IOException {
		Board originalboard = setUp(fileName);

		for (SearchStrategy strategy : SearchStrategy.unInformed()) {
			Board board = originalboard.deepCopy();
			GPSHeuristic<CalcudokuState> h = state -> 1;
			runStrategyTest(board, strategy, h, answers);
		}

		for (SearchStrategy strategy : SearchStrategy.informed()) {
			Board board = originalboard.deepCopy();
			for (GPSHeuristic<CalcudokuState> h : heuristics) {
				runStrategyTest(board, strategy, h, answers);
			}
		}
	}

	private void runStrategyTest(Board board, SearchStrategy strategy, GPSHeuristic<CalcudokuState> h,
			int[][] answers) {
		System.out.println(strategy);

		GPSSolution<CalcudokuRule, CalcudokuState> solution = CalcudokuApplication.getSolution(board, strategy, h);

		Board finalBoard = getSolutionBoard(solution);
		if (solution.isFailure())
			System.out.println("algo salio mal");
		for (int[] ansLine : answers) {
			int i = ansLine[0];
			int j = ansLine[1];
			int value = ansLine[2];
			assertEquals(value, finalBoard.getCellValue(position(i, j)).intValue());
		}
	}

	// 2x2
	@Test
	public void test1() throws IOException {
		String fileName = "./src/test/resources/A.json";

		int[][] answers = { { 0, 0, 1 }, { 0, 1, 2 }, { 1, 0, 2 }, { 1, 1, 1 } };

		runTest(fileName, answers);

	}

	// 2x2
	@Test
	public void test2() throws IOException {
		String fileName = "./src/test/resources/D.json";

		int[][] answers = { { 0, 0, 1 }, { 0, 1, 2 }, { 1, 0, 2 }, { 1, 1, 1 } };
		runTest(fileName, answers);
	}

	// 3x3
	@Test
	public void test3() throws IOException {
		String fileName = "./src/test/resources/3x3_easy1.json";

		int[][] answers = { { 0, 0, 3 }, { 0, 1, 1 }, { 0, 2, 2 }, { 1, 0, 2 }, { 1, 1, 3 }, { 1, 2, 1 }, { 2, 0, 1 },
				{ 2, 1, 2 }, { 2, 2, 3 } };
		runTest(fileName, answers);
	}

	// 3x3
	@Test
	public void test4() throws IOException {
		String fileName = "./src/test/resources/3x3_easy2.json";

		int[][] answers = { { 0, 0, 2 }, { 0, 1, 1 }, { 0, 2, 3 }, { 1, 0, 1 }, { 1, 1, 3 }, { 1, 2, 2 }, { 2, 0, 3 },
				{ 2, 1, 2 }, { 2, 2, 1 } };
		runTest(fileName, answers);
	}

	// 4x4
	// @Test
	public void test5() throws IOException {
		String fileName = "./src/test/resources/4X4_easy.json";

		int[][] answers = { { 0, 0, 4 }, { 0, 1, 3 }, { 0, 2, 2 }, { 0, 3, 1 }, { 1, 0, 1 }, { 1, 1, 4 }, { 1, 2, 3 },
				{ 1, 3, 2 }, { 2, 0, 3 }, { 2, 1, 2 }, { 2, 2, 1 }, { 2, 3, 4 }, { 3, 0, 2 }, { 3, 1, 1 }, { 3, 2, 4 },
				{ 3, 3, 3 } };
		runTest(fileName, answers);
	}

}
