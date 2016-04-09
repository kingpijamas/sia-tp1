import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

import ar.itba.edu.sia.tp1.calcudoku.CalcudokuApplication;
import ar.itba.edu.sia.tp1.calcudoku.CalcudokuRule;
import ar.itba.edu.sia.tp1.calcudoku.CalcudokuState;
import ar.itba.edu.sia.tp1.calcudoku.domain.Board;
import ar.itba.edu.sia.tp1.calcudoku.domain.Position;
import ar.itba.edu.sia.tp1.calcudoku.heuristics.H1;
import ar.itba.edu.sia.tp1.calcudoku.heuristics.H2;
import ar.itba.edu.sia.tp1.calcudoku.heuristics.Heuristic;
import ar.itba.edu.sia.tp1.calcudoku.marshall.CalcudokuJsonParser;
import ar.itba.edu.sia.tp1.gps.engine.GPSNode;
import ar.itba.edu.sia.tp1.gps.engine.GPSSolution;
import ar.itba.edu.sia.tp1.gps.engine.SearchStrategy;

public class GeneralTest extends GenericTest {
		// answers: es una matriz de 3 columnas(i,j, valor esperado en dicha celda)
	private void runTest(String fileName, int[][] answers) throws IOException {
		Board originalboard = setUp(fileName);

		for (SearchStrategy strategy : SearchStrategy.values()) {

			Board board = originalboard.deepCopy();
			System.out.println(strategy);

			for (Heuristic h : heuristics) {
				GPSSolution<CalcudokuRule, CalcudokuState> solution = CalcudokuApplication.getSolution(board, strategy,
						h);

				Board finalBoard = getSolutionBoard(solution);

				for (int[] ansLine : answers) {
					int i = ansLine[0];
					int j = ansLine[1];
					int value = ansLine[2];
					assertEquals(value, finalBoard.getCellValue(position(i, j)).intValue());

				}
				// en el caso de dfs y bfs no se utiliza heuristicas
				if (strategy == SearchStrategy.DFS || strategy == SearchStrategy.BFS)
					break;

			}

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
