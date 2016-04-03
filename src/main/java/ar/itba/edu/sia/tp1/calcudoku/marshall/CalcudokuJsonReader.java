package ar.itba.edu.sia.tp1.calcudoku.marshall;

import java.io.InputStream;

import org.boon.json.ObjectMapper;
import org.boon.json.implementation.ObjectMapperImpl;

import ar.itba.edu.sia.tp1.calcudoku.CalcudokuState;
import ar.itba.edu.sia.tp1.gps.ProblemReader;

public class CalcudokuJsonReader implements ProblemReader<CalcudokuState> {
	private static final ObjectMapper mapper = new ObjectMapperImpl();

	private final InputStream is;

	public CalcudokuJsonReader(InputStream is) {
		this.is = is;
	}

	@Override
	public CalcudokuState readInitialState() {
		CalcudokuStateMapping stateMapping = mapper.readValue(is,
				CalcudokuStateMapping.class);
		return stateMapping.unmarshall();
	}

}
