package ar.itba.edu.sia.tp1.calcudoku.heuristic;

import static ar.itba.edu.sia.tp1.calcudoku.domain.Operator.IDENTITY;
import static ar.itba.edu.sia.tp1.calcudoku.domain.Operator.PLUS;
import static ar.itba.edu.sia.tp1.calcudoku.domain.Position.position;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

import ar.itba.edu.sia.tp1.calcudoku.CalcudokuState;
import ar.itba.edu.sia.tp1.calcudoku.domain.Board;
import ar.itba.edu.sia.tp1.calcudoku.domain.Group;
import ar.itba.edu.sia.tp1.calcudoku.marshall.CalcudokuJsonParser;

public class HeuristicsTest {
	private static final H1 h1 = new H1();
	private static final H2 h2 = new H2();

	private Board board;
	private CalcudokuState state;

	private void setUp(String fileName) throws IOException {
		try (InputStream is = new FileInputStream(fileName)) {
			CalcudokuJsonParser parser = new CalcudokuJsonParser(is);
			this.state = parser.parse();
			this.board = state.getBoard();
		}
	}

	@Test
	public void testA() {
		Group group1 = Group.of(PLUS, 3, position(0, 0), position(0, 1));
		Group group2 = Group.of(IDENTITY, 2, position(1, 0));
		Group group3 = Group.of(IDENTITY, 1, position(1, 1));

		board = new Board(2, asList(group1, group2, group3));
		board.put(position(0, 0), 2);
		board.put(position(0, 1), 1);

		board.put(position(1, 0), 1);
		board.put(position(1, 1), 2);

		state = new CalcudokuState(new Board(2, asList(group1, group2, group3)));

		assert board.invalidColumnsCount() == 0;
		assert board.invalidGroupsCount() == 2;
		assertEquals(1, h1.getValue(state));
		assertEquals(1, h2.getValue(state));
	}

	@Test
	public void testB() throws IOException {
		String fileName = "./src/test/resources/B.json";
		setUp(fileName);
		board.put(position(0, 0), 1);
		board.put(position(0, 1), 2);

		board.put(position(1, 0), 2);
		board.put(position(1, 1), 1);

		assert board.invalidColumnsCount() == 0;
		assert board.invalidGroupsCount() == 4;
		assertEquals(2, h1.getValue(state));
		assertEquals(1, h2.getValue(state));
	}

	@Test
	public void testC() throws IOException {
		String fileName = "./src/test/resources/C.json";
		setUp(fileName);
		board.put(position(0, 0), 1);
		board.put(position(0, 1), 2);

		board.put(position(1, 0), 1);
		board.put(position(1, 1), 2);

		assert board.invalidColumnsCount() == 2;
		assert board.invalidGroupsCount() == 2;
		assertEquals(1, h1.getValue(state));
		assertEquals(1, h2.getValue(state));
	}

	@Test
	public void testD() throws IOException {
		String fileName = "./src/test/resources/D.json";
		setUp(fileName);
		board.put(position(0, 0), 2);
		board.put(position(0, 1), 1);

		board.put(position(1, 0), 2);
		board.put(position(1, 1), 1);

		assert board.invalidColumnsCount() == 2;
		assert board.invalidGroupsCount() == 2;
		assertEquals(1, h1.getValue(state));
		assertEquals(1, h2.getValue(state));
	}

	@Test
	public void testE() throws IOException {
		String fileName = "./src/test/resources/3x3_easy1.json";
		setUp(fileName);
		board.put(position(0, 0), 3);
		board.put(position(0, 1), 2);
		board.put(position(0, 2), 1);

		board.put(position(1, 0), 2);
		board.put(position(1, 1), 1);
		board.put(position(1, 2), 3);

		board.put(position(2, 0), 1);
		board.put(position(2, 1), 2);
		board.put(position(2, 2), 3);

		assert board.invalidColumnsCount() == 2;
		assert board.invalidGroupsCount() == 2;
		assertEquals(1, h1.getValue(state));
		assertEquals(1, h2.getValue(state));
	}

	@Test
	public void testF() throws IOException {
		String fileName = "./src/test/resources/3x3_easy1.json";
		setUp(fileName);
		board.put(position(0, 0), 3);
		board.put(position(0, 1), 1);
		board.put(position(0, 2), 2);

		board.put(position(1, 0), 3);
		board.put(position(1, 1), 2);
		board.put(position(1, 2), 1);

		board.put(position(2, 0), 1);
		board.put(position(2, 1), 2);
		board.put(position(2, 2), 3);

		assert board.invalidColumnsCount() == 2;
		assert board.invalidGroupsCount() == 0;
		assertEquals(1, h1.getValue(state));
		assertEquals(1, h2.getValue(state));
	}

	@Test
	public void testG() throws IOException {
		String fileName = "./src/test/resources/3x3_easy1.json";
		setUp(fileName);
		board.put(position(0, 0), 2);
		board.put(position(0, 1), 1);
		board.put(position(0, 2), 3);

		board.put(position(1, 0), 3);
		board.put(position(1, 1), 1);
		board.put(position(1, 2), 2);

		board.put(position(2, 0), 1);
		board.put(position(2, 1), 2);
		board.put(position(2, 2), 3);

		assert board.invalidColumnsCount() == 2;
		assert board.invalidGroupsCount() == 4;
		assertEquals(2, h1.getValue(state));
		assertEquals(2, h2.getValue(state));
	}

	@Test
	public void testH() throws IOException {
		String fileName = "./src/test/resources/h.json";
		setUp(fileName);
		board.put(position(0, 0), 2);
		board.put(position(0, 1), 3);
		board.put(position(0, 2), 4);
		board.put(position(0, 3), 1);

		board.put(position(1, 0), 3);
		board.put(position(1, 1), 4);
		board.put(position(1, 2), 1);
		board.put(position(1, 3), 2);

		board.put(position(2, 0), 3);
		board.put(position(2, 1), 1);
		board.put(position(2, 2), 2);
		board.put(position(2, 3), 4);

		board.put(position(3, 0), 1);
		board.put(position(3, 1), 2);
		board.put(position(3, 2), 3);
		board.put(position(3, 3), 4);

		assert board.invalidColumnsCount() == 2;
		assert board.invalidGroupsCount() == 15;
		assertEquals(8, h1.getValue(state));
		assertEquals(5, h2.getValue(state));
	}

	@Test
	public void testI() throws IOException {
		String fileName = "./src/test/resources/I.json";
		setUp(fileName);
		board.put(position(0, 0), 1);
		board.put(position(0, 1), 3);
		board.put(position(0, 2), 2);

		board.put(position(1, 0), 3);
		board.put(position(1, 1), 1);
		board.put(position(1, 2), 2);

		board.put(position(2, 0), 1);
		board.put(position(2, 1), 2);
		board.put(position(2, 2), 3);

		assert board.invalidColumnsCount() == 2;
		assert board.invalidGroupsCount() == 8;
		assertEquals(4, h1.getValue(state));
		assertEquals(3, h2.getValue(state));
	}
}
