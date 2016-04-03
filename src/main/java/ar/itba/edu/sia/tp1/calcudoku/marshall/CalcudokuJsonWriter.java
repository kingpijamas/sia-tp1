package ar.itba.edu.sia.tp1.calcudoku.marshall;

import java.io.OutputStream;

import org.boon.json.ObjectMapper;
import org.boon.json.implementation.ObjectMapperImpl;

import ar.itba.edu.sia.tp1.calcudoku.Calcudoku;
import ar.itba.edu.sia.tp1.calcudoku.CalcudokuState;
import ar.itba.edu.sia.tp1.gps.ProblemWriter;

public class CalcudokuJsonWriter implements ProblemWriter<Calcudoku> {
	private static final ObjectMapper mapper = new ObjectMapperImpl();

	private final OutputStream os;

	public CalcudokuJsonWriter(OutputStream os) {
		this.os = os;
	}

	@Override
	public void writeInitialState(Calcudoku calcudoku) {
		CalcudokuState initialState = calcudoku.getInitialState();
		mapper.writeValue(os, new CalcudokuStateMapping(initialState));
	}
}
