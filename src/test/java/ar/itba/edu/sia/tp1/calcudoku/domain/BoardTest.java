package ar.itba.edu.sia.tp1.calcudoku.domain;

import static ar.itba.edu.sia.tp1.calcudoku.domain.Operator.DIVIDE;
import static ar.itba.edu.sia.tp1.calcudoku.domain.Operator.MINUS;
import static ar.itba.edu.sia.tp1.calcudoku.domain.Position.position;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class BoardTest {
	private static final int N = 3;

	@Test
	public void testIsValid() {
		// Group gSum = new Group(
		// Arrays.asList(new Position(0, 0), new Position(0, 1),
		// new Position(1, 0), new Position(2, 0)),
		// Operator.PLUS, 7);
		// groups.add(gSum);

		Group gdiv = Group.of(DIVIDE, 2, position(2, 1), position(2, 2));
		Group gsub = Group.of(MINUS, 1, position(1, 1), position(1, 2));
		List<Group> groups = Arrays.asList(gdiv, gsub);

		Board board = new Board(N, groups);

		board.put(position(0, 0), 2);
		board.put(position(0, 1), 1);
		board.put(position(0, 2), 3);

		board.put(position(1, 0), 1);
		board.put(position(1, 1), 3);
		board.put(position(1, 2), 2);

		board.put(position(2, 0), 3);
		board.put(position(2, 1), 2);
		board.put(position(2, 2), 1);

		assertTrue(board.isValid());
	}
}
