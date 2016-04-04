package ar.itba.edu.sia.tp1.calcudoku.marshall;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ar.itba.edu.sia.tp1.calcudoku.CalcudokuState;
import ar.itba.edu.sia.tp1.calcudoku.domain.Group;
import ar.itba.edu.sia.tp1.calcudoku.domain.Operator;
import ar.itba.edu.sia.tp1.calcudoku.domain.Position;

public class CalcudokuJsonSerializingParsingTest {
	private static final int N = 3;

	private CalcudokuJsonParser parser;
	private CalcudokuJsonSerializer serializer;

	private OutputStream os;
	private InputStream is;

	@Before
	public void setUp() throws IOException {
		os = new PipedOutputStream();
		is = new PipedInputStream((PipedOutputStream) os);

		serializer = new CalcudokuJsonSerializer(os);
		parser = new CalcudokuJsonParser(is);
	}

	@After
	public void cleanUp() throws IOException {
		IOUtils.closeQuietly(is);
		IOUtils.closeQuietly(os);
	}

	@Test
	public void testSerializingParsingForEmptyGroups() throws IOException {
		CalcudokuState stateWritten = new CalcudokuState(N, emptyList());

		serializer.serialize(stateWritten);
		CalcudokuState stateRead = parser.parse();

		assertStatesAreEqual(stateWritten, stateRead);
	}

	@Test
	public void testSerializingParsingForNonEmptyGroups() throws IOException {
		List<Position> positions = asList(position(0, 0), position(0, 1),
				position(0, 2));
		List<Group> groups = asList(new Group(positions, Operator.PLUS, 5));
		CalcudokuState stateWritten = new CalcudokuState(N, groups);

		serializer.serialize(stateWritten);
		CalcudokuState stateRead = parser.parse();

		assertStatesAreEqual(stateWritten, stateRead);
	}

	private void assertStatesAreEqual(CalcudokuState stateWritten,
			CalcudokuState stateRead) {
		assertThat(stateRead.getN(), is(stateWritten.getN()));
		assertThat(stateRead.getGroups(), is(stateWritten.getGroups()));
		assertThat(stateRead, is(stateWritten));
	}

	private Position position(int row, int col) {
		return new Position(row, col);
	}
}
