package ar.itba.edu.sia.tp1.calcudoku.heuristics;

import static java.util.Arrays.asList;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.List;

import ar.itba.edu.sia.tp1.calcudoku.CalcudokuState;
import ar.itba.edu.sia.tp1.calcudoku.domain.Group;
import ar.itba.edu.sia.tp1.calcudoku.domain.Operator;
import ar.itba.edu.sia.tp1.calcudoku.domain.Position;
import ar.itba.edu.sia.tp1.calcudoku.marshall.CalcudokuJsonParser;
import ar.itba.edu.sia.tp1.calcudoku.marshall.CalcudokuJsonSerializer;

public class Borrar {

	public static void main(String[] args) {
		String fileName="./src/test/resources/serialized";
		CalcudokuJsonSerializer serializer;

		OutputStream os=null;
		try {
			os = new FileOutputStream(fileName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		serializer = new CalcudokuJsonSerializer(os);

		List<Position> positions = asList(position(0, 0), position(0, 1),
				position(0, 2));
		List<Group> groups = asList(new Group(positions, Operator.PLUS, 5));
		CalcudokuState stateWritten = new CalcudokuState(3, groups);

		serializer.serialize(stateWritten);

	}
	
	private static Position position(int row, int col) {
		return new Position(row, col);
	}
}
